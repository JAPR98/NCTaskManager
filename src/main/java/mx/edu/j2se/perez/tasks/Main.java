package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		AbstractTaskList list1 = TaskListFactory.createTaskList(ListTypes.types.LINKED);
		Task task1 = new Task("Go to dinner", 5);
		Task task2 = new Task("Go to sleep", 15);
		Task task3 = new Task("Go to gim", 20);
		list1.add(task1);
		list1.add(task2);
		list1.add(task3);
		System.out.println(list1);
		System.out.println(list1.size());
		AbstractTaskList list2 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
		Task task4 = new Task("Go to dinner", 5);
		Task task5 = new Task("Go to sleep", 15);
		Task task6 = new Task("Go to gim", 20);
		list2.add(task4);
		list2.add(task5);
		list2.add(task6);
		System.out.println(list2);
		System.out.println(list2.size());
	}
}
