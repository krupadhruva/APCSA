import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P7_Dhruva_Krupa_PracticeQuiz {

    public static void main(String[] args) {

        String[] words = {"abe", "bat", "car", "cat", "dab", "elf", "fig", "get"};
        ArrayList<String> wordList = new ArrayList<String>();

        for (String word : words) {
            wordList.add(word);
        }

        System.out.println("***** Selection Sort *****");
        Collections.shuffle(wordList);
        System.out.println("Before sorting: " + wordList);
        selectionSort(wordList);
        System.out.println("After sorting:  " + wordList);

        int[] intArray = generateRandomIntArray(8);

        System.out.println("\n***** Midsertion Sort *****");
        System.out.println("Before sorting: " + Arrays.toString(intArray));
        midsertionSort(intArray);
        System.out.println("After sorting:  " + Arrays.toString(intArray));

        intArray = generateRandomIntArray(8);

        System.out.println("\n***** V Sort *****");
        System.out.println("Before sorting: " + Arrays.toString(intArray));
        vSort(intArray);
        System.out.println("After sorting:  " + Arrays.toString(intArray));
    }

    private static int[] generateRandomIntArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * length);
        }
        return arr;
    }

    private static void selectionSort(ArrayList<String> arr) {
        /* Add your code here */
    }

    public static void midsertionSort(int[] arr) {
        /* Add your code here */
    }

    public static void vSort(int[] arr) {
        /* Add your code here */
    }
}
