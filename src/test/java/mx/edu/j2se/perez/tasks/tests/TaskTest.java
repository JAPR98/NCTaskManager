package mx.edu.j2se.perez.tasks.tests;

import mx.edu.j2se.perez.tasks.ArrayTaskList;
import mx.edu.j2se.perez.tasks.LinkedTaskList;
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
    
    @Test
    public void testArrayTaskListMethods(){
        Task taskR1 = new Task("Repetitive task 1", 4, 1, 2);
        taskR1.setActive(true);
        Task taskR2 = new Task("Repetitive task 2", 1, 5, 2);
        taskR2.setActive(true);
        Task taskNR1 = new Task("Non Repetitive task 1", 7);
        taskNR1.setActive(true);
        Task taskNR2 = new Task("Non Repetitive task 2", 2);
        taskNR2.setActive(true);
        ArrayTaskList array = new ArrayTaskList();
        array.add(taskR1);
        array.add(taskR1);
        array.add(taskR2); //will stay after the remove method execution
        array.add(taskNR1); //will stay after the remove method execution
        array.add(taskR1);
        array.add(taskNR2); //will stay after the remove method execution
        Assert.assertEquals(array.size(),6);
        array.remove(taskR1);
        Assert.assertEquals(array.size(),3);
        ArrayTaskList arrayTest = array.incoming(2,7);
        Assert.assertEquals(arrayTest.size(), 2);
        for (int i = 0; i < arrayTest.size(); i++){
            System.out.println(arrayTest.getTask(i).getTitle());
        }
    }
    @Test
    public void testLinkedTaskListMethods(){
        Task taskR1 = new Task("Repetitive task 1", 4, 5, 2);
        taskR1.setActive(true);
        Task taskR2 = new Task("Repetitive task 2", 1, 5, 2);
        taskR2.setActive(true);
        Task taskNR1 = new Task("Non Repetitive task 1", 7);
        taskNR1.setActive(true);
        Task taskNR2 = new Task("Non Repetitive task 2", 2);
        taskNR2.setActive(true);
        LinkedTaskList list = new LinkedTaskList();
        list.add(taskR1);
        list.add(taskR1);
        list.add(taskR2); //will stay after the remove method execution
        list.add(taskNR1); //will stay after the remove method execution
        list.add(taskR1);
        list.add(taskNR2); //will stay after the remove method execution
        Assert.assertEquals(list.size(),6);
        list.remove(taskR1);
        Assert.assertEquals(list.size(),3);
        LinkedTaskList listTest = list.incoming(2,7);
        Assert.assertEquals(listTest.size(), 2);
        System.out.println(listTest.getTask(0).getTitle());
        System.out.println(listTest.getTask(1).getTitle());
    }
}
