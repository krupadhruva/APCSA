import java.util.Arrays;

public class P7_Dhruva_Krupa_CardTester {

    public static void main(String[] args) {

        char[] symbols = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        P7_Dhruva_Krupa_Card[] cards = new P7_Dhruva_Krupa_Card[symbols.length * suits.length];
        int counter = 0;

        for (int j = 0; j < suits.length; j++) {
            for (int i = 0; i < symbols.length; i++) {
                cards[counter] = new P7_Dhruva_Krupa_Card(symbols[i], values[i], suits[j]);
                counter++;
            }
        }

        System.out.println("Testing your implementation of the Comparable interface");
        for (int i = 0; i < 10; i++) {
            P7_Dhruva_Krupa_Card c1 = cards[(int) (Math.random() * cards.length)];
            P7_Dhruva_Krupa_Card c2 = cards[(int) (Math.random() * cards.length)];
            System.out.println(c1 + ".compareTo(" + c2 + ") returned " + c1.compareTo(c2));
        }

        System.out.println(
                "Before shuffling (this is what the deck looks like in ascending order):");
        System.out.println(Arrays.toString(cards));

        shuffle(cards);
        System.out.println("\nAfter shuffling:\n" + Arrays.toString(cards));

        Arrays.sort(cards);
        System.out.println("\nAfter sorting with Java's built-in sort:\n" + Arrays.toString(cards));

        shuffle(cards);
        bubbleSort(cards);
        System.out.println("\nAfter sorting with your Bubble Sort:\n" + Arrays.toString(cards));
    }

    public static void shuffle(P7_Dhruva_Krupa_Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int randIndex = (int) (Math.random() * cards.length);
            P7_Dhruva_Krupa_Card temp = cards[i];
            cards[i] = cards[randIndex];
            cards[randIndex] = temp;
        }
    }

    public static void bubbleSort(P7_Dhruva_Krupa_Card[] cards) {
        for (int ii = 0; ii < cards.length - 1; ++ii) {
            for (int jj = cards.length - 1; jj > ii; --jj) {
                if (cards[jj].compareTo(cards[jj - 1]) < 0) {
                    P7_Dhruva_Krupa_Card tmp = cards[jj];
                    cards[jj] = cards[jj - 1];
                    cards[jj - 1] = tmp;
                }
            }
        }
    }
}
