package mx.edu.j2se.perez.tasks.tests;

import mx.edu.j2se.perez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    @Test
    public void testFirstTask(){
        Task tarea = new Task("Ir a comprar el pan", 4, 11, 2);
        Assert.assertFalse(tarea.isActive());
        tarea.setActive(true);
        Assert.assertTrue(tarea.isActive());
        Assert.assertEquals(tarea.nextTimeAfter(5),6);
        Assert.assertEquals(tarea.nextTimeAfter(3),4);
        Assert.assertEquals(tarea.nextTimeAfter(8),10);
        Assert.assertEquals(tarea.nextTimeAfter(12),-1);
        tarea.setTime(5);
        Assert.assertFalse(tarea.isRepeated());
        Assert.assertEquals(tarea.nextTimeAfter(5),-1);
    }
}
