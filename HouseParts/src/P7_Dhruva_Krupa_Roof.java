import gpdraw.DrawingTool;

import java.awt.*;

/**
 * Draw a sloped roof with layered slabs
 */
public class P7_Dhruva_Krupa_Roof {

    final private Color color;
    final private int height;
    final private Point origin;
    final private int thickness;
    final private P7_Dhruva_Krupa_ToolKit toolKit;
    final private int width;

    /**
     * @param toolKit
     * @param origin
     * @param width
     * @param height
     * @param thickness
     * @param color
     */
    public P7_Dhruva_Krupa_Roof(P7_Dhruva_Krupa_ToolKit toolKit, Point origin, int width, int height, int thickness,
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

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Roof roof = new P7_Dhruva_Krupa_Roof(toolKit, new Point(100, 100), 400, 200, 20, Color.RED);
        roof.draw();
    }

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
