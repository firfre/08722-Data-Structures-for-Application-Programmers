import java.util.Arrays;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 14-Advanced Sorting
 *
 * Naive version of Merge Sort.
 * Needs optimizations and you will have a chance to work in lab 5.
 *
 * @author Terry Lee
 */
public class MergeSort {

    /**
     * Constant to set the length of array to sort.
     */
    private static final int SIZE = 10000;

    /**
     * Recursive Merge Sort.
     * This does not sort the values of the input array.
     * Instead, it creates a new array and return it where values are sorted in ascending order.
     * @param unsorted an array to sort
     * @return a new and sorted array
     */
    public static int[] mergeSort(int[] unsorted) {
        // base case
        if (unsorted.length <= 1) {
            return unsorted;
        }

        // recursive case
        // divide into two halves
        int mid = unsorted.length / 2;
        int[] left = new int[mid];
        System.arraycopy(unsorted, 0, left, 0, mid);
        int[] right = new int[unsorted.length - mid];
        System.arraycopy(unsorted, mid, right, 0, right.length);

        // sort the left half using merge sort
        left = mergeSort(left);
        // sort the right half using merge sort
        right = mergeSort(right);

        // merge sorted halves into the final result
        return merge(left, right);
    }

    /**
     * Merges two sorted arrays into one.
     * Precondition: two input arrays are sorted in ascending order and not null
     * @param left sorted array
     * @param right the other sorted array
     * @return one merged array where elements are sorted
     */
    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int indexLeft = 0, indexRight = 0, indexMerged = 0;

        // traverse and put proper values into merged array from left or right
        while (indexLeft < left.length && indexRight < right.length) {
            if (left[indexLeft] <= right[indexRight]) {
                merged[indexMerged] = left[indexLeft];
                indexLeft++;
            } else {
                merged[indexMerged] = right[indexRight];
                indexRight++;
            }
            indexMerged++;
        }

        // since not all the elements are copied over to merged array
        if (indexLeft < left.length) {
            // elements are still in left
            for (int i = indexLeft; i < left.length; i++) {
                merged[indexMerged++] = left[i];
            }
        } else {
            // elements are still in right
            for (int i = indexRight; i < right.length; i++) {
                merged[indexMerged++] = right[i];
            }
        }

        return merged;
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
     * Simple test program to check merge and Merge Sort methods.
     * @param args arguments
     */
    public static void main(String[] args) {
        // test merge method
        int[] left = {12, 42, 63, 89};
        int[] right = {3, 27, 78, 94};
        System.out.println(Arrays.toString(merge(left, right)));

        // a very simple Merge Sort test
        int[] test = {42, 12, 89, 27, 94, 63, 3, 78};
        System.out.println(Arrays.toString(mergeSort(test)));

        // more realistic test of Merge Sort
        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = (int) (Math.random() * SIZE);
        }
        array = mergeSort(array);

        // just to make sure sorting works
        assert isSorted(array);
    }

}
