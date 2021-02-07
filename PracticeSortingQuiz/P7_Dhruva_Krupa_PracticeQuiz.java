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
        for (int pass = 0; pass < arr.size() - 1; ++pass) {
            int indexOfMax = 0;

            for (int ii = 1; ii < arr.size() - pass; ++ii) {
                if (arr.get(ii).compareTo(arr.get(indexOfMax)) < 0) {
                    indexOfMax = ii;
                }
            }

            String tmp = arr.get(arr.size() - 1 - pass);
            arr.set(arr.size() - 1 - pass, arr.get(indexOfMax));
            arr.set(indexOfMax, tmp);

            // System.out.println(arr);
        }
    }

    public static void midsertionSort(int[] arr) {
        // The mid index will point to either mid for odd length array OR
        // right of middle of even length array. Since we iterate till (excluding)
        // mid, we will cover all elements to the left of middle of an array
        // Note: By adding 1 to length, we are sure we will ensure the above requirement
        // Ex:
        // Array length 7, mid ((7 + 1)/2) = 4 => [0, 3] will be left of middle
        // Array length 8, mid ((8 + 1)/2) = 4 => [0, 3] will be left of middle
        final int mid = (arr.length + 1) / 2;

        // Phase 1: Rearrange mirrored pairs such that left pair entry is smaller than right entry
        System.out.println("Phase 1: Swap mirrored pairs");
        for (int ii = 0; ii < mid; ++ii) {
            final int pairIdx = arr.length - 1 - ii;
            if (arr[ii] > arr[pairIdx]) {
                int tmp = arr[ii];
                arr[ii] = arr[pairIdx];
                arr[pairIdx] = tmp;
            }

            System.out.println(Arrays.toString(arr));
        }

        // Insertion sort from the middle in both directions
        System.out.println("Phase 2: Insertion sort middle sub array");

        // left and right defines the sub array from the middle. It expands in both
        // directions during the iteration by ensuring elements in middle sub array
        // is always sorted. When the middle sub array reaches either extremities,
        // the whole array is sorted (since length of sub array will be lenght of array)

        // For odd length array, we pick sub array from either side of middle element
        for (int left = mid - 1 - (arr.length % 2), right = mid;
                left >= 0 && right < arr.length;
                ++right, --left) {
            // Fix position of left side entry in right part of sub array
            for (int i = left; i < right && arr[i] > arr[i + 1]; ++i) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }

            // Fix position of right side entry in left part of sub array
            for (int i = right; i > left && arr[i] < arr[i - 1]; --i) {
                int tmp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = tmp;
            }

            System.out.println(Arrays.toString(arr));
        }
    }

    public static void vSort(int[] arr) {
        int start = 0;
        int last = (arr.length + 1) / 2;

        // Insertion sort first half of array - descending
        for (int pass = last - 2; pass >= start; --pass) {
            for (int i = pass; i < last - 1 && arr[i] < arr[i + 1]; ++i) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }

            System.out.println(Arrays.toString(arr));
        }

        // Selection sort second half of array - ascending
        start = (arr.length / 2);
        for (int pass = 0; pass < (arr.length / 2); ++pass) {
            int indexOfMax = start;

            for (int ii = start + 1; ii < arr.length - pass; ++ii) {
                if (arr[ii] > arr[indexOfMax]) {
                    indexOfMax = ii;
                }
            }

            int tmp = arr[arr.length - 1 - pass];
            arr[arr.length - 1 - pass] = arr[indexOfMax];
            arr[indexOfMax] = tmp;

            System.out.println(Arrays.toString(arr));
        }
    }
}
