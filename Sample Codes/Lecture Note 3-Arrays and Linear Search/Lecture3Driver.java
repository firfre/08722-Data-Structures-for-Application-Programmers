import java.util.Arrays;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 3-Arrays and Linear Search
 *
 * @author Terry Lee
 */
public class Lecture3Driver {

    /**
     * Simple program to test arrays in Java.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {1, 2, 3, 4, 5};

        // checks identity
        System.out.println(a.equals(b));
        // checks identity
        System.out.println(a == b);
        // checks equality
        System.out.println(Arrays.equals(a, b));

        // prints name @ hexdecimal representation of hash code
        System.out.println(a);
        // prints name @ hexdecimal representation of hash code
        System.out.println(a.toString());
        // prints values
        System.out.println(Arrays.toString(a));

        int[] c = {3, 4, 2, 5, 1};
        System.out.println(Arrays.toString(c));
        // sort
        Arrays.sort(c);
        System.out.println(Arrays.toString(c));

        // copy arrays using copyOf
        int[] d = Arrays.copyOf(c, c.length);
        System.out.println(Arrays.toString(d));
        int[] e = Arrays.copyOf(c, 2);
        System.out.println(Arrays.toString(e));
        int[] f = Arrays.copyOf(c, 10);
        System.out.println(Arrays.toString(f));

        // copy arrays using System.arraycopy
        int[] g = {4, 2, 5, 3, 1};
        int[] h = new int[g.length];
        System.out.println(Arrays.toString(h));
        System.arraycopy(g, 0, h, 0, h.length);
        System.out.println(Arrays.toString(h));

        // copy arrays using clone
        int[] i = h.clone();
        System.out.println(Arrays.toString(i));

        char[] data = {'a', 'b', 'c', 'd'};
        System.out.println(Arrays.toString(delete(data, 2)));
    }

    /**
     * Deletes char value in an array of char at the specified position.
     * @param data char array
     * @param position position to remove char value
     * @return a new array of char without the value at the position.
     */
    public static char[] delete(char[] data, int position) {
        if (position >= 0 && position < data.length) {
            char[] tmp = new char[data.length - 1];
            System.arraycopy(data, 0, tmp, 0, position);
            System.arraycopy(data, position + 1, tmp, position, data.length - position - 1);
            return tmp;
        }
        return data;
    }

}
