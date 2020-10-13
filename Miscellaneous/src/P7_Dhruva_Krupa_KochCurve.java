/*
 * Name: Krupa Dhruva
 * Date: October 12, 2020
 * Period: 7
 * Time Taken: 75 minutes
 *
 * Lab Reflection:
 *
 * I tried to solve this without looking at the pseudo code and got little confused.
 * Implemented a function that draws the basic curve with 4 lines and tried assembling it.
 *
 * Reading the pseudo code, I realized it is much easier to build the complete snow flake
 * using recursive calls drawing a basic line at different angles.
 *
 * Since I wanted to make a triangle joining koch curves, I made the DrawingTool (pen)
 * a constructor argument and kept a reference to it in the driver so that I can rotate
 * it by 120 deg.
 */

import gpdraw.DrawingTool;
import gpdraw.SketchPad;

/**
 * Class to draw a snow flake based on Koch curve
 *
 * <p>Starts in horizontal direction by setting the angle in constructor
 */
public class P7_Dhruva_Krupa_KochCurve {
    private final DrawingTool pen;

    /**
     * Constructor taking DrawingTool used
     *
     * @param pen Instance of DrawingTool
     */
    public P7_Dhruva_Krupa_KochCurve(DrawingTool pen) {
        this.pen = pen;
        this.pen.up();
        this.pen.turnRight(90);
        this.pen.down();
    }

    /**
     * Draw Koch curve given length and number of levels
     *
     * @param level Number of levels
     * @param length Total length of koch curve
     */
    public void drawKochCurve(int level, double length) {
        if (level < 1) {
            pen.move(length);
        } else {
            drawKochCurve(level - 1, length / 3);
            pen.turnLeft(60);
            drawKochCurve(level - 1, length / 3);
            pen.turnRight(120);
            drawKochCurve(level - 1, length / 3);
            pen.turnLeft(60);
            drawKochCurve(level - 1, length / 3);
        }
    }

    /**
     * Draws a snowflake using Koch curve
     *
     * @param level Number of levels for Koch curve
     * @param length Total length of Koch curve
     */
    public void drawKochSnowflake(int level, double length) {
        final int KOCH_SIDES = 3;
        for (int count = 0; count < KOCH_SIDES; ++count) {
            drawKochCurve(level, length);
            pen.turnRight(360.0 / KOCH_SIDES);
        }
    }

    /**
     * Driver function to draw and test Koch curve based snowflake
     *
     * @param args Command line arguments (ignored)
     */
    public static void main(String[] args) {
        DrawingTool pen = new DrawingTool((new SketchPad(800, 600, 5L)));
        P7_Dhruva_Krupa_KochCurve curve = new P7_Dhruva_Krupa_KochCurve(pen);

        curve.drawKochSnowflake(5, 300);
    }
}
