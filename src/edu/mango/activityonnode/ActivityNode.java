package edu.mango.activityonnode;

import java.util.Set;
import java.util.HashSet;

/**
 * The ActivityNode is a representation of one activity/task in an
 * activity-on-node network.
 * @author Team Mango
 */
public class ActivityNode {

	/** The name of the activity. */
	private String name;

	/** The duration of the activity. */
	private int duration;

	/** Whether the activity has been visited or not. */
	private boolean visited;

	/** 
	 * The set of activities that depend on this activity. In other words, the
	 * children cannot be completed unless this current activity is finished. 
	 */
	Set<ActivityNode> children;

	/**
	 * Creates a new activity.
	 * @param name - the name of the activity
	 * @param duration - the duration of the activity
	 */
	public ActivityNode(String name, int duration) {
		this.name = name;
		this.duration = duration;
		this.visited = false;
		this.children = new HashSet<>();
	}

	/**
	 * Gets the duration of the activity.
	 * @return the duration of the activity
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * Sets the duration of the activity
	 * @param duration - the new duration of the activity
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the name of the activity.
	 * @return the name of the activity
	 */
	public String getActivityName() {
		return this.name;
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
	 * Sets the children of the activity
	 * @param children - the new set of children for the activity
	 */
	public void setChildren(Set<ActivityNode> children) {
		this.children = children;
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

}
