package mx.edu.j2se.perez.tasks;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		LinkedTaskList array1 = new LinkedTaskList();
		Task task1 = new Task("Go to bed", 5);
		Task task2 = new Task("Go to lunch", 9, 12,1);
		array1.add(task1);
		array1.add(task2);

		LinkedTaskList array2 = new LinkedTaskList();
		Task task3 = new Task("Go to bed", 5);
		Task task4 = new Task("Go to lunch", 9, 12,1);
		array2.add(task3);
		array2.add(task4);

		System.out.println(array1.hashCode());
		System.out.println(array2.hashCode());
	}
}
