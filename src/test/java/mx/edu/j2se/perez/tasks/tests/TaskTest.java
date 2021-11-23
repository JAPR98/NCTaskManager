package mx.edu.j2se.perez.tasks.tests;

import mx.edu.j2se.perez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testRepetitiveTasks(){
        Task task = new Task("Go Shopping", 4, 11, 2);
        Assert.assertFalse(task.isActive());
        task.setActive(true);
        Assert.assertTrue(task.isActive());
        Assert.assertTrue(task.isRepeated());
        Assert.assertEquals(task.getTitle(),"Go Shopping");
        task.setTitle("Changed");
        Assert.assertEquals(task.getTitle(),"Changed");
        task.setTime(8, 12, 3);
        Assert.assertEquals(task.getStartTime(), 8);
        Assert.assertEquals(task.getEndTime(), 12);
        Assert.assertEquals(task.getRepeatInterval(), 3);
    }

    @Test
    public void testNonRepetitiveTasks(){
        Task task = new Task("Go Shopping", 6);
        Assert.assertFalse(task.isActive());
        task.setActive(true);
        Assert.assertTrue(task.isActive());
        Assert.assertFalse(task.isRepeated());
        Assert.assertEquals(task.getTitle(),"Go Shopping");
        task.setTitle("Changed");
        Assert.assertEquals(task.getTitle(),"Changed");
        task.setTime(7);
        Assert.assertEquals(task.getTime(), 7);
    }

    @Test
    public void testTaskNextTimeAfter(){
        /*
        Repetitive
         */
        Task taskR = new Task("Go Shopping", 4, 11, 2);
        taskR.setActive(true);
        Assert.assertEquals(taskR.nextTimeAfter(3),4);
        Assert.assertEquals(taskR.nextTimeAfter(4),6);
        Assert.assertEquals(taskR.nextTimeAfter(9),10);
        Assert.assertEquals(taskR.nextTimeAfter(10),-1);
       /*
       Non-repetitive
       */
        Task taskNR = new Task("Go Shopping", 5);
        taskNR.setActive(true);
        Assert.assertEquals(taskNR.nextTimeAfter(3),5);
        Assert.assertEquals(taskNR.nextTimeAfter(5),-1);
        Assert.assertEquals(taskNR.nextTimeAfter(6),-1);
    }
}
