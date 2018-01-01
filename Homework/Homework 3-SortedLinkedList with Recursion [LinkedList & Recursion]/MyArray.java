/**
 * 08-722 Data Structures for Applications Programmers.
 * Homework 3-SortedLinkedList with Recursion
 *
 * A very simple MyArray class that uses String array
 * as its underlying data structure
 *
 * Do not change anything in this class
 *
 * @author Terry Lee
 */
public class MyArray {

    /**
     * Constant for default capacity of the array.
     */
    private static final int DEFAULT_CAPACITY = 15;

    /**
     * String array for this data structure.
     */
    private String[] array;
    /**
     * Current size variable.
     */
    private int currentSize;

    /**
     * No-arg constructor that instantiate array using default capacity.
     */
    public MyArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with initial capacity.
     * @param initialCapacity initial capacity of the array
     */
    public MyArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Array length should be greater than or equal to 0");
        } else {
            array = new String[initialCapacity];
        }
        currentSize = 0;
    }

    /**
     * Gets a word value at a specific index.
     * @param index index of an array to get a word
     * @return String value for a word at the index
     */
    public String get(int index) {
        return array[index];
    }

    /**
     * Returns the size of the array, or the number of elements.
     * @return int value of the number of elements
     */
    public int size() {
        return currentSize;
    }

    /**
     * Returns the capacity of the array.
     * @return int value of the array capacity
     */
    public int getCapacity() {
        return array.length;
    }

    /**
     * Searches a word in the array.
     * @param key key value to search
     * @return boolean (true if found, false if not)
     */
    public boolean search(String key) {
        for (int i = 0; i < currentSize; i++) {
            if (array[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new word into the array.
     * @param text English word to add
     */
    public void add(String text) {
        ensureSize();
        if (isWord(text)) {
            array[currentSize++] = text;
        }
    }

    /**
     * Private helper method to double the array length.
     */
    private void ensureSize() {
        if (currentSize == array.length) {
            if (array.length == 0) {
                // cannot double size zero, increase to one instead
                array = new String[1];
            } else {
                String[] tmp = new String[array.length * 2];
                System.arraycopy(array, 0, tmp, 0, currentSize);
                array = tmp;
            }
        }
    }

    /**
     * Simple private helper method to validate a word.
     * @param text text to check
     * @return true if valid, false if not
     */
    private boolean isWord(String text) {
        return text.matches("[a-zA-Z]+");
    }

    /**
     * Displays all of the values of the array.
     */
    public void display() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

}
