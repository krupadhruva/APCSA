public class DeckTester {
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        if (symbols.length != values.length) {
            throw new AssertionError();
        }

        Deck deck = new Deck();
        for (int cc = 0; cc < symbols.length; ++cc) {
            deck.add(new Card(symbols[cc], values[cc]));
        }

        System.out.println("Before shuffle: " + deck);
        deck.shuffle();
        System.out.println("After shuffle : " + deck);
    }
}
