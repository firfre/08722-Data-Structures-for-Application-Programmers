import java.io.File;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 5-Document Similarity
 *
 * Document similarity acceptance test (case 3)
 *
 * DO NOT MODIFY THIS CLASS!
 * @author Terry Lee
 */
public class SimilarityTest3 {

    /**
     * Test program to check Similarity with files of large size.
     * @param args arguments
     */
    public static void main(String[] args) {
        Similarity file1 = new Similarity(new File("warpeace.txt"));
        printOutput(file1);

        Similarity file2 = new Similarity(new File("clarissa.txt"));
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
 * 66054 lines.
 * 586422 words.
 * 17599 distinct words.
 * 56784.08509432903 Euclidean norm.
 *
 * 110046 lines.
 * 1004626 words.
 * 18873 distinct words.
 * 92589.79966497389 Euclidean norm.
 *
 * 4.532017232E9 dot product.
 * 0.5316158030425547 distance.
 */
