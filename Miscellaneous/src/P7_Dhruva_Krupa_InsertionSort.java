/*
 * Name: Krupa Dhruva
 * Date: January 13, 2021
 * Period: 7
 * Time Taken: 70 minutes
 *
 * Lab Reflection:
 * I enjoyed implementing InsertionSort and I found it extremely helpful
 * that we were able to produce a real life application with sorting
 * YelpRating. At first, I struggled to grasp how InsertionSort worked
 * because I got confused with the direction changes. This lab helped me
 * solidify my understanding and develop a clearer idea of yet another
 * sorting technique. When I was testing the implementation of toString(),
 * the formatting for the individual elements came as expected but when
 * I called toString() for several items on a list, it appeared a little
 * different. I learned from my father that the reason this occurs is
 * because the toString() implementation done in my class applies for
 * the individual elements but does not account for when the list tries
 * to print out those elements side by side (this would be implemented
 * in the ArrayList's class). Due to this, the readability of the output
 * is not good.
 */

import java.util.*;

public class P7_Dhruva_Krupa_InsertionSort {
    /**
     * Sorts array of integers in ascending order using insertion sort algorithm
     *
     * @param data Input/output array of integers
     */
    public static void insertionSort1(int[] data) {
        for (int pass = 1; pass < data.length; ++pass) {
            for (int i = pass; i != 0 && data[i - 1] > data[i]; --i) {
                int tmp = data[i];
                data[i] = data[i - 1];
                data[i - 1] = tmp;
            }

            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * Sorts an array of strings in descending order using insertion sort algorithm
     *
     * @param data Input/output array of strings
     */
    public static void insertionSort2(String[] data) {
        for (int pass = data.length - 2; pass >= 0; --pass) {
            for (int i = pass; i < data.length - 1 && data[i].compareTo(data[i + 1]) < 0; ++i) {
                String tmp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = tmp;
            }

            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * Sorts list of Yelp reviews in descending order using insertion sort algorithm
     *
     * @param data Input/output, list of Yelp ratings
     */
    public static void insertionSort3(ArrayList<P7_Dhruva_Krupa_YelpRating> data) {
        for (int pass = 1; pass < data.size(); ++pass) {
            for (int i = pass; i != 0 && data.get(i - 1).compareTo(data.get(i)) < 0; --i) {
                P7_Dhruva_Krupa_YelpRating tmp = data.get(i);
                data.set(i, data.get(i - 1));
                data.set(i - 1, tmp);
            }

            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        {
            int[] data = {9, 5, 1, 3, 7, 4};
            System.out.println("Starting Array: " + Arrays.toString(data));
            insertionSort1(data);
            System.out.println("Ending Array: " + Arrays.toString(data));
        }

        System.out.println();
        {
            String[] data = {"9", "5", "1", "3", "7", "4"};
            System.out.println("Starting Array: " + Arrays.toString(data));
            insertionSort2(data);
            System.out.println("Ending Array: " + Arrays.toString(data));
        }

        System.out.println();
        {
            ArrayList<P7_Dhruva_Krupa_YelpRating> ratingsList = new ArrayList<>();
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Great!", 3.8, "Jane"));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Latte", "Good", 4.6, "Jack"));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Nice", 4.8, "Bob"));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Yum!", 3.8, "Jim"));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Latte", "Great!", 2.8, "Anne"));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Cookie", "Meh :-(", 1.8, "Emma"));

            // Extract the ratings to print before sorting
            ArrayList<Double> ratings = new ArrayList<>(ratingsList.size());
            for (var r : ratingsList) {
                ratings.add(r.getRating());
            }
            System.out.println("Starting Ratings: " + ratings);

            insertionSort3(ratingsList);

            // Extract the ratings to print after sorting
            ratings.clear();
            for (var r : ratingsList) {
                ratings.add(r.getRating());
            }
            System.out.println("Ending Ratings: " + ratings);
        }
    }
}
