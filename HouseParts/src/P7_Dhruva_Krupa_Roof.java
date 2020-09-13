import gpdraw.DrawingTool;

import java.awt.*;

/** Draw a sloped roof with layered slabs */
public class P7_Dhruva_Krupa_Roof {

    /** Roof color */
    private final Color color;
    /** Height of the tapered roof */
    private final int height;
    /** Location of the roof */
    private final Point origin;
    /** Thickness of each layer in the tapering roof */
    private final int thickness;
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Width of the first layer of the tapering roof */
    private final int width;

    /**
     * @param toolKit Common drawing tools
     * @param origin Location of roof
     * @param width Width of the first layer of a tapering roof
     * @param height Height of the roof
     * @param thickness Thickness of layer of the roof
     * @param color Color of the roof
     */
    public P7_Dhruva_Krupa_Roof(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int width,
            int height,
            int thickness,
            Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.width = width;
        this.height = height;

        if (thickness > height) {
            thickness = height;
        }

        this.thickness = thickness;
        this.color = color;
    }

    public P7_Dhruva_Krupa_Roof(
            DrawingTool pen, Point origin, int width, int height, int thickness, Color color) {
        this(new P7_Dhruva_Krupa_ToolKit(pen), origin, width, height, thickness, color);
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Roof roof =
                new P7_Dhruva_Krupa_Roof(toolKit, new Point(-200, 0), 400, 200, 20, Color.RED);
        roof.draw();
    }

    /** Draws a tapering roof */
    public void draw() {
        toolKit.reset();

        DrawingTool pen = toolKit.getPen();
        Point ref = origin.getLocation();
        Color outline = toolKit.filteredColor(color, 0.6f);
        int slabs = Math.max(1, height / thickness);

        ref.translate(width / 2, thickness / 2);
        for (int count = 0; count < slabs; ++count) {
            toolKit.move(ref);

            pen.down();
            pen.setColor(color);
            pen.fillRect(width - (2 * thickness * count), thickness);
            pen.setColor(outline);
            pen.drawRect(width - (2 * thickness * count), thickness);

            ref.translate(0, thickness);
        }
    }
}
