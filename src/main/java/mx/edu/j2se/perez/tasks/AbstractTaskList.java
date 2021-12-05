package mx.edu.j2se.perez.tasks;

/**
 * This class is intended to be a way to abstract
 * the functionality of the AbstractTaskList and
 * LinkedTaskList, declaring the methods whose
 * functionality doesn't depend on the storage method
 * of the task objects, and implementing those that
 * do
 *
 * @version     1.0 3 Dic 2021
 * @author      José Antonio Pérez Rodríguez
 */
public abstract class AbstractTaskList {

    protected int size;           //The size of the tasks store object

    /**
     * adds the given task to the end of the tasks list
     * @param task task to be added to the tasks store object
     * @throws IllegalArgumentException whether the task is null
     */
    public abstract void add(Task task) throws IllegalArgumentException;

    /**
     * remove the specified task from the tasks store
     * object, whether the task is repeated, eliminates
     * the repetition also
     * @param task the task to be eliminated from the
     *             tasks store object
     * @return whether the task is in the tasks store
     *         object returns true, otherwise, returns
     *         false
     * @throws IllegalArgumentException whether the task
     *         is null
     * @throws RuntimeException whether the task store
     *         object is empty
     */
    public abstract boolean remove(Task task) throws IllegalArgumentException,
            RuntimeException;

    /**
     * allows to obtain the size of the tasks store
     * object
     * @return the size of the tasks store object
     */
    public int size() {
        return size;
    }

    /**
     * allows to obtain a task from the tasks store object
     * the given index
     * @param index the index of the task
     * @return the task indicated by the index
     * @throws IndexOutOfBoundsException whether the element
     *         pointed by the index doesn't exist
     */
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     * allows to obtain a subset from the tasks list, which is
     * limited by the from and to parameters
     * @param from the lower limit of task execution time allowed
     * @param to the upper limit of task execution time allowed
     * @return an AbstractTaskList object that contains the subset
     *         of the tasks limited by the aforementioned parameters
     * @throws IllegalArgumentException whether the to or from
     *         parameter are lower than 0 or the from parameter is
     *         greater or equal than the to parameter
     */
    /*
    public AbstractTaskList incoming(int from, int to) throws
            IllegalArgumentException{
        AbstractTaskList tasksObjectStore;
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("from and to " +
                    "parameters must be greater or equal than 0");
        } else if (from >= to) {
            throw new IllegalArgumentException("The from parameter " +
                    "must be lower than the to parameter");
        } else {
            tasksObjectStore = new AbstractTaskList();
            for (int i = 0; i < size; i++) {
                if ((getTask(i).nextTimeAfter(from) <= to) &&
                        (getTask(i).nextTimeAfter(from) != -1)) {
                    tasksObjectStore.add(getTask(i));
                }
            }
        }
        return tasksObjectStore;
    }
    */
}
