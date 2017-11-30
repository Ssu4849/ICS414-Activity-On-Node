package edu.mango.activityonnode;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TestAON {
	public static void main(String[] args) {
		Project project;
		int menuOption;
		String menu1 = "\nMain menu:\n1) Create new project\n2) Load example project\n3) Exit\n";
		String menu2 = "\nProject menu:\n1) Add activity\n2) Delete activity\n3) Add dependencies\n4) List all activities\n5) Print critical path\n6) Print topological sort\n7) Exit\n";
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println(menu1);
			menuOption = Integer.parseInt(sc.nextLine());
			if (menuOption == 1) {
				System.out.print("Enter a name for the project: ");
				String projectName = sc.nextLine();
				project = new Project(projectName);
			} else if (menuOption == 2) {
				project = loadExample();
			} else {
				break;
			}
			
			boolean exit = false;
			while (!exit) {
				System.out.println(menu2);
				try {
					menuOption = Integer.parseInt(sc.nextLine());

					switch (menuOption) {
					case 1:
						System.out.print("Name for this activity: ");
						String activityName = sc.nextLine();
						System.out.print("Duration of activity in number of time units: ");
						int duration = Integer.parseInt(sc.nextLine());
						ActivityNode an = new ActivityNode(activityName, duration);
						project.addActivity(an);
						break;
					case 2:
						System.out.print("Enter the name of the activity to be deleted: ");
						String activityToDelete = sc.nextLine();
						Set<ActivityNode> activities = project.getActivities();
						for (Iterator<ActivityNode> iterator = activities.iterator(); iterator.hasNext();) {
							ActivityNode activity = iterator.next();
							if (activity.getName().equalsIgnoreCase(activityToDelete)) {
								project.deleteActivity(activity);
								System.out.println("Deleted activity: " + activityToDelete);
							}
						}
						break;
					case 3:
						ActivityNode parentActivity = null, childActivity = null;
						System.out.print("Modify dependency for activity name: ");
						String childActivityName = sc.nextLine();
						for (ActivityNode activity : project.getActivities()) {
							if (activity.getName().equalsIgnoreCase(childActivityName)) {
								childActivity = activity;
							}
						}
						System.out.print("Enter a list of prerequisites for this activity, seperated with spaces: ");
						String prereqs[] = sc.nextLine().split(" ");
						for (String parentActivityName : prereqs) {
							for (ActivityNode activity : project.getActivities()) {
								if (activity.getName().equalsIgnoreCase(parentActivityName)) {
									parentActivity = activity;
								}
							}
							if (parentActivity != null && childActivity != null) {
								parentActivity.addChild(childActivity);
							}
						}
						break;
					case 4:
						project.getCriticalPath(); 
						for (ActivityNode node : project.getActivities()) {
							System.out.println(node.getName() + " EST:" + node.getEarliestStartTime() + " EFT:"
									+ node.getEarliestFinishTime());
							System.out.println(
									"  LST:" + node.getLatestStartTime() + " LFT:" + node.getLatestFinishTime());
							System.out.println("Slack: " + node.getSlackTime());
						}
						break;
					case 5:
						List<ActivityNode> critPath = project.getCriticalPath();
						System.out.println("Critical Path:");
						for (ActivityNode node : critPath) {
							System.out.println(node.getName() + " EST:" + node.getEarliestStartTime() + " EFT:"
									+ node.getEarliestFinishTime());
							System.out.println(
									"  LST:" + node.getLatestStartTime() + " LFT:" + node.getLatestFinishTime());
							System.out.println("Slack: " + node.getSlackTime());
						}
						System.out.println(
								"Critical path duration: " + critPath.get(critPath.size() - 1).getLatestFinishTime());
						break;
					case 6:
						project.getCriticalPath();
						List<ActivityNode> sort = project.getTopologicalSort();
						for (ActivityNode node : sort) {
							System.out.println(node.getName() + " EST:" + node.getEarliestStartTime() + " EFT:"
									+ node.getEarliestFinishTime());
							System.out.println(
									"  LST:" + node.getLatestStartTime() + " LFT:" + node.getLatestFinishTime());
							System.out.println("Slack: " + node.getSlackTime());
						}
						break;
					case 7:
						exit = true;
						break;
					default:
						System.out.println(menuOption + " is not a valid option");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter a number");
				}
			}
		}
		sc.close();
	}

	public static Project loadExample() {
		Project project = new Project("Example Project");

		// This is based on the activity-on-node network on
		//
		//
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

		return project;
	}
}
