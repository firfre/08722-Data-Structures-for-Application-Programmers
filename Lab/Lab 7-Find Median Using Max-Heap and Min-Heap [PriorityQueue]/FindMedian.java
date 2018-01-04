import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 7-Find Median Using Max-Heap and Min-Heap.
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class FindMedian {

    /**
     * Max heap data structure using PQ.
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20, Collections.reverseOrder());
    /**
     * Min heap data structure using PQ.
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(20);

    /**
     * Adds int values into two heaps.
     * It should maintain a condition that maxHeap.size() >= minHeap.size().
     * @param value int value to add
     */
    public void addNumber(int value) {
        if (maxHeap.size() == minHeap.size()) {
            // First add the number to the max heap
            maxHeap.add(value);
            if (minHeap.size() == 0) {
                return;
            }
        } else {
            // First add the number to the min heap
            minHeap.add(value);
        }

        // In order to maintain that all the numbers in the max heap are smaller than or equal to all the numbers in
        // the min heap, swap the tops if necessary.
        if (maxHeap.peek() > minHeap.peek()) {
            swapTops();
        }
    }

    /**
     * Private helper method to swap the tops between the max heap and the min
     * heap.
     * Note that the top of the max heap is greater than the top of the min heap.
     */
    private void swapTops() {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(maxHeap.poll());
    }

    /**
     * Returns the median value of the added values.
     * If maxHeap and minHeap are of different sizes, then maxHeap must have one extra element.
     * @return median value
     */
    public double getMedian() {
        if (maxHeap.size() == 0) {
            throw new RuntimeException("Not enough numbers to get the median.");
        }

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    /**
     * Test program to add int values and find median of those.
     * @param args arguments
     */
    public static void main(String[] args) {
        FindMedian tester = new FindMedian();
        tester.addNumber(6);
        tester.addNumber(4);
        tester.addNumber(3);
        tester.addNumber(10);
        tester.addNumber(12);

        // should print 6.0
        System.out.println(tester.getMedian());

        tester.addNumber(5);

        // should print 5.5
        System.out.println(tester.getMedian());

        tester.addNumber(7);
        tester.addNumber(8);

        // should print 6.5
        System.out.println(tester.getMedian());
    }

}
