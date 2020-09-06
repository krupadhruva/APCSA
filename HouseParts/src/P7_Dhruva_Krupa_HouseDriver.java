import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.*;
import java.util.PrimitiveIterator;
import java.util.Random;

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

        // Sky & grass background
        P7_Dhruva_Krupa_Background background =
                new P7_Dhruva_Krupa_Background(toolKit, 100, skyColor, grassColor);
        background.draw();

        // Sun
        P7_Dhruva_Krupa_Sun sun =
                new P7_Dhruva_Krupa_Sun(toolKit, new Point(600, 450), 30, 60, Color.ORANGE);
        sun.draw();

        // Grass sprouts
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

        Random rand = toolKit.getRandom();
        PrimitiveIterator.OfInt randInnerRad = rand.ints(3, 6).iterator();
        PrimitiveIterator.OfInt xVals = rand.ints(500, 800).iterator();
        PrimitiveIterator.OfInt yVals = rand.ints(10, 100).iterator();
        for (int count = 0; count < 15; ++count) {
            int innerRadius = randInnerRad.nextInt();
            P7_Dhruva_Krupa_Flower flower =
                    new P7_Dhruva_Krupa_Flower(
                            innerRadius,
                            innerRadius * 3,
                            new Point(xVals.nextInt(), yVals.nextInt()),
                            Color.ORANGE,
                            Color.RED,
                            5,
                            toolKit);
            flower.draw();
        }

        P7_Dhruva_Krupa_Fence fence =
                new P7_Dhruva_Krupa_Fence(
                        toolKit, new Point(40, 50), 12, 60, 20, 7, Color.WHITE, 14);
        fence.draw();

        // For debugging: Draw a simple scale
        // toolKit.drawScale();
    }
}
