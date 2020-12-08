/*
 * Name: Krupa Dhruva
 * Date: December 08, 2020
 * Period: 7
 * Time Taken: 180 minutes
 *
 * Lab Reflection:
 * Deciding the right collections to use was a learning experience. I decided to use a Stack for
 * stack of cards since we always add to the top and remove top most cards and never from in between
 * Stack type allows me to look at the last card for ensuring rules without having to remove from
 * the collection to examine and put it back. Most of the time was spent in error handling and enforcing
 * game rules.
 *
 * I took help from my parent to build a lookup table to find the value given a symbol. Building a
 * list of cards that I use to compare the stack of cards to enforce 'clear' command is another area
 * I took help. I had coded it in a long form but found a much shorter way to implement it. I need to
 * spend more time to learn using Java streams.
 */

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes

    /** Deck of cards from which we build the initial stacks and the draw pile */
    private final Deck deck;

    /** Stacks of cards implemented as a List of card stacks */
    private final List<Stack<Card>> stacks;

    /** List of cards in sorted that resemble a stack that can be cleared */
    private static final List<Card> CLEAR_SORTED_STACK_COMPARATOR =
            Deck.makeDefaultCards().stream()
                    .peek(c -> c.setFaceUp(true))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

    /** Lookup table to get value for a given symbol since user inputs symbol (not value) */
    private final Map<String, Integer> SYMBOL_TO_VALUE;

    /**
     * Stores the error message and shows after displaying the stacks closer to user input and
     * visible area
     */
    private String lastError = "";

    /**
     * Sets up the Board and fills the stacks and draw pile from a Deck consisting of numDecks
     * Decks. Here are examples:
     *
     * <p># numDecks #cards in overall Deck 1 13 (all same suit) 2 26 (all same suit) 3 39 (all same
     * suit) 4 52 (all same suit)
     *
     * <p>Once the overall Deck is built, it is shuffled and half the cards are placed as evenly as
     * possible into the stacks. The other half of the cards remain in the draw pile.
     */
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        deck = new Deck();
        for (int count = 0; count < numDecks; ++count) {
            deck.add(Deck.makeDefaultCards());
        }

        // Shuffle the cards in the deck
        deck.shuffle();

        // REFLECTION: Sought help from parent to create a lookup
        SYMBOL_TO_VALUE =
                Board.CLEAR_SORTED_STACK_COMPARATOR.stream()
                        .collect(Collectors.toMap(Card::getSymbol, Card::getValue));

        stacks = new ArrayList<>(numStacks);
        int initialStackSize = deck.size() / (2 * numStacks);
        for (int cc = 0; cc < numStacks; ++cc) {
            Stack<Card> cards = new Stack<>();
            cards.addAll(deck.deal(initialStackSize));
            cards.peek().setFaceUp(true);
            stacks.add(cards);
        }
    }

    /**
     * Moves a run of cards from src to dest (if possible) and flips the next card if one is
     * available.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */

        // Our symbol lookup table is based on upper case, normalize the input
        symbol = symbol.toUpperCase();

        // Input validation
        if (!SYMBOL_TO_VALUE.containsKey(symbol)) {
            lastError = "move failed: Symbol [" + symbol + "] not found";
            return;
        }

        if (src < 0 || !(src < stacks.size())) {
            lastError =
                    String.format(
                            "move failed: Source stack %d should be in range [1, %d]",
                            src + 1, stacks.size());
            return;
        }

        if (dest < 0 || !(dest < stacks.size())) {
            lastError =
                    String.format(
                            "move failed: Destination stack %d should be in range [1, %d]",
                            dest + 1, stacks.size());
            return;
        }

        if (src == dest) {
            lastError = "move failed: Source and destination stacks cannot be same";
            return;
        }

        // Once we reach here, we have valid inputs. We now need to validate the move based on game
        // rules
        int symToVal = SYMBOL_TO_VALUE.get(symbol);

        // Check if move is valid on destination
        Stack<Card> destStack = stacks.get(dest);

        // The card stack we want to move should be 1 greater than last card in destination
        if (!destStack.isEmpty()) {
            Card lastCard = destStack.peek();
            if ((lastCard.getValue() - symToVal) != 1) {
                lastError =
                        String.format(
                                "move failed: Cannot stack higher/same value card [%s] on top of"
                                        + " [%s]",
                                symbol, lastCard.getSymbol());
                return;
            }
        }

        Stack<Card> sourceStack = stacks.get(src);

        // Find the top most entry matching the card to move and ensure it
        // is face up - we do not want to look at face down cards
        int index = sourceStack.lastIndexOf(new Card(symbol, symToVal, true));
        if (index == -1) {
            lastError =
                    String.format(
                            "move failed: Card with symbol [%s] not found in stack %d",
                            symbol, src + 1);
            return;
        }

        // Extract the sublist of cards we want to move - We get a view of cards in the stack, any
        // operation done on the sublist modifies the stack
        List<Card> cardsToMove = sourceStack.subList(index, sourceStack.size());

        // Very similar to pop quiz question: isAscending()
        for (int cc = 1; cc < cardsToMove.size(); ++cc) {
            if ((cardsToMove.get(cc - 1).getValue() - cardsToMove.get(cc).getValue()) != 1) {
                lastError = "move failed: Stack of cards to move is not in descending order";
                return;
            }
        }

        // Add cards in sublist to destination stack
        destStack.addAll(cardsToMove);

        // Remove cards from source since we have added to destination
        cardsToMove.clear();

        // After a successful move, turn the last card face up
        if (!sourceStack.isEmpty()) {
            sourceStack.peek().setFaceUp(true);
        }
    }

    /** Moves one card onto each stack, or as many as are available */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        Iterator<Card> cards = deck.deal(Math.min(stacks.size(), deck.size())).iterator();

        for (Stack<Card> stack : stacks) {
            if (!cards.hasNext()) {
                break;
            }

            Card card = cards.next();
            card.setFaceUp(true);
            stack.add(card);
        }
    }

    /** Returns true if all stacks and the draw pile are all empty */
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        if (deck.size() > 0) {
            return false;
        }

        for (Stack<Card> stack : stacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * If there is a run of A through K starting at the end of sourceStack then the run is removed
     * from the game or placed into a completed stacks area.
     *
     * <p>If there is not a run of A through K starting at the end of sourceStack then an invalid
     * move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        if (sourceStack < 0 || !(sourceStack < stacks.size())) {
            lastError =
                    String.format(
                            "clear failed: Source stack %d should be in range [1, %d]",
                            sourceStack + 1, stacks.size());
            return;
        }

        // Check if the stack can be cleared by comparing with the sorted stack we have
        List<Card> stack = stacks.get(sourceStack);
        if (CLEAR_SORTED_STACK_COMPARATOR.equals(stack)) {
            stack.clear();
        } else {
            lastError =
                    String.format(
                            "clear failed: Stack %d does not have cards A through K",
                            sourceStack + 1);
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw pile, and done stacks
     * (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks:");

        int count = 1;
        for (Stack<Card> stack : stacks) {
            System.out.println(count++ + ": " + stack);
        }

        System.out.println();
        System.out.println("Draw pile:");
        System.out.println(deck);

        if (!lastError.isEmpty()) {
            System.out.printf("%nLast error:%n%s%n", lastError);
            lastError = "";
        }
    }
}
