import java.util.Random;

/**
 * 08-722 Data Structures for Application Programmers.
 *
 * Lab 5-Merge Sort vs Quick Sort
 *
 * A simple implementation of improved Quick Sort
 * @author Terry Lee
 */
public class QuickSortImproved {

    /**
     * Constant for SIZE of an array to sort.
     */
    private static final int SIZE = 100000;
    /**
     * Random numbers.
     */
    private static Random rand = new Random();

    /**
     * A version of Quick Sort.
     * @param array array to sort
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursive method to sort an array using Quick Sort.
     * This is the core algorithm.
     * @param array array to sort
     * @param left left boundary
     * @param right right boundary
     */
    private static void quickSort(int[] array, int left, int right) {
        int leftPointer = left;
        int rightPointer = right;

        // Get the pivot value
        int mid = left + (right - left) / 2;
        int pivot = array[mid];

        // inner loops are simpler
        while (leftPointer <= rightPointer) {
            while (leftPointer <= right && array[leftPointer] <= pivot) {
                leftPointer++;
            }
            while (array[rightPointer] > pivot) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) {
                swap(array, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        // Two special cases to handle:
        if ((leftPointer == (right + 1)) && (rightPointer == right)) { // Meaning that the pivot is the local maximum
            swap(array, mid, right);
            quickSort(array, left, right - 1);
            return;
        } else if ((leftPointer == left) && (rightPointer == (left - 1))) { // Meaning that the pivot is the local minimum
            swap(array, mid, left);
            quickSort(array, left + 1, right);
            return;
        }

        // Recursion
        if (left < rightPointer) {
            quickSort(array, left, rightPointer);
        }
        if (leftPointer < right) {
            quickSort(array, leftPointer, right);
        }
    }

    /**
     * Helper method to swap two values in an array.
     * @param array array to modify
     * @param one first index value
     * @param two second index value
     */
    private static void swap(int[] array, int one, int two) {
        int tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }

    /**
     * A simple debugging program to check if array is sorted.
     * @param array array to check
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array) {
        return isSorted(array, 0, array.length - 1);
    }

    /**
     * Helper method to check if array is sorted.
     * @param array array to check
     * @param lo low boundary
     * @param hi high boundary
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test program to check a version of Quick Sort and its running time.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] array = new int[SIZE];

        for (int i = 0; i < SIZE; i++) array[i] = rand.nextInt();

        //for (int i = 0; i < SIZE; i++) array[i] = SIZE - i;

        Stopwatch timer = new Stopwatch();
        quickSort(array);
        System.out.println("Time taken to sort " + SIZE + " elements (Quick Sort Improved): " + timer.elapsedTime()
                + " milliseconds");

        // to make sure sorting works.
        // add "-ea" vm argument
        assert isSorted(array);
    }

}
