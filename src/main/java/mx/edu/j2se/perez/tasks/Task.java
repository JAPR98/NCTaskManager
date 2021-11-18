package mx.edu.j2se.perez.tasks;

/**
 * This class aims to create events called tasks as a
 * reminder for a user, these tasks can be repetitive
 * and non-repetitive, each of them has a different
 * behavior and also provides different results when
 * their methods are executed.
 *
 * @version     1.1 17 Nov 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class Task {

    private String title;           // task name
    private int time;               // non-repetitive execution time
    private int start;              // repetitive task start time
    private int end;                // repetitive task end time
    private int interval;           // repetitive task execution interval
    private boolean isActive;       // indicates if the task is gona be executed
    private boolean isRepetitive;   // repetitive state of the task

    /**
     * constructor that creates a non-repetitive task
     * @param title task name
     * @param time task execution time
     */
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.isActive = false;
        this.isRepetitive = false;
    }

    /**
     * constructor that creates a repetitive task
     * @param title task name
     * @param start time from which the task is to be repeated
     * @param end time at which the task repetition ends
     * @param interval task repetition time
     */
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isActive = false;
        this.isRepetitive = true;
    }

    /**
     * gets the task name
     * @return task name
     */
    public String getTitle() { return this.title; }

    /**
     * sets the task name
     * @param title task name
     */
    public void setTitle(String title) { this.title = title; }

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
    public int getTime(){
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
     */
    public void setTime(int time) {
        if (this.isRepetitive) {
            this.isRepetitive = false;
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
        this.time = time;
    }

    /**
     * gets the task start time
     * @return whether the task is a repetitive one returns the
     *         task execution start time, if is not, returns the
     *         execution time
     */
    public int getStartTime() {
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
    public int getEndTime() {
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
     */
    public void setTime(int start, int end, int interval) {
        if (this.isRepetitive) {;
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else {
            this.isRepetitive = true;
            this.time = 0;
            this.start = start;
            this.end = end;
            this.interval = interval;
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
     *         the taks end time, returns -1, otherwise returns the task next
     *         start time of the task execution after the current time
     */
    public int nextTimeAfter(int current) {
        if (!this.isActive) {
            return -1;
        }
        if (!this.isRepetitive && this.time > current){
            return this.time;
        } else {
            if (this.start > current) {
                return this.start;
            }
            for (int i = this.start; i <= (this.end - this.interval);
                    i += this.interval) {
                if (current < (i + this.interval)) {
                    return (i + this.interval);
                }
            }
        }
        return -1;
    }
}
