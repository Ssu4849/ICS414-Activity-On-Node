package edu.mango.activityonnode;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivityNodeTest {

    @Test
    public void testConstructor() {
        ActivityNode node = new ActivityNode("Node 1", 1);
        assertNotNull(node);

        assertEquals(node.getName(), "Node 1");
        assertEquals(node.getDuration(), 1);
        assertFalse(node.isVisited());

        assertNotNull(node.getChildren());
        assertEquals(node.getChildren().size(), 0);
    }

    @Test
    public void addChild() {
        ActivityNode node = new ActivityNode("Node 1", 1);
        ActivityNode child = new ActivityNode("Node 2", 2);

        node.addChild(child);
        assertEquals(node.getChildren().size(), 1);
        assertTrue(node.getChildren().contains(child));
    }
    
    @Test
    public void deleteChild() {
        ActivityNode node = new ActivityNode("Node 1", 1);
        ActivityNode child = new ActivityNode("Node 2", 2);

        node.addChild(child);
        node.deleteChild(child);
        assertEquals(node.getChildren().size(), 0);
        assertFalse(node.getChildren().contains(child));
    }
    
    @Test
    public void testGetDuration() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	assertEquals(node.getDuration(), 1);
    }
    
    @Test
    public void testSetDuration() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setDuration(2);
    	assertEquals(node.getDuration(), 2);
    }
    
    @Test
    public void testGetName() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	assertEquals(node.getName(), "Node 1");
    }
    
    @Test
    public void testSetName() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setName("Node 2");
    	assertEquals(node.getName(), "Node 2");
    }
    
    @Test
    public void testGetChildren() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setChildren(node.children);
    	assertEquals(node.getChildren(), node.children);
    }
    
    @Test
    public void testGetEarliestStartTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setEarliestStartTime(0);
    	assertEquals(node.getEarliestStartTime(), 0);
    }
   
    @Test
    public void testSetEarliestStartTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setEarliestStartTime(1);
    	assertEquals(node.getEarliestStartTime(), 1);
    }
    
    @Test
    public void testGetEarliestFinishTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setEarliestFinishTime(0);
    	assertEquals(node.getEarliestFinishTime(), 0);
    }
    
    @Test
    public void testSetEarliestFinishTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setEarliestFinishTime(1);
    	assertEquals(node.getEarliestFinishTime(), 1);
    }
    
    @Test
    public void testGetLatestStartTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setLatestStartTime(0);
    	assertEquals(node.getLatestStartTime(), 0);
    }
    
    @Test
    public void testSetLatestStartTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setLatestStartTime(1);
    	assertEquals(node.getLatestStartTime(), 1);
    }
    
    @Test
    public void testGetLatestFinishTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setLatestFinishTime(0);
    	assertEquals(node.getLatestFinishTime(), 0);
    }
    
    @Test
    public void testSetLatestFinishTime() {
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setLatestFinishTime(1);
    	assertEquals(node.getLatestFinishTime(), 1);
    }
    
    @Test
    public void testGetSlackTime(){
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setSlackTime(1);
    	assertEquals(node.getSlackTime(), 1);
    }
    
    @Test
    public void testSetSlackTime(){
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	node.setSlackTime(2);
    	assertEquals(node.getSlackTime(), 2);
    }
    
    @Test
    public void testGetId(){
    	ActivityNode node = new ActivityNode("Node 1", 1);
    	assertNull(node.getId());
    }
}