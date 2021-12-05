package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		ArrayTaskList list = new ArrayTaskList();
		Task task1 = new Task("Go to dinner", 5);
		Task task2 = new Task("Go to sleep", 15);
		Task task3 = new Task("Go to gim", 20);
		list.add(task1);
		list.add(task2);
		list.add(task3);
	}
}
