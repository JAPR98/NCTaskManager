package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		Task tarea = new Task("Ir a comprar el pan", 4, 11, 2);
		System.out.println(tarea.nextTimeAfter(3));
	}
}
