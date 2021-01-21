import java.util.*;

/**
 * Description of the Class
 *
 * @author Your Name Here
 * @created Month Day, Year
 */
public class P7_Dhruva_Krupa_Sorts {
    private long steps;

    /** Description of Constructor */
    public P7_Dhruva_Krupa_Sorts() {
        steps = 0;
    }

    /**
     * Description of the Method
     *
     * @param list reference to an array of integers to be sorted
     */
    public void bubbleSort(ArrayList<Comparable> list) {
        setStepCount(getStepCount() + 2);
        for (int ii = 0; ii < list.size() - 1; ++ii) {

            setStepCount(getStepCount() + 4);
            for (int jj = list.size() - 1; jj > ii; --jj) {
                setStepCount(getStepCount() + 5);
                if (list.get(jj).compareTo(list.get(jj - 1)) < 0) {
                    setStepCount(getStepCount() + 2);
                    swap(list, jj, jj - 1);
                }

                setStepCount(getStepCount() + 2);
            }

            // System.out.println(list);

            setStepCount(getStepCount() + 4);
        }
    }

    /**
     * Description of the Method
     *
     * @param list reference to an array of integers to be sorted
     */
    public void selectionSort(ArrayList<Comparable> list) {
        setStepCount(getStepCount() + 4);
        for (int pass = 0; pass < list.size() - 1; ++pass) {
            setStepCount(getStepCount() + 1);
            int indexOfMax = 0;

            setStepCount(getStepCount() + 4);
            for (int ii = 1; ii < list.size() - pass; ++ii) {
                setStepCount(getStepCount() + 4);
                if (list.get(ii).compareTo(list.get(indexOfMax)) > 0) {
                    setStepCount(getStepCount() + 1);
                    indexOfMax = ii;
                }

                setStepCount(getStepCount() + 4);
            }

            setStepCount(getStepCount() + 4);
            swap(list, list.size() - 1 - pass, indexOfMax);

            // System.out.println(list);

            setStepCount(getStepCount() + 4);
        }
    }

    /**
     * Description of the Method
     *
     * @param list reference to an array of integers to be sorted
     */
    public void insertionSort(ArrayList<Comparable> list) {
        setStepCount(getStepCount() + 3);
        for (int pass = 1; pass < list.size(); ++pass) {
            setStepCount(getStepCount() + 8);
            for (int i = pass; i != 0 && list.get(i - 1).compareTo(list.get(i)) < 0; --i) {
                setStepCount(getStepCount() + 2);
                swap(list, i, i - 1);

                setStepCount(getStepCount() + 8);
            }

            // System.out.println(list);

            setStepCount(getStepCount() + 3);
        }
    }

    /**
     * Takes in entire vector, but will merge the following sections together: Left sublist from
     * a[first]..a[mid], right sublist from a[mid+1]..a[last]. Precondition: each sublist is already
     * in ascending order
     *
     * @param a reference to an array of integers to be sorted
     * @param first starting index of range of values to be sorted
     * @param mid midpoint index of range of values to be sorted
     * @param last last index of range of values to be sorted
     */
    private void merge(ArrayList<Comparable> a, int first, int mid, int last) {
        // replace these lines with your code
        System.out.println();
        System.out.println("Merge");
        System.out.println();
    }

    /**
     * Recursive mergesort of an array of integers
     *
     * @param a reference to an array of integers to be sorted
     * @param first starting index of range of values to be sorted
     * @param last ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList<Comparable> a, int first, int last) {
        // replace these lines with your code
        System.out.println();
        System.out.println("Merge Sort");
        System.out.println();
    }

    /** Accessor method to return the current value of steps */
    public long getStepCount() {
        return steps;
    }

    /**
     * Modifier method to set or reset the step count. Usually called prior to invocation of a sort
     * method.
     *
     * @param stepCount value assigned to steps
     */
    public void setStepCount(long stepCount) {
        steps = stepCount;
    }

    /**
     * Interchanges two elements in an ArrayList
     *
     * @param list reference to an array of integers
     * @param a index of integer to be swapped
     * @param b index of integer to be swapped
     */
    public void swap(ArrayList<Comparable> list, int a, int b) {
        setStepCount(getStepCount() + 2);
        Comparable tmp = list.get(a);

        setStepCount(getStepCount() + 2);
        list.set(a, list.get(b));

        setStepCount(getStepCount() + 1);
        list.set(b, tmp);
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_SortStep sortStep = new P7_Dhruva_Krupa_SortStep();
        sortStep.sortMenu();
    }
}
