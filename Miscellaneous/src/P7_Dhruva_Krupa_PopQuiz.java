import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class P7_Dhruva_Krupa_PopQuiz {
    // Mon 07, Dec 2020 03:00:26 PM
    public static String substring(String str, int start, int length) {
        if ((start + length) > str.length()) {
            return "";
        }

        return str.substring(start, start + length);
    }

    public static boolean isTrending(String master, String substr) {
        if (master.length() < substr.length()) {
            return false;
        }

        int count = 0;
        for (int cc = 0; cc < master.length(); ++cc) {
            String sub = substring(master, cc, substr.length());
            if (sub.equals(substr)) {
                count++;
                cc += substr.length() - 1;
            }
        }

        return count == 3;
    }

    public static boolean isAscending(int[] arr) {
        if (arr.length <= 1) {
            return true;
        }

        for (int cc = 1; cc < arr.length - 1; ++cc) {
            if (arr[cc] <= arr[cc - 1]) {
                return false;
            }
        }

        return true;
    }

    public static void moveToFront(ArrayList<Integer> arr) {
        Iterator<Integer> iter = arr.iterator();
        ArrayList<Integer> tmp = new ArrayList<>();
        while (iter.hasNext()) {
            int d = iter.next();
            if (d > 65) {
                tmp.add(d);
                iter.remove();
            }
        }

        arr.addAll(0, tmp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Collections.addAll(arr, 1, 2, 3, 4, 66, 67, 68, 9);
        System.out.println(arr);
        moveToFront(arr);
        System.out.println(arr);
        System.out.println(isTrending("aaabaaa", "aa"));
    }
}
