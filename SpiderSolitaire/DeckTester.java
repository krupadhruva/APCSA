import java.io.*;
import java.util.Collection;

public class DeckTester {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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

        // Saving objects to file:
        // https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
        File stateFile = new File("deck.state");
        OutputStream outStrm = new FileOutputStream(stateFile);
        ObjectOutputStream out = new ObjectOutputStream(outStrm);
        out.writeObject(deck);
        out.close();
        outStrm.close();

        InputStream inStrm = new FileInputStream(stateFile);
        ObjectInputStream in = new ObjectInputStream(inStrm);
        Deck loadedDeck = (Deck) in.readObject();
        in.close();
        inStrm.close();

        boolean deleted = stateFile.delete();
        assert deleted;

        System.out.println("Saved  deck : " + deck.debugToString());
        System.out.println("Loaded deck : " + loadedDeck.debugToString());
        assert deck.equals(loadedDeck);
    }
}
