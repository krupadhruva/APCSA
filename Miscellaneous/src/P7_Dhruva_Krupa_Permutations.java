/*
 * Name: Krupa Dhruva
 * Date: November 22, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * To avoid repetition in calling the add(), I used addAll() to fill a list in one go.
 * This was a fun lab where I go to better work with randomization and also practice
 * using Array Lists. I also had fun coming up with a solution as to how to deal with
 * changing element numbers. I handled this by checking if the number is less than the
 * size of the list.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

public class P7_Dhruva_Krupa_Permutations {
    // Returns an ArrayList of Integers that are a permutation of the numbers 1-10
    public static ArrayList<Integer> nextPermutation() {
        List<Integer> numList = new ArrayList<>(10);
        Collections.addAll(numList, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Random rand = new Random();

        ArrayList<Integer> output = new ArrayList<>(10);
        while (!numList.isEmpty()) {
            PrimitiveIterator.OfInt ints = rand.ints(0, numList.size()).iterator();
            int idx = ints.nextInt();
            output.add(numList.get(idx));
            numList.remove(idx);
        }

        return output;
    }

    public static void main(String[] args) {
        for (int count = 1; count <= 10; ++count) {
            System.out.printf(
                    "List %d: %s%n", count, P7_Dhruva_Krupa_Permutations.nextPermutation());
        }
    }
}
