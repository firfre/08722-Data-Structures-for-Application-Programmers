/**
 * 08-722 Data Structures for Application Programmers.
 * Lab 3-Simple Sorting Performance Comparison and Stability
 *
 * Insertion Sort Implementation
 *
 * Andrew ID: ziangl
 * @author Ziang Lu
 */
public class InsertionSortApp {

    /**
     * test insertion sort and its stability.
     * @param args arguments
     */
    public static void main(String[] args) {
        Employee[] list = new Employee[10];

        // employee data : first name, last name, zip
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

        System.out.println("Before Insertion Sorting: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "last");

        System.out.println("After Insertion Sorting by last name: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "zip");

        System.out.println("After Insertion Sorting by zip code: ");
        for (Employee e : list) {
            System.out.println(e);
        }

    }

    /**
     * Sorts employees either by last name or zip using Insertion Sort.
     * @param list list of employee objects
     * @param key key param value should be either "last" or "zip"
     */
    public static void insertionSort(Employee[] list, String key) {
        for (int curr = 1; curr < list.length; ++curr) {
            // Temporarily store the current element
            Employee tmp = list[curr];
            // Find the divide line in the partially sorted part
            int smallestGreaterPtr = curr;
            // Use the compare condition accordingly
            if (key.equals("last")) {
                while ((smallestGreaterPtr > 0)
                        && (list[smallestGreaterPtr - 1].getLastName().compareTo(tmp.getLastName()) > 0)) {
                    --smallestGreaterPtr;
                }
            } else if (key.equals("zip")) {
                while ((smallestGreaterPtr > 0)
                        && (Integer.compare(list[smallestGreaterPtr - 1].getZipCode(), tmp.getZipCode()) > 0)) {
                    --smallestGreaterPtr;
                }
            }
            // Move the greater part in the partially sorted part to the right by 1 position
            if (smallestGreaterPtr != curr) {
                System.arraycopy(list, smallestGreaterPtr, list, smallestGreaterPtr + 1, curr - smallestGreaterPtr);
                // Insert the current element
                list[smallestGreaterPtr] = tmp;
            }
        }
    }

}
