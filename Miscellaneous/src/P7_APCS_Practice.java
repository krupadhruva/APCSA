import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class P7_APCS_Practice {
    int[] mergeAlternateArrays(int[] a, int[] b) {
        int[] big, small;
        if (a.length > b.length) {
            big = a;
            small = b;
        } else {
            big = b;
            small = a;
        }

        // Copying arrays
        int[] res = Arrays.copyOf(big, big.length);
        for (int ii = 1; ii < small.length; ii += 2) {
            res[ii] = small[ii];
        }

        return res;
    }

    @org.junit.jupiter.api.Test
    void mergeAlternateArraysTest() {
        int[] a = {1, 2, 3, 4, 5, 6};
        int[] b = {7, 8, 9, 10, 11};
        System.out.println(Arrays.toString(mergeAlternateArrays(a, b)));
    }

    // String reverse
    String strReverse(String str) {
        StringBuilder builder = new StringBuilder();
        builder.append(str);
        return builder.reverse().toString();
    }

    // String reverse
    String strReverseRecurse(String str) {
        if (str.length() == 0) {
            return "";
        }

        return strReverseRecurse(str.substring(1)) + str.charAt(0);
    }

    @org.junit.jupiter.api.Test
    void strReverseTest() {
        System.out.println(strReverse("hello world"));
        System.out.println(strReverseRecurse("hello world"));
    }

    // In place array reverse
    void reverseArray(int[] a) {
        // NOTE: ii < jj (do not used ii != jj as it will cross over for array with even length)
        for (int ii = 0, jj = a.length - 1; ii < jj; ++ii, --jj) {
            int tmp = a[ii];
            a[ii] = a[jj];
            a[jj] = tmp;
        }
    }

    @org.junit.jupiter.api.Test
    void reverseArrayTest() {
        int[] a = {1, 2, 3, 4, 5, 6};
        reverseArray(a);
        System.out.println(Arrays.toString(a));
    }

    // Reverse list in place
    void reverseList(ArrayList<Integer> nums) {
        for (int ii = 0; ii < nums.size(); ++ii) {
            // Remove from end and arrange from front
            nums.add(ii, nums.remove(nums.size() - 1));
        }
    }

    @org.junit.jupiter.api.Test
    void reverseListTest() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        System.out.println(nums);
        reverseList(nums);
        System.out.println(nums);
    }

    // Merge 2 lists with new list
    List<Integer> mergeLists(List<Integer> a, List<Integer> b) {
        Iterator<Integer> itA = a.iterator();
        Iterator<Integer> itB = b.iterator();

        List<Integer> res = new ArrayList<>();
        while (itA.hasNext() && itB.hasNext()) {
            res.add(itA.next());
            res.add(itB.next());
        }

        while (itA.hasNext()) {
            res.add(itA.next());
        }

        while (itB.hasNext()) {
            res.add(itB.next());
        }

        return res;
    }

    @org.junit.jupiter.api.Test
    void mergeListsTest() {
        List<Integer> a = Arrays.asList(1, 3, 5, 7);
        List<Integer> b = Arrays.asList(2, 4, 6, 8);

        System.out.println(mergeLists(a, b));
    }

    // Keep track of binary search visits
    static int left = 0, right = 0, count = 0;

    int binarySearch(List<Integer> theList, int low, int high, int target) {
        ++count;

        if (low > high) {
            return -1;
        }

        int middle = (low + high) / 2;

        if (target == theList.get(middle)) {
            return middle;
        } else if (target < theList.get(middle)) {
            ++left;
            System.out.printf("\tleft (%d): low=%d, high=%d%n", left, low, middle - 1);
            return binarySearch(theList, low, middle - 1, target);
        } else {
            ++right;
            System.out.printf("\tright (%d): low=%d, high=%d%n", right, middle + 1, high);
            return binarySearch(theList, middle + 1, high, target);
        }
    }

    @org.junit.jupiter.api.Test
    void binarySearchTest() {
        List<Integer> nums = Arrays.asList(10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60);
        left = right = count = 0;
        int idx = binarySearch(nums, 0, nums.size() - 1, 45);
        System.out.printf("index=%d, count=%d [left=%d, right=%d]%n", idx, count, left, right);
    }
}
