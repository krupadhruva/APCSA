import gpdraw.DrawingTool;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Draw a random collection of birds in a bounding box
 *
 * <p>The randomization could be external to this class
 */
public class P7_Dhruva_Krupa_RandomFlock {

    /** Color of individual component of flock */
    private final Color color;
    /** Number of instances in a flock */
    private final int count;
    /** Height of the bounding box */
    private final int height;
    /** Location of bounding box for flock */
    private final Point origin;
    /** Radius of the individual component */
    private final int radius;
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Width of the bounding box for flock */
    private final int width;

    /**
     * Random flock of birds
     *
     * @param toolKit Common drawing tools
     * @param origin Location of the bounding box for the flock
     * @param radius Determines the size of a bird
     * @param width Width of the bounding box
     * @param height Height of the bounding box
     * @param count Number of instances/components in the flock
     * @param color Color of the component
     */
    public P7_Dhruva_Krupa_RandomFlock(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int radius,
            int width,
            int height,
            int count,
            Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.count = count;
        this.color = color;
    }

    public P7_Dhruva_Krupa_RandomFlock(
            DrawingTool pen,
            Point origin,
            int radius,
            int width,
            int height,
            int count,
            Color color) {
        this(new P7_Dhruva_Krupa_ToolKit(pen), origin, radius, width, height, count, color);
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_RandomFlock flock =
                new P7_Dhruva_Krupa_RandomFlock(
                        toolKit, new Point(-250, -50), 20, 300, 100, 20, Color.BLACK);
        flock.draw();
    }

    /** Draws a random flock of components with in a bounding box */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        Random rand = toolKit.getRandom();

        // Random position for the flock with in the bounding box
        PrimitiveIterator.OfInt xVals = rand.ints(origin.x, origin.x + width).iterator();
        PrimitiveIterator.OfInt yVals = rand.ints(origin.y, origin.y + height).iterator();

        pen.setWidth(2);
        pen.setColor(color);
        pen.down();
        for (int cc = 0; cc < count; ++cc) {
            Point ref = new Point(xVals.next(), yVals.next());

            toolKit.move(ref);
            pen.fillCircle(radius * 0.15);

            pen.setDirection(70);
            pen.move(radius * 0.6);

            pen.setDirection(40);
            pen.move(radius * 0.4);

            toolKit.move(ref);
            pen.setDirection(110);
            pen.move(radius * 0.6);

            pen.setDirection(140);
            pen.move(radius * 0.4);
        }
    }
}
