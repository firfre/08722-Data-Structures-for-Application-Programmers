import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index using BST
 *
 * Index class
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class Index {

    /**
     * Builds an index tree from the given file.
     * @param fileName given filename
     * @return built index tree
     */
    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null);
    }

    /**
     * Builds an index tree from the given file using the given comparator.
     * @param fileName given filename
     * @param comparator comparator used to build the tree
     * @return built index tree
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> bst = new BST<Word>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                ++lineNum;
                String line = scanner.nextLine();
                String[] texts = line.split("\\W");
                for (String text : texts) {
                    if (text.matches("^[A-Za-z]+$")) {
                        String word = text;
                        // If IgnoreCase comparator is passed into buildIndex() method, then all of the words need to
                        // be converted into lowercase and then added to the BST
                        if (comparator instanceof IgnoreCase) {
                            word = word.toLowerCase();
                        }
                        Word wordObj = bst.search(new Word(word));
                        if (wordObj == null) {
                            Word newWord = new Word(word);
                            newWord.addToIndex(lineNum);
                            bst.insert(newWord);
                        } else {
                            wordObj.addToIndex(lineNum);
                            wordObj.setFrequency(wordObj.getFrequency() + 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return bst;
    }

    /**
     * Builds an index from the given Word list.
     * @param list given Word list
     * @param comparator comparator used to build the tree
     * @return built index tree
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        // Check whether the input Word list is null
        if (list == null) {
            return new BST<Word>();
        }

        BST<Word> bst = new BST<Word>(comparator);

        for (Word wordObj : list) {
            bst.insert(wordObj);
        }

        return bst;
    }

    /**
     * Sorts the Word objects in the given index tree using AlphaFreq comparator.
     * @param tree given index tree
     * @return sorted Word list
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        // Check whether the input BST is null
        if (tree == null) {
            return new ArrayList<Word>();
        }

        /*
         * Even though there should be no ties with regard to words in BST, in
         * the spirit of using what you wrote, use AlphaFreq comparator in this
         * method.
         */
        ArrayList<Word> wordList = new ArrayList<Word>();

        Iterator<Word> it = tree.iterator();
        while (it.hasNext()) {
            wordList.add(it.next());
        }
        wordList.sort(new AlphaFreq());

        return wordList;
    }

    /**
     * Sort the Word objects in the given index tree using Frequency comparator.
     * @param tree given index tree
     * @return sorted Word list
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        // Check whether the input BST is null
        if (tree == null) {
            return new ArrayList<Word>();
        }

        ArrayList<Word> wordList = new ArrayList<Word>();

        Iterator<Word> it = tree.iterator();
        while (it.hasNext()) {
            wordList.add(it.next());
        }
        wordList.sort(new Frequency());

        return wordList;
    }

    /**
     * Gets the Word objects in the given index tree with the highest frequency.
     * @param tree given index tree
     * @return Word list with highest frequency
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        // Check whether the input BST is null
        if (tree == null) {
            return new ArrayList<Word>();
        }

        ArrayList<Word> sortedByFreq = sortByFrequency(tree);
        int highestFreq = sortedByFreq.get(0).getFrequency();
        ArrayList<Word> highestFreqWords = new ArrayList<Word>();
        for (Word wordObj : sortedByFreq) {
            if (wordObj.getFrequency() == highestFreq) {
                highestFreqWords.add(wordObj);
            } else {
                break;
            }
        }
        return highestFreqWords;
    }

}
