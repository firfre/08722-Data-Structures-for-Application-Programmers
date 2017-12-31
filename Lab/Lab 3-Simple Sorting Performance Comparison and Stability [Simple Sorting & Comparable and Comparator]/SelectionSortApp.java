/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 3-Simple Sorting Performance Comparison and Stability
 *
 * Selection Sort Implementation
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class SelectionSortApp {

    /**
     * Test selection sort and its stability.
     * @param args arguments
     */
    public static void main(String[] args) {
        Employee[] list = new Employee[10];

        // employee data: first name, last name, zip
        list[0] = new Employee("Patty", "Evans", 15213);
        list[1] = new Employee("Doc", "Smith", 15214);
        list[2] = new Employee("Lorraine", "Smith", 15216);
        list[3] = new Employee("Paul", "Smith", 15216);
        list[4] = new Employee("Tom", "Yee", 15216);
        list[5] = new Employee("Sato", "Hashimoto", 15218);
        list[6] = new Employee("Henry", "Stimson", 15215);
        list[7] = new Employee("Jose", "Vela", 15211);
        list[8] = new Employee("Minh", "Vela", 15211);
        list[9] = new Employee("Lucinda", "Craswell", 15210);

        System.out.println("Before Selection Sorting: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        selectionSort(list, "last");

        System.out.println("After Selection Sorting by last name: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        selectionSort(list, "zip");

        System.out.println("After Selection Sorting by zip code: ");
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    /**
     * Sorts employees either by last name or zip using Selection Sort.
     * @param list list of employee objects
     * @param key key param value should be either "last" or "zip"
     */
    public static void selectionSort(Employee[] list, String key) {
        for (int out = 0; out < (list.length - 1); ++out) {
            int minIdx = out;
            for (int in = out + 1; in < list.length; ++in) {
                // Use the compare condition accordingly
                if (key.equals("last") && (list[in].getLastName().compareTo(list[minIdx].getLastName()) < 0)) {
                    minIdx = in;
                } else if (key.equals("zip")
                        && (Integer.compare(list[in].getZipCode(), list[minIdx].getZipCode()) < 0)) {
                    minIdx = in;
                }
            }
            if (minIdx != out) {
                swap(list, minIdx, out);
            }
        }
    }

    /**
     * Private helper method to swap elements in an array.
     * @param list list of employee objects
     * @param one first index position
     * @param two second index position
     */
    private static void swap(Employee[] list, int one, int two) {
        Employee temp = list[one];
        list[one] = list[two];
        list[two] = temp;
    }

}
