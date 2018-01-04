import java.util.Comparator;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index Using BST
 *
 * Comparator comparing Word objects by alphabetic order first and then
 * ascending frequencies.
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class AlphaFreq implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        int alphaResult = o1.getWord().compareTo(o2.getWord());
        if (alphaResult != 0) {
            return alphaResult;
        }
        return Integer.compare(o1.getFrequency(), o2.getFrequency());
    }

}
