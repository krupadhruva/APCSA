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
        // The card real value for comparison:
        // It's value + Value of suite + Value of trump card

        int thisValue = getValue();

        // Since compareTo() returns the difference (can be negative value), we should add
        // it to any one of the cards. Multiplying by highest value will ensure the weight
        // for suit is greater than card value
        thisValue +=
                (getSuit().compareTo(other.getSuit()) * Math.max(getValue(), other.getValue()));

        int otherValue = other.getValue();

        // The value of trump card should be given highest weight, hence pick the highest value
        // of the cards so far. Adding it to any of the cards will make it much higher than the
        // weight of value or suit
        int trumpValue = thisValue + otherValue;

        if (isTrump()) {
            thisValue += trumpValue;
        }

        if (other.isTrump()) {
            otherValue += trumpValue;
        }

        return thisValue - otherValue;
    }
}
