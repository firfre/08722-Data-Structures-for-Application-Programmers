/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 12-Hash Table
 *
 * Hash Table interface (linear probing)
 *
 * A rudimentary HashTable interface takes only positive integers
 * For the sake of simplicity, there is only keys
 *
 * @author Terry Lee
 */
public interface HashTableInterface {

    /**
     * Returns true when the key is found.
     * @param key int key to search for
     * @return boolean true if found, false not found
     */
    boolean search(int key);

    /**
     * Deletes and returns the int key found in the table.
     * @param key int key to delete
     * @return deleted int from the table (if not found, -1)
     */
    int delete(int key);

    /**
     * Inserts a positive int key to the table.
     * @param key int key to insert
     */
    void insert(int key);

}
