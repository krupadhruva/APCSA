/*
 * Name: Krupa Dhruva
 * Date: March 02, 2021
 * Period: 7
 * Time Taken: 120 minutes
 *
 * Lab Reflection:
 * My earlier implementation attempting to make it easy when erasing made it very complex.
 * I was trying to keep lists of connected cells. This became difficult when a cell becomes
 * connected at a later point if input data in file is not in a specified order.
 *
 * Implementing a recursive method to erase made the whole implementation lot simpler.
 * However, it is not optimal for erasing since I will have to search the surroundings to
 * find all connected cells.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class P7_Dhruva_Krupa_EraseObject {
    /** 2D grid of pixels */
    private final Boolean[][] grid;

    /**
     * Helper class to represent a cell in the grid of pixels
     *
     * <p>Using Point class was confusing in trying to translate 'x' and 'y' to row and column
     */
    static class Position {
        private final int row;
        private final int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private P7_Dhruva_Krupa_EraseObject(int dim) {
        grid = new Boolean[dim][dim];
        for (var rows : grid) {
            Arrays.fill(rows, false);
        }
    }

    /**
     * Helper method to generate all possible neighbor cells. Some cells may be out of bounds,
     * validate is isPositionInValid()
     *
     * @param pos Cell position to find all neighbors
     * @return Array of all neighbors (can go beyond grid), caller should check validity of position
     */
    private Position[] getAllNeighborCells(Position pos) {
        return new Position[] {
            new Position(pos.row - 1, pos.col),
            new Position(pos.row, pos.col - 1),
            new Position(pos.row, pos.col + 1),
            new Position(pos.row + 1, pos.col)
        };
    }

    /**
     * Helper method to validate if a position is valid inside the grind
     *
     * @param pos Position to be validated
     * @return True if invalid
     */
    private boolean isPositionInValid(Position pos) {
        return (pos.row < 0 || pos.col < 0 || pos.row >= grid.length || pos.col >= grid[0].length);
    }

    /**
     * Mark a cell as painted in the grid
     *
     * @param pos Position of cell to be marked
     */
    private void add(Position pos) {
        if (isPositionInValid(pos)) {
            return;
        }

        grid[pos.row][pos.col] = true;
    }

    /**
     * Recursively erase connected cells
     *
     * @param pos Start position to erase connected cells
     */
    private void erase(Position pos) {
        if (isPositionInValid(pos) || !grid[pos.row][pos.col]) {
            return;
        }

        // Erase the current cell before recursive call to avoid revisiting this cell leading to
        // stack overflow due to unbounded recursion
        grid[pos.row][pos.col] = false;

        for (var nc : getAllNeighborCells(pos)) {
            erase(nc);
        }
    }

    public void draw() {
        System.out.printf("%3c", ' ');
        for (int ii = 0; ii < grid[0].length; ++ii) {
            System.out.printf("%3d", ii + 1);
        }
        System.out.println();

        for (int row = 0; row < grid.length; ++row) {
            System.out.printf("%3d", row + 1);
            for (int col = 0; col < grid[0].length; ++col) {
                char ch = ' ';
                if (grid[row][col]) {
                    ch = '@';
                }
                System.out.printf("%3c", ch);
            }

            System.out.println();
        }
    }

    /**
     * Helper method to load the input file and construct the EraseObject
     *
     * @param fileName Name of input file
     * @return EraseObject with data loaded from file
     * @throws FileNotFoundException Throws on error loading file
     */
    public static P7_Dhruva_Krupa_EraseObject loadFile(String fileName)
            throws FileNotFoundException {
        P7_Dhruva_Krupa_EraseObject result;

        // try with resource allocation: Ensures Scanner instance is always closed
        try (Scanner scan = new Scanner(new File(fileName))) {
            if (!scan.hasNextInt()) {
                throw new InputMismatchException(String.format("malformed file: %s", fileName));
            }

            int dim = scan.nextInt();
            result = new P7_Dhruva_Krupa_EraseObject(dim);

            while (scan.hasNextInt()) {
                int row = scan.nextInt();
                if (!scan.hasNextInt()) {
                    break;
                }

                int col = scan.nextInt();
                result.add(new Position(row - 1, col - 1));
            }
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        P7_Dhruva_Krupa_EraseObject canvas = P7_Dhruva_Krupa_EraseObject.loadFile("digital.txt");

        System.out.printf("Image before erasure:%n%n");
        canvas.draw();

        // try with resource allocation: Ensures Scanner instance is always closed
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Do you want to erase [y/n]: ");

            while (scan.hasNext()) {
                String ans = scan.next().toLowerCase();
                if (ans.equals("n") || ans.equals("no")) {
                    break;
                }

                System.out.print("Enter y-coordinate (row) of point to erase: ");
                int row = scan.nextInt();
                System.out.print("Enter x-coordinate (col) of point to erase: ");
                int col = scan.nextInt();

                canvas.erase(new Position(row - 1, col - 1));
                System.out.printf("Image after erasure:%n%n");
                canvas.draw();
                System.out.print("Do you want to erase [y/n]: ");
            }
        }
    }
}
