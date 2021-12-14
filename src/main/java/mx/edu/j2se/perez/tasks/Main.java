package mx.edu.j2se.perez.tasks;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		LinkedTaskList array1 = new LinkedTaskList();
		Task task1 = new Task("Go to bed", 5);
		Task task2 = new Task("Go to lunch", 9, 12,1);
		array1.add(task1);
		array1.add(task2);
		LinkedTaskList array2 = (LinkedTaskList)array1.clone();
		array2.getTask(1).setActive(true);
		for (Task i: array1) {
			System.out.println(i);
		}
	}
}
