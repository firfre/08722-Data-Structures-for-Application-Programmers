import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 13-Hashing in Java
 *
 * A Simple HashMap demo
 *
 * @author Terry Lee
 */
public class HashMapDemo {

    /**
     * Simple test program using HashMap.
     * @param args arguments
     */
    public static void main(String[] args) {
        // try to the initial capacity of HashMap to be 10 and load factor to be 0.65
        // But, initial capacity will be set to 16 by its design.
        Map<String, Integer> freqOfWords = new HashMap<>(10, 0.65f);

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

        System.out.println(freqOfWords);
        System.out.println();

        // using iterator object instance
        Iterator<String> itr = freqOfWords.keySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println();

        // using enhanced for-loop and keySet method
        for (String word : freqOfWords.keySet()) {
            System.out.println(word);
        }
        System.out.println();

        // using enhanced for-loop and values method
        for (Integer frequency : freqOfWords.values()) {
            System.out.println(frequency);
        }
    }

}
