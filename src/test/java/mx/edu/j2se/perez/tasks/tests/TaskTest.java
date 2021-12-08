package mx.edu.j2se.perez.tasks.tests;

import mx.edu.j2se.perez.tasks.*;
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
    
    @Test
    public void testTaskListFactory(){
        // Creating an LinkedTaskList
        AbstractTaskList list1 = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        Assert.assertTrue(list1 instanceof LinkedTaskList);
        Task task1 = new Task("Go to dinner", 5);
        task1.setActive(true);
        Task task2 = new Task("Go to sleep", 15);
        task2.setActive(true);
        Task task3 = new Task("Go to gim", 20);
        task3.setActive(true);
        list1.add(task1);
        list1.add(task2);
        list1.add(task3);
        Assert.assertEquals(list1.size(),3);
        Assert.assertEquals(list1.getTask(0),task1);
        AbstractTaskList result1 = list1.incoming(4,18);
        Assert.assertEquals(result1.size(), 2);
        System.out.println(result1);

        // Creating an ArrayTaskList
        AbstractTaskList list2 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Assert.assertTrue(list2 instanceof ArrayTaskList);
        Task task4 = new Task("Go to dinner", 5);
        task4.setActive(true);
        Task task5 = new Task("Go to sleep", 15);
        task5.setActive(true);
        Task task6 = new Task("Go to gim", 20);
        task6.setActive(true);
        list2.add(task4);
        list2.add(task5);
        list2.add(task6);
        Assert.assertEquals(list2.size(),3);
        Assert.assertEquals(list2.getTask(0),task4);
        AbstractTaskList result2 = list2.incoming(4,18);
        Assert.assertEquals(result2.size(), 2);
        System.out.println(result2);
    }
}
