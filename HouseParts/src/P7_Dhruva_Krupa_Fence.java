import gpdraw.DrawingTool;

import java.awt.Color;
import java.awt.Point;

/**
 * Draws a simple fence with a rectangle vertical block and a joining rectangular horizontal block.
 *
 * <p>The height at which the joining block is hardcoded to 2/3rd the height of the vertical block
 */
public class P7_Dhruva_Krupa_Fence {
    /** Common drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Width of the vertical component of the fence */
    private final int fenceWidth;
    /** Height of the vertical component of the fence */
    private final int fenceHeight;
    /** Width of the joiner component */
    private final int joinerWidth;
    /** Height of the joiner component */
    private final int joinerHeight;
    /** Fence color */
    private final Color color;
    /** Starting location of fence */
    private final Point origin;
    /** Number of fence vertical components */
    private final int count;

    /**
     * Constructs a repeated layout of '|-' shaped fence components
     *
     * @param toolKit Common drawing tools
     * @param origin Location of start of fence
     * @param fenceWidth Width of vertical component
     * @param fenceHeight Height of vertical component
     * @param joinerWidth Width of joiner component
     * @param joinerHeight Height of joiner component
     * @param color Color of fence
     * @param count Number of fence blocks
     */
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

    /** Draw the fence */
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
                        toolKit, new Point(-250, 0), 15, 75, 10, 5, Color.RED, 10);
        fence.draw();
    }
}
