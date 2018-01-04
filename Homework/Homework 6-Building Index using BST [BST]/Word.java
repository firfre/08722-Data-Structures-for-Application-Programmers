import java.util.HashSet;
import java.util.Set;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index Using BST
 *
 * Word class (wrapper class for a word)
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class Word implements Comparable<Word> {

    /**
     * Underlying word.
     */
    private String word;
    /**
     * Line numbers of the word.
     */
    private Set<Integer> index;
    /**
     * Frequency of the word.
     */
    private int frequency;

    /**
     * Constructor with parameter.
     * @param w word to wrap
     */
    public Word(String w) {
        word = w;
        index = new HashSet<Integer>();
        frequency = 1;
    }

    /**
     * Accessor of word.
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * Accessor of index.
     * @return defensive copy of index
     */
    public Set<Integer> getIndex() {
        // Make a defensive copy to prevent index being modified from outside of the class
        return new HashSet<Integer>(index);
    }

    /**
     * Accessor of frequency.
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Mutator of word.
     * @param w word to set
     */
    public void setWord(String w) {
        word = w;
    }

    /**
     * Adds the given line number to index.
     * @param lineNum given line number
     */
    public void addToIndex(Integer lineNum) {
        index.add(lineNum);
    }

    /**
     * Mutator of frequency.
     * @param freq frequency to set
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }

    @Override
    public int compareTo(Word o) {
        // Natural alphabetic order
        return word.compareTo(o.word);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(word).append(" ").append(frequency).append(" ").append(index);
        return s.toString();
    }

}
