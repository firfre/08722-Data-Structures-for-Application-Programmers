import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 2-LinkedList Operation Implementation
 *
 * @param <AnyType> data type to insert into list
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class LinkedListLab<AnyType> implements Iterable<AnyType> {

    /**
     * Head node variable.
     */
    private Node<AnyType> head;

    /**
     * No-arg constructor.
     */
    public LinkedListLab() {
        head = null;
    }

    /**
     * Inserts a new item to the end.
     * @param item data item to be inserted
     */
    public void insert(AnyType item) {
        if (head == null) {
            head = new Node<AnyType>(item, head);
            return;
        }
        Node<AnyType> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node<AnyType>(item, null);
    }

    /**
     * Finds object that is kth to the last node of linkedlist.
     * @param k kth position to the last. 1 means the last node
     * @return Object that is located at kth to the last
     */
    public AnyType kthToLast(int k) {
        // Check whether the input k is positive
        if (k <= 0) {
            return null;
        }

        if (head == null) {
            return null;
        }

        // The front pointer moves (k - 1) steps.
        Node<AnyType> front = head;
        for (int i = 0; i < (k - 1); ++i) {
            front = front.next;
            if (front == null) { // Meaning that there are less than k nodes in this linked-list.
                return null;
            }
        }
        Node<AnyType> back = head;
        // By now the back pointer is behind the front pointer by (k - 1) positions.

        // Move the front and back pointers at the same time until the front pointer reaches the last node
        // Then the back pointer is the k-th to the last node.
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }
        return back.data;
    }

    /**
     * Returns a string representation.
     * @return String representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object x : this) {
            result.append(x).append(" ");
        }
        return result.toString();
    }

    /**
     * Iterator implementation.
     * @return Iterator object to go through elements in the list
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Non-static nested class for Iterator implementation.
     */
    private class LinkedListIterator implements Iterator<AnyType> {
        /**
         * Reference to nextNode to access.
         */
        private Node<AnyType> nextNode;

        /**
         * No-arg constructor.
         */
        LinkedListIterator() {
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType result = nextNode.data;
            nextNode = nextNode.next;
            return result;
        }
    }

    /**
     * Node (static nested class).
     * @param <AnyType> data type of node class
     */
    private static class Node<AnyType> {
        /**
         * Data type of node.
         */
        private AnyType data;
        /**
         * Reference to next node.
         */
        private Node<AnyType> next;

        /**
         * Constructor a new node with data and next node reference.
         * @param newData data element of the node
         * @param newNode next node reference
         */
        Node(AnyType newData, Node<AnyType> newNode) {
            data = newData;
            next = newNode;
        }
    }

    /**
     * A few simple test cases.
     * @param args arguments
     */
    public static void main(String[] args) {
        LinkedListLab<String> theList = new LinkedListLab<String>();
        theList.insert("data");
        theList.insert("strutures");
        theList.insert("rock");
        theList.insert("the");
        theList.insert("world");
        theList.insert("way");
        theList.insert("to");
        theList.insert("go");
        theList.insert("dude");
        System.out.println("values: " + theList);
        // should print null
        System.out.println("0: " + theList.kthToLast(0));
        // should print "dude"
        System.out.println("1: " + theList.kthToLast(1));
        // should print "go"
        System.out.println("2: " + theList.kthToLast(2));
        // should print "to"
        System.out.println("3: " + theList.kthToLast(3));
        // should print "data"
        System.out.println("9: " + theList.kthToLast(9));
        // should print null
        System.out.println("10: " + theList.kthToLast(10));
    }

}
