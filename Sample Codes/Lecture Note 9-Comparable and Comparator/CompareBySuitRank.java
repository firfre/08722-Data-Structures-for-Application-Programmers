import java.util.Comparator;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 9-Simple Sorting
 *
 * Simple Card class with Comparable and Comparator
 * @author Terry Lee
 */
public class CompareBySuitRank implements Comparator<Card> {

    @Override
    public int compare(Card x, Card y) {
        int suitResult = x.getSuit().compareTo(y.getSuit());
        if (suitResult != 0) {
            return suitResult;
        }
        return Integer.compare(x.getRank(), y.getRank());
    }

}
