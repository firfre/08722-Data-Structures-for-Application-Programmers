/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 18-Heaps and Heap Sort
 *
 * A very simple MaxHeap Interface (insert and removeMax)
 *
 * You can add bells and whistles on your own
 *
 * @author Terry Lee
 */
public interface MaxHeapInterface {

    /**
     * Inserts a new key into a heap.
     * @param key key to insert
     * @return boolean to check whether it is successfully inserted or not
     */
    boolean insert(int key);

    /**
     * Removes the highest priority key value (maximum key for max heap).
     * @return removed key
     * @throws NoSuchElementExcpetion when there is nothing to remove (empty heap)
     */
    int removeMax();

}
