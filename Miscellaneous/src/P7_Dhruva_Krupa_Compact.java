/*
 * Name: Krupa Dhruva
 * Date: November 22, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * Overall, I had fun doing this lab and this lab helped me better
 * understand the differences between Array and ArrayList. My dad
 * gave me an idea as to how I can optimize my below strategy
 * (a description of the optimization is written under the strategy
 * for array).
 *
 * Strategy using Array:
 * Use an outer for loop to traverse the list with the exception of the last
 * entry. If there is a 0 present, the inner for loop traverses the list
 * until it finds a non-zero value. If the for loop finds a non-zero value,
 * store the value at that index in a temporary variable so that the initial
 * zero can be swapped with the non-zero value.
 *
 * 2 Optimizations I made after coding:
 *
 * First: We avoid revisiting numbers by comparing the current index of the
 * outer loop vs the location of the non-zero value to see which of the two
 * is further along the list (so that we can jump to that index)
 *
 * Second: When looping, if a list from a given index onward only contains
 * zeros, return from the list; decreases the number of loops
 *
 * Strategy using ArrayList:
 * Using an iterator and a while loop to keep traversing the list while
 * there are entries. If there is a 0 in the list, simply remove it.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class P7_Dhruva_Krupa_Compact {
    public static void compactArray(int[] arr) {
        int nonZeroLoc = 0;
        int loops = 0;
        boolean foundNonZero = true;

        for (int ii = 0; foundNonZero && ii < arr.length - 1; ++ii) {
            ++loops;

            if (arr[ii] == 0) {
                foundNonZero = false;

                for (int jj = Math.max(ii + 1, nonZeroLoc); jj < arr.length; ++jj) {
                    ++loops;

                    if (arr[jj] != 0) {
                        nonZeroLoc = jj;
                        foundNonZero = true;

                        int tmp = arr[ii];
                        arr[ii] = arr[jj];
                        arr[jj] = tmp;
                        break;
                    }
                }
            }
        }

        // This is to track loops
        // System.out.println(loops);
    }

    public static void compactArrayList(ArrayList<Integer> arr) {
        Iterator<Integer> iter = arr.iterator();
        while (iter.hasNext()) {
            if (iter.next() == 0) {
                iter.remove();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("compact.txt"));
        ArrayList<Integer> arrList = new ArrayList<>();
        while (scan.hasNext()) {
            arrList.add(scan.nextInt());
        }
        scan.close();

        int[] arr = new int[arrList.size()];
        Iterator<Integer> iter = arrList.iterator();

        for (int idx = 0; iter.hasNext(); ++idx) {
            arr[idx] = iter.next();
        }

        System.out.println("Array\nBefore:" + Arrays.toString(arr));
        compactArray(arr);
        System.out.println("After:" + Arrays.toString(arr));

        System.out.println("\nArrayList\nBefore:" + arrList);
        compactArrayList(arrList);
        System.out.println("After:" + arrList);
    }
}
