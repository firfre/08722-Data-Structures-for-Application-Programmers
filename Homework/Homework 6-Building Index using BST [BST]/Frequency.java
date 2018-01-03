import java.util.Comparator;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index using BST
 *
 * Comparator comparing Word objects by descending frequencies.
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class Frequency implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }

}
