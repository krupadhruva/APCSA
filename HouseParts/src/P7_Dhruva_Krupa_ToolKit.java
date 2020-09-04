import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.*;
import java.util.Random;

/**
 * Class providing basic drawing tools like pen
 *
 * Since we use a different coordinate system with lower left corner treated as (0,0),
 * we provide helper methods to transform from gpdraw coordinate system to our system
 *
 * Took help to implement the following:
 * Tool kit provides a single instance of random number generator with support for
 * custom seed value to ensure reproducibility across runs
 */
public final class P7_Dhruva_Krupa_ToolKit {

    /** Our view of origin - Lower left corner of the sheet */
    final private Point origin;
    /** Pen used for drawing objects */
    final private DrawingTool pen;
    /** Common random number generator across shapes */
    final private Random random;

    /**
     * Construct a toolkit with SketchPad and a pen
     * Uses lower left corner as the origin instead of sheet centre to make it easy to layout
     * objects without having to deal with negative numbers.
     *
     * @param width  Width of the drawing area
     * @param height Height of the drawing area
     */
    public P7_Dhruva_Krupa_ToolKit(int width, int height) {
        this(new DrawingTool(new SketchPad(width, height, 5L)), null);
    }

    /**
     * Construct tool kit from existing SketchPad
     *
     * @param pad  Existing instance
     * @param seed Random number generator seed. Can be null.
     */
    public P7_Dhruva_Krupa_ToolKit(SketchPad pad, Long seed) {
        this(new DrawingTool(pad), seed);
    }

    /**
     * Construct toolkit from an existing DrawingTool (pen)
     *
     * @param pen  Existing instance
     * @param seed Random number generator seed. Can be null.
     */
    public P7_Dhruva_Krupa_ToolKit(DrawingTool pen, Long seed) {
        Dimension dim = pen.getPadPanel().getSize();
        this.origin = new Point(-dim.width / 2, -dim.height / 2);
        this.pen = pen;

        if (seed == null) {
            this.random = new Random();
        } else {
            this.random = new Random(seed);
        }

        reset();
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        DrawingTool pen = toolKit.getPen();
        pen.down();

        // Test drawing a circle at center with our lower left corner based coordinate system
        Point localCenter = new Point(Math.abs(toolKit.origin.x), Math.abs(toolKit.origin.y));
        toolKit.move(localCenter);
        pen.drawCircle(10);
        Point localPos = new Point((int) pen.getXPos(), (int) pen.getYPos());

        // Test drawing another circle at center using gpdraw coordinate system
        Point gpdrawCenter = new Point(0, 0);
        toolKit.move(toolKit.gpdrawToLocal(gpdrawCenter.x, gpdrawCenter.y));
        pen.drawCircle(15);
        Point gpPos = new Point((int) pen.getXPos(), (int) pen.getYPos());

        // Note: Took help to test the translation
        if (!localPos.equals(gpPos)) {
            throw new AssertionError("Translating local to gpdraw coordinate system failed");
        }
    }

    /**
     * Helper method to draw Y axis scale for vertical alignment
     * with 25 count granularity
     */
    public void drawScale() {
        int height = pen.getPadPanel().getSize().height;

        reset();
        pen.down();
        for (int count = 0; count < height / 25; ++count) {
            move(0, 25 * count);
            if (count % 2 != 0) {
                pen.drawString("- " + 25 * count);
            } else {
                pen.drawString("-- " + 25 * count);
            }
        }
    }

    /**
     * Helper method to apply color transformations to base color
     *
     * @param color  Base color
     * @param factor Transformation factor for RGB
     * @param alpha  Transformation factor for A
     * @return Transformed color
     */
    public Color filteredColor(Color color, float factor, float alpha) {
        float rgba[] = color.getRGBComponents(null);
        return new Color(rgba[0] * factor, rgba[1] * factor, rgba[2] * factor, rgba[3] * alpha);
    }

    /**
     * Helper function to apply a transformation to RGBA
     *
     * @param color  Base color
     * @param factor Transformation factor
     * @return Transformed color
     */
    public Color filteredColor(Color color, float factor) {
        return filteredColor(color, factor, factor);
    }

    /**
     * Returns the pen for drawing shapes
     *
     * @return DrawingTool used across
     */
    public DrawingTool getPen() {
        return pen;
    }

    /**
     * Use single pseudo random number generator
     *
     * @return
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Translates from gpdraw coordinate system to local
     *
     * @param x X in gpdraw coordinate system
     * @param y Y in gpdraw coordinate system
     * @return Point in local coordinate system
     */
    public Point gpdrawToLocal(int x, int y) {
        // Since our local coordinate is at a negative offset along both X & Y
        // translate them along both X & Y to reach gpdraw coordinate system
        return new Point(x + Math.abs(origin.x), y + Math.abs(origin.y));
    }

    /**
     * Returns the coordinates with respect to gpdraw world
     *
     * @param x X coordinate with respect to lower left corner
     * @param y Y coordinate with respect to lower left corner
     * @return Translated point in gpdraw system
     */
    public Point localToGpdraw(int x, int y) {
        Point pt = origin.getLocation();
        pt.translate(x, y);
        return pt;
    }

    /**
     * Position pen by translating lower left corner based origin
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void move(int x, int y) {
        boolean wasPenDown = pen.isDown();
        if (wasPenDown) {
            pen.up();
        }

        pen.move(origin.getX() + (double) x, origin.getY() + (double) y);

        if (wasPenDown) {
            pen.down();
        }
    }

    /**
     * Position pen by translating lower left corner based origin
     *
     * @param point Position relative to lower left corner
     */
    public void move(Point point) {
        move(point.x, point.y);
    }

    /**
     * Reset to defaults for pen and pad
     */
    public void reset() {
        pen.setColor(Color.BLUE);
        pen.setWidth(1);
        pen.home();
        pen.up();
        move(0, 0);
    }
}
