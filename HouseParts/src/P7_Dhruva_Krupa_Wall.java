import gpdraw.DrawingTool;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Draw wall with multiple layers of overlapping brick work
 * Attempting to show stone cladding
 */
public class P7_Dhruva_Krupa_Wall {

    /** Pen width to draw brick outlines */
    static private final int outlineWidth = 2;
    /** Brick height */
    private final int brickHeight;
    /** Width of the brick */
    private final int brickWidth;
    /** Brick color */
    private final Color color;
    /** Height of the wall */
    private final int height;
    /** Location of the wall */
    private final Point origin;
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Width of the wall */
    private final int width;

    /**
     * Construct a wall with overlapping brick work
     *
     * @param toolKit     Common drawing tools
     * @param origin      Location of the wall
     * @param width       Width of the wall
     * @param height      Height of the wall
     * @param brickWidth  Width of the brick
     * @param brickHeight Brick height
     * @param color       Brick color
     */
    public P7_Dhruva_Krupa_Wall(P7_Dhruva_Krupa_ToolKit toolKit, Point origin, int width, int height,
                                int brickWidth, int brickHeight, Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;

        this.brickWidth = brickWidth;
        int residue = height % brickHeight;
        if (residue == 0) {
            this.brickHeight = brickHeight;
        } else {
            int layers = height / brickHeight;
            this.brickHeight = (int) Math.ceil(brickHeight + (double) (residue / layers));
        }
        this.color = color;
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Wall wall = new P7_Dhruva_Krupa_Wall(toolKit, new Point(100, 100), 300, 200, 50, 20, Color.RED);
        wall.draw();
    }

    /**
     * Draws a wall with random placement of bricks with outlines
     */
    public void draw() {
        toolKit.move(origin);

        Point ref = origin.getLocation();
        DrawingTool pen = toolKit.getPen();
        Random rand = toolKit.getRandom();

        Color outline = toolKit.filteredColor(color, 0.6f, 1.0f);
        PrimitiveIterator.OfInt offset = rand.ints(brickWidth / 4, brickWidth).iterator();

        // Start with negative offset so that adding offset for first layer will align correctly
        ref.translate(0, -brickHeight / 2);

        pen.down();
        int layers = height / brickHeight;
        for (int layer = 0; layer < layers; ++layer) {
            // Draw outer rectangle to cover end to end
            ref.setLocation(origin.x + (width / 2), ref.y + brickHeight);
            toolKit.move(ref);

            pen.setWidth(outlineWidth);
            pen.setColor(outline);
            pen.drawRect(width, brickHeight);

            pen.setWidth(1);
            pen.setColor(color);
            pen.fillRect(width, brickHeight);

            // Set the starting point for first brick
            ref.setLocation(origin.x + (brickWidth / 2), ref.y);

            // Overlapped layout of bricks with random x offset
            for (; ref.x + (brickWidth / 2) < origin.x + width; ) {
                toolKit.move(ref);

                pen.setWidth(outlineWidth);
                pen.setColor(outline);
                pen.drawRect(brickWidth, brickHeight);

                pen.setWidth(1);
                pen.setColor(color);
                pen.fillRect(brickWidth, brickHeight);
                ref.translate(offset.next(), 0);
            }
        }
    }
}
