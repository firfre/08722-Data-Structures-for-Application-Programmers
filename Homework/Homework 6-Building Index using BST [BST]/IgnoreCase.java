import java.util.Comparator;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index Using BST
 *
 * Comparator comparing Word objects by ignoring cases
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class IgnoreCase implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
    }

}
