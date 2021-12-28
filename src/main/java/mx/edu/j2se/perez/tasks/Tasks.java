package mx.edu.j2se.perez.tasks;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

public class Tasks {

    /**
     * allows to obtain a subset from the tasks list, which is
     * limited by the from and to parameters
     * @param start the lower limit of task execution time allowed
     * @param end the upper limit of task execution time allowed
     * @return an AbstractTaskList object that contains the subset
     *         of the tasks limited by the aforementioned parameters
     * @throws IllegalArgumentException whether the to or from
     *         parameter are null or the from parameter is
     *         greater or equal than the to parameter
     */
    public static Iterator<Task> incoming  (
            Iterator<Task> tasks, LocalDateTime start,
            LocalDateTime end) throws IllegalArgumentException{
        Stream.Builder<Task> builder = Stream.builder();
        if ( start == null || end == null ) {
            throw new IllegalArgumentException("The end and start" +
                    "time mustn't be null");
        } else if (start.isAfter(end) || start.isEqual(end)){
            throw new IllegalArgumentException("The end time must " +
                    "be greater than star time");
        } else {
            while (tasks.hasNext()) {
                builder.add(tasks.next());
            }
            return builder.build()
                    .filter(t -> t.nextTimeAfter(start) != null)
                    .filter(t -> t.nextTimeAfter(start).isBefore(end) ||
                            t.nextTimeAfter(start).isEqual(end)).iterator();
        }
    }
}
