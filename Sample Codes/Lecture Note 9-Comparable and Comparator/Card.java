/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 9-Simple Sorting
 *
 * Simple Card class with Comparable implementation
 * Also, includes examples of using Commons Lang and Guava Libraries
 *
 * @author Terry Lee
 */
public class Card implements Comparable<Card> {
    /**
     * Suit field.
     */
    private String suit;
    /**
     * Rank field.
     */
    private int rank;

    /**
     * Constructs a card with suit and rank.
     * @param suit suit of a card
     * @param rank rank of a card
     */
    public Card(String s, int r) {
        suit = s;
        rank = r;
    }

    /**
     * Returns suit of a card.
     * @return returns string value of suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns rank of a card.
     * @return returns rank of a card
     */
    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank, other.rank);
    }

    @Override
    public String toString() {
        return suit + ", " + rank;
    }

}
