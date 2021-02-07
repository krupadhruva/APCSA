public class P7_Dhruva_Krupa_Card implements Comparable<P7_Dhruva_Krupa_Card> {

    /* Attributes */
    private char symbol;
    private int value;
    private String suit;
    private boolean isTrump;

    /* Constructors */
    public P7_Dhruva_Krupa_Card(char symbol, int value, String suit) {
        this.symbol = symbol;
        this.value = value;
        this.suit = suit;
        this.isTrump = false;
    }

    public P7_Dhruva_Krupa_Card(char symbol, int value, String suit, boolean isTrump) {
        this.symbol = symbol;
        this.value = value;
        this.suit = suit;
        this.isTrump = isTrump;
    }

    /* Getters and Setters */
    public char getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isTrump() {
        return isTrump;
    }

    public void setTrump(boolean isTrump) {
        this.isTrump = isTrump;
    }

    /* Other methods */
    @Override
    public String toString() {
        return symbol + "" + suit.charAt(0);
    }

    @Override
    public int compareTo(P7_Dhruva_Krupa_Card other) {
        // Trump card takes precedence in deciding value of the card
        if (isTrump() ^ other.isTrump()) {
            return isTrump() ? 1 : -1;
        }

        // Suit takes next precedence when comparing cards
        final int suitValue = getSuit().compareTo(other.getSuit());
        if (suitValue != 0) {
            return suitValue;
        }

        // All else being equal, the actual value of the card decides
        return getValue() - other.getValue();
    }
}
