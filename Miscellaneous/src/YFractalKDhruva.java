import gpdraw.*;

public class YFractalKDhruva {

    // Attributes
    SketchPad pad;
    DrawingTool pen;

    // Constructor
    public YFractalKDhruva() {

        pad = new SketchPad(500, 500, 50);
        pen = new DrawingTool(pad);

        // Back the pen up so the Y is drawn in the middle of the screen
        pen.up();
        pen.backward(150);
        pen.down();
    }

    public void drawYFractal(int level, double length) {

        // Base case:  Draw an Y
        if (level == 0) {
            double originalX = pen.getXPos();
            double originalY = pen.getYPos();
            double originalDir = pen.getDirection();

            pen.forward(length);
            pen.turnLeft(60);
            pen.forward(length);
            pen.backward(length);
            pen.turnRight(120);
            pen.forward(length);
            pen.up();

            pen.move(originalX, originalY);
            pen.setDirection(originalDir);
        }

        // Recursive case:  Draw an Y at each midpoint
        // of the current Y's segments
        else {

            // Move to first midpoint of the bottom leg of Y
            pen.forward(length / 2.0);
            pen.turnRight(90);

            // Save current drawing position
            double x = pen.getXPos();
            double y = pen.getYPos();
            double dir = pen.getDirection();

            // Recursively draw another Y at the midpoint
            drawYFractal(level - 1, length / 2.0);

            // Restore drawing position
            pen.up();
            pen.move(x, y);
            pen.setDirection(dir);
            pen.turnLeft(90);
            pen.down();

            // Move to second midpoint
            pen.forward(length / 2);
            pen.turnRight(60);
            pen.forward(length);
            pen.backward(length / 2.0);
            pen.turnLeft(90);

            // Save current drawing position
            x = pen.getXPos();
            y = pen.getYPos();
            dir = pen.getDirection();

            // Recursively draw another Y at the midpoint
            drawYFractal(level - 1, length / 2.0);

            // Restore drawing position
            pen.up();
            pen.move(x, y);
            pen.setDirection(dir);
            pen.turnLeft(90);
            pen.down();

            // Moving to last leg of Y
            pen.forward(length / 2);
            pen.turnRight(60);
            pen.forward(length);
            pen.backward(length / 2.0);
            pen.turnLeft(90);

            // Save current position
            x = pen.getXPos();
            y = pen.getYPos();
            dir = pen.getDirection();

            // Recursively draw Y
            drawYFractal(level - 1, length / 2.0);

            // Restoring original starting position
            pen.up();
            pen.move(x, y);
            pen.setDirection(dir);
            pen.turnLeft(90);
            pen.down();
            pen.forward(length / 2.0);
            pen.turnRight(60);
            pen.forward(length);
            pen.up();
            pen.setDirection(180);
        }
    }

    public static void main(String[] args) {

        YFractalKDhruva fractal = new YFractalKDhruva();

        // Draw YFractal with given level and side length
        fractal.drawYFractal(3, 200);
    }
}
