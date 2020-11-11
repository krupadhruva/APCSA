public class P7_CodingBat_String3 {
    public int countYZ(String str) {
        // Simplify comparison by converting to lower case
        str = str.toLowerCase();

        // Check if last word ends with y/z since we miss it when we loop
        int count = (str.endsWith("y") || str.endsWith("z")) ? 1 : 0;

        for (int idx = 0; idx < str.length(); ++idx) {
            // Check if we are at word boundary, handle first non-letter
            if (!Character.isLetter(str.charAt(idx)) && idx > 0) {
                // On word boundary, previous char will be last char of previous word
                char prev = str.charAt(idx - 1);
                count += (prev == 'y' || prev == 'z') ? 1 : 0;
            }
        }

        return count;
    }

    public String withoutString(String base, String remove) {
        // Simple regex based solution:
        // return base.replaceAll("(?i)" + remove, "");

        String baseLC = base.toLowerCase();
        String removeLC = remove.toLowerCase();

        int index = 0;
        int cursor = 0;
        StringBuilder build = new StringBuilder();

        while ((index = baseLC.indexOf(removeLC, cursor)) != -1) {
            build.append(base, cursor, index);
            cursor = index + remove.length();
        }

        if (cursor == 0) {
            return base;
        }

        build.append(base, cursor, base.length());
        return build.toString();
    }

    public boolean equalIsNot(String str) {
        /*
            Replacing all occurances of string we want to count from input will
            reduce the length of string by replace string times number of occurances
            Ex: "is" in "This is not" (length = 11)
            After replace: "Th  not" (length = 7)
            Reduction in length: 11 - 7 = 4
            Length of "is": 2
            => Count of "is": 4/2 = 2
        */
        int is = (str.length() - str.replace("is", "").length()) / 2;
        int not = (str.length() - str.replace("not", "").length()) / 3;
        return is == not;
    }

    /*
    We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' immediately to its left or right.
    Return true if all the g's in the given string are happy.

        gHappy("xxggxx") → true
        gHappy("xxgxx") → false
        gHappy("xxggyygxx") → false
    */
    public boolean gHappy(String str) {
        // Remove all 'g' clusters and check if we have any 'g' left.
        // If we have any 'g' left, they are long 'g' not part of cluster
        // return !str.replaceAll("g{2,}", "").contains("g");

        // Iterative approach:
        // Track cluster of 'g'. If we find a lone 'g', return false
        int cluster = 0;
        for (int idx = 0; idx < str.length(); ++idx) {
            if (str.charAt(idx) == 'g') {
                ++cluster;
            } else {
                if (cluster == 1) {
                    return false;
                }

                cluster = 0;
            }
        }

        // If cluster is 1, it is a long 'g'
        return cluster != 1;
    }

    public static void main(String[] args) {
        P7_CodingBat_String3 string3 = new P7_CodingBat_String3();

        System.out.println(string3.withoutString("This is a FISH", "IS"));

        System.out.println(string3.countYZ("fez day"));

        System.out.println(string3.gHappy("xxgggxygg"));
    }
}
