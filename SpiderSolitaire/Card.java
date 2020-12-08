/*
 * Name: Krupa Dhruva
 * Date: December 02, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Details are in class Deck
 */

/** Card.java <code>Card</code> represents a basic playing card. */
public class Card implements Comparable<Card> {
    /**
     * String value that holds the symbol of the card. Examples: "A", "Ace", "10", "Ten", "Wild",
     * "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */
    public Card(String symbol, int value) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        this(symbol, value, false);
    }

    /**
     * Underlying constructor allowing state for all attributes
     *
     * @param symbol a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     * @param isFaceUp a <code>boolean</code> value indicating if card is facing up
     */
    public Card(String symbol, int value, boolean isFaceUp) {
        this.symbol = symbol.toUpperCase();
        this.value = value;
        this.isFaceUp = isFaceUp;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     *
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return symbol;
    }

    /**
     * Gets the value of the card
     *
     * @return Value of card
     */
    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return value;
    }

    /**
     * Returns the state of the card face
     *
     * @return True if card is facing up
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Set the face state of the card
     *
     * @param state True is card face is up
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *
     * @return whether or not this Card is equal to other.
     */
    @Override
    public boolean equals(Object other) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return other instanceof Card
                && compareTo((Card) other) == 0
                && isFaceUp() == ((Card) other).isFaceUp();
    }

    /**
     * Returns this card as a String. If the card is face down, "X" is returned. Otherwise the
     * symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X, depending on whether the card
     *     is face up or down.
     */
    @Override
    public String toString() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return isFaceUp() ? getSymbol() : "X";
    }

    /**
     * Implements method from interface Comparable on Card type
     *
     * @param other The card to compare against
     * @return Difference between current card value and the other card value
     */
    @Override
    public int compareTo(Card other) {
        return getValue() - other.getValue();
    }
}
