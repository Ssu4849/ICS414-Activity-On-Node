package edu.mango.activityonnode;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Project is a representation of all activities that make up an activity-on-node-network.
 * @author Team Mango
 */
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final AtomicInteger counter = new AtomicInteger();

	/** Number that uniquely identifies the project. */
	private int id;

	/** The name of the project. */
	private String name;

	/** The set of activities that make up the project. */
	private Set<ActivityNode> activities;

	/**
	 * Creates a new project.
	 * @param name - the name of the project
	 */
	public Project(String name) {
		this.id = counter.incrementAndGet();
		this.name = name;
		this.activities = new HashSet<>();
	}

	/**
	 * Creates a new project.
	 * @param name - the name of the project
	 * @param activities - the activities in the project
	 */
	public Project(String name, Set<ActivityNode> activities) {
		this.id = counter.incrementAndGet();
		this.name = name;
		this.activities = activities;
	}

	/**
	 * Gets the name of the project.
	 * @return the name of the project
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the project.
	 * @param name - the new name of the project.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets all the activities in the project.
	 * @return the activities in the project
	 */
	public Set<ActivityNode> getActivities() {
		return activities;
	}

	/**
	 * Sets the activities in the project.
	 * @param activities - the new set of activities
	 */
	public void setActivities(Set<ActivityNode> activities) {
		this.activities = activities;
	}

	/**
	 * Adds an activity to the Project.
	 * @param activity - the activity to add
	 */
	public void addActivity(ActivityNode activity) {
		activities.add(activity);
	}

	/**
	 * Gets the ID of the project.
	 * @return the ID of the project
	 */
	public int getId() {
		return id;
	}

	/**
	 * Deletes an activity from the Project.
	 * @param activity - the activity to delete
	 */
	public void deleteActivity(ActivityNode activity) {
		// Go through the children of every node in the project and remove the activity to delete, if it exists
		for (ActivityNode act : activities) {
			act.deleteChild(activity);
		}
		activities.remove(activity);
	}

	/**
	 * Marks every activity in the Project as unvisited.
	 */
	private void markActivitiesAsUnvisited() {
		for (ActivityNode act : activities) {
			act.setVisited(false);
		}
	}

	/**
	 * Gets a topological sorting of the activity-on-node network.
	 * @return a list containing the activity-on-node, topologically sorted.
	 */
	public List<ActivityNode> getTopologicalSort() {
		List<ActivityNode> sortedNetwork = new ArrayList<>();
		markActivitiesAsUnvisited();
		for (ActivityNode activity : activities) {
			if (!activity.isVisited()) {
				visitActivityNode(activity, sortedNetwork);
			}
		}
		return sortedNetwork;
	}

	/**
	 * Visits an activity node, and then recursively visits its child nodes if it is undiscovered. After its child
	 * nodes are discovered, then the activity is added to the front of a list.
	 * @param activity - the activity to visit
	 * @param list - the sorted activity-on-node network
	 */
	private void visitActivityNode(ActivityNode activity, List<ActivityNode> list) {
		activity.setVisited(true);
		Set<ActivityNode> childNodes = activity.getChildren();
		for (ActivityNode child : childNodes) {
			if (!child.isVisited()) {
				visitActivityNode(child, list);
			}
		}
		list.add(0, activity);
	}

	/**
	 * Gets the critical path of the activity-on-node network. It requires three things: the earliest start times,
	 * latest start times, and the slack time of every activity.
	 * @return a list containing the critical path of the activity-on-node network
	 */
	public List<ActivityNode> getCriticalPath() {
		resetTimes();

		// Temporary nodes which serve as the single source and sink nodes for the entire network during traversal
		ActivityNode start = new ActivityNode("Start", 0);
		ActivityNode end = new ActivityNode("End", 0);
		addStartActivity(start);
		addEndActivity(end);

		calculateEarliestStartTimes(start, end);
		calculateLatestStartTimes(start, end);
		// Recalculates the actual earliest/latest start and finish times and slack times
		finalizeAllTimes();

		List<ActivityNode> criticalPath = findCriticalPath();
		removeEndActivity(end);
		return criticalPath;
	}

	/**
	 * Calculates the earliest start times of the activities in the network. The times calculated for each activity is
	 * one less than their actual.
	 * @param start - the start activity
	 * @param end - the end activity
	 */
	private void calculateEarliestStartTimes(ActivityNode start, ActivityNode end) {
		ArrayDeque<ActivityNode> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			ActivityNode current = queue.poll();
			List<ActivityNode> parents = getParentActivities(current);
			int est = calculateEarliestStartTime(parents);
			current.setEarliestStartTime(est);
			Set<ActivityNode> children = current.getChildren();
			queue.addAll(children);
		}
	}

	/**
	 * Calculates the latest start times of the activities in the network. The times calculated for each activity is
	 * one less than their actual.
	 * @param start - the start activity
	 * @param end - the end activity
	 */
	private void calculateLatestStartTimes(ActivityNode start, ActivityNode end) {
		ArrayDeque<ActivityNode> queue = new ArrayDeque<>();
		end.setLatestStartTime(end.getEarliestStartTime());
		queue.offer(end);
		while (!queue.isEmpty()) {
			ActivityNode current = queue.poll();
			Set<ActivityNode> children = current.getChildren();
			if (current != end) {
				int lst = calculateLatestStartTime(current, children);
				current.setLatestStartTime(lst);	
			}
			List<ActivityNode> parents = getParentActivities(current);
			queue.addAll(parents);
		}
	}

	/**
	 * Finalizes the start, end, and slack times for the activities.
	 */
	private void finalizeAllTimes() {
		for (ActivityNode activity : activities) {
			int calculatedEst = activity.getEarliestStartTime();
			int calculatedLst = activity.getLatestStartTime();
			int duration = activity.getDuration();
			activity.setEarliestStartTime(calculatedEst + 1);
			activity.setEarliestFinishTime(calculatedEst + duration);
			activity.setLatestStartTime(calculatedLst + 1);
			activity.setLatestFinishTime(calculatedLst + duration);
			activity.setSlackTime(calculatedLst - calculatedEst);
		}
	}

	/**
	 * Resets the earliest/latest start, finish, and slack times of every activity.
	 */
	private void resetTimes() {
		for (ActivityNode activity : activities) {
			activity.setEarliestStartTime(0);
			activity.setEarliestFinishTime(0);
			activity.setLatestStartTime(0);
			activity.setLatestFinishTime(0);
			activity.setSlackTime(0);
		}
	}

	/**
	 * Adds a start activity, whose purpose is to act as the temporary, single source node for the entire network.
	 * @param start - the start activity
	 */
	private void addStartActivity(ActivityNode start) {
		List<ActivityNode> startingActivities = getStartingActivities();
		for (ActivityNode activity : startingActivities) {
			start.addChild(activity);
		}
	}

	/**
	 * Adds an end activity, whose purpose is to act as the temporary, single sink node for the entire network.
	 * @param end - the end activity
	 */
	private void addEndActivity(ActivityNode end) {
		for (ActivityNode activity : activities) {
			if (activity.getChildren().size() == 0) {
				activity.getChildren().add(end);
			}
		}
	}

	/**
	 * Gets the set of the starting activities.
	 * @return the set of starting activities
	 */
	private List<ActivityNode> getStartingActivities() {
		List<ActivityNode> startingActivities = new ArrayList<>();
		for (ActivityNode activity : activities) {
			List<ActivityNode> parents = getParentActivities(activity);
			if (parents.isEmpty()) {
				startingActivities.add(activity);
			}
		}
		return startingActivities;
	}

	/**
	 * Gets the parents of an activity.
	 * @param activity - the activity to check
	 * @return a list containing the parents of an activity
	 */
	private List<ActivityNode> getParentActivities(ActivityNode activity) {
		List<ActivityNode> predecessors = new ArrayList<>();
		for (ActivityNode a : activities) {
			if (a.getChildren().contains(activity)) {
				predecessors.add(a);
			}
		}
		return predecessors;
	}

	/**
	 * Calculates the earliest start time for an activity.
	 * @param parents - the parents of an activity
	 * @return the earliest start time of an activity
	 */
	private int calculateEarliestStartTime(List<ActivityNode> parents) {
		int time = Integer.MIN_VALUE;
		// Special case for the start node since it has no parent activities
		if (parents.isEmpty()) {
			return 0;
		}
		for (ActivityNode parent : parents) {
			int possibleTime = parent.getEarliestStartTime() + parent.getDuration();
			if (possibleTime > time) {
				time = possibleTime;
			}
		}
		return time;
	}

	/**
	 * Calculates the latest start time for an activity.
	 * @param current - the activity that is being looked at
	 * @param children - the child activities of the current node
	 * @return the latest start time of the current activity
	 */
	private int calculateLatestStartTime(ActivityNode current, Set<ActivityNode> children) {
		int time = Integer.MAX_VALUE;
		for (ActivityNode child : children) {
			int possibleTime = child.getLatestStartTime() - current.getDuration();
			if (possibleTime < time) {
				time = possibleTime;
			}
		}
		return time;
	}

	/**
	 * Removes the end activity.
	 * @param end - the end activity
	 */
	private void removeEndActivity(ActivityNode end) {
		for (ActivityNode activity : activities) {
			activity.deleteChild(end);
		}
	}

	/**
	 * Finds the activities on the critical path.
	 * @return a list containing the activities on the critical path
	 */
	private List<ActivityNode> findCriticalPath() {
		List<ActivityNode> path = new ArrayList<>();
		for (ActivityNode activity : activities) {
			if (activity.getSlackTime() == 0) {
				path.add(activity);
			}
		}
		path.sort(new Comparator<ActivityNode>() {
			/**
			 * Compares the earliest start time of two activities.
			 * @param first - the first node to compare
			 * @param second - the second node to compare
			 * @return 1 if the first node's start time is greater, -1 if the second node's start time is greater, or 0
			 * if both start times are equal.
			 */
			@Override
			public int compare(ActivityNode first, ActivityNode second) {
				if (first.getEarliestStartTime() > second.getEarliestStartTime()) return 1;
				if (first.getEarliestStartTime() < second.getEarliestStartTime()) return -1;
				return 0;
			}
		});
		return path;
	}
}
