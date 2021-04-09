/*
 * Name: Krupa Dhruva
 * Date: April 08, 2021
 * Period: 7
 * Time Taken: 150 minutes
 *
 * Lab Reflection:
 *
 * I took sometime understanding the available code template and figuring out how to
 * create a proper layout. Working on adding handler for mouse click.
 *
 * For finding the neighboring cells, I found similarities in the knights tour problem
 * and used a similar logic to get all neighboring cells. Loading grid file was
 * similar to loading image file in a previous assignment and have used "*.txt" pattern
 * to restrict file types.
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class P7_Dhruva_Krupa_GridViewer extends Application {

    /** Handler to zoom the grid when slider is moved */
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

    /** Handler to flip neighboring cell colors when a cell in grid is clicked */
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

    /** Handler to load grid data from a file when Load button is pressed */
    static class LoadHandler implements EventHandler<ActionEvent> {
        private final BooleanGridPane gamePane;

        LoadHandler(BooleanGridPane gridPane) {
            this.gamePane = gridPane;
        }

        @Override
        public void handle(ActionEvent event) {
            FileChooser filePicker = new FileChooser();
            filePicker.setTitle("Select file");
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter("Grid Files", "*.txt");
            filePicker.getExtensionFilters().add(filter);

            final File file = filePicker.showOpenDialog(gamePane.getScene().getWindow());
            if (file == null) {
                return;
            }

            try (Scanner scan = new Scanner(file)) {
                if (!scan.hasNext()) {
                    return;
                }

                int row = scan.nextInt();
                if (!scan.hasNext()) {
                    return;
                }
                int col = scan.nextInt();

                final Boolean[][] grid = new Boolean[row][col];

                for (row = 0; row < grid.length; ++row) {
                    for (col = 0; col < grid[row].length; ++col) {
                        if (!scan.hasNext()) {
                            return;
                        }

                        grid[row][col] = scan.next().equals("X");
                    }
                }

                gamePane.setModel(new GridModel<>(grid));
            } catch (FileNotFoundException ignored) {
            }
        }
    }

    /** Handler to clear the grid when Clear button is clicked */
    static class ClearHandler implements EventHandler<ActionEvent> {
        private final BooleanGridPane gamePane;
        private final GridModel<Boolean> model;

        ClearHandler(BooleanGridPane gridPane, GridModel<Boolean> model) {
            this.gamePane = gridPane;
            this.model = model;
        }

        @Override
        public void handle(ActionEvent event) {
            final Boolean[][] grid = new Boolean[model.getNumRows()][model.getNumCols()];
            for (Boolean[] row : grid) {
                Arrays.fill(row, false);
            }

            model.setGrid(grid);
            gamePane.setModel(model);
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
            loadBtn.setOnAction(new LoadHandler(gamePane));

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button clearBtn = new Button();
            clearBtn.setPadding(new Insets(5));
            clearBtn.setText("Clear");
            clearBtn.setOnAction(new ClearHandler(gamePane, model));

            Separator vs = new Separator(Orientation.VERTICAL);
            vs.setVisible(false);
            vs.setPadding(new Insets(10));

            Slider controlSldr = new Slider(0.0, 100.0, 0);
            controlSldr.setPadding(new Insets(2));
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(1);
            controlSldr.setSnapToTicks(true);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));
            controlSldr.setValue(60);

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
                        model.getNumCols() * gamePane.cellAtGridCoords(0, 0).getHeight() + 150,
                        model.getNumRows() * gamePane.cellAtGridCoords(0, 0).getHeight() + 200);
        stage.setTitle("Grid Viewer");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
