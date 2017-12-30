import java.util.List;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 2-Josephus Problem
 *
 * Solve Josephus problem with different data structures and different
 * algorithms and compare running times
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size number of people in the circle that is bigger than 0
     * @param rotation elimination order in the circle. The value has to be greater than 0
     * @return the position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // Check whether the input size is positive
        if (size <= 0) {
            throw new RuntimeException("The input size must be greater than 0.");
        }
        // Check whether the input rotation is positive
        if (rotation <= 0) {
            throw new RuntimeException("The input rotation must be greater than 0.");
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 1; i <= size; ++i) {
            queue.offer(i);
        }

        while (queue.size() > 1) {
            int actualRotation = rotation % queue.size();
            if (actualRotation == 0) {
                actualRotation = queue.size();
            }
            for (int i = 0; i < (actualRotation - 1); ++i) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size number of people in the circle that is bigger than 0
     * @param rotation elimination order in the circle. The value has to be greater than 0
     * @return the position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // Check whether the input size is positive
        if (size <= 0) {
            throw new RuntimeException("The input size must be greater than 0.");
        }
        // Check whether the input rotation is positive
        if (rotation <= 0) {
            throw new RuntimeException("The input rotation must be greater than 0.");
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= size; ++i) {
            queue.offer(i);
        }

        while (queue.size() > 1) {
            int actualRotation = rotation % queue.size();
            if (actualRotation == 0) {
                actualRotation = queue.size();
            }
            for (int i = 0; i < (actualRotation - 1); ++i) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size number of people in the circle that is bigger than 0
     * @param rotation elimination order in the circle. The value has to be greater than 0
     * @return the position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // Check whether the input size is positive
        if (size <= 0) {
            throw new RuntimeException("The input size must be greater than 0.");
        }
        // Check whether the input rotation is positive
        if (rotation <= 0) {
            throw new RuntimeException("The input rotation must be greater than 0.");
        }

        List<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= size; ++i) {
            list.add(i);
        }

        int eliminateIdx = -1;
        while (list.size() > 1) {
            int actualRotation = rotation % list.size();
            if (actualRotation == 0) {
                actualRotation = list.size();
            }
            eliminateIdx = (eliminateIdx + actualRotation) % list.size();
            list.remove(eliminateIdx);
            --eliminateIdx;
        }
        return list.get(0);
    }

}
