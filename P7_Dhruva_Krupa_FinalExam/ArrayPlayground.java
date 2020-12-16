import java.util.*;

public class ArrayPlayground {

    public static void main(String[] args) {

        // Testing forwardReverse
        System.out.println(forwardReverse("aTopPotKitchen", "top"));
        
        // Testing Customer toString
        Customer c = new Customer("Michael", "Wang", 123456, true);
        System.out.println(c);
        
        Database d1 = new Database(5);
        d1.add(new Customer("Ade", "Zeke", 123456, true));
        d1.add(new Customer("Ben", "Ranu", 9458774, false));
        d1.add(new Customer("Kat", "Chou", 4879611, false));
        d1.add(new Customer("Siv", "Sala", 67455211, true));

        Database d2 = new Database(3);
        d2.add(new Customer("Raj", "Bala", 654321, true));
        d2.add(new Customer("Zak", "Unon", 749234, false));

        Database d3 = new Database(1);
        d3.add(new Customer("Har", "Gnus", 000000, true));
        d3.add(new Customer("Qin", "Rius", 000234, false));
        d3.add(new Customer("Lev", "Taki", 423400, true));

        // Testing Database size method
        System.out.println(d1 + " has size " + d1.size());
        
        // Testing numGoldMembers method
        System.out.println("Gold members in database is " + d1.numGoldMembers());
        
        // Testing isSorted method
        System.out.println("d1 isSorted " + d1.isSorted());
        System.out.println("d3 isSorted " + d3.isSorted());
        
        MasterCollection master = new MasterCollection();
        master.add(d1);
        master.add(d2);
        master.add(d3);
        
        // Testing allGoldMembers of MasterCollection
        System.out.println(master.allGoldMembers());
        
        // Testing replaceWords
        System.out.println(replaceWords("the bigger the bold and the big bold"));        
    }

    private static String reverseString(String target) {
        // Reverse the string
        StringBuilder builder = new StringBuilder();
        builder.append(target.toLowerCase());
        builder.reverse();

        return builder.toString();
    }

    // Problem 1
    public static boolean forwardReverse(String str, String target) {
        // Your code here

        String match = target + reverseString(target);
        return str.toLowerCase().contains(match);
    }

    static int indexOfNextWord(String str, int startIndex) {
        for (int pos = startIndex; pos < str.length(); ++pos) {
            if (Character.isLetter(str.charAt(pos))) {
                return pos;
            }
        }

        return -1;
    }

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

    static class Word {
        public String word;
        public int index;

        public Word(String word, int index) {
            this.word = word;
            this.index = index;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null || getClass() != other.getClass()) return false;
            return word.equals(((Word)other).word);
        }
    }

    // Problem 10 (Work on this LAST)
    public static String replaceWords(String str) {
        StringBuilder out = new StringBuilder();
        ArrayList<Word> words = new ArrayList<>();

        int index = 0;
        while (-1 != (index = indexOfNextWord(str, index))) {
            String w = getWord(str, index);

            // Compare word
            Word cw = new Word(w, 0);

            // Search in word list
            boolean found = false;
            for (Word word : words) {
                if (word.equals(cw)) {
                    out.append(String.valueOf(word.index));
                    found = true;
                    break;
                }
            }

            if (!found) {
                out.append(w);
                words.add(new Word(w, index));
            }

            index += w.length();
            out.append(" ");
        }
        
        return out.toString();
    }
}
