/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 5-Document Similarity
 *
 * Document similarity acceptance test (case 1)
 *
 * No need to worry about number of lines for this case.
 *
 * DO NOT MODIFY THIS CLASS!
 * @author Terry Lee
 */
public class SimilarityTest1 {

    /**
     * Test program to check Similarity.
     * @param args arguments
     */
    public static void main(String[] args) {
        Similarity text1 = new Similarity("hello there nice to meet you you look nice");
        printOutput(text1);

        Similarity text2 = new Similarity("time to say hello nice seeing you should meet again");
        printOutput(text2);

        System.out.println(text1.dotProduct(text2.getMap()) + " dot product.");
        System.out.println(text1.distance(text2.getMap()) + " distance.");
    }

    /**
     * Prints some values of Similarity object.
     * @param sim Similarity object to deal with
     */
    private static void printOutput(Similarity sim) {
        System.out.println(sim.numOfWords() + " words.");
        System.out.println(sim.numOfWordsNoDups() + " distinct words.");
        System.out.println(sim.euclideanNorm() + " Euclidean norm.\n");
    }
}

/*
 * <EXPECTED OUTPUT>
 *
 * 9 words.
 * 7 distinct words.
 * 3.605551275463989 Euclidean norm.
 *
 * 10 words.
 * 10 distinct words.
 * 3.1622776601683795 Euclidean norm.
 *
 * 7.0 dot product.
 * 0.9097531579442097 distance.
 */
