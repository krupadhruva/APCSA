/*
 * Name: Krupa Dhruva
 * Date: April 08, 2021
 * Period: 7
 * Time Taken: 90 minutes
 *
 * Lab Reflection:
 *
 * I took sometime understanding the available code template and figuring out how to
 * create a proper layout. Working on adding handler for mouse click.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class P7_Dhruva_Krupa_GridViewer extends Application {
    static class SliderHandler implements ChangeListener<Number> {
        private final BooleanGridPane gridPane;

        SliderHandler(BooleanGridPane gridPane) {
            this.gridPane = gridPane;
        }

        @Override
        public void changed(
                ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            gridPane.setTileSize(newValue.doubleValue());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = new VBox();
        HBox hbBottom = new HBox();

        final BooleanGridPane gamePane = new BooleanGridPane();
        gamePane.setTileSize(50);
        Boolean[][] grid = new Boolean[5][5];
        for (Boolean[] row : grid) {
            Arrays.fill(row, false);
            row[2] = true;
        }
        gamePane.setModel(new GridModel<>(grid));

        // Horizontal bottom panel
        {
            Button loadBtn = new Button();
            loadBtn.setPadding(new Insets(5));
            loadBtn.setText("Load");
            // cancelBtn.setOnAction();

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button clearBtn = new Button();
            clearBtn.setPadding(new Insets(5));

            clearBtn.setText("Clear");
            // applyBtn.setOnAction();

            Separator vs = new Separator(Orientation.VERTICAL);
            vs.setVisible(false);
            vs.setPadding(new Insets(10));

            Slider controlSldr = new Slider(0, 1, 0);
            controlSldr.setPadding(new Insets(2));
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(1);
            controlSldr.setSnapToTicks(true);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));
            controlSldr.setValue(gamePane.getTileSize());

            hbBottom.getChildren().addAll(loadBtn, vSpace, clearBtn, vs, controlSldr);
        }

        // Top most pane containing rest of the layouts
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5)); // space between elements and window border
        root.setCenter(gamePane);
        root.setBottom(hbBottom);

        // Create scene since we have the image to set the size of scene
        final Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Grid Viewer");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
