/**
 * 08722 Data Structures for Application Programmers.
 * Lecture 6-Stack
 *
 * A very simple Stack Interface
 *
 * @author Terry Lee
 * @param <AnyType> type of element objects
 */
public interface StackInterface<AnyType> {

    /**
     * Checks if the stack is empty or not in O(1).
     * @return true if it is empty, false if it is not empty
     */
    boolean isEmpty();

    /**
     * Pushes a new item onto the top of the stack in O(1).
     * @param item a new item to add
     * @throws StackException if stack is full
     */
    void push(AnyType item);

    /**
     * Gets and removes the item on the top in O(1).
     * @return top item
     * @throws StackException indicates that stack is empty
     */
    AnyType pop();

    /**
     * Gets the item on the top but does NOT remove it in O(1).
     * @return top item
     * @throws StackException indicates that stack is empty
     */
    AnyType peek();

}
