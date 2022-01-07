package mx.edu.j2se.perez.tasks;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		LocalDateTime time1 = LocalDateTime.of(2021, 12, 27, 12, 12);
		LocalDateTime time2 = LocalDateTime.of(2021, 12, 27, 23, 12);
		Task task1 = new Task("Out", time1, time2, 2);
		Task task2 = new Task("Out", time1);
		task1.setActive(true);
		AbstractTaskList list1 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		list1.add(task1);
		list1.add(task2);
		System.out.println(list1);
	}
}
