/*
 * Name: Krupa Dhruva
 * Date: October 21, 2020
 * Period: 7
 * Time Taken: 90 minutes
 *
 * Lab Reflection:
 * I picked the Sierpinski Gasket. It wasn't too difficult but I did spend some
 * time trying to figure out how to get to the position I needed in order for the
 * recursion of the three triangles to occur. One thing I would like
 * to figure out for the future is how to fill in the triangles. Overall, I had
 * fun doing this lab as it was the right amount of challenging for me and I got to
 * better understand the concept of recursion.
 */

import gpdraw.DrawingTool;
import gpdraw.SketchPad;

public class P7_Dhruva_Krupa_MyFractal {

    private final DrawingTool pen;

    public P7_Dhruva_Krupa_MyFractal() {
        pen = new DrawingTool(new SketchPad(600, 800));

        pen.up();
        pen.home();
        pen.setDirection(0);
    }

    public void drawSGasket1(int level, double length) {
        if (level == 0) {
            // No need to save position because pen returns to its original location
            // Draws a basic equilateral triangle

            pen.down();
            pen.forward(length / 2.0);
            pen.turnLeft(120);
            pen.forward(length);
            pen.turnLeft(120);
            pen.forward(length);
            pen.turnLeft(120);
            pen.forward(length / 2.0);

        } else {
            // Save "home" position
            double x = pen.getXPos();
            double y = pen.getYPos();
            double direction = pen.getDirection();

            // Draws basic triangle from level 0 which is needed to draw level 1
            pen.down();
            pen.forward(length / 2.0);
            pen.turnLeft(120);
            pen.forward(length);
            pen.turnLeft(120);
            pen.forward(length);
            pen.turnLeft(120);
            pen.forward(length / 4.0);

            // Recursively draws the lower left triangle
            drawSGasket1(level - 1, length / 2.0);

            // Moves to the location of second recursion
            pen.forward((2 * length) / 4.0);

            // Recursively draws the lower right triangle
            drawSGasket1(level - 1, length / 2.0);

            // Moves to the upper middle location of where the third recursion occurs
            pen.up();
            pen.forward(length / 4.0);
            pen.turnLeft(120);
            pen.forward(length / 2.0);
            pen.turnLeft(60);
            pen.forward(length / 4.0);
            pen.setDirection(0);
            pen.down();

            // Recursively draws the upper middle triangle
            drawSGasket1(level - 1, length / 2.0);

            // Restores the pen to the starting location (halfway through the bottom
            // leg of the level 0 triangle) or "home" position
            pen.up();
            pen.move(x, y);
            pen.setDirection(direction);
        }
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_MyFractal fractal = new P7_Dhruva_Krupa_MyFractal();
        fractal.drawSGasket1(3, 200);
    }
}
