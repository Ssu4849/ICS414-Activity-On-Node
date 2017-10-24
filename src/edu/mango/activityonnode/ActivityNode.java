package edu.mango.activityonnode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Team Mango
 */

public class ActivityNode {
	
	private int duration;
	
	private String activityName;
	
	List<ActivityNode> children = new ArrayList<ActivityNode>();
	
	public ActivityNode(String activityName, int duration) {
		this.activityName = activityName;
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setTitle(String title) {
		this.activityName = title;
	}

	public List<ActivityNode> getChildren() {
		return children;
	}

	public void setChildren(List<ActivityNode> children) {
		this.children = children;
	}
	
}
