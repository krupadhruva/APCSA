public class CardTester {
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */

        Card one = new Card("1", 1);
        Card two = new Card("2", 2);

        if (!one.equals(one)) {
            throw new AssertionError();
        }

        if (one.getValue() != 1) {
            throw new AssertionError();
        }

        if (!one.getSymbol().equals("1")) {
            throw new AssertionError();
        }

        if (one.compareTo(two) != -1) {
            throw new AssertionError();
        }

        if (two.compareTo(one) != 1) {
            throw new AssertionError();
        }

        if (one.isFaceUp()) {
            throw new AssertionError();
        }

        // face down is default
        if (!one.toString().equals("X")) {
            throw new AssertionError();
        }

        one.setFaceUp(true);
        if (!one.isFaceUp()) {
            throw new AssertionError();
        }

        if (!one.toString().equals(one.getSymbol())) {
            throw new AssertionError();
        }

        System.out.println("All tests succeeded!");
    }
}
