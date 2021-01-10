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
            int indexOfMax = 0;

            for (int ii = 1; ii < data.length - pass; ++ii) {
                if (data[ii].compareTo(data[indexOfMax]) > 0) {
                    indexOfMax = ii;
                }
            }

            String tmp = data[0 + pass];
            data[0 + pass] = data[indexOfMax];
            data[indexOfMax] = tmp;

            System.out.println(Arrays.toString(data));
        }
    }

    public static void main(String[] args) {
        int[] num = {5, 7, 2, 4, 3, 9};
        System.out.println("Starting array: " + Arrays.toString(num));
        selectionSort1(num);

        String[] data = {"big", "eat", "steaks", "juicy", "dogs"};
        System.out.println("Starting array: " + Arrays.toString(data));
        selectionSort2(data);
    }
}
