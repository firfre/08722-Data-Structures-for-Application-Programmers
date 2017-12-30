import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 9-Simple Sorting
 *
 * Simple Card class with Comparable and Comparator
 * @author Terry Lee
 */
public class Lecture9Driver {

    /**
     * Simple test program to sort card objects.
     * @param args arguments
     */
    public static void main(String[] args) {
        Card card1 = new Card("hearts", 1);
        Card card2 = new Card("diamonds", 1);

        System.out.println(card1.compareTo(card2));
        System.out.println(card1.equals(card2));
        System.out.println(card1.hashCode());
        System.out.println(card2.hashCode());
        System.out.println();
        System.out.println("**************");

        List<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card("hearts", 2));
        cards.add(new Card("diamonds", 2));
        cards.add(new Card("spades", 3));
        cards.add(new Card("clubs", 4));
        cards.add(new Card("hearts", 1));

        // uses compareTo method implemented in Card object to compare them
        //Collections.sort(cards);

        // uses compare method implemented in Comparator class
        //Collections.sort(cards, new CompareBySuit());
        Collections.sort(cards, new CompareBySuitRank());

        for (Card card : cards) {
            System.out.println(card);
        }
    }

}
