package mx.edu.j2se.perez.tasks;

public class Main {
	public static void main(String[] args) {
		Task tarea = new Task("Ir a comprar el pan", 5);
		System.out.println(tarea.getTitle());
		System.out.println(tarea.isActive());
		System.out.println(tarea.isRepeated());
		tarea.setActive(true);
		System.out.println(tarea.isActive());
		System.out.println(tarea.nextTimeAfter(5));
		tarea.setTime(1, 10, 5);
		System.out.println(tarea.isRepeated());
		System.out.println(tarea.nextTimeAfter(5));
	}
}
