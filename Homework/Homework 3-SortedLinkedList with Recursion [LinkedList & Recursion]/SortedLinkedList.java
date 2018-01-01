/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 3-SortedLinkedList with Recursion
 *
 * SortedLinkedList class
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class SortedLinkedList implements MyListInterface {

    /**
     * Inner static Node class.
     * @author Ziang Lu
     */
    private static class Node {
        /**
         * String data stored in this Node.
         */
        private String data;
        /**
         * Reference to next node.
         */
        private Node next;

        /**
         * Constructor with parameter.
         * @param word word to store in this Node
         * @param nextNode next node
         */
        Node(String word, Node nextNode) {
            data = word;
            next = nextNode;
        }
    }

    /**
     * Head of this SortedLinkedList.
     */
    private Node head;

    /**
     * Default constructor.
     */
    public SortedLinkedList() {
        head = null;
    }

    /**
     * Constructor with parameter.
     * @param unsorted unsorted words
     */
    public SortedLinkedList(String[] unsorted) {
        // Check whether the input string array is null or empty
        if ((unsorted == null) || (unsorted.length == 0)) {
            head = null;
            return;
        }

        addUnsortedArrayHelper(unsorted, 0);
    }

    /**
     * Private helper method to add the given string sub-array to this
     * SortedLinkedList.
     * @param unsorted unsorted words
     * @param i current index of the sub-array
     */
    private void addUnsortedArrayHelper(String[] unsorted, int i) {
        // Base case
        if (i >= unsorted.length) {
            return;
        }
        // Recursive case
        add(unsorted[i]);
        addUnsortedArrayHelper(unsorted, i + 1);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return sizeHelper(head);
    }

    /**
     * Private helper method to return the size of the given sub-list.
     * @param curr current node
     * @return size of the sub-list
     */
    private int sizeHelper(Node curr) {
        // Base case
        if (curr == null) {
            return 0;
        }
        // Recursive case
        return 1 + sizeHelper(curr.next);
    }

    @Override
    public boolean contains(String key) {
        // Check whether the input string is null or empty
        if ((key == null) || (key.length() == 0)) {
            return false;
        }

        return containsHelper(key, head);
    }

    /**
     * Private helper method to check whether the given key is in the given
     * sub-list.
     * @param key String key to search
     * @param curr current node
     * @return whether the key is in the sub-list
     */
    private boolean containsHelper(String key, Node curr) {
        // Base case 1: Not found
        if (curr == null) {
            return false;
        }
        // Base case 2: Found it
        if (curr.data.equals(key)) {
            return true;
        }

        // Recursive case
        return containsHelper(key, curr.next);
    }

    @Override
    public void add(String value) {
        // Check whether the input string is null or empty
        if ((value == null) || (value.length() == 0)) {
            return;
        }

        if (head == null) {
            head = new Node(value, null);
            return;
        }

        if (head.data.compareTo(value) > 0) {
            head = new Node(value, head);
            return;
        }

        addHelper(value, null, head);
    }

    /**
     * Private helper method to add the given String to the given sub-list.
     * @param value String to be added
     * @param prev previous node
     * @param curr current node
     */
    private void addHelper(String value, Node prev, Node curr) {
        // Base case 1: Not found
        if (curr == null) {
            prev.next = new Node(value, null);
            return;
        }
        // Base case 2: Found it
        if (curr.data.equals(value)) {
            // No duplicates allowed
            return;
        }
        // Base case 3: Found the position to add
        if (curr.data.compareTo(value) > 0) {
            prev.next = new Node(value, curr);
            return;
        }

        // Recursive case
        addHelper(value, curr, curr.next);
    }

    @Override
    public String removeFirst() {
        if (head == null) {
            return null;
        }

        String first = head.data;
        head = head.next;
        return first;
    }

    @Override
    public String removeAt(int index) {
        // Check whether the input index is non-negative
        if (index < 0) {
            throw new RuntimeException("Index must be non-negative.");
        }

        if (head == null) {
            throw new RuntimeException("This SortedLinkedList is empty.");
        }

        if (index == 0) {
            return removeFirst();
        }

        return removeAtHelper(head, head.next, 1, index);
    }

    /**
     * Private helper method to remove and return the string at the given index
     * from the given sub-list.
     * @param prev previous node
     * @param curr current node
     * @param currIdx current index
     * @param tgtIdx index to remove String object
     * @return String object that is removed
     */
    private String removeAtHelper(Node prev, Node curr, int currIdx, int tgtIdx) {
        // Base case 1: Target index is out of bound (too large)
        if (curr == null) {
            throw new RuntimeException("Target index is out of bound (too large).");
        }
        // Base case 2: Found it
        if (currIdx == tgtIdx) {
            prev.next = curr.next;
            return curr.data;
        }

        // Recursive case
        return removeAtHelper(curr, curr.next, currIdx + 1, tgtIdx);
    }

    @Override
    public void display() {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        StringBuilder s = new StringBuilder();
        s.append("[");
        displayHelper(head, s);
        s.append("]");
        System.out.println(s.toString());
    }

    /**
     * Private helper method to display the given sub-list.
     * @param curr start node of the sub-list
     * @param s StringBuilder to append
     */
    private void displayHelper(Node curr, StringBuilder s) {
        s.append(curr.data);
        // Base case
        if (curr.next == null) {
            return;
        }
        // Recursive case
        s.append(", ");
        displayHelper(curr.next, s);
    }

}
