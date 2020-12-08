/*
 * Name: Krupa Dhruva
 * Date: December 02, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Since I have not played card games, it took a while for me to understand the meaning of "suit"
 * and the spider solitaire game. The video of game play was very instructive and helped me get
 * a better understanding of the complexities involved in the game.
 * Implementing missing details in Card class provided a gradual introduction into implementing
 * the building blocks for the game.
 * Designing the Deck class was a good learning experience. Starting from figuring out the various
 * methods to support, attributes required and implementing the shuffle method was a good exercise.
 * I took help from my parent in selecting the appropriate collection class for storing the deck
 * of cards. After initial implementation using List, we decided on using Stack since cards are always
 * dealt from the top.
 */

import java.util.*;

public class Deck {
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */

    private final Stack<Card> deck;
    private final int SHUFFLE_COUNT = 100;

    /** Default constructor for the Deck of cards */
    public Deck() {
        deck = new Stack<>();
    }

    /**
     * Insert a card into the deck of cards
     *
     * <p>Sets the card face down since the deck should have all cards with face down
     *
     * @param card Instance of a card to be added to the deck of cards
     */
    public void add(Card card) {
        card.setFaceUp(false);
        deck.add(card);
    }

    /**
     * Adds multiple cards to the deck of cards
     *
     * @param cards Handle to a collection of cards to be added to the deck
     */
    public void add(Iterable<Card> cards) {
        for (Card card : cards) {
            deck.add(card);
        }
    }

    /** Shuffles the deck of cards */
    public void shuffle() {
        PrimitiveIterator.OfInt ints = new Random().ints(0, deck.size() - 1).iterator();
        for (int count = 0; count < Math.max(deck.size(), SHUFFLE_COUNT); ++count) {
            Card card = deck.pop();
            deck.add(ints.nextInt(), card);
        }
    }

    /**
     * Deals the cards from the deck
     *
     * <p>Cards returned are with face turned up
     *
     * @param count Number of cards to deal
     * @return List of cards with face turned up
     */
    public Collection<Card> deal(int count) {
        if (count == 0 || count > deck.size()) {
            return Collections.emptyList();
        }

        List<Card> result = new ArrayList<>(count);
        for (int cc = 0; cc < count; ++cc) {
            result.add(deck.pop());
        }

        return result;
    }

    /**
     * Helper function to return the number of cards in the deck
     *
     * @return Number of cards in deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Overrides string representation of the Deck class to print the cards in deck using the string
     * representation of Card
     *
     * <p>The card details are printed
     *
     * @return String representation of Deck
     */
    @Override
    public String toString() {
        return deck.toString();
    }

    /**
     * Used for debugging, will show card details
     *
     * @return <code>String</code> representation of cards in deck
     */
    public String debugToString() {
        List<String> symbols = new ArrayList<>(deck.size());
        for (Card card : deck) {
            symbols.add(
                    String.format(
                            "%s:%d:%s",
                            card.getSymbol(), card.getValue(), card.isFaceUp() ? "up" : "down"));
        }

        return symbols.toString();
    }

    /**
     * Helper method to make a deck of cards (single suit)
     *
     * @return List of cards
     */
    public static List<Card> makeDefaultCards() {
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        // Run with JVM option '-ea'
        assert symbols.length == values.length;

        List<Card> cards = new ArrayList<>();
        for (int cc = 0; cc < symbols.length; ++cc) {
            cards.add(new Card(symbols[cc], values[cc]));
        }

        return cards;
    }
}
