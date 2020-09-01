import gpdraw.DrawingTool;

import java.awt.*;

public class P7_Dhruva_Krupa_Sun {

    private final Color color;
    private final int coreRadius;
    private final Point origin;
    private final int rayRadius;
    private final P7_Dhruva_Krupa_ToolKit toolKit;

    public P7_Dhruva_Krupa_Sun(P7_Dhruva_Krupa_ToolKit toolKit, Point origin,
                               int coreRadius, int rayRadius, Color color) {
        this.toolKit = toolKit;
        this.origin = origin;
        this.coreRadius = coreRadius;
        this.rayRadius = rayRadius;
        this.color = color;
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Sun sun = new P7_Dhruva_Krupa_Sun(toolKit, new Point(500, 500), 50, 80, Color.ORANGE);
        sun.draw();
    }

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
            pen.setDirection(15 * count);
            pen.down();
            pen.move(coreRadius + delta);
        }
    }
}
