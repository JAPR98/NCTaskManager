package mx.edu.j2se.perez.tasks;

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
     * allows to obtain a subset from the tasks list, which is
     * limited by the from and to parameters
     * @param from the lower limit of task execution time allowed
     * @param to the upper limit of task execution time allowed
     * @return an LinkedTaskList object that contains the subset
     *         of the tasks limited by the aforementioned parameters
     * @throws IllegalArgumentException whether the to or from
     *         parameter are lower than 0 or the from parameter is
     *         greater or equal than the to parameter
     */
    public LinkedTaskList incoming(int from, int to) throws
            IllegalArgumentException {
        LinkedTaskList linkedList;
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("from and to " +
                    "parameters must be greater or equal than 0");
        } else if (from >= to) {
            throw new IllegalArgumentException("The from parameter " +
                    "must be lower than the to parameter");
        } else {
            linkedList = new LinkedTaskList();
            for (int i = 0; i < size; i++) {
                if ((getTask(i).nextTimeAfter(from) <= to) &&
                        (getTask(i).nextTimeAfter(from) != -1)) {
                    linkedList.add(getTask(i));
                }
            }
            return linkedList;
        }
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
    private class Node {
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
