package mx.edu.j2se.perez.tasks;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class is intended to be a storage of Task
 * objects, using double linked lists to store the
 * objects and other methods that let you use the class
 * like a list.
 *
 * @version     1.0 29 Nov 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class LinkedTaskList extends AbstractTaskList {
    private Node firstNode;     //A pointer to the first element of the list
    private Node lastNode;      //A pointer to the last element of the list
    private boolean isEmpty;    //Attribute that indicates whether the list is empty

    /**
     * Constructor used to initialize the attributes of
     * the double linked list
     */
    public LinkedTaskList() {
        firstNode = null;
        lastNode = null;
        isEmpty = true;
        size = 0;
    }

    /**
     * adds the given task at the end of the tasks list
     * @param task task to be added to the list of tasks
     * @throws IllegalArgumentException whether the task is null
     */
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("The task " +
                    "mustn't be null");
        } else {
            Node node = new Node(task);
            if (isEmpty) {
                firstNode = node;
                lastNode = node;
                isEmpty = false;
            } else {
                lastNode.nextNode = node;
                node.prevNode = lastNode;
                lastNode = node;
            }
            size++;
        }
    }

    /**
     * remove the specified task from list of task,
     * whether the task is repeated, eliminates also
     * the repetitions
     * @param task the task to be eliminated from the
     *             tasks list
     * @return whether the task is in the tasks list,
     * returns true, otherwise, returns false
     * @throws IllegalArgumentException whether the task
     *         is null
     * @throws RuntimeException whether the task array is
     *         null
     */
    public boolean remove (Task task) throws IllegalArgumentException,
            RuntimeException {
        boolean isInTheList = false;
        if (task == null) {
            throw new IllegalArgumentException("The task mustn't be null");
        } else if (size() == 0) {
            throw new RuntimeException("The tasks list is empty");
        } else {
            Node iterator = firstNode;
            while (iterator != null) {
                if (!firstNode.equals(lastNode) &&
                        task.equals(iterator.task)) {
                    if (firstNode.equals(iterator)) {

                        /* The task to be eliminated is at the beginning */
                        firstNode.nextNode.prevNode = null;
                        firstNode = firstNode.nextNode;

                    } else if (lastNode.equals(iterator)) {

                        /* The task to be eliminated is at the end */
                        lastNode.prevNode.nextNode = null;
                        lastNode = lastNode.prevNode;
                    } else {

                        /* The task to be eliminated is in the middle */
                        iterator.prevNode.nextNode = iterator.nextNode;
                        iterator.nextNode.prevNode = iterator.prevNode;
                    }
                    isInTheList = true;
                }
                else if (task.equals(iterator.task)) {

                    /* There is only a task in the list */
                    firstNode = null;
                    lastNode = null;
                    isEmpty = true;
                    isInTheList = true;
                }
                iterator = iterator.nextNode;
            }
        }
        Node iterator = firstNode;
        int counter = 0;
        while (iterator != null) {
            counter++;
            iterator = iterator.nextNode;
        }
        size = counter;
        return isInTheList;
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
        if ((size() >= (index + 1) && (index >= 0))) {
            Node iterator = firstNode;
            int counter = 0;
            while (iterator != null) {
                if (counter == index) {
                    return iterator.task;
                }
                counter++;
                iterator = iterator.nextNode;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return null;
    }

    /**
     * this method is used to create a new instance of the
     * actual class, it is only used by the incoming method
     * @return an instance of the actual class
     */
    protected AbstractTaskList newListObject() {
        return new LinkedTaskList();
    }

    /**
     * This method allows iterating the class
     * @return an iterator object that can be used
     * to iterate over the class instance
     * @throws IndexOutOfBoundsException whether the
     * iterator has no more items to iterate over
     */
    @Override
    public Iterator<Task> iterator() throws IndexOutOfBoundsException{
        return new Iterator<Task>() {
            private Node iterator = null;

            @Override
            public boolean hasNext() {
                if ((iterator == null) && (size() != 0)) {
                    return true;
                } else return (iterator != null) &&
                        (iterator.nextNode != null);
            }
            @Override
            public Task next() {
                if (hasNext()) {
                    if ((iterator == null) && (size() > 0)) {
                        iterator = firstNode;
                    } else iterator = iterator.nextNode;
                } else {
                    throw new IndexOutOfBoundsException("The list has " +
                            "no more tasks");
                }

                return iterator.task;
            }
        };
    }

    /**
     * allows comparing whether two LinkedTaskList objects are the same
     * @param obj the object to be compared against the current object
     * @return whether the object is equal to the current object, returns
     * true, otherwise returns false
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj instanceof LinkedTaskList)) {
            LinkedTaskList listObj = (LinkedTaskList) obj;
            if (this.size() == listObj.size()) {
                for (int i = 0; i < this.size(); i++) {
                    if (!this.getTask(i).equals(listObj.getTask(i))) {
                        return result;
                    }
                }
                result = true;
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
        return Objects.hash(firstNode.task.hashCode(),
                lastNode.task.hashCode(), size);
    }

    /**
     * Allows to represent the current object as a String
     * @return the String representation of the object
     */
    @Override
    public String toString() {
        return "LinkedTaskList array, "+this.size()+" elements";
    }

    /**
     * This inner class is used to represent the node
     * object to be stored in the LinkedTaskList,
     * each one of the nodes has a reference to its next
     * and previous neighbor, and a task attribute where
     * the task data is stored.
     *
     * @version     1.0 29 Nov 2021
     * @author      José Antonio Pérez Rodríguez
     */
    private static class Node {
        private final Task task;    //Object where the task data is stored
        private Node nextNode;      //A reference to its next neighbor
        private Node prevNode;      //A reference to its previous neighbor

        /**
         * Constructor thar is used to initialize
         * the Node object
         * @param task The task to be stored within
         *             the node
         */
        private Node(Task task) {
            this.task = task;
            nextNode = null;
            prevNode = null;
        }


    }
}
