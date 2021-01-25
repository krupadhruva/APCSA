/*
 * Name: Krupa Dhruva
 * Date: January 10, 2021
 * Period: 7
 * Time Taken: 45 minutes
 *
 * Lab Reflection:
 * I enjoyed working on this lab and experimenting with the selection
 * sorting technique. I developed a better understanding of yet
 * another type of sorting and I also learned how to come up with
 * the logic for an algorithm; the thought process. Approaching this
 * as a fun puzzle really helped me better understand and enjoy the lab!
 */
import java.util.ArrayList;
import java.util.Arrays;

public class P7_Dhruva_Krupa_SelectionSort {
    public static void selectionSort1(int[] num) {
        for (int pass = 0; pass < num.length - 1; ++pass) {
            int indexOfMax = 0;

            for (int ii = 1; ii < num.length - pass; ++ii) {
                if (num[ii] > num[indexOfMax]) {
                    indexOfMax = ii;
                }
            }

            int tmp = num[num.length - 1 - pass];
            num[num.length - 1 - pass] = num[indexOfMax];
            num[indexOfMax] = tmp;

            System.out.println(Arrays.toString(num));
        }
    }

    public static void selectionSort2(String[] data) {
        for (int pass = 0; pass < data.length - 1; ++pass) {
            int indexOfMax = pass;

            for (int ii = indexOfMax + 1; ii < data.length; ++ii) {
                if (data[ii].compareTo(data[indexOfMax]) > 0) {
                    indexOfMax = ii;
                }
            }

            String tmp = data[pass];
            data[pass] = data[indexOfMax];
            data[indexOfMax] = tmp;

            System.out.println(Arrays.toString(data));
        }
    }

    public static void selectionSort(ArrayList<Comparable> list) {
        for (int pass = 0; pass < list.size() - 1; ++pass) {
            int indexOfMax = 0;

            for (int ii = 1; ii < list.size() - pass; ++ii) {
                if (list.get(ii).compareTo(list.get(indexOfMax)) > 0) {
                    indexOfMax = ii;
                }
            }

            Comparable tmp = list.get(indexOfMax);
            list.set(indexOfMax, list.get(list.size() - 1 - pass));
            list.set(list.size() - 1 - pass, tmp);

            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        int[] num = {5, 7, 2, 4, 3, 9};
        System.out.println("Starting array: " + Arrays.toString(num));
        selectionSort1(num);

        System.out.println();

        String[] data = {"eat", "steaks", "juicy", "huge", "dogs", "big"};
        System.out.println("Starting array: " + Arrays.toString(data));
        selectionSort2(data);

        System.out.println();

        ArrayList<Comparable> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(6);
        list.add(2);
        System.out.println("Starting Deck: " + list);
        selectionSort(list);
    }
}
