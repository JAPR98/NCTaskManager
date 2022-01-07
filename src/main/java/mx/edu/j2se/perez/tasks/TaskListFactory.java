package mx.edu.j2se.perez.tasks;

/**
 * This class allows to create either an ArrayTaskList
 * or a LinkedTaskList object, based on the type stated
 * by the ListTypes class
 *
 * @version     1.0 3 Dic 2021
 * @author      José Antonio Pérez Rodríguez
 */
public class TaskListFactory {

    /**
     * This method allows to create an ArrayTaskList or a
     * LinkedTaskList object, based on the type stated by
     * the ListTypes class, which can be ARRAY or LINKED
     * @param type The type of list that you want to create
     * @return An AbstractTaskList that contains either the
     *         ArrayTaskList or LinkedTaskList object
     * @throws IllegalArgumentException whether the given
     *         type is null
     */
    public static AbstractTaskList createTaskList(ListTypes.types type) throws
            IllegalArgumentException {
        AbstractTaskList storeTaskObject;
        if (type == null) {
            throw new IllegalArgumentException("The type " +
                    "mustn't be null");
        } else {
            if (type == ListTypes.types.LINKED) {
                storeTaskObject = new LinkedTaskList();
            } else {
                storeTaskObject = new ArrayTaskList();
            }
        }
        return storeTaskObject;
    }
}
