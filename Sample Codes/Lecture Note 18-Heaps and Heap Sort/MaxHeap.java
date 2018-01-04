import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 18-Heaps and Heap Sort
 *
 * A very simple and naive MaxHeap implementation
 *
 * @author Terry Lee
 */
public class MaxHeap implements MaxHeapInterface {

    /**
     * An array of Node.
     */
    private Node[] heapArray;
    /**
     * Current size of heap array.
     */
    private int currentSize;

    /**
     * Constructs max heap with initial capacity.
     * precondition : initialCapacity > 0 and reasonably large enough
     * @param initialCapacity initial capacity of heap array
     */
    public MaxHeap(int initialCapacity) {
        heapArray = new Node[initialCapacity];
        currentSize = 0;
    }

    /**
     * Returns the current size of heap array.
     * @return current size
     */
    public int size() {
        return currentSize;
    }

    @Override
    public boolean insert(int key) {
        // array is full
        if (currentSize == heapArray.length) {
            return false;
        }

        // insert into the next available index position
        // to make sure the heap is complete
        heapArray[currentSize] = new Node(key);
        // then restore heap-order property
        percolateUp(currentSize);
        currentSize++;
        return true;
    }

    /**
     * Helper method to percolate up for insert operation.
     * to restore heap-order property of max heap
     * @param index starting index
     */
    private void percolateUp(int index) {
        Node bottom = heapArray[index];
        int parent = (index - 1) / 2;

        // compare with parent and move it down if necessary
        while (index > 0 && heapArray[parent].key < bottom.key) {
            heapArray[index] = heapArray[parent]; // move node down
            index = parent; // move index upward
            parent = (parent - 1) / 2; // parent also moves upward
        }

        // and put bottom node into the right position
        heapArray[index] = bottom;
    }

    @Override
    public int removeMax() {
        if (currentSize == 0) {
            throw new NoSuchElementException("The heap is empty");
        }

        Node root = heapArray[0];
        currentSize--;
        // massive promotion of the last node to the root
        // to make sure the heap is complete
        heapArray[0] = heapArray[currentSize];
        // actually removes the last node
        heapArray[currentSize] = null;
        // and restore heap-order property
        percolateDown(0);
        return root.key;
    }

    /**
     * Helper method to percolate down for remove max operation.
     * to restore the heap-order property of max heap
     * @param index starting index
     */
    private void percolateDown(int index) {
        Node top = heapArray[index];

        // while there is at least left child
        // in other words, about the half of the nodes should be leaves
        while (index < (currentSize / 2)) {
            int leftChild = index * 2 + 1;
            int rightChild = leftChild + 1; // or (index + 1) * 2
            // check rightChild is within the boundary of current size
            // and compare left and right to find larger child
            int largerChild;
            if (rightChild < currentSize && heapArray[leftChild].key < heapArray[rightChild].key) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            // no need to go down any more
            if (heapArray[largerChild].key < top.key) {
                break;
            }
            // largerChild's node moves up
            heapArray[index] = heapArray[largerChild];
            // index goes down toward largerChild
            index = largerChild;
        }

        // finally, insert top node into the right position
        heapArray[index] = top;
    }

    /**
     * Static nested class for Node.
     *
     * Free lunch: No references to left and right children
     * because heap is a complete binary tree
     */
    private static class Node {
        /**
         * Key of node.
         */
        private int key;

        /**
         * Constructs a new node with key.
         * @param k key
         */
        Node(int k) {
            key = k;
        }
    }

    /**
     * A few simple test cases.
     * Building a max heap and use it to sort in descending order (Heap Sort)
     * @param args arguments
     */
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(20);

        // initial removeMax method should throw NoSuchElementException
        //theHeap.removeMax();

        // build a max heap
        maxHeap.insert(24);
        maxHeap.insert(5);
        maxHeap.insert(45);
        maxHeap.insert(10);
        maxHeap.insert(45);
        maxHeap.insert(56);
        maxHeap.insert(17);
        maxHeap.insert(24);
        maxHeap.insert(19);
        maxHeap.insert(20);

        // Now we can use the heap to sort (Heap Sort)
        int[] sorted = new int[maxHeap.size()];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = maxHeap.removeMax();
        }

        System.out.println("Sorted in descending order: " + Arrays.toString(sorted));
    }

}
