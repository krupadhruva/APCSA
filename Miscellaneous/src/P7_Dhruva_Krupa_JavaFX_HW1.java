/*
 * Name: Krupa Dhruva
 * Date: March 17, 2021
 * Period: 7
 * Time Taken: 80 minutes
 *
 * Lab Reflection:
 * Setting up JavaFX on Mac took some time since I am not using Oracle Java SDK.
 * Once I got this working, I spent time understanding the coordinate system by
 * drawing small shapes and positioning them.
 *
 * Drawing regular shapes like rectangle, circle and a polygon was not too difficult.
 * However, positioning an arc was hard. I need to understand the different parameters
 * for constructing an arc better. I tried something similar to cool house project.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class P7_Dhruva_Krupa_JavaFX_HW1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Default window");

        Group root = new Group();
        ObservableList<Node> items = root.getChildren();

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();

        Rectangle sky = new Rectangle(scene.getWidth(), 400, Color.LIGHTBLUE);
        items.add(sky);

        Circle sun = new Circle(600, 100, 30);
        items.add(sun);
        sun.setFill(Color.YELLOW);

        Rectangle grass =
                new Rectangle(
                        0, sky.getHeight(), sky.getWidth(), scene.getHeight() - sky.getHeight());
        grass.setFill(Color.GREEN);
        items.add(grass);

        double xpos = 100;
        double ypos = sky.getHeight();
        Polygon wall =
                new Polygon(xpos, ypos, xpos + 200, ypos, xpos + 200, ypos - 100, xpos, ypos - 100);
        items.add(wall);
        wall.setFill(Color.SANDYBROWN);
        wall.setStroke(Color.BLACK);

        Polygon roof =
                new Polygon(
                        xpos - 10,
                        ypos - 100,
                        xpos + 200 + 10,
                        ypos - 100,
                        xpos + 100,
                        ypos - 150,
                        xpos - 10,
                        ypos - 100);
        items.add(roof);
        roof.setStroke(Color.BLACK);
        roof.setFill(Color.FIREBRICK);

        // Draw an umbrella
        xpos += 210;

        Line stick = new Line(xpos + 200, ypos, xpos + 200, ypos - 85);
        items.add(stick);

        stick.setStroke(Color.BLACK);
        stick.setStrokeWidth(5);

        Arc umbrella = new Arc(xpos + 200, ypos - 30, 50, 50, 30, Math.PI * 40);
        items.add(umbrella);

        umbrella.setType(ArcType.CHORD);
        umbrella.setStroke(Color.BLACK);
        umbrella.setStrokeWidth(5);
        umbrella.setFill(Color.DARKSLATEBLUE);

        stage.show();
    }
}
