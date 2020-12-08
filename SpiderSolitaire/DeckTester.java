import java.util.Collection;

public class DeckTester {
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        Deck deck = new Deck();
        deck.add(Deck.makeDefaultCards());

        System.out.println("Before shuffle: " + deck.debugToString());
        deck.shuffle();
        System.out.println("After shuffle : " + deck.debugToString());

        Collection<Card> dealt = deck.deal(5);
        dealt.forEach(c -> c.setFaceUp(true));
        System.out.println("Dealt cards   : " + dealt);

        // Check equality of list of cards
        assert Deck.makeDefaultCards().equals(Deck.makeDefaultCards());
    }
}
