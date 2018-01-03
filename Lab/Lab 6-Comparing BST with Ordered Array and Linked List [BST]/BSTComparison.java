import java.util.LinkedList;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 6-Comparing BST with Ordered Array and Linked List
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class BSTComparison {

    /**
     * Constant of SIZE.
     */
    private static final int SIZE = 1000000;
    /**
     * Constant value to search.
     */
    private static final int VALUE_TO_SEARCH = SIZE - 1;

    /**
     * Builds a new BST with the minimum height inserting values in an ordered
     * array recursively.
     * @param orderedArray ordered array from which values to get and add into BST
     */
    public static BST createMinBST(int[] orderedArray) {
        BST theBST = new BST();
        createMinBST(theBST, orderedArray, 0, orderedArray.length - 1);
        return theBST;
    }

    /**
     * Recursive helper method to build BST with the minimum height.
     * @param theBST BST data structure to build
     * @param array array from which values to get and add into BST
     * @param start start index
     * @param end end index
     */
    private static void createMinBST(BST theBST, int[] array, int start, int end) {
        // Base case
        if (start > end) {
            return;
        }
        // Recursive case
        int mid = start + (end - start) / 2;
        theBST.insert(array[mid]);
        createMinBST(theBST, array, start, mid - 1);
        createMinBST(theBST, array, mid + 1, end);
    }

    /*
     * All of the following methods are operations of an ordered array.
     * 1. Binary search
     * 2. insert
     * 3. delete
     */

    /**
     * Binary search operation on an ordered array.
     * @param array ordered array
     * @param searchKey a key to search for
     * @return the index of an array where the key is. if not found, array length.
     */
    public static int binarySearch(int[] array, int searchKey) {
        int lowerBound = 0;
        int upperBound = array.length - 1;
        while (true) {
            if (lowerBound > upperBound) {
                return array.length;
            }

            int mid = lowerBound + (upperBound - lowerBound) / 2;
            if (array[mid] == searchKey) {
                return mid;
            }

            if (array[mid] < searchKey) {
                lowerBound = mid + 1;
            } else {
                upperBound = mid - 1;
            }
        }
    }

    /**
     * Insert a new value into an ordered array.
     * @param array ordered array where a new value to add
     * @param keyToInsert key value to add
     */
    public static void insert(int[] array, int keyToInsert) {
        // Search first
        int index = binarySearch(array, keyToInsert);
        // if the value is in the array, then do not perform insertion
        if (index != array.length) {
            return;
        }
        // find the place to be inserted
        int place = 0;
        for (; place < array.length; place++) {
            if (array[place] > keyToInsert) {
                break;
            }
        }
        // shift larger values up
        // not really perfect but good enough to see the difference
        for (int k = array.length - 1; k > place; k--) {
            array[k] = array[k - 1];
        }
        array[place] = keyToInsert;
    }

    /**
     * Deletes a value from an ordered array.
     * @param array ordered array where the value to delete
     * @param keyToDelete key value to delete
     */
    public static void delete(int[] array, int keyToDelete) {
        int index = binarySearch(array, keyToDelete);
        if (index == array.length) {
            return;
        }
        // not really perfect but enough to see the difference
        for (int k = index; k < array.length - 1; k++) {
            array[k] = array[k + 1];
        }
    }

    /**
     * Test program to compare BST with Array and LL.
     * @param args arguments
     */
    public static void main(String[] args) {
        // create an ordered array and linked list with the same values.
        int[] array = new int[SIZE];
        LinkedList<Integer> llist = new LinkedList<Integer>();
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            llist.add(i);
        }
        // build a BST with the values from the ordered array
        BST theBST = createMinBST(array);

        Stopwatch timer1 = new Stopwatch();
        theBST.find(VALUE_TO_SEARCH);
        System.out.println("Computing time for BST search: " + timer1.elapsedTime() + " millisec");

        Stopwatch timer2 = new Stopwatch();
        binarySearch(array, VALUE_TO_SEARCH);
        System.out.println("Computing time for Binary Search on ordered array: " + timer2.elapsedTime() + " millisec");

        Stopwatch timer3 = new Stopwatch();
        llist.contains(VALUE_TO_SEARCH);
        System.out.println(
                "Computing time for search, or contains call, on linkedlist: " + timer3.elapsedTime() + " millisec");

        Stopwatch timer4 = new Stopwatch();
        theBST.delete(0);
        System.out.println("Computing time for BST deletion: " + timer4.elapsedTime() + " millisec");

        Stopwatch timer5 = new Stopwatch();
        delete(array, 0);
        System.out.println("Computing time for deletion on ordered array: " + timer5.elapsedTime() + " millisec");

        Integer llvalue = new Integer(VALUE_TO_SEARCH);
        Stopwatch timer6 = new Stopwatch();
        llist.remove(llvalue);
        System.out.println("Computing time for deletion on linked list: " + timer6.elapsedTime() + " millisec");

        Stopwatch timer7 = new Stopwatch();
        theBST.insert(0);
        System.out.println("Computing time for BST insertion: " + timer7.elapsedTime() + " millisec");

        Stopwatch timer8 = new Stopwatch();
        insert(array, 0);
        System.out.println("Computing time for insertion on ordered array: " + timer8.elapsedTime() + " millisec");

        Stopwatch timer9 = new Stopwatch();
        llist.add(llvalue);
        System.out.println("Computing time for insertion on linked list: " + timer9.elapsedTime() + " millisec");
    }

}
