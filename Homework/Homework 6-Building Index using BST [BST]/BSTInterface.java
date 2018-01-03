/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index using BST
 *
 * A very simple interface: DO NOT MODIFY THIS!
 * @param <T> data type of objects
 * @author Terry Lee
 */
public interface BSTInterface<T> {

    /**
     * Given the value (object), tries to find it.
     * @param toSearch Object value to search
     * @return The value (object) of the search result. If not found, null.
     */
    T search(T toSearch);

    /**
     * Inserts a value (object) to the tree.
     * No duplicates allowed.
     * @param toInsert a value (object) to insert into the tree
     */
    void insert(T toInsert);

}
