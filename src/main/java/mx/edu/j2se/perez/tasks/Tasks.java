package mx.edu.j2se.perez.tasks;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Tasks  {

    /**
     * allows to obtain a subset from the tasks list, which is
     * limited by the from and to parameters
     * @param start the lower limit of task execution time allowed
     * @param end the upper limit of task execution time allowed
     * @return an Iterator object that contains the subset
     *         of the tasks limited by the aforementioned parameters
     * @throws IllegalArgumentException whether the to or from
     *         parameter are null or the from parameter is
     *         greater or equal than the to parameter
     */
    public static Iterator<Task> incoming  (
            Iterator<Task> tasks, LocalDateTime start,
            LocalDateTime end) throws IllegalArgumentException{
        Stream.Builder<Task> builder = Stream.builder();
        if (start == null || end == null) {
            throw new IllegalArgumentException("The end and start" +
                    "time mustn't be null");
        } else if (start.isAfter(end) || start.isEqual(end)){
            throw new IllegalArgumentException("The end time must " +
                    "be greater than start time");
        } else {
            while (tasks.hasNext()) {
                builder.add(tasks.next());
            }
            return builder.build()
                    .filter(t -> t.nextTimeAfter(start) != null)
                    .filter(t -> t.nextTimeAfter(start).isBefore(end) ||
                            t.nextTimeAfter(start).isEqual(end))
                    .iterator();
        }
    }

    /**
     *  This method builds the calendar of tasks for a specific
     *  period of time
     * @param tasks An iterator of tasks
     * @param start The start time from which the calendar will be
     *              built
     * @param end  The end time at which the calendar is to be built
     * @return A SortedMap that is used to store the tasks and the
     *         time at which they will be executed
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks,
                                                               LocalDateTime start,
                                                               LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        LocalDateTime time = start;
        ArrayList<Task> taskList = new ArrayList<>();
        while (tasks.hasNext()) {
            taskList.add(tasks.next());
        }
        while (end.isAfter(time)) {
            Set<Task> taskSet = new HashSet<>();
            Iterator<Task> it = Tasks.incoming(taskList.iterator(),
                    time.minusMinutes(1), time);
            while (it.hasNext()) {
                taskSet.add(it.next());
            }
            if (!taskSet.isEmpty()) {
                calendar.put(time,taskSet);
            }
            time = time.plusMinutes(1);
        }
        return calendar;
    }
}
