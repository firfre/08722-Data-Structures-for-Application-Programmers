import java.util.Arrays;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 14-Advanced Sorting
 *
 * Naive and (arguably) canonical version of Quick Sort
 *
 * @author Terry Lee
 */
public class QuickSort {

    /**
     * Constant to set the length of array to sort.
     */
    private static final int SIZE = 10000;

    /**
     * Quick Sort.
     * @param unsorted an array to sort
     */
    public static void quickSort(int[] unsorted) {
        quickSort(unsorted, 0, unsorted.length - 1);
    }

    /**
     * Recursive Quick Sort.
     * This sorts the values of the input array directly in ascending order.
     * @param unsorted an array to sort
     * @param left left boundary index
     * @param right right boundary index
     */
    private static void quickSort(int[] unsorted, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }

        // recursive case
        int pivot = unsorted[right];
        // partition into left and right subgroups using the pivot value
        // and get position (index) of pivot value
        // the pivot value is in its final position
        int pivotIdx = partition(unsorted, left, right, pivot);

        // call itself again to sort the left half
        quickSort(unsorted, left, pivotIdx - 1);
        // call itself again to sort the right half
        quickSort(unsorted, pivotIdx + 1, right);
    }

    /**
     * Partitions the input array into two parts using the pivot value.
     * @param arr an array to partition
     * @param left left boundary index (inclusive)
     * @param right right boundary index (exclusive)
     * @param pivot pivot value (this is not index)
     * @return the index of the pivot value in the end
     */
    private static int partition(int[] arr, int left, int right, int pivot) {
        // make both leftPointer and rightPointer exclusive
        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {
            // scanning to find out-of-place values
            while (leftPointer < right && arr[++leftPointer] <= pivot);
            while (rightPointer > 0 && arr[--rightPointer] > pivot);
            if (leftPointer >= rightPointer) {
                // nothing to swap
                break;
            } else {
                // swap out-of-place values
                swap(arr, leftPointer, rightPointer);
            }
        }
        // put pivot value into the right location (also, its final position)
        swap(arr, leftPointer, right);
        // return index where the pivot value ends up
        return leftPointer;
    }

    /**
     * Helper method to swap two elements in the input array.
     * @param data input array to update
     * @param one one index
     * @param two the other index
     */
    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
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
     * Simple program to test partition and Quick Sort methods.
     * @param args arguments
     */
    public static void main(String[] args) {
        // test partition method
        int[] b = {42, 89, 63, 12, 94, 27, 78, 3, 50, 36};
        int right = b.length - 1;
        int pivot = b[right];
        int pivotIndex = partition(b, 0, right, pivot);
        System.out.println("Partition test result: " + Arrays.toString(b));
        System.out.println(pivotIndex);

        // simple Quick Sort test
        int[] test = {42, 12, 89, 27, 94, 63, 3, 78};
        quickSort(test);
        System.out.println(Arrays.toString(test));

        // more realistic test of Quick Sort
        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = (int) (Math.random() * SIZE);
        }
        quickSort(array);

        // just to make sure sorting works.
        // add "-ea" vm argument
        assert isSorted(array);
    }

}
