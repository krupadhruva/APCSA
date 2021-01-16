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

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        // Add '-ea' or '-enableassertions' to JVM options to run extensive tests
        // If command line arguments are provided, first argument is number of test
        // iterations to run
        assert runTests(args.length > 0 ? Integer.parseInt(args[1]) : 100);

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
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Great!", "Jane", 3.8));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Latte", "Good", "Jack", 4.6));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Nice", "Bob", 4.8));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Burger", "Yum!", "Jim", 3.8));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Latte", "Great!", "Anne", 2.8));
            ratingsList.add(new P7_Dhruva_Krupa_YelpRating("Cookie", "Meh :-(", "Emma", 1.8));

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

    /**
     * Run tests with randomized inputs for various insertion sort implementations
     *
     * <p>String parameters are constructed from random integers
     *
     * @throws AssertionError on test failure
     */
    static void testInsertionSort(int iterations) throws AssertionError {
        Random random = new Random();
        PrimitiveIterator.OfInt ints = random.ints(Integer.MIN_VALUE, Integer.MAX_VALUE).iterator();
        PrimitiveIterator.OfDouble doubles = random.doubles(-1, 6).iterator();

        for (int ii = 0; ii < iterations; ++ii) {
            int[] data = IntStream.range(0, 10).map(i -> ints.nextInt()).toArray();
            Integer[] orig = Arrays.stream(data).boxed().toArray(Integer[]::new);

            insertionSort1(data);

            Arrays.sort(orig, Comparator.naturalOrder());
            Integer[] sorted = Arrays.stream(data).boxed().toArray(Integer[]::new);
            if (!Arrays.equals(orig, sorted)) {
                throw new AssertionError("insertionSort1 test failed");
            }
        }

        for (int ii = 0; ii < iterations; ++ii) {
            String[] data =
                    IntStream.range(0, 10)
                            .mapToObj(i -> String.valueOf(ints.nextInt()))
                            .toArray(String[]::new);
            String[] orig = Arrays.copyOf(data, data.length);
            Arrays.sort(orig, Comparator.reverseOrder());

            insertionSort2(data);

            if (!Arrays.equals(orig, data)) {
                throw new AssertionError("insertionSort2 test failed");
            }
        }

        for (int ii = 0; ii < iterations; ++ii) {
            ArrayList<P7_Dhruva_Krupa_YelpRating> data =
                    IntStream.range(0, 10)
                            .mapToObj(
                                    i ->
                                            new P7_Dhruva_Krupa_YelpRating(
                                                    String.valueOf(i),
                                                    String.valueOf(i),
                                                    String.valueOf(i),
                                                    doubles.next()))
                            .collect(Collectors.toCollection(ArrayList::new));
            Double[] orig =
                    data.stream()
                            .map(P7_Dhruva_Krupa_YelpRating::getRating)
                            .sorted(Comparator.reverseOrder())
                            .toArray(Double[]::new);

            insertionSort3(data);

            Double[] sorted =
                    data.stream().map(P7_Dhruva_Krupa_YelpRating::getRating).toArray(Double[]::new);
            if (!Arrays.equals(orig, sorted)) {
                throw new AssertionError("insertionSort3 test failed");
            }
        }
    }

    /**
     * Helper method to redirect output to NULL stream to avoid polluting console output
     *
     * <p>Sets the default output to NULL stream and restores previous state before return
     */
    static boolean runTests(int iterations) {
        boolean success = false;
        System.out.println("Running tests...");

        PrintStream stdout = System.out;
        try {
            System.setOut(new PrintStream(PrintStream.nullOutputStream()));
            testInsertionSort(iterations);
            success = true;
        } catch (Exception ignored) {
        } finally {
            System.setOut(stdout);
            System.out.println("Test run success=" + success + "\n");
        }

        return success;
    }
}
