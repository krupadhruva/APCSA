import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import p7_house_parts.P7_Das_Ishani_Cloud;
import p7_house_parts.P7_Dhruva_Krupa_Door;
import p7_house_parts.P7_Dhruva_Krupa_Fence;
import p7_house_parts.P7_Dhruva_Krupa_Flower;
import p7_house_parts.P7_Dhruva_Krupa_RandomFlock;
import p7_house_parts.P7_Dhruva_Krupa_ToolKit;
import p7_house_parts.P7_Doan_Kenny_Roof;
import p7_house_parts.P7_Han_Eric_Wall;
import p7_house_parts.P7_Jenna_Jaehnig_Background;
import p7_house_parts.P7_Lee_Jihoo_PottedPlant;
import p7_house_parts.P7_Mhaiskar_Arya_Window;
import p7_house_parts.P7_Tata_Ilyes_Tree;

import java.awt.*;

public class P7_Dhruva_Krupa_CoolHouse {

    public static void main(String[] args) {
        SketchPad pad = new SketchPad(800, 600, 5L);
        P7_Dhruva_Krupa_ToolKit toolKit = new P7_Dhruva_Krupa_ToolKit(pad, null);
        DrawingTool pen = toolKit.getPen();
        toolKit.reset();

        // Background
        P7_Jenna_Jaehnig_Background bg = new P7_Jenna_Jaehnig_Background(800, 600, pen);
        bg.setTime(false);
        bg.draw();
        toolKit.reset();

        // Cloud
        P7_Das_Ishani_Cloud cloud = new P7_Das_Ishani_Cloud(200, 100, pen);
        cloud.drawCloud();
        toolKit.reset();

        cloud = new P7_Das_Ishani_Cloud(325, 125, pen);
        cloud.drawCloud();
        toolKit.reset();

        // Grass
        P7_Dhruva_Krupa_RandomFlock grass =
                new P7_Dhruva_Krupa_RandomFlock(
                        toolKit, new Point(-100, -250), 5, 500, 150, 250, new Color(30, 80, 30));
        grass.draw();
        toolKit.reset();

        // Brick wall
        P7_Han_Eric_Wall wall = new P7_Han_Eric_Wall(-180, -100, 210, 125, 20, 10, pen);
        wall.draw();
        toolKit.reset();

        // Roof with chimney and smoke
        P7_Doan_Kenny_Roof roof = new P7_Doan_Kenny_Roof(pen, -180, -35, 250, 75);
        roof.setRoofColors(
                toolKit.filteredColor(Color.LIGHT_GRAY, 0.6f, 1.0f),
                new Color(130, 15, 15),
                Color.LIGHT_GRAY);
        roof.draw();
        toolKit.reset();

        // Partially open door
        P7_Dhruva_Krupa_Door door =
                new P7_Dhruva_Krupa_Door(
                        toolKit,
                        new Point(-160, -163),
                        50,
                        80,
                        new Color(105, 65, 65),
                        new Color(240, 222, 170));
        door.draw();
        toolKit.reset();

        // Window next to door
        P7_Mhaiskar_Arya_Window window = new P7_Mhaiskar_Arya_Window(-220, -100, 80, 40, pen, 1);
        window.setWindowColor(240, 222, 170);
        window.draw();
        toolKit.reset();

        // First part of fence
        P7_Dhruva_Krupa_Fence fence =
                new P7_Dhruva_Krupa_Fence(
                        toolKit, new Point(-350, -200), 10, 60, 8, 4, Color.WHITE, 15);
        fence.draw();
        toolKit.reset();

        // Second part of fence with gap
        fence =
                new P7_Dhruva_Krupa_Fence(
                        toolKit, new Point(-25, -200), 10, 60, 8, 4, Color.WHITE, 5);
        fence.draw();
        toolKit.reset();

        // Draw 3 trees
        P7_Tata_Ilyes_Tree tree = new P7_Tata_Ilyes_Tree(pen, 20, 125, Color.RED);
        tree.setXY(150, -50);
        tree.draw();
        toolKit.reset();

        tree.setXY(250, -60);
        tree.draw();
        toolKit.reset();

        tree.setXY(300, -40);
        tree.draw();
        toolKit.reset();

        P7_Lee_Jihoo_PottedPlant pot = new P7_Lee_Jihoo_PottedPlant(pen);
        pot.setPlantSize(25);
        pot.setPotSize(15);

        pot.setPotLocation(-50, -125);
        pot.draw();

        pot.setPotLocation(-25, -125);
        pot.draw();

        pot.setPotLocation(0, -125);
        pot.draw();

        pot.setPotLocation(25, -125);
        pot.draw();

        Point flowerLocation = new Point(-50, -75);
        P7_Dhruva_Krupa_Flower flower =
                new P7_Dhruva_Krupa_Flower(
                        toolKit, 3, 9, flowerLocation, Color.ORANGE, Color.RED, 5);
        flower.draw();

        flowerLocation.translate(25, 0);
        flower =
                new P7_Dhruva_Krupa_Flower(
                        toolKit, 3, 9, flowerLocation, Color.ORANGE, Color.RED, 5);
        flower.draw();

        flowerLocation.translate(60, 0);
        flower =
                new P7_Dhruva_Krupa_Flower(
                        toolKit, 3, 9, flowerLocation, Color.ORANGE, Color.RED, 5);
        flower.draw();

        // Dimension dim = pen.getPadPanel().getSize();
        // toolKit.setOrigin(new Point(-dim.width / 2, -dim.height / 2));
        // toolKit.drawScale();
    }
}
