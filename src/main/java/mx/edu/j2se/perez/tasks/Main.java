package mx.edu.j2se.perez.tasks;
import static mx.edu.j2se.perez.tasks.ListTypes.types.*;
import static mx.edu.j2se.perez.tasks.TaskListFactory.createTaskList;

public class Main {
	public static void main(String[] args) {
		AbstractTaskList array1 = createTaskList(LINKED);
		Task task1 = new Task("Go to bed", 5);
		Task task2 = new Task("Go to lunch", 9, 12,1);
		array1.add(task1);
		array1.add(task2);
		AbstractTaskList array2 = array1.clone();
		for (Task i: array1) {
			i.setActive(true);
		}
		array2.clear();
		System.out.println(array1);
		System.out.println(array2);
	}
}
