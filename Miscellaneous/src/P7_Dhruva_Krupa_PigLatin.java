/*
 * Name: Krupa Dhruva
 * Date: November 15, 2020
 * Period: 7
 * Time Taken: 90 minutes
 *
 * Lab Reflection:
 * For splitting the words, I checked to find the first character of the string
 * and continued to read the characters until there was a non-letter character.
 * This will extract a word from the given sentence. I used a different
 * function, "pigLatinizeWord()" for actually constructing the pig-latinated
 * word. Overall, coming up with the logic for doing this was a bit challenging
 * but breaking down the problem into smaller tasks made it easier to visualize
 * how I to solve this.
 *
 * Based on the rules specified, I feel the expected output is incorrect:
 *
 * Input:           "Hey you Do you know how to speak in Pig Latin"
 * Expected output: "Eyhay ouyay Oday ouyay owknay owhay otay eakspay inyay Igpay Atinlay"
 * My output:       "Eyhay ouyay Oday ouyay nowkay owhay otay peaksay inyay Igpay Atinlay"
 *
 * In the above example where string has a vowel but does not start with vowel:
 * "Hey" becomes "Eyhay", but
 * "speak" is shown to transform to "eakspay". However, as per the rule followed to transform
 * "Hey", it should have become "peaksay"
 */
public class P7_Dhruva_Krupa_PigLatin {
    private static final String VOWELS = "aeiouAEIOU";

    private String pigLatinizeWord(String word) {
        boolean shouldWeCapitalize = Character.isUpperCase(word.charAt(0));
        boolean containsVowels = false;
        for (int idx = 0; !containsVowels && idx < VOWELS.length(); ++idx) {
            containsVowels = word.indexOf(VOWELS.charAt(idx)) != -1;
        }

        if (containsVowels) {
            if (VOWELS.indexOf(word.charAt(0)) != -1) {
                word = word + "yay";
            } else {
                word = word.substring(1) + word.charAt(0) + "ay";
            }
        }

        if (shouldWeCapitalize) {
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
        }

        return word;
    }

    public String translateToPigLatin(String str) {
        StringBuilder builder = new StringBuilder();

        String word;
        int startWord = -1;

        for (int idx = 0; idx < str.length(); ++idx) {
            char chr = str.charAt(idx);

            if (Character.isLetter(chr)) {
                if (startWord == -1) {
                    startWord = idx;
                }
            } else {
                if (startWord == -1) {
                    builder.append(chr);
                    continue;
                }

                word = str.substring(startWord, idx);
                word = pigLatinizeWord(word);
                builder.append(word);
                builder.append(chr);

                // We have processed the word, reset the start index of the word
                startWord = -1;
            }
        }

        // Process the last word since we would have come out of loop if last character is a letter
        if (startWord != -1) {
            word = str.substring(startWord);
            word = pigLatinizeWord(word);
            builder.append(word);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_PigLatin pigLatin = new P7_Dhruva_Krupa_PigLatin();
        String expected = "Eyhay ouyay! Oday ouyay owknay owhay otay eakspay inyay Igpay Atinlay?";
        String result =
                pigLatin.translateToPigLatin("Hey you! Do you know how to speak in Pig Latin?");
        System.out.println(result);

        // Run with JVM option "-ea" or "-enableassertions"
        assert expected.equals(result);
    }
}
