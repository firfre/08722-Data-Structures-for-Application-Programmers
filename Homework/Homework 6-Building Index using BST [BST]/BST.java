import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index using BST
 *
 * BST implementation
 *
 * @param <T> data type of objects
 *
 * Andrew ID: ziangl
 * @author Ziang_Lu
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {

    /**
     * Root of this BST.
     */
    private Node<T> root;
    /**
     * Comparator used by this BST.
     */
    private Comparator<T> comparator;

    /**
     * Default constructor.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor with parameter.
     * @param comp comparator to use
     */
    public BST(Comparator<T> comp) {
        root = null;
        comparator = comp;
    }

    /**
     * Returns the data stored in the root.
     * @return data stored in the root
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }

        return root.data;
    }

    /**
     * Accessor of comparator.
     * @return comparator
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Returns the number of nodes in this BST.
     * @return number of nodes
     */
    public int getNumberOfNodes() {
        return getNumberOfNodesHelper(root);
    }

    /**
     * Private helper method to get the number of nodes in the given sub-tree
     * recursively.
     * @param curr current node
     * @return number of nodes in the given sub-tree
     */
    private int getNumberOfNodesHelper(Node<T> curr) {
        // Base case
        if (curr == null) {
            return 0;
        }
        // Recursive case
        return 1 + getNumberOfNodesHelper(curr.left) + getNumberOfNodesHelper(curr.right);
    }

    /**
     * Returns the height of this BST.
     * @return height
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }

        // Note that both an empty BST and a singleton BST are of height 0
        return getHeightHelper(root) - 1;
    }

    /**
     * Private helper method to get the height of the given sub-tree
     * recursively.
     * @param curr current node
     * @return height of the given sub-tree
     */
    private int getHeightHelper(Node<T> curr) {
        // Base case
        if (curr == null) {
            return 0;
        }
        int leftHeight = getHeightHelper(curr.left), rightHeight = getHeightHelper(curr.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public T search(T toSearch) {
        // Check whether the input data is null
        if (toSearch == null) {
            return null;
        }

        return searchHelper(toSearch, root);
    }

    /**
     * Private helper method to search for the given data in this BST
     * recursively.
     * @param toSearch Object value to search
     * @param curr current node
     * @return The value (object) of the search result. If not found, null.
     */
    private T searchHelper(T toSearch, Node<T> curr) {
        // Base case 1: Not found
        if (curr == null) {
            return null;
        }
        // Base case 2: Found it
        if (((comparator == null) && (curr.data.compareTo(toSearch) == 0))
                || ((comparator != null) && (comparator.compare(curr.data, toSearch) == 0))) {
            return curr.data;
        }

        // Recursive case
        if (((comparator == null) && (curr.data.compareTo(toSearch) > 0))
                || ((comparator != null) && (comparator.compare(curr.data, toSearch) > 0))) {
            return searchHelper(toSearch, curr.left);
        } else {
            return searchHelper(toSearch, curr.right);
        }
    }

    @Override
    public void insert(T toInsert) {
        // Check whether the input data is null
        if (toInsert == null) {
            return;
        }

        if (root == null) {
            root = new Node<T>(toInsert);
            return;
        }

        insertHelper(toInsert, null, root, true);
    }

    /**
     * Private helper method to insert the given data into this BST recursively.
     * @param toInsert a value (object) to insert into the tree
     * @param parent parent node
     * @param curr current node
     * @param isLC whether the current node is a left child
     */
    private void insertHelper(T toInsert, Node<T> parent, Node<T> curr, boolean isLC) {
        // Base case 1: Not found
        if (curr == null) {
            if (isLC) {
                parent.left = new Node<T>(toInsert);
            } else {
                parent.right = new Node<T>(toInsert);
            }
            return;
        }
        // Base case 2: Found it
        if (((comparator == null) && (curr.data.compareTo(toInsert) == 0))
                || ((comparator != null) && (comparator.compare(curr.data, toInsert) == 0))) {
            // Keep the old one. Do not update or combine.
            return;
        }

        // Recursive case
        if (((comparator == null) && (curr.data.compareTo(toInsert) > 0))
                || ((comparator != null) && (comparator.compare(curr.data, toInsert) > 0))) {
            insertHelper(toInsert, curr, curr.left, true);
        } else {
            insertHelper(toInsert, curr, curr.right, false);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    /**
     * Inner BSTIterator class.
     */
    private class BSTIterator implements Iterator<T> {
        /**
         * Helper stack.
         */
        private Stack<Node<T>> stack;

        /**
         * Default constructor.
         */
        BSTIterator() {
            stack = new Stack<Node<T>>();
            Node<T> curr = root;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (stack.isEmpty()) {
                return null;
            }

            Node<T> nextNode = stack.pop();
            if (nextNode.right != null) {
                Node<T> curr = nextNode.right;
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
            }
            return nextNode.data;
        }
    }

    /**
     * Static nested Node class.
     * @param <T> data type of objects
     */
    private static class Node<T> {
        /**
         * Data stored in this Node.
         */
        private T data;
        /**
         * Reference to left child.
         */
        private Node<T> left;
        /**
         * Reference to right child.
         */
        private Node<T> right;

        /**
         * Constructor with parameter.
         * @param d data to store
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructor with parameter.
         * @param d data to store
         * @param l left child
         * @param r right child
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
