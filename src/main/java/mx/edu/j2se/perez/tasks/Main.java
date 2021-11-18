package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		Task tarea = new Task("Ir a comprar el pan", 4, 11, 2);
		tarea.setActive(true);
		System.out.println(tarea.nextTimeAfter(3));

	}
}
