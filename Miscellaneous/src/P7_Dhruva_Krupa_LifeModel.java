/*
 * Name: Krupa Dhruva
 * Date: April 14, 2021
 * Period: 7
 * Time Taken: 45 minutes
 *
 * Lab Reflection:
 * Refactoring to reuse grid from GridModel turned out to be a little
 * more complex than it should have been. I had optimized the code by
 * storing the number of live cells in each row and column to avoid
 * iterating for live cells in a row/column. In hind sight, I would
 * avoid that and keep my code simple.
 *
 * As part of refactoring, I got rid of the optimization and kept it
 * simple.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class P7_Dhruva_Krupa_LifeModel extends GridModel<Boolean> {
    private boolean gameOver;
    private int generation;
    private final ArrayList<GenerationListener> generationListeners;

    public P7_Dhruva_Krupa_LifeModel(Boolean[][] grid, int generation) {
        super(grid);

        gameOver = false;
        this.generation = generation;
        generationListeners = new ArrayList<>();
    }

    public P7_Dhruva_Krupa_LifeModel(String fileName) throws FileNotFoundException {
        this(makeBoard(fileName), 0);
    }

    public static Boolean[][] makeBoard(String fileName) {
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

            Boolean[][] board = new Boolean[rows][cols];
            for (Boolean[] booleans : board) {
                Arrays.fill(booleans, false);
            }

            // Populate the board with values read from file
            while (scan.hasNextInt()) {
                int row = scan.nextInt();

                if (!scan.hasNextInt()) {
                    break;
                }
                int col = scan.nextInt();

                board[row][col] = true;
            }

            return board;
        } catch (FileNotFoundException | InputMismatchException ex) {
            return new Boolean[0][0];
        }
    }

    public void addGenerationListener(GenerationListener l) {
        if (!generationListeners.contains(l)) {
            generationListeners.add(l);
        }
    }

    public void removeGenerationListener(GenerationListener l) {
        generationListeners.remove(l);
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
        if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols()) {
            return 0;
        }

        return getValueAt(row, col) ? 1 : 0;
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

    public void setGrid(Boolean[][] grid) {
        super.setGrid(grid);
        gameOver = false;
        setGeneration(0);
    }

    public void setValueAt(int row, int col, boolean value) {
        gameOver = false;
        super.setValueAt(row, col, value);
    }

    public void nextGeneration() {
        final Boolean[][] newBoard = new Boolean[getNumRows()][getNumCols()];
        for (Boolean[] booleans : newBoard) {
            Arrays.fill(booleans, false);
        }

        for (int row = 0; row < getNumRows(); ++row) {
            for (int col = 0; col < getNumCols(); ++col) {
                int count = getNeighborsValue(row, col);
                if (count == 2 || count == 3) {
                    // Continuation of life OR birth
                    if (getValueAt(row, col) || count == 3) {
                        newBoard[row][col] = true;
                    }
                }
            }
        }

        // Transition to the next generation
        gameOver = true;
        for (int row = 0; row < getNumRows(); ++row) {
            for (int col = 0; col < getNumCols(); ++col) {
                if (gameOver) {
                    gameOver = getValueAt(row, col) == newBoard[row][col];
                }

                setValueAt(row, col, newBoard[row][col]);
            }
        }

        if (!gameOver) {
            setGeneration(getGeneration() + 1);
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int newGeneration) {
        int oldGeneration = generation;
        generation = newGeneration;

        if (newGeneration != oldGeneration) {
            for (GenerationListener l : generationListeners) {
                l.generationChanged(oldGeneration, newGeneration);
            }
        }
    }

    public void runLife(int numGenerations) {
        while (generation < numGenerations) {
            nextGeneration();
        }
    }

    public void printBoard() {
        System.out.printf("%3c", ' ');
        for (int row = 0; row < getNumRows(); ++row) {
            System.out.printf("%3d", row);
        }
        System.out.println();

        for (int row = 0; row < getNumRows(); ++row) {
            System.out.printf("%3d", row);
            for (int col = 0; col < getNumCols(); ++col) {
                System.out.printf("%3c", getValueAt(row, col) ? '*' : '.');
            }

            System.out.println();
        }
    }

    public int rowCount(int row) {
        int count = -1;
        if (row >= 0 && row < getNumRows()) {
            count = 0;
            for (int col = 0; col < getNumCols(); ++col) {
                if (getValueAt(row, col)) {
                    ++count;
                }
            }
        }

        return count;
    }

    public int colCount(int col) {
        int count = -1;
        if (col >= 0 && col < getNumCols()) {
            count = 0;
            for (int row = 0; row < getNumRows(); ++row) {
                if (getValueAt(row, col)) {
                    ++count;
                }
            }
        }

        return count;
    }

    public int totalCount() {
        int count = 0;
        for (int row = 0; row < getNumRows(); ++row) {
            count += rowCount(row);
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        P7_Dhruva_Krupa_LifeModel life = new P7_Dhruva_Krupa_LifeModel("happy.txt");

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
