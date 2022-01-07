package mx.edu.j2se.perez.tasks;
import java.io.Serializable;
import java.util.stream.Stream;

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
public abstract class AbstractTaskList implements Cloneable, Iterable<Task>, Serializable {

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
     * Allows to eliminate all the tasks inside the
     * current object
     */
    public abstract void clear();

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
     * Allows to create a clone of the current object
     * @return the clone of the current object
     */
    @Override
    public AbstractTaskList clone() {
        try {
            AbstractTaskList list = (AbstractTaskList)
                    super.clone();
            list.clear();
            for (int i = 0; i < this.size(); i++) {
               list.add(getTask(i).clone());
            }
            return list;
        } catch (CloneNotSupportedException e) {
            System.out.println("Operation not supported");
        }
        return null;
    }

    /**
     * This method allows working with collections as with
     * Streams
     * @return a Stream of tasks
     */
    abstract public Stream<Task> getStream();

    /**
     * Allows to represent the current object as a String
     * @return the String representation of the object
     */
    @Override
    public String toString() {
        String toString = "[";
        for (int i = 0; i < size(); i++) {
            toString += getTask(i).toString();
            if (i < size() - 1) {
                toString += ",\n";
            }
        }
        toString += "]";
        return toString;
    }
}
