/*
 * Name: Krupa Dhruva
 * Date: January 6, 2021
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * I had fun implementing the two bubbleSort methods and one optimization
 * I worked on was to reduce the number of loops (from inner loop) since
 * with each progressive loop, an item on the array has achieved its
 * final position. If we exclude reading the items that have already
 * reached its final position (i.e # of items with final position = outer
 * loop counter), we reduce the number of inner loops made with each
 * progressive outer loop. Overall, this was a fun lab which helped me
 * strengthen my understanding of the Bubble Sorting technique.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class P7_Dhruva_Krupa_BubbleSort {
    /**
     * This function sorts an array of ints in descending order using the bubble sort technique
     * Bubbles from left to right
     *
     * @param data array to be sorted
     */
    public static void bubbleSort1(int[] data) {
        for (int ii = 0; ii < data.length - 1; ++ii) {
            for (int jj = 0; jj < data.length - 1 - ii; ++jj) {
                if (data[jj] < data[jj + 1]) {
                    int tmp = data[jj];
                    data[jj] = data[jj + 1];
                    data[jj + 1] = tmp;
                }
            }

            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * This function sorts the input String in alphabetical order using the bubble sort technique
     * Instead of bubbling from left to right, this method bubbles from right to left
     *
     * @param data array to be sorted
     */
    public static void bubbleSort2(String[] data) {
        for (int ii = 0; ii < data.length - 1; ++ii) {
            for (int jj = data.length - 1; jj > ii; --jj) {
                if (data[jj].compareTo(data[jj - 1]) < 0) {
                    String tmp = data[jj];
                    data[jj] = data[jj - 1];
                    data[jj - 1] = tmp;
                }
            }

            System.out.println(Arrays.toString(data));
        }
    }

    public static void bubbleSort3(ArrayList<Comparable> list) {
        for (int ii = 0; ii < list.size() - 1; ++ii) {
            for (int jj = list.size() - 2; jj >= ii; --jj) {
                if (list.get(jj).compareTo(list.get(jj + 1)) < 0) {
                    Comparable tmp = list.get(jj);
                    list.set(jj, list.get(jj + 1));
                    list.set(jj + 1, tmp);
                }
            }

            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        {
            int[] data = {5, 7, 2, 4, 3, 9};
            System.out.println("Starting array: " + Arrays.toString(data));
            bubbleSort1(data);
        }

        System.out.println();

        {
            String[] data = {"eat", "steaks", "juicy", "huge", "dogs", "big"};
            System.out.println("Starting array: " + Arrays.toString(data));
            bubbleSort2(data);
        }

        System.out.println();

        {
            ArrayList<Comparable> list = new ArrayList<>();
            list.add("1");
            list.add("8");
            list.add("6");
            list.add("9");
            list.add("2");
            list.add("3");
            System.out.println("Starting Array: " + list);
            bubbleSort3(list);
        }
    }
}
