import gpdraw.DrawingTool;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Draw a random collection of birds in a bounding box
 */
public class P7_Dhruva_Krupa_RandomFlock {

    private final Color color;
    private final int count;
    private final int height;
    private final Point origin;
    private final int radius;
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    private final int width;

    /**
     * Random flock of birds
     *
     * @param toolKit
     * @param origin
     * @param radius  Determines the size of a bird
     * @param width
     * @param height
     * @param count
     * @param color
     */
    public P7_Dhruva_Krupa_RandomFlock(P7_Dhruva_Krupa_ToolKit toolKit, Point origin,
                                       int radius, int width, int height, int count, Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.count = count;
        this.color = color;
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_RandomFlock flock =
                new P7_Dhruva_Krupa_RandomFlock(toolKit, new Point(100, 100), 20, 300, 100, 20, Color.BLACK);
        flock.draw();
    }

    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        Random rand = toolKit.getRandom();

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
