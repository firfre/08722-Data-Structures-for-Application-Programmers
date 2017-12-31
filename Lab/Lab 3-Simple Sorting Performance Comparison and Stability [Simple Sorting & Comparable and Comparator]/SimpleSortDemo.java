import java.util.Random;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 3-Simple Sorting Performance Comparison and Stability
 *
 * O(n^2): Bubble Sort, Selection Sort, and Insertion Sort
 *
 * @author Terry Lee
 */
public class SimpleSortDemo {

    /**
     * Test program to compare simple sorting algorithms.
     * @param args arguments
     */
    public static void main(String[] args) {
        int size = 50000;
        int[] a = new int[size];
        int[] b = new int[size];
        int[] c = new int[size];

        Random rand = new Random();
        // Case 1
        //for (int i = 0; i < size; i++) a[i] = rand.nextInt();

        // Case 2
        for (int i = 0; i < size; i++) a[i] = i;

        System.arraycopy(a, 0, b, 0, a.length);
        System.arraycopy(a, 0, c, 0, a.length);

        Stopwatch timer1 = new Stopwatch();
        bubbleSort(a);
        System.out.println("bubble sort: " + timer1.elapsedTime() + " millisec");
        // to make sure sorting works
        assert isSorted(a);

        Stopwatch timer2 = new Stopwatch();
        selectionSort(b);
        System.out.println("selection sort: " + timer2.elapsedTime() + " millisec");
        // to make sure sorting works
        assert isSorted(b);

        Stopwatch timer3 = new Stopwatch();
        insertionSort(c);
        System.out.println("insertion sort: " + timer3.elapsedTime() + " millisec");
        // to make sure sorting works
        assert isSorted(c);
    }

    /**
     * Bubble sort runs in O(n^2) simple but slow.
     * Focus on the largest value!
     * @param data an array of int to sort
     */
    public static void bubbleSort(int[] data) {
        // move backward from the last index
        for (int out = data.length - 1; out >= 1; out--) {
            // move forward from the beginning
            // bubble up the largest value to the right
            for (int in = 0; in < out; in++) {
                if (data[in] > data[in + 1]) {
                    swap(data, in, in + 1);
                }
            }
        }
    }

    /**
     * Helper method to swap two values in an array.
     * @param data an array to update
     * @param one index of one value
     * @param two index of the other value
     */
    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
    }

    /**
     * Selection sort runs in O(n^2) too.
     * Focus on the smallest value!
     * Faster than bubble sort mainly due to less number of swaps.
     * @param data an array of int to sort
     */
    public static void selectionSort(int[] data) {
        for (int out = 0; out < data.length - 1; out++) {
            // find the smallest value and put into the left index
            int minIdx = out;
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[minIdx]) {
                    minIdx = in;
                }
            }
            // swap is outside of the inner loop
            if (out != minIdx) {
                swap(data, out, minIdx);
            }
        }
    }

    /**
     * Insertion sort runs in O(n^2) in the worst-case.
     * But its best case running time complexity is O(n).
     * It is the fastest among the three but sensitive to the input values.
     *
     * less number of comparisons on average.
     * uses shifting (copying) instead of swapping (one swap equals to three copies).
     * @param data an array of int to sort
     */
    public static void insertionSort(int[] data) {
        // start from the 1st index till the last index
        for (int out = 1; out < data.length; out++) {
            int tmp = data[out]; // store the value temporarily
            int in = out; // initially set to be the same as out

            /*
             * loop to check the sorted section going backward but
             * not necessarily all the way to the 0th
             * On average, look halfway through the sorted section
             */
            while (in > 0 && data[in - 1] >= tmp) {
                // shift one by one
                //data[in] = data[in - 1];
                in--;
            }

            // if out is not the same as in variable, shift using arraycopy.
            if (out != in) {
                System.arraycopy(data, in, data, in + 1, out - in);
                // finally INSERT the tmp value into the correct index
                data[in] = tmp;
            }
        }
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

}
