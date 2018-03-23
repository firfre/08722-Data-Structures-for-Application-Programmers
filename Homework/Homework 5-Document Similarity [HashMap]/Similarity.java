import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 5-Document Similarity
 *
 * Similarity application
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class Similarity {

    /**
     * Frequency of words.
     */
    private Map<String, BigInteger> freqOfWords;
    /**
     * Number of words.
     */
    private BigInteger nWord;
    /**
     * Number of lines.
     */
    private int nLine;
    /**
     * Euclidean norm of this frequency vector.
     */
    private double euclidean;

    /**
     * Constructor with parameter.
     * @param string string to analyze
     */
    public Similarity(String string) {
        // Check whether the input string is null or empty
        if ((string == null) || (string.length() == 0)) {
            freqOfWords = new HashMap<>();
            nWord = BigInteger.ZERO;
            nLine = 0;
            euclidean = 0.0;
            return;
        }

        freqOfWords = new HashMap<>();
        nWord = BigInteger.ZERO;
        nLine = 0;

        processLine(string);

        euclidean = calcEuclidean(freqOfWords);
    }

    /**
     * Private helper method to process one line.
     * @param line line to process
     */
    private void processLine(String line) {
        String[] texts = line.split("\\W");
        for (String text : texts) {
            if (text.matches("^[A-Za-z]+$")) {
                String word = text.toLowerCase();
                BigInteger freq = freqOfWords.getOrDefault(word, BigInteger.ZERO);
                freq = freq.add(BigInteger.ONE);
                freqOfWords.put(word, freq);

                nWord = nWord.add(BigInteger.ONE);
            }
        }
        ++nLine;
    }

    /**
     * Calculates the Euclidean norm of the given frequency vector.
     * @param freqOfMap given frequency vector
     * @return Euclidean norm
     */
    private double calcEuclidean(Map<String, BigInteger> freqOfMap) {
        BigInteger squareSum = BigInteger.ZERO;
        for (BigInteger freq : freqOfMap.values()) {
            squareSum = squareSum.add(freq.multiply(freq));
        }
        return Math.sqrt(squareSum.doubleValue());
    }

    /**
     * Constructor with parameter.
     * @param file file to analyze
     */
    public Similarity(File file) {
        // Check whether the input file is null
        if (file == null) {
            freqOfWords = new HashMap<>();
            nWord = BigInteger.ZERO;
            nLine = 0;
            euclidean = 0.0;
            return;
        }

        freqOfWords = new HashMap<>();
        nWord = BigInteger.ZERO;
        nLine = 0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        euclidean = calcEuclidean(freqOfWords);
    }

    /**
     * Accessor of freqOfWords.
     * @return defensive copy of freqOfWords
     */
    public Map<String, BigInteger> getMap() {
        // Make a defensive copy to prevent the internal map being modified from outside of the class
        return new HashMap<String, BigInteger>(freqOfWords);
    }

    /**
     * Accessor of nWord.
     * @return nWord
     */
    public BigInteger numOfWords() {
        return nWord;
    }

    /**
     * Returns the number of words without duplicates.
     * @return number of distinct words
     */
    public int numOfWordsNoDups() {
        return freqOfWords.size();
    }

    /**
     * Accessor of euclidean.
     * @return euclidean
     */
    public double euclideanNorm() {
        return euclidean;
    }

    /**
     * Accessor of nLine.
     * @return nLine
     */
    public int numOfLines() {
        return nLine;
    }

    /**
     * Calculates the dot product between this and the given frequency vectors.
     * @param map given frequency vector
     * @return dot product
     */
    public double dotProduct(Map<String, BigInteger> map) {
        // Check whether the input map is null or empty
        if ((map == null) || (map.isEmpty())) {
            return 0;
        }

        if (freqOfWords.isEmpty()) {
            return 0;
        }

        BigInteger dotProd = BigInteger.ZERO;
        for (Map.Entry<String, BigInteger> entry : map.entrySet()) {
            String word = entry.getKey();
            if (freqOfWords.containsKey(word)) {
                dotProd = dotProd.add(entry.getValue().multiply(freqOfWords.get(word)));
            }
        }
        return dotProd.doubleValue();
    }

    /**
     * Calculates the distance between this and the given frequency vectors.
     * @param map given frequency vector
     * @return cosine distance
     */
    public double distance(Map<String, BigInteger> map) {
        // Check whether the input map is null or empty
        if ((map == null) || (map.isEmpty())) {
            return Math.PI / 2;
        }

        if (freqOfWords.isEmpty()) {
            return Math.PI / 2;
        }

        if (isIdenticalFreqVec(map)) {
            return 0.0;
        }

        double dotProd = dotProduct(map);
        return Math.acos(dotProd / (euclidean * calcEuclidean(map)));
    }

    /**
     * Private helper method to determine whether this and the given frequency
     * vectors are identical.
     * @param map given frequency vector
     * @return whether the two frequency vectors are identical
     */
    private boolean isIdenticalFreqVec(Map<String, BigInteger> map) {
        if (freqOfWords.size() != map.size()) {
            return false;
        }

        for (Map.Entry<String, BigInteger> entry : freqOfWords.entrySet()) {
            String word = entry.getKey();
            BigInteger freq = entry.getValue();
            if (!map.containsKey(word) || (!map.get(word).equals(freq))) {
                return false;
            }
        }
        return true;
    }

}
