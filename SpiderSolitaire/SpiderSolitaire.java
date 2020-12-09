import java.util.InputMismatchException;
import java.util.Scanner;

public class SpiderSolitaire {
    /** Number of stacks on the board * */
    public final int NUM_STACKS = 7;

    /**
     * Number of complete decks used in this game. A 1-suit deck, which is the default for this lab,
     * consists of 13 cards (Ace through King).
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile * */
    private Board board;

    /** Used for keyboard input * */
    private final Scanner input;

    public SpiderSolitaire() {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits * */
    public void play() {
        while (!board.isEmpty()) {
            board.printBoard();

            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            try {
                switch (command) {
                    case "move":
                        {
                            /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                            String symbol = input.next();
                            int sourceStack = input.nextInt();
                            int destinationStack = input.nextInt();
                            board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                            break;
                        }
                    case "draw":
                        board.drawCards();
                        break;
                    case "clear":
                        {
                            /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                            int sourceStack = input.nextInt();
                            board.clear(sourceStack - 1);
                            break;
                        }
                    case "restart":
                        board = new Board(NUM_STACKS, NUM_DECKS);
                        break;
                    case "save":
                        board.saveBoard();
                        break;
                    case "load":
                        Board loadedBoard = Board.loadBoard();
                        // On error, we get a null
                        if (loadedBoard != null) {
                            board = loadedBoard;
                        }
                        break;
                    case "quit":
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        Board.setLastError(String.format("Invalid command \"%s\"", command));
                        break;
                }
            } catch (InputMismatchException ignored) {
                Board.setLastError(
                        String.format(
                                "Invalid input: expecting integer, received input \"%s\" for"
                                        + " command \"%s\"",
                                input.next(), command));

                // Clear pending tokens in current input
                input.nextLine();
            }
        }

        System.out.println("Congratulations!  You win!");
    }
}
