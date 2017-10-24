package edu.mango.activityonnode;

import java.util.ArrayList;
import java.util.List;

public class TestAON {
	public static void main(String args[]){
		Project project1 = new Project();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(0);
		
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(0);
		list3.add(1);
		
		project1.addActivity("TEST1", 10, null);
		project1.addActivity("TEST2", 10, list);
		project1.addActivity("TEST3", 10, list2);
		project1.addActivity("TEST4", 10, list3);
		
		project1.printTable();
		
		project1.printProject();
	}
}
