/*
 * Name: Krupa Dhruva
 * Date: January 24, 2021
 * Period: 7
 * Time Taken: 70 minutes
 *
 * Lab Reflection:
 * This was an interesting lab! Understanding the logic was fairly easy but
 * translating that into code was somewhat challenging for me because I soon
 * realized that I had overlooked potential places of error (hence all the
 * try catch and error handling). Overall, I had fun trying to understand
 * construct the code for the merge part of the merge sort algorithm.
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Scanner;

public class P7_Dhruva_Krupa_MergeTemplate {

    /**
     * Sorts any ArrayList of Comparable objects using Selection Sort.
     *
     * @param list reference to an array of integers to be sorted
     */
    public void selectionSort(ArrayList<Comparable> list) {
        // Ascending order and swapping items to the left
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
        }
    }

    /**
     * Write a merge method to merge two sorted lists.
     *
     * <p>Preconditions: Lists A and B are sorted in nondecreasing order. Action: Lists A and B are
     * merged into one list, C. Postcondition: List C contains all the values from Lists A and B, in
     * nondecreasing order.
     */
    public void merge(ArrayList<Comparable> a, ArrayList<Comparable> b, ArrayList<Comparable> c) {
        int aa = 0, bb = 0;
        while (aa < a.size() && bb < b.size()) {
            if (a.get(aa).compareTo(b.get(bb)) < 0) {
                c.add(a.get(aa));
                ++aa;
            } else {
                c.add(b.get(bb));
                ++bb;
            }
        }

        c.addAll(a.subList(aa, a.size()));
        c.addAll(b.subList(bb, b.size()));
    }

    /**
     * Write a method to - Ask the user how many numbers to generate - Ask the user to enter the
     * largest integer to generate - Initialize an ArrayList of random Integers from 1 to largestInt
     * - Return the ArrayList
     *
     * @return an ArrayList of size specified by the user filled with random numbers
     */
    public ArrayList<Comparable> fillArray() {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        int upperBound = 0;

        while (true) {
            try {
                while (count <= 0) {
                    System.out.print("Enter number of entries (positive number): ");
                    count = scan.nextInt();
                }

                while (upperBound <= 1) {
                    System.out.print("Enter highest integer to be generated (greater than 1): ");
                    upperBound = scan.nextInt();
                }

                break;
            } catch (InputMismatchException ex) {
                System.out.printf("Invalid entry \"%s\", please retry%n", scan.nextLine());
            }
        }

        ArrayList<Comparable> result = new ArrayList<>();
        Random rand = new Random();
        PrimitiveIterator.OfInt ints = rand.ints(1, upperBound + 1).iterator();
        for (; count > 0; --count) {
            result.add(ints.nextInt());
        }

        return result;
    }

    /**
     * Write a method to print out the contents of the ArrayList in tabular form, 20 columns. You
     * can use the \t escape character or use printf to format using fields. Thus, you should go to
     * the next line every 20 elements. Example: 98 7 8 56 45 6 4 3 2 1 9 888 7 7 6 5 4 3 2 9 7 6 4
     * 8 6 7 9 0 6 8 7 4 3 7 2 4 7 6 100 68 6 4 3 8 5 7 5 67 9 7
     */
    public void screenOutput(ArrayList<Comparable> temp) {
        for (int ii = 0; ii < temp.size(); ++ii) {
            System.out.printf("\t%s", temp.get(ii));
            if (ii > 0 && ii % 20 == 0) {
                System.out.println();
            }
        }
    }
}
