package edu.mango.activityonnode;

import java.util.List;

/**
 * @Team Mango
 */

public class Project {
	private ActivityNode start;
	
	private ActivityTable activityTable;
	
	/**
	 * Initializes a project plan
	 */
	public Project() {
		 this.activityTable = new ActivityTable();
	}
	
	/**
	 * Adds an activity to the activity table and updates the AON network
	 * @param activityName of this activity
	 * @param duration of this activity
	 * @param predecessors activities required to complete before starting this activity
	 */
	public void addActivity(String activityName, int duration, List<Integer> predecessors) {
		activityTable.addActivity(activityName, predecessors, duration);
		start = activityTable.convertTableToAON();
	}
	
	/**
	 * Prints a graphical representation of this project in plain-text
	 */
	public void printProject() {
		//todo
	}
	
	/**
	 * Prints the activity table of this project in plain-text
	 */
	public void printTable() {
		activityTable.printTable();
	}
	
}
