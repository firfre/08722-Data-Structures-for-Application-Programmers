import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 5-LinkedList
 *
 * Singly Linked-List implementation
 * Simple and only enough to help you understand the concept
 *
 * @param <AnyType> type of item to insert
 * @author Terry Lee
 */
public class SinglyLinkedList<AnyType> implements Iterable<AnyType> {

    /**
     * Reference to the head node.
     */
    private Node<AnyType> head;

    /**
     * Construct an empty list.
     */
    public SinglyLinkedList() {
        head = null;
    }

    /**
     * Inserts the specified item at the beginning of the list.
     * @param item item to add
     */
    public void addFirst(AnyType item) {
        head = new Node<AnyType>(item, head);
    }

    /**
     * Inserts the specified item to the end of the list.
     * @param item item to add
     */
    public void addLast(AnyType item) {
        if (head == null) {
            addFirst(item);
            return;
        }

        // traverse to find the last node which takes time
        Node<AnyType> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }

        tmp.next = new Node<AnyType>(item, null);
    }

    /**
     * Inserts the specified item after the specified key item.
     * @param key key item to search for
     * @param item item to add after the key item
     */
    public void insertAfter(AnyType key, AnyType item) {
        // traverse to find the node with the key value
        Node<AnyType> tmp = head;
        while (tmp != null && !tmp.data.equals(key)) {
            tmp = tmp.next;
        }

        // if the key is found
        if (tmp != null) {
            tmp.next = new Node<AnyType>(item, tmp.next);
        }
    }

    /**
     * Inserts the specified item before the specified key item.
     * @param key key item to search for
     * @param item item to add before the key item
     */
    public void insertBefore(AnyType key, AnyType item) {
        if (head == null) {
            return;
        }

        if (head.data.equals(key)) {
            addFirst(item);
            return;
        }

        // traverse to find the node with the key value
        Node<AnyType> prev = null;
        Node<AnyType> curr = head;
        while (curr != null && !curr.data.equals(key)) {
            prev = curr;
            curr = curr.next;
        }

        // if the key is found
        if (curr != null) {
            prev.next = new Node<AnyType>(item, curr);
        }
    }

    /**
     * Removes the first occurrence of the specified item from the list.
     * @param key key item to search for and remove
     */
    public void remove(AnyType key) {
        if (head == null) {
            return;
        }

        if (head.data.equals(key)) {
            head = head.next;
            return;
        }

        // traverse to find the node with the key value
        Node<AnyType> prev = null;
        Node<AnyType> curr = head;
        while (curr != null && !curr.data.equals(key)) {
            prev = curr;
            curr = curr.next;
        }

        // if the key is found
        if (curr != null) {
            prev.next = curr.next;
        }
    }

    /**
     * Returns iterator object that allows access to private elements.
     * @return Iterator object
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner (non-static) class for Iterator implementation.
     */
    private class LinkedListIterator implements Iterator<AnyType> {
        /**
         * Reference to nextNode to access.
         */
        private Node<AnyType> nextNode;

        /**
         * No-arg constructor that starts the iteration from the head.
         */
        LinkedListIterator() {
            // access to the head instance variable of the Outer class
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
     * Static nested class for Node.
     * @param <AnyType> Generic type of item
     */
    private static class Node<AnyType> {
        /**
         * Data of a node (item).
         */
        private AnyType data;
        /**
         * Reference to next node.
         */
        private Node<AnyType> next;

        /**
         * Construct a new node with data and next node reference.
         * @param newData data of the node (item)
         * @param newNext reference to next node
         */
        Node(AnyType newData, Node<AnyType> newNext) {
            data = newData;
            next = newNext;
        }
    }

}
