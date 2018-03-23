import java.util.HashSet;
import java.util.Set;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 13-Hashing in Java
 *
 * A Simple HashSet demo
 *
 * @author Terry Lee
 */
public class HashSetDemo {

    /**
     * Simple program to demo HashSet.
     * @param args arguments
     */
    public static void main(String[] args) {
        Set<String> distinctWords = new HashSet<>();
        String[] words = "coming together is a beginning keeping together is progress working together is success"
                .split(" ");

        for (String word : words) {
            distinctWords.add(word);
        }

        System.out.println("There are " + distinctWords.size() + " distinct words.");
        System.out.println("They are " + distinctWords);
        System.out.println();

        // using enhanced for-loop
        for (String word : distinctWords) {
            System.out.println(word);
        }
    }

}
