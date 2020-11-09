public class P7_CodingBat_BigWord {

    /*
     The index to search from can either be at start, beyond the end or in-between

     * If beyond the string, we return -1 since there is nothing beyond
     * Since the start index will never be in-between a word,
       we loop till we find the first character and return the index
    */
    static int indexOfNextWord(String str, int startIndex) {
        for (int pos = startIndex; pos < str.length(); ++pos) {
            if (Character.isLetter(str.charAt(pos))) {
                return pos;
            }
        }

        return -1;
    }

    /*
     Since we know the start index is at the beginning of a word, we will
     loop till we find a non-character.

     Accumulate characters and return the final result on encountering non-character
     or exhausting all input.
    */
    static String getWord(String str, int startIndex) {
        String result = "";
        for (int pos = startIndex; pos < str.length(); ++pos) {
            if (Character.isLetter(str.charAt(pos))) {
                result += str.charAt(pos);
            } else {
                break;
            }
        }

        return result;
    }

    /*
     Loop from start till less than end and input length

     Accumulate characters that are not '@' and return the result.
    */
    static String substringWithoutAt(String str, int startIndex, int endIndex) {
        String result = "";
        for (int pos = startIndex; pos < endIndex && pos < str.length(); ++pos) {
            if (str.charAt(pos) != '@') {
                result += str.charAt(pos);
            }
        }

        return result;
    }

    /*
     Using recursion with a boundary case of string length less than or equal to 1,
     return the input as it is - there is nothing to reverse

     Take the first character and append it to the result of recursive call on
     string with first character removed (since we are appending it).
    */
    static String reverseStr(String str) {
        if (str.length() <= 1) {
            return str;
        }

        return reverseStr(str.substring(1)) + str.charAt(0);
    }

    /*
     Loop through the string and examine if each character is a vowel in both
     upper and lower case using a switch. If it is a vowel, replace it with '*' or
     retain the same character.

     Accumulate the characters in a result string and return.

     Note: I became aware of regular expression way of doing it but have not
     understood it sufficiently.
    */
    static String vowelsToStars(String str) {
        // return str.replaceAll("[AaEeIiOoUu]", "*");

        String result = "";
        for (int pos = 0; pos < str.length(); ++pos) {
            char c = str.charAt(pos);
            switch (c) {
                case 'a':
                case 'A':
                case 'e':
                case 'E':
                case 'i':
                case 'I':
                case 'o':
                case 'O':
                case 'u':
                case 'U':
                    c = '*';
            }

            result += c;
        }

        return result;
    }

    /*
     Let's say a word is defined as a substring of a string that has either no character or
     a non-letter character before and after it (i.e. in "This is a word", the words are "This", "is", "a", and "word".
     You can use Character.isLetter(char) to find out if a character is a letter.

     Given a string, reverse all words and replace all vowels ('a', 'e', 'i', 'o', 'u'),
     ignoring case with "*" (i.e. "Code And Live" reverses to "edoC dnA eviL", which becomes "*d*C dn* *v*L").

     However, if a word is preceded by "@", then don't replace the vowels and if it is followed by an "@",
     then don't reverse the word, but in either case, remove the "@".

     reverseStarVowels("To live or die?") → "*T *v*l r* **d?"
     reverseStarVowels("@Cafe Babe@") → "efaC B*b*"
     reverseStarVowels("@wicked@") → "wicked"
    */
    public static String reverseStarVowels(String str) {
        StringBuilder builder = new StringBuilder();

        int cursor = 0;
        int nextIndex = 0;

        while ((nextIndex = indexOfNextWord(str, cursor)) != -1) {
            // Get the next word based on index of next word
            String word = getWord(str, nextIndex);

            // Get the non-word between previous and next word and strip leading and trailing '@'
            // since they belong to previous or next word - they are not dangling
            String nonWord = str.substring(cursor, nextIndex).replaceAll("(^@|@$)", "");

            // Update cursor position to find next word
            cursor = nextIndex + word.length();

            nextIndex = (nextIndex > 0) ? nextIndex : 1;
            char preCh = str.charAt(nextIndex - 1);
            char postCh = (cursor < str.length()) ? str.charAt(cursor) : ' ';

            // Check if we should skip replacing vowels
            if (preCh != '@') {
                word = vowelsToStars(word);
            }

            // Check if we should skip reversing the string
            if (postCh != '@') {
                word = reverseStr(word);
            } else {
                // '@' belongs to previous word and not dangling, skip it
                cursor += 1;
            }

            builder.append(nonWord).append(word);
        }

        return builder.append(str.substring(cursor)).toString();
    }

    public static void main(String[] args) {
        String input = "rev it@ @up, @dude@! Much$$$2BHad!?";
        String expected = "v*r *t pu, dude! hc*M$$$2d*HB!?";

        input = "hi @ at not @djacent oh $%@";
        expected = "*h @ t* t*n tnecajd h* $%@";

        input = "happy@birthday";
        expected = "h*ppyyadhtrib";

        String result = reverseStarVowels(input);

        System.out.printf("Input   : %s%nExpected: %s%nResult  : %s%n", input, expected, result);
        assert expected.equals(result);
    }
}
