import java.util.NoSuchElementException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 7-Queue
 *
 * A very simple Queue class using array
 * Concept of wrapping around using modulus operation (Circular Queue)
 *
 * @author Terry Lee
 * @param <AnyType> type of element objects
 */
public class ArrayQueue<AnyType> implements QueueInterface<AnyType> {

    /**
     * Constant for default capacity of the underlying array.
     */
    private static final int DEFAULT_CAPACITY = 6;

    /**
     * Array of elements.
     */
    private Object[] elements;
    /**
     * Locations of front and back elements and number of items.
     */
    private int front, back, nItems;

    /**
     * Constructs a new queue with default capacity.
     */
    public ArrayQueue() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1; // similar to top variable in ArrayStack
        nItems = 0;
    }

    @Override
    public boolean isEmpty() {
        return nItems == 0;
    }

    @Override
    public void enqueue(AnyType item) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        // back moves up
        back++;
        // need to wrap around
        elements[back % elements.length] = item;
        nItems++;
    }

    /**
     * Private helper method to check if queue is full or not.
     * @return true if it is full and false if not
     */
    private boolean isFull() {
        return nItems == elements.length;
    }

    @Override
    public AnyType dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // need to wrap around
        int index = front % elements.length;
        @SuppressWarnings("unchecked")
        AnyType item = (AnyType) elements[index];
        // make sure to delete the item
        elements[index] = null;
        // front moves up to the right
        front++;
        nItems--;
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AnyType peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (AnyType) elements[front % elements.length];
    }

    /**
     * A few test cases.
     * @param args arguments
     */
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println(queue.dequeue()); // 1
        System.out.println(queue.peekFront()); // 3
        queue.enqueue(2);
        queue.enqueue(10);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(8);
        System.out.println(queue.peekFront()); // 3
        System.out.println(queue.dequeue()); // 3
        System.out.println(queue.peekFront()); // 2
        queue.enqueue(9);
        queue.enqueue(11); // exception (Queue is full)
    }

}
