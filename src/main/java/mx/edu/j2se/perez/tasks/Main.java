package mx.edu.j2se.perez.tasks;
import static mx.edu.j2se.perez.tasks.ListTypes.types.*;
import static mx.edu.j2se.perez.tasks.TaskListFactory.createTaskList;

public class Main {
	public static void main(String[] args) {
		AbstractTaskList array1 = createTaskList(LINKED);
		Task task1 = new Task("Go to bed", 5);
		task1.setActive(true);
		Task task2 = new Task("Go to lunch", 9, 12,1);
		Task task3 = new Task("Go to cinema", 9);
		task3.setActive(true);
		array1.add(task1);
		array1.add(task2);
		array1.add(task3);
		array1.getStream().filter(Task::isActive).forEach(System.out::println);
		for (Task t: array1) {
			System.out.println(t);
		}
	}
}
