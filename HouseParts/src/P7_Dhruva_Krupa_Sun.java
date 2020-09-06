import gpdraw.DrawingTool;

import java.awt.*;

/**
 * Draw a sun with ray of alternating lengths
 *
 * <p>The ratio of long and short rays are hardcoded to 50%
 */
public class P7_Dhruva_Krupa_Sun {

    /** Color of the sun */
    private final Color color;
    /** Radius of the core circle */
    private final int coreRadius;
    /** Location of the origin of the core */
    private final Point origin;
    /** Radius of the longest ray of sun */
    private final int rayRadius;
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;

    /**
     * Draws Sun with alternating sized rays pointing radially
     *
     * @param toolKit Common drawing tools
     * @param origin Location of the origin of the core
     * @param coreRadius Radius of the core circle
     * @param rayRadius Radius of the longest ray of sun
     * @param color Color of the sun
     */
    public P7_Dhruva_Krupa_Sun(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int coreRadius,
            int rayRadius,
            Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.coreRadius = coreRadius;
        this.rayRadius = rayRadius;
        this.color = color;
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Sun sun =
                new P7_Dhruva_Krupa_Sun(toolKit, new Point(500, 200), 50, 80, Color.ORANGE);
        sun.draw();
    }

    /** Draw the sun with 24 rays of alternating lengths */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        toolKit.move(origin);

        pen.setColor(color);
        pen.down();
        pen.fillCircle(coreRadius);
        pen.setWidth(5);

        int angle = 15;
        int rays = 360 / angle;
        for (int count = 0; count < rays; ++count) {
            int delta = rayRadius - coreRadius;
            if (count % 2 == 0) {
                delta /= 2;
            }

            pen.up();
            toolKit.move(origin);
            pen.setDirection(angle * count);
            pen.down();
            pen.move(coreRadius + delta);
        }
    }
}
