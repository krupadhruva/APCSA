import gpdraw.DrawingTool;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * RandomTree draws a tree with a trunk and leaves using tool kit coordinate system based
 *
 * <p>Leaves are positioned randomly within a circular bounding box. The color of the leaves are
 * random shades of the base color
 */
public class P7_Dhruva_Krupa_RandomTree {

    /** Height of the tree trunk */
    private final int height;
    /** Base color of the leaf */
    private final Color leafColor;
    /** Number of leaves */
    private final int leaves;
    /** Location of the tree trunk */
    private final Point origin;
    /** Radius of bounding circle for random leaves */
    private final int radius;
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Tree trunk color */
    private final Color trunkColor;
    /** Width of the tree trunk */
    private final int width;

    /**
     * Constructs a tree with random leaves in a give radius boundary
     *
     * @param toolKit Handle to drawing pen and common helper methods
     * @param origin Use local coordinate system (lower left corner is origin)
     * @param width Width of trunk
     * @param height Height of trunk
     * @param radius Boundary circle containing random leaves
     * @param leaves Number of leaves
     * @param trunkColor Trunk color
     * @param leafColor Leaf base color - actual leaves will be random shades of this color
     */
    public P7_Dhruva_Krupa_RandomTree(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int width,
            int height,
            int radius,
            int leaves,
            Color trunkColor,
            Color leafColor) {
        this.toolKit = toolKit;
        this.origin = origin;

        this.width = width;
        this.height = height;

        // Prevent leaves from covering the trunk and reaching ground
        if (radius > (height / 2)) {
            radius = height / 2;
        }

        this.radius = radius;
        this.leaves = leaves;
        this.trunkColor = trunkColor;
        this.leafColor = leafColor;
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);

        Point location = new Point(-250, -250);
        Color trunkColor = new Color(99, 62, 62);

        P7_Dhruva_Krupa_RandomTree tree1 =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, location, 50, 200, 100, 45, trunkColor, Color.GREEN);
        tree1.draw();

        location.translate(200, 50);
        P7_Dhruva_Krupa_RandomTree tree2 =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, location, 50, 200, 100, 45, trunkColor, Color.GREEN);
        tree2.draw();
    }

    /** Draw tree with random collection of filled circles and ovals for leaves */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();

        // Draw the trunk
        pen.up();
        toolKit.move(origin.x + (width / 2), origin.y + (height / 2));
        pen.setColor(trunkColor);
        pen.down();
        pen.fillRect(width, height);
        toolKit.move(origin.x + (width / 2), origin.y + height);
        pen.setColor(leafColor);
        pen.fillCircle(width / 2);
        pen.up();

        // Draw leaves
        Random rand = toolKit.getRandom();

        // Random factor to create different shades of leaves
        PrimitiveIterator.OfDouble randShade = rand.doubles(0.2, 1.0).iterator();
        // Randomize leaf sizes
        PrimitiveIterator.OfDouble randRadius = rand.doubles(radius * 0.4, radius * 0.8).iterator();
        // Randomize leaf location with in the bounding circle
        PrimitiveIterator.OfInt randOrigin = rand.ints(-radius, radius).iterator();

        // Get RGBA of the base leaf color - Shades will be generated by multiple a factor
        float[] rgba = leafColor.getRGBComponents(null);
        Point leafOrig = new Point(origin.x + (width / 2), origin.y + height + radius);
        for (int count = 0; count < leaves / 2; ++count) {
            float shade = randShade.next().floatValue();
            pen.setColor(
                    new Color(rgba[0] * shade, rgba[1] * shade, rgba[2] * shade, rgba[3] * shade));
            toolKit.move(leafOrig.x + randOrigin.next(), leafOrig.y + randOrigin.next());
            pen.down();
            pen.fillCircle(randRadius.next());
            pen.up();

            shade = randShade.next().floatValue();
            pen.setColor(
                    new Color(rgba[0] * shade, rgba[1] * shade, rgba[2] * shade, rgba[3] * shade));
            toolKit.move(leafOrig.x + randOrigin.next(), leafOrig.y + randOrigin.next());
            pen.down();
            pen.fillOval(randRadius.next(), randRadius.next());
            pen.up();
        }
    }
}
