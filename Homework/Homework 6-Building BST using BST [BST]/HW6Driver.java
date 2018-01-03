import java.io.IOException;

/**
 * 08-722 Data Structures for Application Programmers.
 * Homework 6-Building Index using BST
 *
 * DO NOT MODIFY THIS CLASS!
 * ALL OF YOUR WORK SHOULD BE DONE IN OTHER CLASSES!
 * @author Terry Lee
 */
public class HW6Driver {

    /**
     * Test program to test building index using BST.
     * @param args arguments
     * @throws IOException IOException might be thrown
     */
    public static void main(String[] args) throws IOException {
        Index index = new Index();
        String inputFile = "src/test.txt";

        System.out.println("********BUILD index tree: case sensitive************\n");
        BST<Word> tree1 = index.buildIndex(inputFile);
        System.out.println("the height is " + tree1.getHeight());
        System.out.println("the number of nodes is " + tree1.getNumberOfNodes());
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("tree sorted by alpha");
        System.out.println(index.sortByAlpha(tree1));
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("tree sorted by frequency");
        System.out.println(index.sortByFrequency(tree1));
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("all words of the highest frequency");
        System.out.println(index.getHighestFrequency(tree1));
        System.out.println();
        System.out.println("***********************************************************");
        System.out.println();

        System.out.println("*********BUILD index tree; all lowercases************\n");
        BST<Word> tree2 = index.buildIndex(inputFile, new IgnoreCase());
        System.out.println("the height is " + tree2.getHeight());
        System.out.println("the number of nodes is " + tree2.getNumberOfNodes());
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("tree sorted by alpha");
        System.out.println(index.sortByAlpha(tree2));
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("tree sorted by frequency");
        System.out.println(index.sortByFrequency(tree2));
        System.out.println();

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("all words of the highest frequency");
        System.out.println(index.getHighestFrequency(tree2));
        System.out.println();
        System.out.println("***********************************************************");
        System.out.println();

        System.out.println("****RE-BUILD tree; alphabetically first and frequencies****\n");
        BST<Word> tree3 = index.buildIndex(index.sortByFrequency(tree1), new AlphaFreq());
        System.out.println("root: " + tree3.getRoot());

        // Note: Invoking this method should not modify the tree structure.
        System.out.println("tree sorted by frequency");
        System.out.println(index.sortByFrequency(tree3));
        System.out.println();
        System.out.println("***********************************************************");
    }
}

/* <Expected results with test.txt> Note: while building, insert words in the same order as they appear in a file.

********BUILD index tree: case sensitive************

the height is 11
the number of nodes is 69

tree sorted by alpha
[A 2 [8], Bible 3 [1, 4, 7], Children 1 [1], English 1 [1], It 1 [6], New 1 [2], Old 1 [2], Testament 1 [2], The 2 [1, 4], These 1 [2], a 9 [1, 3, 8], and 4 [2, 4, 5, 8], are 1 [6], as 1 [3], be 1 [5], been 1 [3], both 1 [2], but 1 [4], by 1 [5], child 2 [5, 7], children 1 [5], easily 1 [5], five 1 [3], for 1 [6], from 1 [2], gradually 1 [7], have 1 [3], he 1 [7], idioms 1 [8], in 3 [1, 4, 6], is 3 [4, 6, 7], it 1 [5], itself 1 [4], language 1 [4], learn 1 [7], made 1 [3], may 1 [5], meaning 1 [7], more 1 [3], new 1 [7], not 1 [6], observation 1 [3], of 7 [1, 3, 4, 6, 7], older 1 [6], one 1 [6], provides 1 [1], read 1 [5], reading 1 [7], result 1 [3], s 1 [1], selections 2 [2], should 1 [7], simple 1 [1], so 1 [5], study 1 [4], syllable 1 [6], text 1 [4], than 1 [3], that 2 [4, 5], the 9 [2, 4, 5, 6, 7], those 1 [6], to 1 [5], translation 1 [1], twenty 1 [3], while 1 [6], who 1 [6], words 2 [6, 8], years 1 [3], younger 1 [5]]

tree sorted by frequency
[a 9 [1, 3, 8], the 9 [2, 4, 5, 6, 7], of 7 [1, 3, 4, 6, 7], and 4 [2, 4, 5, 8], Bible 3 [1, 4, 7], in 3 [1, 4, 6], is 3 [4, 6, 7], A 2 [8], The 2 [1, 4], child 2 [5, 7], selections 2 [2], that 2 [4, 5], words 2 [6, 8], Children 1 [1], English 1 [1], It 1 [6], New 1 [2], Old 1 [2], Testament 1 [2], These 1 [2], are 1 [6], as 1 [3], be 1 [5], been 1 [3], both 1 [2], but 1 [4], by 1 [5], children 1 [5], easily 1 [5], five 1 [3], for 1 [6], from 1 [2], gradually 1 [7], have 1 [3], he 1 [7], idioms 1 [8], it 1 [5], itself 1 [4], language 1 [4], learn 1 [7], made 1 [3], may 1 [5], meaning 1 [7], more 1 [3], new 1 [7], not 1 [6], observation 1 [3], older 1 [6], one 1 [6], provides 1 [1], read 1 [5], reading 1 [7], result 1 [3], s 1 [1], should 1 [7], simple 1 [1], so 1 [5], study 1 [4], syllable 1 [6], text 1 [4], than 1 [3], those 1 [6], to 1 [5], translation 1 [1], twenty 1 [3], while 1 [6], who 1 [6], years 1 [3], younger 1 [5]]

all words of the highest frequency
[a 9 [1, 3, 8], the 9 [2, 4, 5, 6, 7]]

***********************************************************

*********BUILD index tree; all lowercases************

the height is 11
the number of nodes is 64

tree sorted by alpha
[a 11 [1, 3, 8], and 4 [2, 4, 5, 8], are 1 [6], as 1 [3], be 1 [5], been 1 [3], bible 3 [1, 4, 7], both 1 [2], but 1 [4], by 1 [5], child 2 [5, 7], children 2 [1, 5], easily 1 [5], english 1 [1], five 1 [3], for 1 [6], from 1 [2], gradually 1 [7], have 1 [3], he 1 [7], idioms 1 [8], in 3 [1, 4, 6], is 3 [4, 6, 7], it 2 [5, 6], itself 1 [4], language 1 [4], learn 1 [7], made 1 [3], may 1 [5], meaning 1 [7], more 1 [3], new 2 [2, 7], not 1 [6], observation 1 [3], of 7 [1, 3, 4, 6, 7], old 1 [2], older 1 [6], one 1 [6], provides 1 [1], read 1 [5], reading 1 [7], result 1 [3], s 1 [1], selections 2 [2], should 1 [7], simple 1 [1], so 1 [5], study 1 [4], syllable 1 [6], testament 1 [2], text 1 [4], than 1 [3], that 2 [4, 5], the 11 [1, 2, 4, 5, 6, 7], these 1 [2], those 1 [6], to 1 [5], translation 1 [1], twenty 1 [3], while 1 [6], who 1 [6], words 2 [6, 8], years 1 [3], younger 1 [5]]

tree sorted by frequency
[a 11 [1, 3, 8], the 11 [1, 2, 4, 5, 6, 7], of 7 [1, 3, 4, 6, 7], and 4 [2, 4, 5, 8], bible 3 [1, 4, 7], in 3 [1, 4, 6], is 3 [4, 6, 7], child 2 [5, 7], children 2 [1, 5], it 2 [5, 6], new 2 [2, 7], selections 2 [2], that 2 [4, 5], words 2 [6, 8], are 1 [6], as 1 [3], be 1 [5], been 1 [3], both 1 [2], but 1 [4], by 1 [5], easily 1 [5], english 1 [1], five 1 [3], for 1 [6], from 1 [2], gradually 1 [7], have 1 [3], he 1 [7], idioms 1 [8], itself 1 [4], language 1 [4], learn 1 [7], made 1 [3], may 1 [5], meaning 1 [7], more 1 [3], not 1 [6], observation 1 [3], old 1 [2], older 1 [6], one 1 [6], provides 1 [1], read 1 [5], reading 1 [7], result 1 [3], s 1 [1], should 1 [7], simple 1 [1], so 1 [5], study 1 [4], syllable 1 [6], testament 1 [2], text 1 [4], than 1 [3], these 1 [2], those 1 [6], to 1 [5], translation 1 [1], twenty 1 [3], while 1 [6], who 1 [6], years 1 [3], younger 1 [5]]

all words of the highest frequency
[a 11 [1, 3, 8], the 11 [1, 2, 4, 5, 6, 7]]

***********************************************************

****RE-BUILD tree; alphabetically first and frequencies****

root: a 9 [1, 3, 8]
tree sorted by frequency
[a 9 [1, 3, 8], the 9 [2, 4, 5, 6, 7], of 7 [1, 3, 4, 6, 7], and 4 [2, 4, 5, 8], Bible 3 [1, 4, 7], in 3 [1, 4, 6], is 3 [4, 6, 7], A 2 [8], The 2 [1, 4], child 2 [5, 7], selections 2 [2], that 2 [4, 5], words 2 [6, 8], Children 1 [1], English 1 [1], It 1 [6], New 1 [2], Old 1 [2], Testament 1 [2], These 1 [2], are 1 [6], as 1 [3], be 1 [5], been 1 [3], both 1 [2], but 1 [4], by 1 [5], children 1 [5], easily 1 [5], five 1 [3], for 1 [6], from 1 [2], gradually 1 [7], have 1 [3], he 1 [7], idioms 1 [8], it 1 [5], itself 1 [4], language 1 [4], learn 1 [7], made 1 [3], may 1 [5], meaning 1 [7], more 1 [3], new 1 [7], not 1 [6], observation 1 [3], older 1 [6], one 1 [6], provides 1 [1], read 1 [5], reading 1 [7], result 1 [3], s 1 [1], should 1 [7], simple 1 [1], so 1 [5], study 1 [4], syllable 1 [6], text 1 [4], than 1 [3], those 1 [6], to 1 [5], translation 1 [1], twenty 1 [3], while 1 [6], who 1 [6], years 1 [3], younger 1 [5]]

***********************************************************
*/
