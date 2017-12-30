/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 4-ArrayList and Binary Search
 *
 * Binary Search implementation
 *
 * @author Terry Lee
 */
public class BinarySearch {

    /**
     * A simple binary search in an array that is not null.
     * @param data int array to search
     * @param key int key value to search for
     * @return index of the key value if found, -1 if not found
     */
    public static int binarySearch(int[] data, int key) {
        int lowerBound = 0;
        int upperBound = data.length - 1;
        int mid;

        while (true) {
            // not found
            if (lowerBound > upperBound) {
                return -1;
            }

            mid = lowerBound + (upperBound - lowerBound) / 2;
            // found
            if (data[mid] == key) {
                return mid;
            }

            if (data[mid] < key) {
                // go to right
                lowerBound = mid + 1;
            } else {
                // go to left
                upperBound = mid - 1;
            }
        }
    }

    /**
     * Simple test program to run binary search algorithm.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(data, 6));
    }

}
