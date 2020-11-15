import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.Color;

public class P7_Dhruva_Krupa_InClassLab {
    public static void printDiamond(int n) {
        if (n < 1 || n > 40) {
            System.out.println("n is too small or too big.");
            return;
        }

        for (int row = 0; row < n; ++row) {
            System.out.println(" ".repeat(n - row) + "*".repeat(1 + (2 * row)));
        }

        for (int row = n - 2; row >= 0; --row) {
            System.out.println(" ".repeat(n - row) + "*".repeat(1 + (2 * row)));
        }
    }

    private final DrawingTool pen;

    public P7_Dhruva_Krupa_InClassLab() {
        pen = new DrawingTool(new SketchPad(300, 300, 5L));

        pen.up();
        pen.move(0, 0);
        pen.setDirection(90);
    }

    public void printCheckerBoard() {
        double length = 200.0 / 8;
        pen.move(-100, -100);

        pen.setDirection(270);
        pen.move(length / 2.0);

        pen.setDirection(180);
        pen.move(length / 2.0);

        for (int row = 0; row < 8; ++row) {
            pen.setDirection(90);
            pen.move(length);

            double x = pen.getXPos();
            double y = pen.getYPos();

            pen.setDirection(0);
            for (int column = 0; column < 8; ++column) {
                pen.up();
                pen.move(length);
                pen.down();

                if (row % 2 == column % 2) {
                    pen.setColor(Color.BLACK);
                } else {
                    pen.setColor(Color.RED);
                }

                pen.fillRect(25, 25);
            }

            pen.up();

            pen.move(x, y);
        }
    }

    public static void main(String[] args) {
        printDiamond(5);
        P7_Dhruva_Krupa_InClassLab checkerBoard = new P7_Dhruva_Krupa_InClassLab();
        checkerBoard.printCheckerBoard();
    }
}
