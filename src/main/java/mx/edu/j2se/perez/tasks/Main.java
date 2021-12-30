package mx.edu.j2se.perez.tasks;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		LocalDateTime time1 = LocalDateTime.of(2021, 12, 27, 12, 12);
		LocalDateTime time2 = LocalDateTime.of(2021, 12, 27, 23, 12);
		Task task1 = new Task("Out", time1, time2, 2);
		Task task2 = new Task("Out", time1, time2, 1);
		task1.setActive(true);
		task2.setActive(true);
		LinkedTaskList list1 = new LinkedTaskList();
		list1.add(task1);
		list1.add(task2);
		LocalDateTime start = LocalDateTime.of(2021,12,27,15,0);
		LocalDateTime end = LocalDateTime.of(2021,12,27,19,13);
		System.out.println(Tasks.calendar(list1.iterator(),start,end));
	}
}
