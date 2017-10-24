package edu.mango.activityonnode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Team Mango
 */

public class ActivityTable {
	private List<Row> table = new ArrayList<Row>();
	
	/**
	 * Keeps track of the total count of activities added to the table
	 */
	private int activityCount = 0;

	public int getActivityCount() {
		return activityCount;
	}

	/**
	 * Represents a row in the activity table. Each row identifies a task with a
	 * specific duration, unique activity number, activity name, and a list of immediate
	 * predecessors
	 */
	public class Row {
		int activityNum, duration;
		String activityName;
		List<Integer> predecessors = new ArrayList<Integer>();

		Row(String activityName, List<Integer> predecessors, int activityNo, int duration) {
			this.activityNum = activityNo;
			this.predecessors = predecessors;
			this.activityName = activityName;
			this.duration = duration;
		}
	}

	/**
	 * Adds an activity to the activity table and increments total activity count.
	 * @param activityName
	 * @param predecessors
	 * @param duration
	 */
	public void addActivity(String activityName, List<Integer> predecessors, int duration) {
		table.add(new Row(activityName, predecessors, activityCount++, duration));
	}

	/**
	 * Todo
	 */
	public void deleteActivity(int activityNo) {
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).activityNum == activityNo) {
				table.remove(i);
				activityCount--;
			}
		}
	}

	/**
	 * Prints this activity table in plain-text
	 */
	public void printTable() {
		System.out.format("||%-3s||%-10s||%-10s||%-30s||\n", "No.", "Name", "Duration", "Predecessors");
		for (Row row : table) {
			System.out.println("---------------------------------------------------------------");
			System.out.println("||" + String.format("%03d", row.activityNum) + "||"
					+ String.format("%-10s", row.activityName) + "||" + String.format("%-10s", row.duration) + "||"
					+ String.format("%-30s", (row.predecessors != null ? row.predecessors.toString() : "")) + "||");
		}
	}

	/**
	 * Converts the activity table to an Activity-on-node network
	 * 
	 * @return an ActivityNode object of the first activity
	 */
	public ActivityNode convertTableToAON() {
		HashMap<Integer, ActivityNode> hm = new HashMap<Integer, ActivityNode>();
		if (activityCount == 0) {
			return null;
		}
		ActivityNode start = null;
		for (int i = 0; i < table.size(); i++) {
			Row task = table.get(i);
			if (task.activityNum == 0) {
				start = new ActivityNode(task.activityName, task.duration);
				hm.put(0, start);
			} else {
				for (Integer activityNum : task.predecessors) {
					ActivityNode an = hm.get(activityNum);
					ActivityNode node = new ActivityNode(task.activityName, task.duration);
					an.children.add(node);
					hm.put(task.activityNum, node);
				}
			}
		}
		return start;
	}
}
