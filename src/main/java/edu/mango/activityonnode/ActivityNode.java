package edu.mango.activityonnode;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The ActivityNode is a representation of one activity/task in an activity-on-node network.
 * @author Team Mango
 */
public class ActivityNode implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final AtomicInteger counter = new AtomicInteger();

	/** Number that uniquely identifies the activity. */
	private int id;

	/** The name of the activity. */
	private String name;

	/** The duration of the activity. */
	private int duration;

	/** Whether the activity has been visited or not. */
	private boolean visited;

	/** The earliest time an activity can be started. */
	private int earliestStartTime;

	/** The latest time an activity can be started. */
	private int latestStartTime;

	/** The earliest time an activity can be finished. */
	private int earliestFinishTime;

	/** The latest time an activity can be finished. */
	private int latestFinishTime;

	/** The amount of time an activity can be delayed without delaying the whole project. */
	private int slackTime;

	/** 
	 * The set of activities that depend on this activity. In other words, the children cannot be completed unless this
	 * current activity is finished.
	 */
	Set<ActivityNode> children;

	/**
	 * Creates a new activity.
	 * @param name - the name of the activity
	 * @param duration - the duration of the activity
	 */
	public ActivityNode(String name, int duration) {
		this.id = counter.incrementAndGet();
		this.name = name;
		this.duration = duration;
		this.visited = false;
		this.earliestStartTime = 0;
		this.latestStartTime = 0;
		this.earliestFinishTime = 0;
		this.latestFinishTime = 0;
		this.children = new HashSet<>();
	}

	/**
	 * Gets the duration of the activity.
	 * @return the duration of the activity
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the activity.
	 * @param duration - the new duration of the activity
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the name of the activity.
	 * @return the name of the activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the activity.
	 * @param name - the new name of the activity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the children of the activity.
	 * @return the children of the activity
	 */
	public Set<ActivityNode> getChildren() {
		return children;
	}

	/**
	 * Sets the children of the activity.
	 * @param children - the new set of children for the activity
	 */
	public void setChildren(Set<ActivityNode> children) {
		this.children = children;
	}

	/**
	 * Adds a activity to the list of children.
	 * @param child - the activity to add to the list of children
	 */
	public void addChild(ActivityNode child) {
		if (this != child) {
			children.add(child);
		}
	}

	/**
	 * Removes an activity from the list of children.
	 * @param child - the activity to remove from the list of children
	 */
	public void deleteChild(ActivityNode child) {
		children.remove(child);
	}

	/**
	 * Checks whether the activity has been visited.
	 * @return true if the activity has been visited, otherwise returns false
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets the visited status on the activity.
	 * @param visited - the new visit status of the activity
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Gets the earliest start time of the activity.
	 * @return the earliest start time of the activity
	 */
	public int getEarliestStartTime() {
		return earliestStartTime;
	}

	/**
	 * Sets the earliest start time of the activity.
	 * @param duration - the new earliest start time of the activity
	 */
	public void setEarliestStartTime(int earliestStartTime) {
		this.earliestStartTime = earliestStartTime;
	}

	/**
	 * Gets the latest start time of the activity.
	 * @return the latest start time of the activity
	 */
	public int getLatestStartTime() {
		return latestStartTime;
	}

	/**
	 * Sets the latest start time of the activity.
	 * @param duration - the new latest start time of the activity
	 */
	public void setLatestStartTime(int latestStartTime) {
		this.latestStartTime = latestStartTime;
	}

	/**
	 * Gets the earliest finish time of the activity.
	 * @return the earliest finish time of the activity
	 */
	public int getEarliestFinishTime() {
		return earliestFinishTime;
	}

	/**
	 * Sets the earliest finish time of the activity.
	 * @param earliestFinishTime - the new earliest finish time of the activity
	 */
	public void setEarliestFinishTime(int earliestFinishTime) {
		this.earliestFinishTime = earliestFinishTime;
	}

	/**
	 * Gets the latest finish time of the activity.
	 * @return the latest finish time of the activity
	 */
	public int getLatestFinishTime() {
		return latestFinishTime;
	}

	/**
	 * Sets the latest finish time of the activity.
	 * @param duration - the new latest finish time of the activity
	 */
	public void setLatestFinishTime(int latestFinishTime) {
		this.latestFinishTime = latestFinishTime;
	}

	/**
	 * Gets the slack time of the activity.
	 * @return the slack time of the activity
	 */
	public int getSlackTime() {
		return slackTime;
	}

	/**
	 * Sets the slack time of the activity.
	 * @param slackTime - the new slack time of the activity
	 */
	public void setSlackTime(int slackTime) {
		this.slackTime = slackTime;
	}

	/**
	 * Gets the ID of the activity.
	 * @return the ID of the activity
	 */
	public int getId() {
		return id;
	}
}
