package edu.mango.activityonnode;

import java.util.List;

public class TestAON {
	public static void main(String[] args) {
		Project project = new Project("Project");

		// This is based on the activity-on-node network on
		// https://en.wikipedia.org/wiki/Critical_path_method#Visualizing_critical_path_schedule
		ActivityNode a = new ActivityNode("A", 10);
		ActivityNode b = new ActivityNode("B", 20);
		ActivityNode c = new ActivityNode("C", 5);
		ActivityNode d = new ActivityNode("D", 10);
		ActivityNode e = new ActivityNode("E", 20);
		ActivityNode f = new ActivityNode("F", 15);
		ActivityNode g = new ActivityNode("G", 5);
		ActivityNode h = new ActivityNode("H", 15);
		
		a.addChild(f);
		a.addChild(b);
		a.addChild(h);
		
		b.addChild(c);
		c.addChild(g);
		c.addChild(d);
		d.addChild(e);
		f.addChild(g);
		g.addChild(e);
		h.addChild(e);

		project.addActivity(a);
		project.addActivity(c);
		project.addActivity(b);
		project.addActivity(d);
		project.addActivity(e);
		project.addActivity(f);
		project.addActivity(g);
		project.addActivity(h);
		
		List<ActivityNode> critPath = project.getCriticalPath();
		for (ActivityNode node : project.getActivities()) {
			System.out.println(node.getName() + " " + node.getEarliestStartTime() + " " + node.getEarliestFinishTime());
			System.out.println("  " + node.getLatestStartTime() + " " + node.getLatestFinishTime());
			System.out.println(node.getSlackTime());
		}

		System.out.println("Critical Path");
		for (ActivityNode node : critPath) {
			System.out.println(node.getName() + " " + node.getEarliestStartTime() + " " + node.getEarliestFinishTime());
			System.out.println("  " + node.getLatestStartTime() + " " + node.getLatestFinishTime());
			System.out.println(node.getSlackTime());
		}
	}
}
