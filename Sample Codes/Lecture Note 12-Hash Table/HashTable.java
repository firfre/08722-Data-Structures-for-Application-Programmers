/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 12-Hash Table
 *
 * Assumption: It takes only positive integers.
 * For the sake of simplicity, there is only keys.
 *
 * A very rudimentary HashTable with linear probing.
 *
 * Note that this code is just to show basic concepts.
 * In reality, there is a lot more to think about, especially with rehashing.
 *
 * @author Terry Lee
 */
public class HashTable implements HashTableInterface {

    /**
     * Constant to indicate item in a cell (index) has been deleted.
     * With open addressing, each cell can be one of the following three states.
     * 1. empty
     * 2. occupied
     * 3. deleted
     */
    private static final DataItem DELETED = new DataItem(-1);

    /**
     * Underlying array of DataItem.
     */
    private DataItem[] hashArray;

    /**
     * Constructs a new hash table with the specified initial capacity.
     * precondition: initialCapacity is a positive integer (reasonably big)
     * @param initialCapacity initial capacity of the table
     */
    public HashTable(int initialCapacity) {
        hashArray = new DataItem[initialCapacity];
    }

    @Override
    public boolean search(int key) {
        // get initial hash value (index)
        int hashVal = hashFunc(key);
        // continue as long as it does not hit an empty slot
        while (hashArray[hashVal] != null) {
            // found
            if (hashArray[hashVal].key == key) {
                return true;
            }
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }
        return false;
    }

    @Override
    public int delete(int key) {
        // get initial hash value (index)
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            // found
            if (hashArray[hashVal].key == key) {
                DataItem item = hashArray[hashVal];
                // set flag for deletion
                hashArray[hashVal] = DELETED;
                return item.key;
            }
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }
        return -1;
    }

    @Override
    public void insert(int key) {
        // get initial hash value (index)
        int hashVal = hashFunc(key);
        // search for an empty slot or reusable slot, flagged as DELETED
        while ((hashArray[hashVal] != null) && (hashArray[hashVal] != DELETED)) {
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }
        hashArray[hashVal] = new DataItem(key);
    }

    /**
     * Rudimentary modular hashing function.
     * We will look into this further later.
     * @param key key for which hash value need to be calculated (only positive integers)
     * @return hash value (index) of the specified key
     */
    private int hashFunc(int key) {
        return key % hashArray.length;
    }

    /**
     * Simple static nested class for DataItem.
     */
    private static class DataItem {
        /**
         * Key of DataItem.
         */
        private int key;

        /**
         * Constructs a new DataItem of the table.
         * @param k key of DataItem
         */
        DataItem(int k) {
            key = k;
        }
    }

}
