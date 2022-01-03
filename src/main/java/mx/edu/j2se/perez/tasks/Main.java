package mx.edu.j2se.perez.tasks;
import java.io.*;
import java.time.LocalDateTime;

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
		AbstractTaskList list2 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		try {
			/*
			TaskIO.write(list1,file);
			TaskIO.read(list2, file);
			for (Task task: list2) {
				System.out.println(task);
			}
			 */
			File file = new File("C:/Users/japer/Desktop/Output.txt");
			TaskIO.readText(list2, file);
			for (Task t: list2) {
				System.out.println(t);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
