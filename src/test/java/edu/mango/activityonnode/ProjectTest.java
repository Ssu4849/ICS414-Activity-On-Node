package edu.mango.activityonnode;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

public class ProjectTest {

	@Test
	public void testConstructor() {
		Project project = new Project("A");
		assertEquals(project.getName(), "A");
		assertEquals(project.getActivities().size(), 0);
		assertNotNull(project.getId());
	}

	@Test
	public void testCriticalPath() {
		Project project = new Project("Example Project");
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

		List<ActivityNode> criticalPath = project.getCriticalPath();
		assertEquals(criticalPath.size(), 5);

		assertEquals(criticalPath.get(0), a);
		assertEquals(criticalPath.get(1), b);
		assertEquals(criticalPath.get(2), c);
		assertEquals(criticalPath.get(3), d);
		assertEquals(criticalPath.get(4), e);
	}

}
