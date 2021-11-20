package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		Task tarea = new Task("test",5);
		tarea.setActive(true);
		System.out.println(tarea.nextTimeAfter(6));

	}
}
