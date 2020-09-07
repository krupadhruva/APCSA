import gpdraw.DrawingTool;

import java.awt.*;

/**
 * Draws a door with 2 halves. Optionally draws a partially open door. The opening is hardcoded for
 * simplicity
 */
public class P7_Dhruva_Krupa_Door {

    /** Color of the door */
    private final Color color;
    /** Door height */
    private final int height;
    /** Color of house interior to show in partially open door */
    private final Color inside;
    /** Position of door */
    private final Point origin;
    /** Handle to common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Door width */
    private final int width;

    /**
     * Partially open door
     *
     * @param toolKit Handle to common methods
     * @param origin Lower left corner location of door
     * @param width Width of door
     * @param height Height of the door
     * @param color Door color
     * @param inside Color of the house interiors that is visible in partially open door
     */
    public P7_Dhruva_Krupa_Door(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int width,
            int height,
            Color color,
            Color inside) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.color = color;
        this.inside = inside;
    }

    /**
     * Draws a simple closed door
     *
     * @param toolKit Handle to common methods
     * @param origin Lower left corner location of door
     * @param width Width of door
     * @param height Height of the door
     * @param color Door color
     */
    public P7_Dhruva_Krupa_Door(
            P7_Dhruva_Krupa_ToolKit toolKit, Point origin, int width, int height, Color color) {
        // Reuse the constructor
        this(toolKit, origin, width, height, color, null);
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);

        P7_Dhruva_Krupa_Door closeDoor =
                new P7_Dhruva_Krupa_Door(toolKit, new Point(-250, -100), 150, 300, Color.BLACK);
        closeDoor.draw();

        P7_Dhruva_Krupa_Door openDoor =
                new P7_Dhruva_Krupa_Door(
                        toolKit, new Point(100, -100), 150, 300, Color.BLACK, Color.LIGHT_GRAY);
        openDoor.draw();
    }

    /**
     * Method to actually draw the door based based on attributes
     *
     * <p>Supports drawing partially open door. Since the shape of the partially open door is not a
     * rectangle, we use multiple lines to fill the polygon.
     */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        Color panelColor = toolKit.filteredColor(color, 0.9f);

        int penWidth = pen.getWidth();

        // Draw open/close half
        if (inside != null) {
            pen.setColor(inside);
        } else {
            pen.setColor(panelColor);
        }

        Point ref = origin.getLocation();
        ref.translate(width / 2, height / 2);
        toolKit.move(ref);
        pen.down();
        pen.fillRect(width, height);
        ref = origin.getLocation();

        // Draw the outer boundary for the door
        ref.translate(width / 2, height / 2);
        toolKit.move(ref);
        pen.setWidth(5);
        pen.setColor(color);
        pen.down();
        pen.drawRect(width, height);
        pen.up();
        pen.setWidth(penWidth);

        // Draw the door border to show 2 halves
        ref.translate(width / 4, 0);
        toolKit.move(ref);
        pen.setColor(panelColor);
        pen.down();
        pen.setWidth(5);
        pen.drawRect(width / 2, height);

        if (inside != null) {
            // Draw the closed half of the door
            pen.setWidth(penWidth);
            pen.fillRect(width / 2, height);

            pen.setColor(panelColor);

            // Fixed perspective to simplify calculations
            double slope = Math.tan(15 * Math.PI / 180.0);
            int lines = (int) (width * 0.4 / pen.getWidth());
            for (int count = 0; count < lines; ++count) {
                ref = origin.getLocation();
                ref.translate(count, (int) (count * slope));
                toolKit.move(ref);

                // We need vertical lines
                pen.down();
                pen.setDirection(90);

                // Shorten the line by 2 times 'y' since we need gap at bottom and top
                pen.move(height - 2 * count * slope);
                pen.up();
            }
        }
    }
}
