package edu.mango.activityonnode;

import java.util.Set;
import java.util.HashSet;

/**
 * The Project is a representation of all activities that make up an
 * activity-on-node-network.
 * @author Team Mango
 */
public class Project {

	/** The name of the project. */
	private String name;

//	private ActivityTable activityTable;

	/** The set of activities that make up the project. */
	private Set<ActivityNode> activities;

	/**
	 * Creates a new project.
	 * @param name - the name of the project
	 */
	public Project(String name) {
		this.name = name;
//		this.activityTable = new ActivityTable();
		this.activities = new HashSet<>();
	}

	public Project(String name, Set<ActivityNode> activities) {
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
	 * Deletes an activity from the Project.
	 * @param activity - the activity to delete
	 */
	public void deleteActivity(ActivityNode activity) {
		// Go through the children of every node in the project and remove
		// the activity to delete, if it exists
		for (ActivityNode act : activities) {
			act.deleteChild(activity);
		}
		activities.remove(activity);
	}
	
	/**
	 * Marks every activity in the Project as unvisited.
	 */
	private void markAllAsUnvisited() {
		for (ActivityNode act : activities) {
			act.setVisited(false);
		}
	}

//	/**
//	 * Adds an activity to the activity table and updates the AON network
//	 * @param activityName of this activity
//	 * @param duration of this activity
//	 * @param predecessors activities required to complete before starting this activity
//	 */
//	public void addActivity(String activityName, int duration, List<Integer> predecessors) {
//		activityTable.addActivity(activityName, predecessors, duration);
//		start = activityTable.convertTableToAON();
//	}
//	
//	/**
//	 * Prints a graphical representation of this project in plain-text
//	 */
//	public void printProject() {
//		//todo
//	}
//	
//	/**
//	 * Prints the activity table of this project in plain-text
//	 */
//	public void printTable() {
//		activityTable.printTable();
//	}

}
