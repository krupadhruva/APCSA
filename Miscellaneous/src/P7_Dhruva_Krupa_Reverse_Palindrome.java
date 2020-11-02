/*
 * Name: Krupa Dhruva
 * Date: November 1, 2020
 * Period: 7
 * Time Taken: 20 minutes
 *
 * Lab Reflection:
 * I learned the idea of using StringBuilder with pre-allocated memory (capacity)
 * and also became aware of regular expressions. I got help from my father for this part.
 * Overall, this gave me an exposure to iterations and different building blocks. It was
 * fun to practice looping in reverse order and dealing with 0 index!
 */
public class P7_Dhruva_Krupa_Reverse_Palindrome {
    public static String stringReverseIterative(String str) {
        StringBuilder builder = new StringBuilder(str.length());
        for (int count = str.length() - 1; count >= 0; --count) {
            builder.append(str.charAt(count));
        }

        return builder.toString();
    }

    public static boolean isPalindrome(String str) {
        String clean = str.replaceAll("[^A-Za-z0-9]", "");
        return clean.equalsIgnoreCase(stringReverseIterative(clean));
    }

    public static void main(String[] args) {
        System.out.println(
                stringReverseIterative("Straw? No, too stupid a fad, I put soot on warts."));
    }
}
