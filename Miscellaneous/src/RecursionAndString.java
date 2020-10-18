public class RecursionAndString {
    // ------- String manipulation ---------

    // Replicate first 2 chars of a string 3 times
    // Java 11 has repeat()
    public static String extraFront(String str) {
        String subStr;
        if (str.length() <= 2) {
            subStr = str;
        } else {
            subStr = str.substring(0, 2);
        }

        // If Java 11
        // return subStr.repeat(3);

        return subStr + subStr + subStr;
    }

    // Strip leading 2 chars if the string ends with same 2 chars
    // Ex: "is there an oasis" => " there an oasis"
    public static String without2(String str) {
        if (str.length() == 2) {
            return "";
        } else if (str.length() <= 1) {
            return str;
        }

        String subStr = str.substring(0, 2);
        if (str.endsWith(subStr)) {
            str = str.substring(2);
        }

        return str;
    }

    // Strip first char if anything other than 'a' or 'b'
    public static String deFront(String str) {
        String result = str.substring(2);
        if (str.charAt(1) == 'b') {
            result = 'b' + result;
        }

        if (str.charAt(0) == 'a') {
            result = 'a' + result;
        }

        return result;
    }

    // Check if first string starts with second ignoring first char
    public static String startWord(String str, String word) {
        String firstStr = str.substring(1);
        String secStr = word.substring(1);

        int numChar = 0;

        if (secStr.length() == 0 || firstStr.startsWith(secStr)) {
            numChar = word.length();
        }

        return str.substring(0, numChar);
    }

    // Strip first/last 'x'
    public static String withoutX(String str) {
        return str.replaceAll("^x|x$", "");
    }

    // Strip first/second occurrence of 'x'
    public static String withoutX2(String str) {
        if (str.length() < 2) {
            return "";
        }

        String result = str.substring(2);

        if (str.charAt(1) != 'x') {
            result = str.charAt(1) + result;
        }

        if (str.charAt(0) != 'x') {
            result = str.charAt(0) + result;
        }

        return result;
    }

    // ------- Recursion exercises ---------

    // Count 'x' in a string
    public static int countX(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int count = str.charAt(0) == 'x' ? 1 : 0;
        return count + countX(str.substring(1));
    }

    // Count "hi" in a string
    public int countHi(String str) {
        if (str.length() < 2) {
            return 0;
        }

        int count = (str.startsWith("hi")) ? 1 : 0;
        return count + countHi(str.substring(1));
    }

    // Replace "x" with "y"
    public static String changeXY(String str) {
        if (str.length() == 0) {
            return str;
        }

        String ch = (str.charAt(0) == 'x') ? "y" : str.substring(0, 1);
        return ch + changeXY(str.substring(1));
    }

    // Replace all "pi" with "3.14"
    public static String changePi(String str) {
        if (str.length() < 2) {
            return str;
        }

        int skipCount = str.startsWith("pi") ? 2 : 1;

        String val = skipCount == 2 ? "3.14" : str.substring(0, 1);
        return val + changePi(str.substring(skipCount));
    }

    // Strip 'x'
    public static String noX(String str) {
        if (str.length() == 0) {
            return str;
        }

        return (str.charAt(0) == 'x' ? "" : str.charAt(0)) + noX(str.substring(1));
    }

    // Insert '*' to adjacent chars in a string
    public String allStar(String str) {
        if (str.length() <= 1) {
            return str;
        }

        return str.charAt(0) + "*" + allStar(str.substring(1));
    }

    // Check if array contains 6
    public static boolean array6(int[] nums, int index) {
        if (nums.length == 0 || index == nums.length) {
            return false;
        }

        return nums[index] == 6 || array6(nums, index + 1);
    }

    // Count number of 11 in an array
    public static int array11(int[] nums, int index) {
        if (nums.length == 0 || index == nums.length) {
            return 0;
        }

        return ((nums[index] == 11) ? 1 : 0) + array11(nums, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(withoutX("xobxjectx"));
    }
}
