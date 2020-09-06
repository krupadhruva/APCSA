import gpdraw.DrawingTool;

import java.awt.Color;
import java.awt.Point;

// TODO: Add javadocs
public class P7_Dhruva_Krupa_Fence {
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    private final int fenceWidth;
    private final int fenceHeight;
    private final int joinerWidth;
    private final int joinerHeight;
    private final Color color;
    private final Point origin;
    private final int count;

    // this is the constructor
    public P7_Dhruva_Krupa_Fence(
            P7_Dhruva_Krupa_ToolKit toolKit,
            Point origin,
            int fenceWidth,
            int fenceHeight,
            int joinerWidth,
            int joinerHeight,
            Color color,
            int count) {
        this.toolKit = toolKit;
        this.fenceWidth = fenceWidth;
        this.fenceHeight = fenceHeight;
        this.joinerWidth = joinerWidth;
        this.joinerHeight = joinerHeight;
        this.color = color;
        this.origin = origin;
        this.count = count;
    }

    // this is the actual logic for drawing
    public void draw() {
        DrawingTool pen = toolKit.getPen();

        for (int num = 0; num < count; ++num) {
            int offset = (fenceWidth + joinerWidth) * num;
            toolKit.move(origin.x + (fenceWidth / 2) + offset, origin.y + (fenceHeight / 2));
            pen.down();
            pen.setColor(color);
            pen.fillRect(fenceWidth, fenceHeight);

            if (num < (count - 1)) {
                toolKit.move(
                        origin.x + fenceWidth + (joinerWidth / 2) + offset,
                        origin.y + (int) (fenceHeight * 0.66));
                pen.fillRect(joinerWidth, joinerHeight);
            }
        }
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);
        P7_Dhruva_Krupa_Fence fence =
                new P7_Dhruva_Krupa_Fence(
                        toolKit, new Point(400, 300), 20, 50, 10, 5, Color.RED, 3);
        fence.draw();
    }
}
