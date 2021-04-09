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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

    static class GamePaneHandler implements EventHandler<MouseEvent> {
        private final BooleanGridPane gridPane;
        private final GridModel<Boolean> model;

        GamePaneHandler(BooleanGridPane gridPane, GridModel<Boolean> model) {
            this.gridPane = gridPane;
            this.model = model;
        }

        @Override
        public void handle(MouseEvent event) {
            int row = gridPane.rowForYPos(event.getY());
            int col = gridPane.colForXPos(event.getX());

            for (int rowIdx = row - 1; rowIdx <= row + 1; ++rowIdx) {
                for (int colIdx = col - 1; colIdx <= col + 1; ++colIdx) {
                    if (!(rowIdx == row && colIdx == col)) {
                        try {
                            Boolean currVal = model.getValueAt(rowIdx, colIdx);
                            model.setValueAt(rowIdx, colIdx, !currVal);
                            gridPane.cellChanged(rowIdx, colIdx, currVal, !currVal);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }
    }

    static class ClearHandler implements EventHandler<ActionEvent> {
        private final BooleanGridPane gamePane;
        private final GridModel<Boolean> model;

        ClearHandler(BooleanGridPane gridPane, GridModel<Boolean> model) {
            this.gamePane = gridPane;
            this.model = model;
        }

        @Override
        public void handle(ActionEvent event) {
            for (int row = 0; row < model.getNumRows(); ++row) {
                for (int col = 0; col < model.getNumCols(); ++col) {
                    model.setValueAt(row, col, false);
                }
            }
            gamePane.gridReplaced();
        }
    }

    @Override
    public void start(Stage stage) {
        HBox hbBottom = new HBox();

        final BooleanGridPane gamePane = new BooleanGridPane();
        gamePane.setTileSize(100);
        final Boolean[][] grid = new Boolean[5][5];
        for (Boolean[] row : grid) {
            Arrays.fill(row, false);
        }

        final GridModel<Boolean> model = new GridModel<>(grid);
        gamePane.setModel(model);
        gamePane.setOnMouseClicked(new GamePaneHandler(gamePane, model));

        // Horizontal bottom panel
        {
            Button loadBtn = new Button();
            loadBtn.setPadding(new Insets(5));
            loadBtn.setText("Load");

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button clearBtn = new Button();
            clearBtn.setPadding(new Insets(5));

            clearBtn.setText("Clear");
            clearBtn.setOnAction(new ClearHandler(gamePane, model));

            Separator vs = new Separator(Orientation.VERTICAL);
            vs.setVisible(false);
            vs.setPadding(new Insets(10));

            Slider controlSldr = new Slider(0.0, 100.0, 60);
            controlSldr.setPadding(new Insets(2));
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(1);
            controlSldr.setSnapToTicks(true);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));

            hbBottom.getChildren().addAll(loadBtn, vSpace, clearBtn, vs, controlSldr);
            HBox.setHgrow(controlSldr, Priority.ALWAYS);
        }

        // Top most pane containing rest of the layouts
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5)); // space between elements and window border
        root.setCenter(gamePane);
        root.setBottom(hbBottom);

        // Create scene since we have the grid to set the size of scene
        final Scene scene =
                new Scene(
                        root,
                        // TODO: Need to understand how to get the horizontal box height & width
                        model.getNumCols() * gamePane.getTileSize() + 100,
                        model.getNumRows() * gamePane.getTileSize() + 200);
        stage.setTitle("Grid Viewer");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
