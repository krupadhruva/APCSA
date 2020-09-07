import gpdraw.DrawingTool;

import java.awt.Color;
import java.awt.Point;

/**
 * Draws a simple single flower comprising of circles with an inner core and outer circular bounding
 * box for petals.
 *
 * <p>The flowers can be placed randomly within a bounding box for better layout
 */
public class P7_Dhruva_Krupa_Flower {

    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Radius of the pistil - inner circle */
    private final int innerRadius;
    /** Bounding radius of the circular petals */
    private final int outerRadius;
    /** Location of flower */
    private final Point origin;
    /** Color of pistil */
    private final Color innerColor;
    /** Petal color */
    private final Color petalColor;
    /** Number of petals */
    private final int petalCount;

    /**
     * Construct a single flower
     *
     * @param toolKit Common drawing tools
     * @param innerRadius Radius of the pistil
     * @param outerRadius Bounding radius of the circular petals
     * @param origin Location of flower
     * @param innerColor Color of pistil
     * @param petalColor Petal color
     * @param petalCount Number of petals
     */
    public P7_Dhruva_Krupa_Flower(
            P7_Dhruva_Krupa_ToolKit toolKit,
            int innerRadius,
            int outerRadius,
            Point origin,
            Color innerColor,
            Color petalColor,
            int petalCount) {
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.origin = origin;
        this.innerColor = innerColor;
        this.petalColor = petalColor;
        this.petalCount = petalCount;
        this.toolKit = toolKit;
    }

    /** Draws the flower based on inputs in object */
    public void draw() {
        DrawingTool pen = toolKit.getPen();

        int angle = 360 / petalCount;

        for (int count = 0; count < petalCount; ++count) {
            toolKit.move(origin);
            pen.setDirection(angle * count);
            pen.up();
            pen.move(outerRadius / 2);
            pen.down();
            pen.setColor(toolKit.filteredColor(petalColor, 0.6f, 1.0f));
            pen.drawCircle(outerRadius / 2);
            pen.setColor(toolKit.filteredColor(petalColor, 0.8f, 0.6f));
            pen.fillCircle(outerRadius / 2);
        }

        toolKit.move(origin);
        pen.setColor(innerColor);
        pen.down();
        pen.fillCircle(innerRadius);
        pen.up();
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = P7_Dhruva_Krupa_ToolKit.createToolKit();

        P7_Dhruva_Krupa_Flower flower =
                new P7_Dhruva_Krupa_Flower(
                        toolKit, 5, 15, new Point(0, 0), Color.ORANGE, Color.RED, 5);
        flower.draw();
    }
}
