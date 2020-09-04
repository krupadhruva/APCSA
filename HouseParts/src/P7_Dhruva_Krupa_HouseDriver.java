import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.*;

/**
 * Driver class to assemble the various house parts and test the final view The intent is to test
 * the positioning of different parts with respect to each other.
 */
public final class P7_Dhruva_Krupa_HouseDriver {

    /**
     * Driver method combining different parts in a single layout
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        P7_Dhruva_Krupa_ToolKit toolKit =
                new P7_Dhruva_Krupa_ToolKit(new SketchPad(800, 600, 5L), 100L);
        DrawingTool pen = toolKit.getPen();

        Color skyColor = new Color(140, 189, 237);
        Color grassColor = new Color(59, 150, 47);

        Color leafColor = grassColor;
        Color trunkColor = new Color(99, 62, 62);

        Color wallColor = new Color(245, 179, 98);
        Color roofColor = new Color(130, 15, 15);
        Color doorColor = new Color(105, 65, 65);
        Color insideColor = new Color(240, 222, 170);
        Color[] windowColors = new Color[] {Color.BLUE, trunkColor, roofColor, doorColor};

        // Sky
        toolKit.move(400, 300);
        pen.setColor(skyColor);
        pen.down();
        pen.fillRect(800, 600);

        // Sun
        P7_Dhruva_Krupa_Sun sun =
                new P7_Dhruva_Krupa_Sun(toolKit, new Point(600, 450), 30, 60, Color.ORANGE);
        sun.draw();

        // Grass
        toolKit.move(400, 50);
        pen.setColor(grassColor);
        pen.fillRect(800, 100);
        P7_Dhruva_Krupa_RandomFlock sprouts =
                new P7_Dhruva_Krupa_RandomFlock(
                        toolKit,
                        new Point(500, 0),
                        10,
                        300,
                        100,
                        100,
                        toolKit.filteredColor(grassColor, 0.6f, 1.0f));
        sprouts.draw();

        // Wall
        Point wallPos = new Point(70, 75);
        P7_Dhruva_Krupa_Wall wall =
                new P7_Dhruva_Krupa_Wall(toolKit, wallPos, 350, 175, 50, 30, wallColor);
        wall.draw();

        // Roof
        P7_Dhruva_Krupa_Roof roof =
                new P7_Dhruva_Krupa_Roof(toolKit, new Point(60, 70 + 175), 370, 100, 15, roofColor);
        roof.draw();

        // Window
        P7_Dhruva_Krupa_CutGlassWindow window =
                new P7_Dhruva_Krupa_CutGlassWindow(
                        toolKit, new Point(100, 100), 120, 80, windowColors, insideColor);
        window.draw();

        // Partially open door
        P7_Dhruva_Krupa_Door door =
                new P7_Dhruva_Krupa_Door(
                        toolKit, new Point(300, 75), 60, 120, doorColor, insideColor);
        door.draw();

        // Flock of birds
        P7_Dhruva_Krupa_RandomFlock birds =
                new P7_Dhruva_Krupa_RandomFlock(
                        toolKit, new Point(100, 400), 10, 400, 100, 15, Color.BLACK);
        birds.draw();

        // Trees
        P7_Dhruva_Krupa_RandomTree treeB =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, new Point(500, 100), 30, 150, 75, 30, trunkColor, leafColor);
        treeB.draw();
        P7_Dhruva_Krupa_RandomTree treeM =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, new Point(575, 50), 30, 100, 100, 30, trunkColor, leafColor);
        treeM.draw();
        P7_Dhruva_Krupa_RandomTree treeF =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, new Point(650, 25), 30, 150, 75, 30, trunkColor, leafColor);
        treeF.draw();
        P7_Dhruva_Krupa_RandomTree treeS =
                new P7_Dhruva_Krupa_RandomTree(
                        toolKit, new Point(750, 90), 20, 100, 100, 50, trunkColor, leafColor);
        treeS.draw();

        // For debugging: Draw a simple scale
        // toolKit.drawScale();
    }
}
