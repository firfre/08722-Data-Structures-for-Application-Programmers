/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 1 MyArray
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class MyArray {

    /**
     * Default capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Internal String array.
     */
    private String[] myWords;
    /**
     * Size of this MyArray.
     */
    private int size;

    /**
     * Default constructor.
     */
    public MyArray() {
        myWords = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructor with parameter.
     * @param initialCapacity initial capacity
     */
    public MyArray(int initialCapacity) {
        // 0 can be passed as its initial capacity.
        if (initialCapacity < 0) {
            myWords = new String[DEFAULT_CAPACITY];
        } else {
            myWords = new String[initialCapacity];
        }
        size = 0;
    }

    /**
     * Accessor of size.
     * Running time complexity: O(1)
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Returns the capacity of this MyArray.
     * Running time complexity: O(1)
     * @return capacity
     */
    public int getCapacity() {
        return myWords.length;
    }

    /**
     * Searches for the given word in this MyArray.
     * Running time complexity: O(n)
     * @param key word to search for
     * @return whether the given word is in this MyArray
     */
    public boolean search(String key) {
        // Check whether the input string is null
        if (key == null) {
            return false;
        }

        for (String myWord : myWords) {
            if (myWord.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the given word to this MyArray.
     * Running time complexity: Amortized O(1)
     * @param text word to add
     */
    public void add(String text) {
        // Check whether the input string is null
        if (text == null) {
            return;
        }

        // add() method should take care of validating words.
        // Your clients convert words to lowercase before inserting them.
        if (!text.matches("[A-Za-z]+")) {
            return;
        }

        // Resize if necessary
        if (size == 0) {
            myWords = new String[1];
        } else if (size == myWords.length) {
            // The underlying String[] array should double its length when necessary.
            doubleUpCapacity();
        }
        myWords[size] = text;
        ++size;
    }

    /**
     * Doubles the capacity.
     */
    private void doubleUpCapacity() {
        String[] tmp = new String[myWords.length * 2];
        System.arraycopy(myWords, 0, tmp, 0, size);
        myWords = tmp;
    }

    /**
     * Removes duplicates in this MyArray.
     * Running time complexity: O(n^2)
     */
    public void removeDups() {
        // Move all the distinct words to the left
        int distinctPtr = 0;
        int i = 0;
        while (i < size) {
            boolean isDistinct = true;
            for (int j = 0; j < distinctPtr; ++j) {
                if (myWords[j].equals(myWords[i])) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                swap(myWords, i, distinctPtr);
                ++distinctPtr;
            }
            ++i;
        }

        // Remove all the duplicates
        for (int j = distinctPtr; j < size; ++j) {
            myWords[j] = null;
        }
        size = distinctPtr;
    }

    /**
     * Private helper method to swap the given indices in the given array.
     * @param array given array
     * @param i first index
     * @param j second index
     */
    private void swap(String[] array, int i, int j) {
        String tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    /**
     * Displays this MyArray.
     * Running time complexity: O(n)
     */
    public void display() {
        StringBuilder s = new StringBuilder();
        for (String myWord : myWords) {
            s.append(myWord).append(" ");
        }
        System.out.println(s.toString().trim()); // Trim off the last space
    }

}
