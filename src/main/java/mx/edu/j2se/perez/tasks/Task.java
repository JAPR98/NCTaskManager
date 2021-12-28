package mx.edu.j2se.perez.tasks;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class aims to create events called tasks as a
 * reminder for a user, these tasks can be repetitive
 * and non-repetitive, each of them has a different
 * behavior and also provides different results when
 * their methods are executed.
 *
 * @version     2.0 22 Nov 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class Task implements Cloneable {

    private String title;           // task name
    private LocalDateTime time;               // non-repetitive execution time
    private LocalDateTime start;              // repetitive task start time
    private LocalDateTime end;                // repetitive task end time
    private int interval;           // repetitive task execution interval
    private boolean isActive;       // indicates if the task is gona be executed
    private boolean isRepetitive;   // repetitive state of the task

    /**
     * constructor that creates a non-repetitive task
     * @param title task name
     * @param time task execution time
     * @throws IllegalArgumentException whether the title is null or the given
     *         time is null
     */
    public Task(String title, LocalDateTime time) throws
            IllegalArgumentException {
        if (title == null) {
            throw new IllegalArgumentException("The title " +
                    "mustn´t be null");
        } else if ( time == null ) {
            throw new IllegalArgumentException("The time of " +
                    "excecution mustn't be null");
        } else {
            this.title = title;
            this.time = time;
            this.start = null;
            this.end = null;
            this.interval = 0;
            this.isActive = false;
            this.isRepetitive = false;
        }
    }

    /**
     * constructor that creates a repetitive task
     * @param title task name
     * @param start time from which the task is to be repeated
     * @param end time at which the task repetition ends
     * @param interval task repetition time
     * @throws IllegalArgumentException whether the title is null, the
     *         start or end time are null, the start time is
     *         greater than or equal to end time or the interval is lower
     *         than 1
     */
    public Task(String title, LocalDateTime start,
                LocalDateTime end, int interval) throws
            IllegalArgumentException{
        if (title == null) {
            throw new IllegalArgumentException("The title mustn´t " +
                    "be null");
        } else if ( start == null || end == null){
            throw new IllegalArgumentException("The start and end " +
                    "time mustn´t be null");
        } else if ( start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("The end time must " +
                    "be greater than star time");
        } else if ( interval < 1){
            throw new IllegalArgumentException("The interval must " +
                    "be greater than 0");
        } else {
            this.title = title;
            this.time = null;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.isActive = false;
            this.isRepetitive = true;
        }
    }

    /**
     * gets the task name
     * @return task name
     */
    public String getTitle() { return this.title; }

    /**
     * sets the task name
     * @param title task name
     * @throws IllegalArgumentException whether the given title is null
     */
    public void setTitle(String title) throws IllegalArgumentException{
        if (title == null) {
            throw new IllegalArgumentException("The title mustn´t " +
                    "be null");
        } else {
            this.title = title;
        }
    }

    /**
     * informs whether the task is in active status
     * @return task status
     */
    public boolean isActive() { return this.isActive; }

    /**
     * sets the task status
     * @param active status to be setted
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * gets the task execution time
     * @return start time of execution whether is a repetitive one
     *         time of execution if is not
     */
    public LocalDateTime getTime(){
        if (this.isRepetitive) {
            return this.start;
        } else {
            return this.time;
        }
    }

    /**
     * sets the execution time whether is a non-repetitive task and
     * converts the task from a repetitive one to a non-repetitive
     * one if is not, and set the execution time as well
     * @param time task execution time
     * @throws IllegalArgumentException whether the given time is
     *         null
     */
    public void setTime(LocalDateTime time) throws IllegalArgumentException{
        if ( time == null) {
            throw new IllegalArgumentException("The excecution time mustn't " +
                    "be null");
        } else {
            if (this.isRepetitive) {
                this.isRepetitive = false;
                this.start = null;
                this.end = null;
                this.interval = 0;
            }
            this.time = time;
        }
    }

    /**
     * gets the task start time
     * @return whether the task is a repetitive one returns the
     *         task execution start time, if is not, returns the
     *         execution time
     */
    public LocalDateTime getStartTime() {
        if (this.isRepetitive) {
            return this.start;
        } else {
            return this.time;
        }
    }

    /**
     * gets the task end execution time
     * @return whether the task is a repetitive one returns the
     *         task execution end time, if is not, returns the
     *         execution time
     */
    public LocalDateTime getEndTime() {
        if (this.isRepetitive) {
            return this.end;
        } else {
            return this.time;
        }
    }

    /**
     * gets the task repeat interval
     * @return whether the task is a repetitive one returs the
     *         task execution interval, if is not, returns 0
     */
    public int getRepeatInterval() {
        if (this.isRepetitive) {
            return this.interval;
        } else {
            return 0;
        }
    }

    /**
     * wheter is a repetitive task, sets the task start and end execution
     * time, as well as the task execution interval, if is not, converts the
     * task from a non-repetitive one to a repetitive one and also sets the
     * aforementioned parameters
     * @param start time from which the task is to be repeated
     * @param end time at which the task repetition ends
     * @param interval task repetition time
     * @throws IllegalArgumentException whether the start or end time are
     *         null, the start time is greater than or equal to
     *         end time or the interval is lower than 1
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws
            IllegalArgumentException{
        if ( start == null || end == null){
            throw new IllegalArgumentException("The start and end " +
                    "time mustn´t be null");
        } else if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("The end time must " +
                    "be greater than star time");
        } else if (interval < 1){
            throw new IllegalArgumentException("The interval must " +
                    "be greater than 0");
        } else {
            if (this.isRepetitive) {;
                this.start = start;
                this.end = end;
                this.interval = interval;
            } else {
                this.isRepetitive = true;
                this.time = null;
                this.start = start;
                this.end = end;
                this.interval = interval;
            }
        }
    }

    /**
     * informs whether the task is going to be repeated
     * @return task repetition status
     */
    public boolean isRepeated() {
        return this.isRepetitive;
    }

    /**
     * returns the next start time of the task execution after the current time.
     * @param current the actual time of execution
     * @return wheter is not an active task, or the current time is later
     *         the taks end time, returns -1, otherwise returns the task
     *         next start time of the task execution after the current time
     * @throws IllegalArgumentException wether the current time is lower than 0
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) throws
            IllegalArgumentException{
        if ( current == null) {
            throw new IllegalArgumentException("The current time " +
                    "mustn't be null");
        } else {
            if (!isActive) {
                return null;
            }
            if (!isRepetitive){
                LocalDateTime execTime = time.isAfter(current) ? time : null;
                return execTime;
            } else {
                if (start.isAfter(current)) {
                    return start;
                } else {
                    int aux = 1;
                    while (start.plusHours(interval * aux).isBefore(end)) {
                        if (current.isBefore(start.plusHours(interval * aux)) &&
                                start.plusHours(interval * aux).isBefore(end)) {
                            return start.plusHours(interval * aux);
                        }
                        aux++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * allows to compare whether two task objects are the same
     * @param obj the object to be compared against the current object
     * @return whether the object is equal to the current object, returns
     * true, otherwise returns false
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj != null) && (obj instanceof Task)) {
            Task taskObj = (Task)obj;
            if (taskObj.isRepeated()) {
                if (title.equals(taskObj.getTitle()) &&
                        start.isEqual(taskObj.getStartTime()) &&
                        end.isEqual(taskObj.getEndTime()) &&
                        interval == taskObj.getRepeatInterval()) {
                    result = true;
                }
            } else {
                if (title.equals(taskObj.getTitle()) &&
                        time.isEqual(taskObj.getTime())) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * This method allows to obtain the hash value of the object
     * @return the hash value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, isActive,
                isRepetitive);
    }

    /**
     * Allows to represent the current object as a String, whether the
     * task is repetitive its string format will be: title startTime endTime
     * interval activeStatus, otherwise the format will be: title executionTime
     * activeStatus
     * @return the String representation of the object
     */
    @Override
    public String toString() {
        if (this.isRepetitive) {
            return "Title: "+this.title+"\nStart: "+
                    this.start+"\nEnd: "+ this.end+"\nInterval: "+
                    this.interval+"\nActive: "+ this.isActive+"\n";
        } else {
            return "Title: "+this.title+"\nTime: "+this.time+"\nActive: "+
                    this.isActive+"\n";
        }
    }

    /**
     * Allows to create a clone of the current object
     * @return the clone of the current object
     * @return null whether the operation is not defined
     */
    @Override
    public Task clone() {
        try {
            Task taskCloned = (Task)super.clone();
            return taskCloned;
        } catch (CloneNotSupportedException e) {
            System.out.println("Operation not supported");
        }
        return null;
    }
}
