/*
 * Name: Krupa Dhruva
 * Date: February 14, 2021
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * I was able to use the different algorithms I learned as part of this project. Since I had used Scanner
 * with a custom pattern for word delimiter, I implemented the solution using that approach first.
 * However, since regular expressions is not covered in our course, I implemented a function to
 * sanitize the word after reading from the default Scanner instance.
 *
 * Implementing different sorting based on 2 different fields was challenging. I did not want to
 * created multiple lists and duplicate entries. I took help from my parent to pass the comparator
 * as an argument to the sort function. I was able to use the same sorting function and specialize
 * based on comparator function. With this, I am able to use a single list of unique words based
 * on word search and finally sort based on word frequency to print the final output of top repeating
 * words in a file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class P7_Dhruva_Krupa_CountWordsDriver {
    static class WordFrequency {
        private final String word;
        private Integer count;

        WordFrequency(String word) {
            this.word = word;
            this.count = 1;
        }

        public void increment() {
            ++count;
        }

        public Integer getCount() {
            return count;
        }

        // Compare based on word for detecting and counting duplicates
        public static int wordCompare(Object lhs, Object rhs) {
            assert lhs instanceof WordFrequency && rhs instanceof WordFrequency;

            return ((WordFrequency) (lhs)).word.compareTo(((WordFrequency) (rhs)).word);
        }

        // Compare based on word frequency to print top 30 frequent words
        public static int countCompare(Object lhs, Object rhs) {
            assert lhs instanceof WordFrequency && rhs instanceof WordFrequency;

            // We want reverse sorting (descending order)
            int diff = ((WordFrequency) (rhs)).count - ((WordFrequency) (lhs)).count;
            if (diff == 0) {
                return wordCompare(lhs, rhs);
            }

            return diff;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof WordFrequency) {
                WordFrequency casted = (WordFrequency) other;
                return word.equals(casted.word);
            }

            return false;
        }

        @Override
        public String toString() {
            return String.format("%s:%d", word, count);
        }
    }

    private int totalWords;
    private final List<WordFrequency> words;

    public P7_Dhruva_Krupa_CountWordsDriver() {
        words = new ArrayList<>();
    }

    public List<WordFrequency> getWords() {
        return words;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void sort(Comparator<WordFrequency> cmp, int first, int last) {
        int mid = first + (last - first) / 2;
        if (mid == first) {
            return;
        }

        sort(cmp, first, mid);
        sort(cmp, mid, last);

        for (int ii = first; ii < mid && mid < last; ++ii) {
            final WordFrequency self = words.get(ii);
            final WordFrequency other = words.get(mid);

            if (cmp.compare(self, other) > 0) {
                words.remove(mid);
                words.add(ii, other);
                ++mid;
            }
        }
    }

    public WordFrequency search(WordFrequency word, int first, int last) {
        if (words.isEmpty()) {
            return null;
        }

        int low = WordFrequency.wordCompare(word, words.get(first));
        int high = WordFrequency.wordCompare(word, words.get(last));

        if (low < 0 || high > 0) {
            return null;
        } else if (low == 0) {
            return words.get(0);
        } else if (high == 0) {
            return words.get(last);
        }

        int mid = first + (last - first) / 2;
        if (mid == first) {
            return null;
        }

        WordFrequency result = search(word, first, mid);
        if (result == null) {
            result = search(word, mid, last);
        }

        return result;
    }

    public void add(String token) {
        ++totalWords;

        WordFrequency word = new WordFrequency(token);
        WordFrequency entry = search(word, 0, words.size() - 1);
        if (entry != null) {
            entry.increment();
        } else {
            words.add(word);
            sort(WordFrequency::wordCompare, 0, words.size());
        }
    }

    @Override
    public String toString() {
        return words.toString();
    }

    private static String sanitizeWord(final String word) {
        // Find first index of letter character (remove leading non-letter characters)
        int start = 0;
        while (start < word.length()
                // Word can start with apostrophe
                && word.charAt(start) != '\''
                && !Character.isLetter(word.charAt(start))) {
            ++start;
        }

        // Find last index of letter character (remove trailing non-letter characters)
        int end = word.length() - 1;
        while (end >= 0
                // Word can end with apostrophe
                && word.charAt(end) != '\''
                && !Character.isLetter(word.charAt(end))) {
            --end;
        }

        // Since substring upper bound is exclusive, it should be 1 more than last index we want
        ++end;

        // Extract substring only if we have seen leading/trailing non-letters
        if ((start > 0 || end < word.length())) {
            // Handle single character non-letter
            if (start >= end) {
                return "";
            }

            return word.substring(start, end);
        }

        return word;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File(args.length > 1 ? args[0] : "dream.txt");
        Scanner scan = new Scanner(input);

        // Using regex to specify word delimiters
        // scan.useDelimiter("( - )|([ \t\r\n.,:;!?\"])");

        P7_Dhruva_Krupa_CountWordsDriver driver = new P7_Dhruva_Krupa_CountWordsDriver();

        while (scan.hasNext()) {
            String word = scan.next();

            // Sanitize word to remove leading/trailing non-letters conforming to problem rules
            // If we use the regex as word delimiter in Scanner, we do not need this function
            word = sanitizeWord(word);

            if (!word.isEmpty()) {
                driver.add(word.toLowerCase());
            }
        }
        scan.close();

        // Sort the words based on word frequency in descending order
        driver.sort(WordFrequency::countCompare, 0, driver.words.size());

        // Display the top 30 words based on frequency
        for (int count = 0; count < 30 && count < driver.getWords().size(); ++count) {
            WordFrequency word = driver.getWords().get(count);
            System.out.printf("%5d\t%5d\t%s%n", count + 1, word.count, word.word);
        }

        System.out.println("\n\nNumber of unique words used = " + driver.getWords().size());
        System.out.println("Total # of words = = " + driver.getTotalWords());
    }
}
