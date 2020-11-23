import java.util.ArrayList;
import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.Random;

public class P7_Dhruva_Krupa_InClassLab15 {
    public static void printList(ArrayList<Integer> numbers) {
        System.out.print("[");
        int count = 0;
        for (Integer num : numbers) {
            count++;
            if (count < numbers.size()) {
                System.out.print(num + " ");
            } else {
                System.out.print(num);
            }
        }
        System.out.println("]");
    }

    public static ArrayList<Integer> randomList(int n, int a, int b) {
        PrimitiveIterator.OfInt random = new Random().ints(a, b + 1).iterator();
        ArrayList<Integer> randInts = new ArrayList<>();
        for (int count = 0; count < n; ++count) {
            randInts.add(random.nextInt());
        }

        return randInts;
    }

    public static void removeNegatives(ArrayList<Integer> nums) {
        Iterator<Integer> iter = nums.iterator();
        while (iter.hasNext()) {
            if (iter.next() < 0) {
                iter.remove();
            }
        }
    }

    public static ArrayList<Integer> commonList(
            ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();
        Iterator<Integer> iter = list1.iterator();
        while (iter.hasNext()) {
            Integer value = iter.next();

            // Avoid duplicates in results
            if (list2.contains(value) && !result.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = randomList(25, -5, 10);
        ArrayList<Integer> b = randomList(25, -5, 10);

        System.out.println("BEFORE");
        System.out.print("a = ");
        printList(a);
        System.out.print("b = ");
        printList(b);

        removeNegatives(a);
        removeNegatives(b);
        System.out.println("\nAFTER REMOVING NEGATIVES");
        System.out.print("a = ");
        printList(a);
        System.out.print("b = ");
        printList(b);

        System.out.println("\nCOMMON LIST");
        printList(commonList(a, b));
    }
}
