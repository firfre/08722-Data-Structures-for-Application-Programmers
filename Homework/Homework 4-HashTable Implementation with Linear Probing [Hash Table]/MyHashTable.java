/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 4-HashTable Implementation with Linear Probing
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class MyHashTable implements MyHTInterface {

    /**
     * Default capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Load factor.
     */
    private static final double LOAD_FACTOR = 0.5;
    /**
     * Flag for deleted items.
     */
    private static final DataItem DELETED = new DataItem("");

    /**
     * Underlying data items.
     */
    private DataItem[] data;
    /**
     * Number of items.
     */
    private int nItem;
    /**
     * Number of collisions.
     */
    private int nCollision;

    /**
     * Default constructor.
     */
    public MyHashTable() {
        data = new DataItem[DEFAULT_CAPACITY];
        nItem = 0;
        nCollision = 0;
    }

    /**
     * Constructor with parameter.
     * @param initialCapacity initial capacity
     */
    public MyHashTable(int initialCapacity) {
        // Check whether the input initial capacity is positive
        if (initialCapacity <= 0) {
            throw new RuntimeException("Initial capacity must be positive.");
        }

        data = new DataItem[initialCapacity];
        nItem = 0;
        nCollision = 0;
    }

    @Override
    public int hashValue(String value) {
        // Check whether the input string is a lowercase word
        if (!isLowerWord(value)) {
            return -1;
        }

        return hashFunc(value);
    }

    /**
     * Private helper method to determine whether the given text is a lowercase
     * word.
     * @param text text to check
     * @return whether the given text is a lowercase word
     */
    private boolean isLowerWord(String text) {
        return (text != null) && (text.matches("^[a-z]+$"));
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = 0;
        for (char c : input.toCharArray()) {
            hashVal = (hashVal * 27 + (c - 'a' + 1)) % data.length;
        }
        return hashVal;
    }

    @Override
    public int size() {
        return nItem;
    }

    @Override
    public boolean contains(String key) {
        // Check whether the input string is a lowercase word
        if (!isLowerWord(key)) {
            return false;
        }

        int hashVal = hashFunc(key);
        int i = hashVal;
        while (data[i] != null) {
            if (data[i].value.equals(key)) { // Found it
                return true;
            }
            ++i;
            // Wrap around
            i %= data.length;
        }
        // Not found
        return false;
    }

    @Override
    public int showFrequency(String key) {
        // Check whether the input string is a lowercase word
        if (!isLowerWord(key)) {
            return 0;
        }

        int hashVal = hashFunc(key);
        int i = hashVal;
        while (data[i] != null) {
            if (data[i].value.equals(key)) { // Found it
                return data[i].frequency;
            }
            ++i;
            // Wrap around
            i %= data.length;
        }
        // Not found
        return 0;
    }

    @Override
    public void insert(String value) {
        // Check whether the input string is a lowercase word
        if (!isLowerWord(value)) {
            return;
        }

        int hashVal = hashFunc(value);
        int i = hashVal;
        while ((data[i] != null) && (data[i] != DELETED)) {
            if (data[i].value.equals(value)) { // Found it
                data[i].addFreq();
                return;
            }
            ++i;
            // Wrap around
            i %= data.length;
        }
        // Not found

        // Take care of number of collision
        for (DataItem item : data) {
            if ((item != null) && (item != DELETED) && (hashFunc(item.value) == hashVal)) {
                ++nCollision;
                break;
            }
        }

        data[i] = new DataItem(value);
        ++nItem;
        if ((1.0 * nItem / data.length) > LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Doubles array length and rehash items whenever the load factor is
     * reached.
     */
    private void rehash() {
        // Rather than insert the words again, we simply need to reconnect the references between two arrays
        // Temporarily store the original array
        DataItem[] tmp = data;
        // Create the new array
        int newSize = findNextPrime(data.length * 2 + 1);
        System.out.println("Rehashing " + nItem + " items, new length is " + newSize);
        data = new DataItem[newSize];
        // Recalculate number of collisions
        nCollision = 0;
        for (DataItem item : tmp) {
            if ((item != null) && (item != DELETED)) {
                int hashVal = hashFunc(item.value);
                int i = hashVal;
                while (data[i] != null) {
                    ++i;
                    // Wrap around
                    i %= data.length;
                }
                // Take care of number of collisions
                for (DataItem currItem : data) {
                    if ((currItem != null) && (hashFunc(currItem.value) == hashVal)) {
                        ++nCollision;
                        break;
                    }
                }
                data[i] = item;
            }
        }
    }

    /**
     * Private helper method to find the next prime starting from the given
     * number.
     * @param start starting number
     * @return next prime
     */
    private int findNextPrime(int start) {
        int n = start;
        while (!isPrime(n)) {
            ++n;
        }
        return n;
    }

    /**
     * Helper method to determine whether the given number is prime.
     * @param n number to check
     * @return whether the given number is prime
     */
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if ((n % 2) == 0) {
            return false;
        }
        for (int i = 3; i < Math.sqrt(n); i += 2) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String remove(String key) {
        // Check whether the input string is a lowercase word
        if (!isLowerWord(key)) {
            return null;
        }

        int hashVal = hashFunc(key);
        int i = hashVal;
        while (data[i] != null) {
            if (data[i].value.equals(key)) { // Found it
                String removedData = data[i].value;
                data[i] = DELETED;
                --nItem;
                return removedData;
            }
            ++i;
            // Wrap around
            i %= data.length;
        }
        // Not found
        return null;
    }

    @Override
    public int numOfCollisions() {
        return nCollision;
    }

    @Override
    public void display() {
        StringBuilder s = new StringBuilder();
        for (DataItem item : data) {
            if (item == null) {
                s.append("**");
            } else {
                s.append(item);
            }
            s.append(" ");
        }
        System.out.println(s.toString().trim());
    }

    /**
     * Private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        /**
         * Constructor with parameter.
         * @param data data of this item
         */
        DataItem(String data) {
            value = data;
            frequency = 1;
        }

        /**
         * Adds 1 to the frequency.
         */
        void addFreq() {
            frequency += 1;
        }

        @Override
        public String toString() {
            if (value.equals("")) {
                return "#DEL#";
            }

            StringBuilder s = new StringBuilder();
            s.append("[").append(value).append(", ").append(frequency).append("]");
            return s.toString();
        }
    }

}
