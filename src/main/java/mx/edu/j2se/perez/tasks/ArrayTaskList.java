package mx.edu.j2se.perez.tasks;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

/**
 * This class is intended to be a storage of Task
 * objects, using arrays to store the objects and
 * other methods that let you use the class like a
 * list.
 *
 * @version     2.0 29 Nov 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class ArrayTaskList extends AbstractTaskList {

    private Task[] taskArray;       //Structure used to storage the task objects

    /**
     * Constructor used to initialize the task list
     */
    public ArrayTaskList() {
        taskArray = new Task[0];
        size = 0;
    }

    /**
     * adds the given task to the end of the tasks list
     * @param task task to be added to the list of tasks
     * @throws IllegalArgumentException whether the task is null
     */
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("The task " +
                    "mustn't be null");
        } else {
            Task[] taskArrayAux = new Task[taskArray.length + 1];
            System.arraycopy(taskArray, 0, taskArrayAux,
                    0, taskArray.length);
            taskArrayAux[taskArray.length] = task;
            taskArray = taskArrayAux;
            size++;
        }
    }

    /**
     * remove the specified task from list of task,
     * whether the task is repeated, eliminates the
     * repetition also
     * @param task the task to be eliminated from the
     *             list of tasks
     * @return whether the task is in the tasks list,
     * returns true, otherwise, returns false
     * @throws IllegalArgumentException whether the task
     *         is null
     * @throws RuntimeException whether the task array is
     *         null
     */
    public boolean remove(Task task) throws IllegalArgumentException,
            RuntimeException {
        boolean isInTheList = false;
        if (task == null) {
            throw new IllegalArgumentException("The task mustn't be null");
        } else if (taskArray.length == 0) {
            throw new RuntimeException("The tasks array is empty");
        } else {
            if ((taskArray.length == 1) && task.equals(taskArray[0])) {
                isInTheList = true;
                taskArray = new Task[0];
            } else {
                for (int i = 0; i < taskArray.length; ) {
                    if (task.equals(taskArray[i])) {
                        isInTheList = true;
                        Task[] taskArrayAux = new Task[taskArray.length - 1];
                        if (i == 0) {
                            System.arraycopy(taskArray, 1, taskArrayAux,
                                    0, taskArray.length - 1);
                            taskArray = taskArrayAux;
                        } else if (i == taskArray.length - 1) {
                            System.arraycopy(taskArray, 0, taskArrayAux,
                                    0, taskArray.length - 1);
                            taskArray = taskArrayAux;
                        } else {
                            System.arraycopy(taskArray, 0, taskArrayAux,
                                    0, i);
                            System.arraycopy(taskArray, i + 1, taskArrayAux,
                                    i, taskArray.length - (i + 1));
                            taskArray = taskArrayAux;
                        }
                    }
                    else {
                        i++;
                    }
                }
            }
        }
        size = taskArray.length;
        return isInTheList;
    }

    /**
     * this method is used to create a new instance of the
     * actual class, it is only used by the incoming method
     * @return an instance of the actual class
     */
    protected AbstractTaskList newListObject() {
        return new ArrayTaskList();
    }

    /**
     * allows to obtain a task from the tasks list using
     * the given index
     * @param index the index of the task
     * @return the task indicated by the index
     * @throws IndexOutOfBoundsException whether the element
     *         pointed by the index doesn't exist
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (((index + 1) > size()) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
        return taskArray[index];
    }
}

