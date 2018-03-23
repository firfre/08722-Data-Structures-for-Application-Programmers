import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 17-TreeMap and TreeSet
 *
 * Example usages of TreeMap and TreeSet
 *
 * @author Terry Lee
 */
public class Lecture17Driver {

    /**
     * Simple program to demo TreeMap and TreeSet usages.
     * @param args arguments
     */
    public static void main(String[] args) {
        Map<String, Integer> freqOfWords = new HashMap<>();
        String[] words = "coming together is a beginning keeping together is progress working together is success"
                .split(" ");

        for (String word : words) {
            Integer frequency = freqOfWords.get(word);
            if (frequency == null) {
                frequency = 1;
            } else {
                frequency++;
            }
            freqOfWords.put(word, frequency);
        }

        System.out.println("Elements in HashMap: " + freqOfWords);

        // print freqOfWords in ascending order
        TreeMap<String, Integer> sortedWords = new TreeMap<>(freqOfWords);
        System.out.println("Sorted with TreeMap: " + sortedWords);

        // print in descending order
        System.out.println("Sorted with TreeMap in descending order: " + sortedWords.descendingMap());

        // print in descending order (keys only)
        System.out.println("Sorted with TreeMap (keys) in descending order: " + sortedWords.descendingKeySet());

        Set<String> distinctWords = new HashSet<>();
        for (String word : words) {
            distinctWords.add(word);
        }

        System.out.println("There are " + distinctWords.size() + " words in HashSet.");
        System.out.println("And, they are: " + distinctWords);

        // print distinctWords in ascending order
        TreeSet<String> sortedDistinctWords = new TreeSet<>(distinctWords);
        System.out.println("Sorted with TreeSet: " + sortedDistinctWords);
    }

}
