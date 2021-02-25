/*
 * Name: Krupa Dhruva
 * Date: February 24, 2021
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Initially in class, I struggled to sort of visualize how 2D arrays
 * were supposed to work but this lab definitely helped clear up my
 * confusion. Coming with the logic as to find all the possible valid
 * moves for the knight at a given point wasn't very challenging since
 * there are only 8 total possibilities at the most. A small twist I
 * did was making knight an iterator. This allowed me to avoid using
 * the Random class. Overall, I had fun doing this lab!
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class P7_Dhruva_Krupa_KnightsTour1 implements Iterator<Point> {
    private final int[][] board = new int[8][8];
    private Point nextPosition = new Point(0, 0);
    private final Random rand = new Random();

    private ArrayList<Point> moveCombinations(Point pos) {
        ArrayList<Point> result = new ArrayList<>(8);
        result.add(new Point(pos.x - 2, pos.y - 1));
        result.add(new Point(pos.x - 2, pos.y + 1));
        result.add(new Point(pos.x + 2, pos.y - 1));
        result.add(new Point(pos.x + 2, pos.y + 1));

        result.add(new Point(pos.x - 1, pos.y - 2));
        result.add(new Point(pos.x - 1, pos.y + 2));
        result.add(new Point(pos.x + 1, pos.y - 2));
        result.add(new Point(pos.x + 1, pos.y + 2));

        return result;
    }

    private void validatePosition(Point pos) throws IndexOutOfBoundsException {
        if (pos.x < 0 || pos.x > board[0].length || pos.y < 0 || pos.y > board.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ArrayList<Point> getValidMoves(Point pos) {
        ArrayList<Point> moves = moveCombinations(pos);

        for (int ii = moves.size() - 1; ii >= 0; --ii) {
            Point move = moves.get(ii);
            try {
                validatePosition(move);

                if (getValue(move) != 0) {
                    moves.remove(ii);
                }

            } catch (IndexOutOfBoundsException ignored) {
                moves.remove(ii);
            }
        }

        return moves;
    }

    private int getValue(Point pos) throws IndexOutOfBoundsException {
        validatePosition(pos);

        return board[pos.x][pos.y];
    }

    public void setValue(Point pos, int val) throws IndexOutOfBoundsException {
        validatePosition(pos);

        board[pos.x][pos.y] = val;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int col = 0; col < board[0].length; ++col) {
            System.out.printf("%2d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < board.length; ++row) {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < board[0].length; ++col) {
                System.out.printf("%2d ", board[row][col]);
            }

            System.out.println();
        }
    }

    public Point getNextMove(Point pos) {
        ArrayList<Point> moves = getValidMoves(pos);
        if (moves.isEmpty()) {
            return null;
        }

        return moves.get(rand.nextInt(moves.size()));
    }

    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public Point next() {
        Point currentPosition = nextPosition;
        nextPosition = getNextMove(nextPosition);

        return currentPosition;
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_KnightsTour1 knights = new P7_Dhruva_Krupa_KnightsTour1();
        int moveCount = 0;

        while (knights.hasNext()) {
            knights.setValue(knights.next(), ++moveCount);
        }

        knights.printBoard();
        System.out.printf("%n%d squares were visited%n", moveCount);
    }
}
