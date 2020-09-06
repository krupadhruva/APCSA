import gpdraw.DrawingTool;

import java.awt.Color;
import java.awt.Point;

public class P7_Dhruva_Krupa_Flower {
    private final int innerRadius;
    private final int outerRadius;
    private final Point origin;
    private final Color innerColor;
    private final Color petalColor;
    private final int petalCount;
    private final P7_Dhruva_Krupa_ToolKit toolKit;

    public P7_Dhruva_Krupa_Flower(
            int innerRadius,
            int outerRadius,
            Point origin,
            Color innerColor,
            Color petalColor,
            int petalCount,
            P7_Dhruva_Krupa_ToolKit toolKit) {
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.origin = origin;
        this.innerColor = innerColor;
        this.petalColor = petalColor;
        this.petalCount = petalCount;
        this.toolKit = toolKit;
    }

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
                        5, 15, new Point(400, 300), Color.ORANGE, Color.RED, 5, toolKit);
        flower.draw();
    }
}
