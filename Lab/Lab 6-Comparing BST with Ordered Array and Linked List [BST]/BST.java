/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 6-Comparing BST with Ordered Array and Linked List
 *
 * A very simple Binary Search Tree implementation
 * No duplicate keys allowed
 *
 * Note: This is only to help your understanding of the concepts
 *
 * @author Terry Lee
 */
public class BST implements BSTInterface {

    /**
     * Reference to root node.
     */
    private Node root;

    /**
     * Constructs an empty BST.
     */
    public BST() {
        root = null;
    }

    @Override
    public boolean find(int key) {
        // tree is empty
        if (root == null) {
            return false;
        }

        Node curr = root;
        // while not found
        while (curr.key != key) {
            if (curr.key < key) {
                // go right
                curr = curr.right;
            } else {
                // go left
                curr = curr.left;
            }

            // not found
            if (curr == null) {
                return false;
            }
        }
        return true; // found
    }

    @Override
    public void insert(int key) {
        // empty tree
        if (root == null) {
            root = new Node(key);
            return;
        }

        Node parent = root; // keep track of parent
        Node curr = root;
        while (true) {
            // no duplicate keys allowed
            // simply keep the existing one here
            if (curr.key == key) {
                return;
            }

            parent = curr; // update parent
            if (curr.key < key) {
                // go right
                curr = curr.right;
                if (curr == null) {
                    // found a spot
                    parent.right = new Node(key);
                    return;
                }
            } else {
                // go left
                curr = curr.left;
                if (curr == null) {
                    // found a spot
                    parent.left = new Node(key);
                    return;
                }
            } // end of if-else to go right or left
        } // end of while
    } // end of insert method

    @Override
    public void delete(int key) {
        // empty tree
        if (root == null) {
            return;
        }

        Node parent = root;
        Node curr = root;
        /*
         * flag to check left child use this flag during actual deletion process
         * which happens after the while loop
         */
        boolean isLC = true;

        while (curr.key != key) {
            parent = curr; // update parent first
            if (curr.key < key) { // go right
                isLC = false;
                curr = curr.right;
            } else { // go left
                isLC = true;
                curr = curr.left;
            }

            // case 1: not found
            if (curr == null) {
                return;
            }
        }

        if (curr.left == null && curr.right == null) {
            // case 2: leaf
            if (curr == root) {
                root = null;
            } else if (isLC) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.right == null) {
            // case 3: one child (no right child)
            if (curr == root) {
                root = curr.left;
            } else if (isLC) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.left == null) {
            // case 3: one child (no left child)
            if (curr == root) {
                root = curr.right;
            } else if (isLC) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else {
            // case 4: two children
            // here we use successor but using predecessor is also an option
            Node successor = getSuccessor(curr);

            if (curr == root) {
                root = successor;
            } else if (isLC) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = curr.left;
        }
    }

    /**
     * Helper method to find the successor of the toDelete node.
     * This tries to find the smallest value of the right subtree of the
     * toDelete node by going down to the left most node in the subtree
     * @param toDelete node to delete
     * @return the successor of the toDelete node
     */
    private Node getSuccessor(Node toDelete) {
        Node successorParent = toDelete;
        Node successor = toDelete;
        // start the search from the root of the right subtree
        Node curr = toDelete.right;

        // move down to left as far as possible in the right subtree
        // successor's left child must be null
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.left;
        }

        /*
         * If successor is NOT the right child of the node to delete, then
         * need to take care of two connections in the right subtree
         */
        if (successor != toDelete.right) {
            successorParent.left = successor.right;
            successor.right = toDelete.right;
        }

        return successor;
    }

    @Override
    public void traverse() {
        inOrderHelper(root);
        System.out.println();
    }

    /**
     * Private helper method to traverse this BST in-order recursively.
     * @param toVisit current node
     */
    private void inOrderHelper(Node toVisit) {
        if (toVisit != null) {
            inOrderHelper(toVisit.left);
            System.out.print(toVisit);
            System.out.print(" ");
            inOrderHelper(toVisit.right);
        }
    }

    /**
     * Static nested Node class for Node.
     */
    private static class Node {
        /**
         * Key integer.
         */
        private int key;
        /**
         * References to left and right children nodes.
         */
        private Node left, right;

        /**
         * Constructs a new node with key and value.
         * @param k integer key
         */
        Node(int k) {
            key = k;
            left = null;
            right = null;
        }
    }

}
