/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 6-Comparing BST with Ordered Array and Linked List
 *
 * A very simple Binary Search Tree interface
 * No duplicate keys allowed
 *
 * @author Terry Lee
 */
public interface BSTInterface {

    /**
     * Searches for the specified key in the tree.
     * @param key key of the element to search
     * @return boolean value indication of success or failure
     */
    boolean find(int key);

    /**
     * Inserts a new element into the tree.
     */
    void insert(int key);

    /**
     * Deletes an element from the tree using the specified key.
     * @param key key of the element to delete
     */
    void delete(int key);

    /**
     * Traverses and prints values of the tree in ascending order based on key.
     */
    void traverse();

}
