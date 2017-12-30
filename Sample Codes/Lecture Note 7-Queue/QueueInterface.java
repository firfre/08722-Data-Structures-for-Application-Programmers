/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 7-Queue
 *
 * A very simple Queue Interface
 *
 * @author Terry Lee
 * @param <AnyType> type of element objects
 */
public interface QueueInterface<AnyType> {

    /**
     * Checks if the queue is empty or not in O(1).
     * @return true if the queue is empty, false if not
     */
    boolean isEmpty();

    /**
     * Enqueues a new item to the back of the queue in O(1).
     * @param item a new item to add
     * @throws RuntimeException indicates that queue is full
     */
    void enqueue(AnyType item);

    /**
     * Gets and deletes an item from the front of the queue in O(1).
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    AnyType dequeue();

    /**
     * Gets an item from the front of the queue in O(1) but does not delete it.
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    AnyType peekFront();

}
