import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class P7_Dhruva_Krupa_Life {
    private int generation;
    private Boolean[][] board;

    // Track various counts to avoid having to loop and count
    private final int[] rowCounts;
    private final int[] colCounts;
    private int totalCount;

    public P7_Dhruva_Krupa_Life(String fileName) throws FileNotFoundException {
        // Read file and get rows & columns
        try (Scanner scan = new Scanner(new File(fileName))) {
            if (!scan.hasNextInt()) {
                throw new InputMismatchException();
            }
            int rows = scan.nextInt();

            if (!scan.hasNextInt()) {
                throw new InputMismatchException();
            }
            int cols = scan.nextInt();

            board = new Boolean[rows][cols];
            for (Boolean[] booleans : board) {
                Arrays.fill(booleans, false);
            }

            rowCounts = new int[rows];
            Arrays.fill(rowCounts, 0);
            colCounts = new int[cols];
            Arrays.fill(colCounts, 0);

            totalCount = 0;

            // Initial generation count
            generation = 0;

            // Populate the board with values read from file
            while (scan.hasNextInt()) {
                int row = scan.nextInt();

                if (!scan.hasNextInt()) {
                    break;
                }
                int col = scan.nextInt();

                board[row][col] = true;
                ++rowCounts[row];
                ++colCounts[col];
                ++totalCount;
            }
        }
    }

    /**
     * Helper method to silently handle attempts to fetch neighbor values near the corners that will
     * lead to out of bound errors
     *
     * @param row Array row index
     * @param col Array column index
     * @return 1 if present OR 0
     */
    private int getValue(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return 0;
        }

        return board[row][col] ? 1 : 0;
    }

    private int getNeighborsValue(int row, int col) {
        int count = 0;

        // Row above
        count += getValue(row - 1, col - 1);
        count += getValue(row - 1, col);
        count += getValue(row - 1, col + 1);

        // Current row
        count += getValue(row, col - 1);
        count += getValue(row, col + 1);

        // Bottom row
        count += getValue(row + 1, col - 1);
        count += getValue(row + 1, col);
        count += getValue(row + 1, col + 1);

        return count;
    }

    public void nextGeneration() {
        Boolean[][] newBoard = new Boolean[board.length][board[0].length];
        for (Boolean[] booleans : newBoard) {
            Arrays.fill(booleans, false);
        }

        // Reset all counts since we recompute it as part transitioning to next generation
        totalCount = 0;
        Arrays.fill(rowCounts, 0);
        Arrays.fill(colCounts, 0);

        for (int ii = 0; ii < board.length; ++ii) {
            for (int jj = 0; jj < board[0].length; ++jj) {
                int count = getNeighborsValue(ii, jj);
                if (count == 2 || count == 3) {
                    // Continuation of life OR birth
                    if (board[ii][jj] || count == 3) {
                        newBoard[ii][jj] = true;
                        ++rowCounts[ii];
                        ++colCounts[jj];
                        ++totalCount;
                    }
                }
            }
        }

        // Transition to the next generation
        ++generation;
        board = newBoard;
    }

    public void runLife(int numGenerations) {
        while (generation < numGenerations) {
            nextGeneration();
        }
    }

    public void printBoard() {
        System.out.printf("%3c", ' ');
        for (int ii = 0; ii < board.length; ++ii) {
            System.out.printf("%3d", ii);
        }
        System.out.println();

        for (int ii = 0; ii < board.length; ++ii) {
            System.out.printf("%3d", ii);
            for (int jj = 0; jj < board[0].length; ++jj) {
                System.out.printf("%3c", board[ii][jj] ? '*' : '.');
            }

            System.out.println();
        }
    }

    public int rowCount(int row) {
        return (row < 0 || row >= rowCounts.length) ? -1 : rowCounts[row];
    }

    public int colCount(int col) {
        return (col < 0 || col >= colCounts.length) ? -1 : colCounts[col];
    }

    public int totalCount() {
        return totalCount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        P7_Dhruva_Krupa_Life life = new P7_Dhruva_Krupa_Life("life100.txt");

        // Test error handling
        assert -1 == life.colCount(-10);
        assert -1 == life.colCount(100);

        life.runLife(5);

        life.printBoard();
        System.out.println("The number of living cells in row 9 --> " + life.rowCount(9));
        System.out.println("The number of living cells in col 9 --> " + life.colCount(9));
        System.out.println("The number of living cells total --> " + life.totalCount());
    }
}
