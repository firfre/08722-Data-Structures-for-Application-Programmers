/**
 * 08722 Data Structures for Application Programmers.
 * Lecture 10-Recursion
 *
 * Examples used in lecture
 * @author Terry Lee
 */
public class Lecture10Driver {

    /**
     * Sums from 1 to n iteratively.
     * precondition: n is a positive integer
     * @param n positive n value
     * @return sum from 1 to n
     */
    public static int sum(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = result + i;
        }
        return result;
    }

    /**
     * Sums from 1 to n recursively.
     * precondition: n is a positive integer
     *
     * recSum(5)                               15
     *    5+recSum(4)                     10+5=15
     *      4+recSum(3)               6+4=10
     *        3+recSum(2)         3+3=6
     *          2+recSum(1) = 1+2=3
     *
     * There is a lot of bookkeeping going on
     * @param n positive n value
     * @return sum from 1 to n
     */
    public static int recSum(int n) {
        if (n == 1) {
            return n;
        }
        return n + recSum(n - 1);
    }

    /**
     * Finds fib of n value.
     * precondition: n is 0 or a positive integer
     * @param n positive value or 0
     * @return fib of n
     */
    public static long fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * A simple binary search in an array that is not null (iterative version).
     * @param data int array to search against
     * @param key int key value to search for
     * @return index value if found, -1 if not found
     */
    public static int binarySearch(int[] data, int key) {
        int lowerBound = 0;
        int upperBound = data.length - 1;

        while (true) {
            // not found
            if (lowerBound > upperBound) {
                return -1;
            }

            int mid = lowerBound + (upperBound - lowerBound) / 2;
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
     * A simple binary search in an array that is not null.
     * @param data int array to search against
     * @param key int key value to search for
     * @return index value if found, -1 if not found
     */
    public static int find(int[] data, int key) {
        // initial call of helper find method
        return find(data, key, 0, data.length - 1);
    }

    /**
     * Private helper method uses recursion for binary search.
     * @param data int array to search against
     * @param key int key value to search for
     * @param lowerBound lowerbound index of the array
     * @param upperBound upperbound index of the array
     * @return index value if found, -1 if not found
     */
    private static int find(int[] data, int key, int lowerBound, int upperBound) {
        // base case 1: not found
        if (lowerBound > upperBound) {
            return -1;
        }

        int mid = lowerBound + (upperBound - lowerBound) / 2;
        // base case 2: found
        if (data[mid] == key) {
            return mid;
        }

        // recursive cases
        if (data[mid] < key) {
            // go to right
            return find(data, key, mid + 1, upperBound);
        } else {
            // go to left
            return find(data, key, lowerBound, mid - 1);
        }
    }

    /**
     * Simple test program to check methods.
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.println("iterative sum: " + sum(5));
        System.out.println("recursive sum: " + recSum(5));
        System.out.println("recursive fib: " + fib(2));
        System.out.println("recursive fib: " + fib(50));

        int[] data = {1, 2, 3, 4, 5};
        System.out.println("iterative binary search:" + binarySearch(data, 2));
        System.out.println("recursive binary search:" + find(data, 2));
    }

}
