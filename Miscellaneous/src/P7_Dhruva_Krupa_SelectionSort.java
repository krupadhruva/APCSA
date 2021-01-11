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

    public static void selectionSort(ArrayList<Card> cards) {
        for (int pass = 0; pass < cards.size() - 1; ++pass) {
            int indexOfMax = 0;

            for (int ii = 1; ii < cards.size() - pass; ++ii) {
                if (cards.get(ii).compareTo(cards.get(indexOfMax)) > 0) {
                    indexOfMax = ii;
                }
            }

            Card tmp = cards.get(cards.size() - 1 - pass);
            cards.set(cards.size() - 1 - pass, cards.get(indexOfMax));
            cards.set(indexOfMax, tmp);

            System.out.println(cards);
        }
    }

    public static void main(String[] args) {
        //        int[] num = {5, 7, 2, 4, 3, 9};
        //        System.out.println("Starting array: " + Arrays.toString(num));
        //        selectionSort1(num);
        //
        //        System.out.println();
        //
        //        String[] data = {"eat", "steaks", "juicy", "huge", "dogs", "big"};
        //        System.out.println("Starting array: " + Arrays.toString(data));
        //        selectionSort2(data);
        //
        //        System.out.println();
        //
        //        ArrayList<Card> cards = Card.getShuffledDeck();
        //        System.out.println("Starting Deck: " + cards);
        //        selectionSort(cards);
    }
}
