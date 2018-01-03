import java.util.TreeSet;

/**
 * 08-722 Data Structures for Application Programmers.
 * Lecture 17-TreeMap and TreeSet
 *
 * Very simple application using TreeSet
 *
 * Search Trees are great at performing range searches
 *
 * @author Terry Lee
 */
public class BusSchedueApp {

    /**
     * Simple program using TreeSet.
     * @param args arguments
     */
    public static void main(String[] args) {
        TreeSet<Integer> schedule = new TreeSet<Integer>();
        schedule.add(1223);
        schedule.add(1430);
        schedule.add(1545);
        schedule.add(1610);
        schedule.add(1705);
        schedule.add(2010);
        schedule.add(2215);
        schedule.add(2320);
        schedule.add(2345);

        // show the last bus that leaves PGH before 4 pm (exclusive)
        System.out.println(schedule.headSet(1600).last());

        // show the first bus that leaves PGH after 10 pm (inclusive)
        System.out.println(schedule.tailSet(2200).first());
    }

}
