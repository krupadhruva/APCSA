import gpdraw.DrawingTool;

import java.awt.*;

/**
 * Class to draw the sky and grass background This should be drawn before any other object If not,
 * this will mask the other objects
 */
public class P7_Dhruva_Krupa_Background {

    /** Tool kit with drawing tools */
    private final P7_Dhruva_Krupa_ToolKit toolKit;
    /** Height of grass */
    private final int grassHeight;
    /** Sky color */
    private final Color skyColor;
    /** Grass color */
    private final Color grassColor;

    /**
     * Construct a background with sky and grass covering the entire width of the layout
     *
     * @param toolKit Tool kit with drawing tools
     * @param grassHeight Height of grass background
     * @param skyColor Color of sky
     * @param grassColor Color of grass background
     */
    public P7_Dhruva_Krupa_Background(
            P7_Dhruva_Krupa_ToolKit toolKit, int grassHeight, Color skyColor, Color grassColor) {
        this.grassHeight = grassHeight;
        this.skyColor = skyColor;
        this.grassColor = grassColor;
        this.toolKit = toolKit;
    }

    /** Draws sky and grass background */
    public void draw() {
        DrawingTool pen = toolKit.getPen();
        Dimension dim = pen.getPadPanel().getSize();

        pen.home();
        pen.setColor(skyColor);

        pen.down();
        pen.fillRect(dim.getWidth(), dim.getHeight());
        pen.up();

        pen.setDirection(-90);
        pen.move((dim.getHeight() - grassHeight) / 2);
        pen.setColor(grassColor);

        pen.down();
        pen.fillRect(dim.getWidth(), grassHeight);
        pen.up();
    }

    /**
     * Simple driver to test methods of the class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(800, 600);

        P7_Dhruva_Krupa_Background bg =
                new P7_Dhruva_Krupa_Background(toolKit, 100, Color.BLUE, Color.GREEN);
        bg.draw();
    }
}
