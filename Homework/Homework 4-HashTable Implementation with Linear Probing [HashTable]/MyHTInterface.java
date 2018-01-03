/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 4-HashTable Implementation with Linear Probing
 *
 * A simple HashTable interface that takes lowercase String values.
 * Assumption is that clients will always insert lowercase values.
 *
 * Do not change anything in this interface
 *
 * @author Terry Lee
 */
public interface MyHTInterface {

    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    int hashValue(String value);

    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    int size();

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    boolean contains(String key);

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    int showFrequency(String key);

    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    void insert(String value);

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    String remove(String key);

    /**
     * Returns the number of collisions in relation to insert and rehash.
     * When rehashing process happens, the number of collisions should be properly updated.
     *
     * The definition of collision is "two different keys map to the same hash value."
     * Be careful with the situation where you could overcount.
     * Try to think as if you are using separate chaining.
     * "How would you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    int numOfCollisions();

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    void display();

}
