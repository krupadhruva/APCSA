import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

public class P7_Dhruva_Krupa_InClassLab15 {
    public static void printList(List<Integer> numbers) {
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

    public static List<Integer> randomList(int n, int a, int b) {
        PrimitiveIterator.OfInt random = new Random().ints(a, b + 1).iterator();
        List<Integer> randInts = new ArrayList<>();
        for (int count = 0; count < n; ++count) {
            randInts.add(random.nextInt());
        }

        return randInts;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(2);
        numbers.add(9);
        numbers.add(1);
        numbers.add(7);

        P7_Dhruva_Krupa_InClassLab15.printList(numbers);
        P7_Dhruva_Krupa_InClassLab15.printList(randomList(10, 1, 15));
    }
}
