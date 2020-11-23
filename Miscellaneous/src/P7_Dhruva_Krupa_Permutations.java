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
