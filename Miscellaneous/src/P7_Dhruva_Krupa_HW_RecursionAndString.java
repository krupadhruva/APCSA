public class P7_Dhruva_Krupa_HW_RecursionAndString {
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
        if (str.length() == 0) {
            return str;
        }

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

    public String pairStar(String str) {
        if (str.length() <= 1) {
            return str;
        }

        return String.format(
                "%c%s%s",
                str.charAt(0),
                (str.charAt(0) == str.charAt(1)) ? "*" : "",
                pairStar(str.substring(1)));
    }

    public String endX(String str) {
        if (str.length() <= 1) {
            return str;
        }

        if (str.startsWith("x")) {
            return endX(str.substring(1)) + "x";
        }

        return str.charAt(0) + endX(str.substring(1));
    }

    public int countPairs(String str) {
        if (str.length() < 3) {
            return 0;
        }

        return ((str.charAt(0) == str.charAt(2)) ? 1 : 0) + countPairs(str.substring(1));
    }

    public int countAbc(String str) {
        if (str.length() < 3) {
            return 0;
        }

        return ((str.startsWith("abc") || str.startsWith("aba")) ? 1 : 0)
                + countAbc(str.substring(1));
    }

    public int count11(String str) {
        if (str.length() < 2) {
            return 0;
        }

        if (str.startsWith("11")) {
            return 1 + count11(str.substring(2));
        }

        return count11(str.substring(1));
    }

    public String stringClean(String str) {
        if (str.length() < 2) {
            return str;
        }

        if (str.charAt(0) == str.charAt(1)) {
            return stringClean(str.substring(1));
        }

        return str.charAt(0) + stringClean(str.substring(1));
    }

    public int countHi2(String str) {
        if (str.length() < 2) {
            return 0;
        }

        if (str.length() == 2 && str.equals("hi")) {
            return 1;
        }

        if (str.startsWith("xhi")) {
            return countHi2(str.substring(3));
        }

        if (str.startsWith("hi")) {
            return 1 + countHi2(str.substring(2));
        }

        return countHi2(str.substring(1));
    }

    public String parenBit(String str) {
        if (str.length() == 0) {
            return str;
        }

        boolean starts = str.startsWith("(");
        boolean ends = str.endsWith(")");
        if (starts && ends) {
            return str;
        }

        String sub = starts ? str.substring(0, str.length() - 1) : str.substring(1);
        return parenBit(sub);
    }

    public boolean nestParen(String str) {
        if (str.length() == 0) {
            return true;
        }

        if (str.length() == 1) {
            return false;
        }

        boolean starts = str.startsWith("(");
        boolean ends = str.endsWith(")");
        if (starts && ends) {
            return nestParen(str.substring(1, str.length() - 1));
        }

        String sub = starts ? str.substring(0, str.length() - 1) : str.substring(1);
        return nestParen(sub);
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_HW_RecursionAndString obj = new P7_Dhruva_Krupa_HW_RecursionAndString();
        System.out.println(obj.nestParen("(())"));
    }
}
