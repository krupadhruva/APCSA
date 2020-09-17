import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.Color;

/*
 * Name: Krupa Dhruva
 * Date: September 16, 2020
 * Period: 7
 * Time Taken: 2 hours
 *
 * Lab Reflection:
 * This lab was pretty fun! The planning process was the most challenging
 * part of this assignment as there was quite a bit of logic that needed to spread across
 * helper methods. Overall, I am really proud of my illusion even though it is a little
 * simplistic in the sense that it only has straight lines. On the contrary, the simplicity
 * was still powerful in creating a cool illusion. Next time, I would like to explore animation
 * based illusions if possible (like the moving dots illusion).
 *
 */

public class P7_Dhruva_Krupa_Driver {
    public static void main(String[] args) throws InterruptedException {
        SketchPad pad = new SketchPad(800, 600, 5L);
        DrawingTool pen = new DrawingTool(pad);

        // Build with simple constructor with default values
        P7_Dhruva_Krupa_Illusion illusion = new P7_Dhruva_Krupa_Illusion(pen);
        illusion.draw();
        Thread.sleep(1000);

        // Build with custom values
        illusion = new P7_Dhruva_Krupa_Illusion(pen, 50, 50, Color.CYAN, Color.RED.darker());
        illusion.draw();
        Thread.sleep(1000);

        // Use setters to update existing values
        illusion.setForeground(Color.YELLOW);
        illusion.setBackground(Color.BLUE.darker());
        illusion.draw();
    }
}
