package mx.edu.j2se.perez.tasks;

/**
 * This class is intended to be a storage of Task
 * objects, using arrays to store the objects and
 * other methods that let you use the class like a
 * list.
 *
 * @version     1.0 22 Nov 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class ArrayTaskList {

    private Task[] taskArray;       //Structure used to storage the task objects

    /**
     * Constructor used to initialize the task list
     */
    public ArrayTaskList() {
        taskArray = new Task[0];
    }

    /**
     * adds the given task to the end of the task list
     * @param task task to be added to the list of tasks
     */
    public void add(Task task) {
        Task[] taskArrayAux = new Task[taskArray.length + 1];
        System.arraycopy(taskArray, 0, taskArrayAux, 0,
                taskArray.length);
        taskArrayAux[taskArray.length] = task;
        taskArray = taskArrayAux;
    }

    /**
     * remove the specified task from list of task,
     * whether the task is repeated, eliminates the
     * repetition also
     * @param task the task to be eliminated from the
     *             list of tasks
     * @return whether the task is in the task list,
     * returns true, otherwise, returns false
     */
    public boolean remove(Task task) {
        boolean isInTheList = false;
        if (taskArray.length > 0) {
            if ((taskArray.length == 1) && task.equals(taskArray[0])) {
                isInTheList = true;
                taskArray = new Task[0];
            } else {
                for (int i = 0; i < taskArray.length; ) {
                    if (task.equals(taskArray[i])) {
                        isInTheList = true;
                        Task[] taskArrayAux = new Task[taskArray.length - 1];
                        if (i == 0) {
                            System.arraycopy(taskArray, 1, taskArrayAux, 0,
                                    taskArray.length - 1);
                            taskArray = taskArrayAux;
                        } else if (i == taskArray.length - 1) {
                            System.arraycopy(taskArray, 0, taskArrayAux, 0,
                                    taskArray.length - 1);
                            taskArray = taskArrayAux;
                        } else {
                            System.arraycopy(taskArray, 0, taskArrayAux, 0, i);
                            System.arraycopy(taskArray, i + 1, taskArrayAux, i,
                                    taskArray.length - (i + 1));
                            taskArray = taskArrayAux;
                        }
                    }
                    else {
                        i++;
                    }
                }
            }
        }
        return isInTheList;
    }

    /**
     * allows to obtain the size of the task list
     * @return the size of the list
     */
    public int size() {
        return taskArray.length;
    }

    /**
     * allows to obtain a task from the task list using
     * the given index
     * @param index the index of the task
     * @return the task indicated by the index
     */
    public Task getTask(int index) {
        return taskArray[index];
    }

    /**
     * allows to obtain a subset from the task list, which is
     * limited by the from and to parameters
     * @param from the lower limit of task execution time allowed
     * @param to the upper limit of task execution time allowed
     * @return an ArrayList object that contains the subset of the
     * tasks limited by the aforementioned parameters
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList resultArray = new ArrayTaskList();
        for (int i = 0; i < taskArray.length; i++) {
            if ((taskArray[i].nextTimeAfter(from) <= to) &&
                    (taskArray[i].nextTimeAfter(from) != -1)) {
                resultArray.add(taskArray[i]);
            }
        }
        return resultArray;
    }
}

