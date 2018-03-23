import java.util.ArrayList;
import java.util.List;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 1-ArrayList Time Comparison and String Manipulation
 *
 * @author Terry Lee
 */
public class ArrayListDemo {

    /**
     * Compare running times of addition and removals on ArrayList.
     * @param args arguments
     */
    public static void main(String[] args) {
        int size = 100000;
        List<Integer> numbers = new ArrayList<>();

        // running time of adding values to the end
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }

        System.out.println(
                "running time for adding " + size + " items into arraylist: " + timer1.elapsedTime() + " millisec");

        // running time of removal
        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < size; i++) {
             //numbers.remove(0);
             //numbers.remove(numbers.size() / 2);
             numbers.remove(numbers.size() - 1);
        }

        System.out.println(
                "running time for removing " + size + " items from arraylist: " + timer2.elapsedTime() + " millisec");
    }

}
