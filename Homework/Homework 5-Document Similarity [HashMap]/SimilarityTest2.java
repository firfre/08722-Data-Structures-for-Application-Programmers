import java.io.File;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 5-Document Similarity
 *
 * Document similarity acceptance test (case 2)
 *
 * DO NOT MODIFY THIS CLASS!
 * @author Terry Lee
 */
public class SimilarityTest2 {

    /**
     * Test program to check Similarity with files.
     * @param args arguments
     */
    public static void main(String[] args) {
        Similarity file1 = new Similarity(new File("greatexpectations.txt"));
        printOutput(file1);

        Similarity file2 = new Similarity(new File("twocities.txt"));
        printOutput(file2);

        System.out.println(file1.dotProduct(file2.getMap()) + " dot product.");
        System.out.println(file1.distance(file2.getMap()) + " distance.");
    }

    /**
     * Prints some values of Similarity object.
     * @param sim Similarity object to deal with
     */
    private static void printOutput(Similarity sim) {
        System.out.println(sim.numOfLines() + " lines.");    
        System.out.println(sim.numOfWords() + " words.");
        System.out.println(sim.numOfWordsNoDups() + " distinct words.");
        System.out.println(sim.euclideanNorm() + " Euclidean norm.\n");
    }
}

/*
 * <EXPECTED OUTPUT>
 *
 * 20422 lines.
 * 192013 words.
 * 10987 distinct words.
 * 18438.499098353965 Euclidean norm.
 *
 * 16272 lines.
 * 141394 words.
 * 9943 distinct words.
 * 13829.997830802433 Euclidean norm.
 *
 * 2.41897875E8 dot product.
 * 0.3220052034996809 distance.
 */
