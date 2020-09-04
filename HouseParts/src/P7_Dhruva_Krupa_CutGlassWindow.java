import gpdraw.DrawingTool;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Draws a window with random colors from input with cut glass like appearance Hard coded to draw 5
 * rows of cut glass appearance
 */
public class P7_Dhruva_Krupa_CutGlassWindow {

    /** Color palette for glass panels: Array of colors */
    private final Color[] colors;
    /** Window height */
    private final int height;
    /** Color of house interiors as window background */
    private final Color inside;
    /** Position of window left lower corner */
    private final Point origin;
    /** Tool kit with drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Window width */
    private final int width;

    /**
     * Draws a random chequered pattern window
     *
     * @param toolKit Common drawing tools
     * @param origin Location of the window
     * @param width Width of the window
     * @param height Height of the window
     * @param colors Array of colors to randomly pick from. Repeating colors will increase
     *     probability of that color
     * @param inside Interior color to draw the background of the window
     */
    public P7_Dhruva_Krupa_CutGlassWindow(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int width,
            int height,
            Color[] colors,
            Color inside) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.colors = colors;
        this.inside = inside;
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_CutGlassWindow window =
                new P7_Dhruva_Krupa_CutGlassWindow(
                        toolKit,
                        new Point(100, 100),
                        100,
                        200,
                        new Color[] {
                            Color.ORANGE,
                            Color.GREEN,
                            Color.RED,
                            Color.RED,
                            Color.YELLOW,
                            Color.BLUE,
                            Color.CYAN
                        },
                        Color.YELLOW);
        window.draw();
    }

    /**
     * Method to actually draw window based based on attributes
     *
     * <p>Cut glass is basically a random color picked from the array of colors with modified alpha
     * to increase transparency. Outline of the individual panel is drawn with the random color
     * picked to create a slight border for each piece.
     */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        int len = Math.min(width, height) / 5;

        Random rand = toolKit.getRandom();

        // Random numbers within array bounds for picking colors randomly from an array of colors
        PrimitiveIterator.OfInt colorPicker = rand.ints(0, colors.length).iterator();

        // For random positioning of glass tile inside the window frame
        PrimitiveIterator.OfInt offset = rand.ints(len / 4, len).iterator();

        Point ref = origin.getLocation();

        pen.down();
        pen.setColor(inside);
        toolKit.move(origin.x + (width / 2), origin.y + (height / 2));
        pen.fillRect(width, height);

        pen.setWidth(2);
        ref.setLocation(origin.x, origin.y - (len / 2));
        for (; ref.y + (len / 2) < origin.y + height; ) {
            // Fill a layer to ensure there are no unfilled gaps showing
            // inside color due to random placement of tiles
            ref.translate(0, len);
            ref.setLocation(origin.x + (width / 2), ref.y);
            toolKit.move(ref);

            Color currentColor = colors[colorPicker.next()];
            pen.setColor(currentColor);
            pen.drawRect(width, len);

            // Reduced alpha value to increase transparency - glass like view
            pen.setColor(toolKit.filteredColor(currentColor, 1.0f, 0.2f));
            pen.fillRect(width, len);

            ref.setLocation(origin.x, ref.y);
            ref.translate(len / 2 + offset.next(), 0);
            for (; ref.x + (len / 2) < origin.x + width; ) {
                toolKit.move(ref);
                currentColor = colors[colorPicker.next()];
                pen.setColor(currentColor);
                pen.drawRect(len, len);

                pen.setColor(toolKit.filteredColor(currentColor, 1.0f, 0.2f));
                pen.fillRect(len, len);

                ref.translate(offset.next(), 0);
            }
        }

        // Draw 4 panes
        pen.setWidth(5);
        pen.setColor(toolKit.filteredColor(Color.BLACK, 1.0f, 0.6f));
        toolKit.move(origin.x + (width / 2), origin.y + (height / 2));
        pen.drawRect(width, height);
        ref.translate(0, height / 2);

        pen.setWidth(3);
        toolKit.move(origin.x + (width / 2), origin.y);
        pen.setDirection(90);
        pen.down();
        pen.move(height);

        toolKit.move(origin.x, origin.y + (height / 2));
        pen.setDirection(0);
        pen.move(width);
    }
}
