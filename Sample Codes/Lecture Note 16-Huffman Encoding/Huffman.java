import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman encoding implementation.
 *
 * @author Ziang Lu
 */
public class Huffman {

    /**
     * Root of the Huffman tree.
     */
    private TreeNode huffmanTreeRoot;
    /**
     * 
     */
    private HashMap<Character, String> encodingMap;

    /**
     * Constructor with parameter.
     * @param s string to build the Huffman tree
     */
    public Huffman(String s) {
        constructHuffmanTree(s);
    }

    /**
     * Constructs the Huffman tree using the given string.
     * @param s string to build the Huffman tree
     */
    public void constructHuffmanTree(String s) {
        // Check whether the input string is null or empty
        if ((s == null) || (s.length() == 0)) {
            huffmanTreeRoot = new TreeNode(null, 0);
            return;
        }

        // Create a map between characters and their frequencies
        HashMap<Character, Integer> freqOfChars = getFreqOfChars(s);

        // Create a TreeNode heap from the map
        PriorityQueue<TreeNode> heap = new PriorityQueue<TreeNode>(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return Integer.compare(o1.freq, o2.freq);
            }
        });
        for (Map.Entry<Character, Integer> entry : freqOfChars.entrySet()) {
            heap.offer(new TreeNode(entry.getKey(), entry.getValue()));
        }

        // Construct the Huffman tree
        while (heap.size() > 1) {
            // Take out two nodes with minimum frequency from the heap
            TreeNode minNode1 = heap.poll();
            TreeNode minNode2 = heap.poll();
            // Combine the two nodes
            TreeNode combined = new TreeNode(null, minNode1.freq + minNode2.freq, minNode1, minNode2);
            // Put the combined node back to the heap
            heap.offer(combined);
        }
        // By now there is only one node in the heap, which is exactly the root of the Huffman tree
        huffmanTreeRoot = heap.poll();

        // Create the encoding
        encodingMap = new HashMap<Character, String>();
        createEncoding(huffmanTreeRoot, new StringBuilder());
    }

    /**
     * Private helper method to create a map between the characters in the given
     * string and their frequencies.
     * @param s given string
     * @return map created map
     */
    private HashMap<Character, Integer> getFreqOfChars(String s) {
        HashMap<Character, Integer> freqOfChars = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            Integer freq = freqOfChars.getOrDefault(c, 0);
            ++freq;
            freqOfChars.put(c, freq);
        }
        return freqOfChars;
    }

    /**
     * Private helper method to create the encoding recursively.
     * @param curr current node
     */
    private void createEncoding(TreeNode curr, StringBuilder s) {
        // Base case
        if ((curr.left == null) && (curr.right == null)) {
            encodingMap.put(curr.c, s.toString());
            return;
        }
        // Recursive case
        createEncoding(curr.left, s.append('0'));
        s.deleteCharAt(s.length() - 1); // Remember to remove the appended character back
        createEncoding(curr.right, s.append('1'));
        s.deleteCharAt(s.length() - 1); // Remember to remove the appended character back
    }

    /**
     * Encodes the given message.
     * @param msg message to encode
     * @return encoded string
     */
    public String encode(String msg) {
        // Check whether the input string is null or empty
        if ((msg == null) || (msg.length() == 0)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (char c : msg.toCharArray()) {
            s.append(encodingMap.get(c));
        }
        return s.toString();
    }

    /**
     * Decodes the given encoded string.
     * @param encoded encoded string to decode
     * @return decoded message
     */
    public String decode(String encoded) {
        // Check whether the input string is null or empty
        if ((encoded == null) || (encoded.length() == 0)) {
            return "";
        }

        StringBuilder msg = new StringBuilder();
        decodeHelper(encoded, 0, huffmanTreeRoot, msg);
        return msg.toString();
    }

    /**
     * Private helper method to decode the encoded string recursively.
     * @param encoded encoded string to decode
     * @param idx index of the character in the encoded string to decode
     * @param curr current node
     * @param s decoded message
     */
    private void decodeHelper(String encoded, int idx, TreeNode curr, StringBuilder s) {
        // Base case 1: Decoded all the encoded string
        if (idx >= encoded.length()) {
            s.append(curr.c);
            return;
        }
        // Base case 2: Reach a leaf
        if ((curr.left == null) && (curr.right == null)) {
            s.append(curr.c);
            // Restart from the root
            decodeHelper(encoded, idx, huffmanTreeRoot, s);
            return;
        }

        // Recursive case
        if (encoded.charAt(idx) == '0') {
            decodeHelper(encoded, idx + 1, curr.left, s);
        } else {
            decodeHelper(encoded, idx + 1, curr.right, s);
        }
    }

    /**
     * Static nested TreeNode class.
     */
    private static class TreeNode {
        /**
         * Character stored in this TreeNode.
         */
        private Character c;
        /**
         * Frequency of the character
         */
        private int freq;
        /**
         * Reference to left child.
         */
        TreeNode left;
        /**
         * Reference to right child
         */
        TreeNode right;

        /**
         * Constructor with parameter.
         * @param c character to store
         * @param freq frequency of the character
         */
        TreeNode(Character c, int freq) {
            this(c, freq, null, null);
        }

        /**
         * Constructor with parameter.
         * @param c character to store
         * @param freq frequency of the character
         * @param left left child
         * @param right right child
         */
        TreeNode(Character c, int freq, TreeNode left, TreeNode right) {
            this.c = c;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Main driver.
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        Huffman huffman = new Huffman("This is a test.\nThis is just a test.");
        System.out.println(huffman.encode("eat"));
        System.out.println(huffman.decode("10100100110"));
    }

}
