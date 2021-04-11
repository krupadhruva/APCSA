/*
 * Name: Krupa Dhruva
 * Date: April 10, 2021
 * Period: 7
 * Time Taken: 90 minutes
 *
 * Lab Reflection:
 * This was mostly refactoring and integrating with game of life.
 * I would like to learn how to resize the main window when the
 * number of cells in a grid changes.
 *
 * Additional features:
 *  - Allows use to build their own life grid by selecting
 *  - Updating running game with additional modification to cells
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
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class P7_Dhruva_Krupa_LifeGUI_1 extends Application {
    private P7_Dhruva_Krupa_LifeModel lifeModel;
    private GridModel<Boolean> gridModel;
    private BooleanGridPane gamePane;
    private Slider controlSldr;

    /** Handler to flip neighboring cell colors when a cell in grid is clicked */
    static class GamePaneHandler implements EventHandler<MouseEvent> {
        private final P7_Dhruva_Krupa_LifeGUI_1 lifeApp;

        GamePaneHandler(P7_Dhruva_Krupa_LifeGUI_1 app) {
            this.lifeApp = app;
        }

        @Override
        public void handle(MouseEvent event) {
            int row = lifeApp.gamePane.rowForYPos(event.getY());
            int col = lifeApp.gamePane.colForXPos(event.getX());

            for (int rowIdx = row - 1; rowIdx <= row + 1; ++rowIdx) {
                for (int colIdx = col - 1; colIdx <= col + 1; ++colIdx) {
                    if (!(rowIdx == row && colIdx == col)) {
                        try {
                            Boolean currVal = lifeApp.gridModel.getValueAt(rowIdx, colIdx);
                            lifeApp.gridModel.setValueAt(rowIdx, colIdx, !currVal);
                            lifeApp.gamePane.cellChanged(rowIdx, colIdx, currVal, !currVal);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }

            Boolean[][] grid =
                    new Boolean[lifeApp.gridModel.getNumRows()][lifeApp.gridModel.getNumCols()];
            for (int rid = 0; rid < grid.length; ++rid) {
                for (int cid = 0; cid < grid[rid].length; ++cid) {
                    grid[rid][cid] = lifeApp.gridModel.getValueAt(rid, cid);
                }
            }

            lifeApp.setLifeModel(
                    new P7_Dhruva_Krupa_LifeModel(
                            grid,
                            lifeApp.lifeModel == null ? 0 : lifeApp.lifeModel.getGeneration()));
        }
    }

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

    /** Handler to load grid data from a file when Load button is pressed */
    static class LoadHandler implements EventHandler<ActionEvent> {
        private final P7_Dhruva_Krupa_LifeGUI_1 lifeApp;

        LoadHandler(P7_Dhruva_Krupa_LifeGUI_1 lifeApp) {
            this.lifeApp = lifeApp;
        }

        @Override
        public void handle(ActionEvent event) {
            final BooleanGridPane gamePane = lifeApp.gamePane;

            FileChooser filePicker = new FileChooser();
            filePicker.setTitle("Select file");
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter("Grid Files", "*.txt");
            filePicker.getExtensionFilters().add(filter);

            final File file = filePicker.showOpenDialog(gamePane.getScene().getWindow());
            if (file == null) {
                return;
            }

            try {
                final P7_Dhruva_Krupa_LifeModel lifeModel =
                        new P7_Dhruva_Krupa_LifeModel(file.getAbsolutePath());
                double width = lifeApp.gridModel.getNumCols() * lifeApp.gamePane.getTileSize();
                double tileSize = width / lifeModel.getBoard().length;
                lifeApp.gamePane.setTileSize(tileSize);
                lifeApp.controlSldr.setValue(tileSize);
                lifeApp.setLifeModel(lifeModel);
            } catch (FileNotFoundException ignored) {
            }
        }
    }

    /** Handler to clear the grid when Clear button is clicked */
    static class ClearHandler implements EventHandler<ActionEvent> {
        private final P7_Dhruva_Krupa_LifeGUI_1 life;
        private final Label label;

        ClearHandler(P7_Dhruva_Krupa_LifeGUI_1 life, Label label) {
            this.life = life;
            this.label = label;
        }

        @Override
        public void handle(ActionEvent event) {
            final Boolean[][] grid =
                    new Boolean[life.gridModel.getNumRows()][life.gridModel.getNumCols()];
            for (Boolean[] row : grid) {
                Arrays.fill(row, false);
            }

            life.setLifeModel(new P7_Dhruva_Krupa_LifeModel(grid, 0));
            label.setText(String.format("%3d", 0));
        }
    }

    /** Handler to show next generation when Next button is clicked */
    static class NextHandler implements EventHandler<ActionEvent> {
        private final P7_Dhruva_Krupa_LifeGUI_1 life;
        private final Label label;

        NextHandler(P7_Dhruva_Krupa_LifeGUI_1 life, Label label) {
            this.life = life;
            this.label = label;
        }

        @Override
        public void handle(ActionEvent event) {
            if (life.lifeModel == null || life.lifeModel.isGameOver()) {
                return;
            }

            life.lifeModel.nextGeneration();
            life.gridModel = new GridModel<>(life.lifeModel.getBoard());
            life.gamePane.setModel(life.gridModel);
            label.setText(String.format("%3d", life.lifeModel.getGeneration()));
        }
    }

    public void setLifeModel(P7_Dhruva_Krupa_LifeModel lifeModel) {
        this.lifeModel = lifeModel;
        this.gridModel = new GridModel<>(lifeModel.getBoard());
        this.gamePane.setModel(gridModel);
    }

    @Override
    public void start(Stage stage) {
        final Boolean[][] grid = new Boolean[8][8];
        for (Boolean[] row : grid) {
            Arrays.fill(row, false);
        }

        gamePane = new BooleanGridPane();
        gridModel = new GridModel<>(grid);

        gamePane.setTileSize(10);
        gamePane.setModel(gridModel);
        gamePane.setOnMouseClicked(new GamePaneHandler(this));

        // Horizontal bottom panel
        HBox hbBottom = new HBox();
        hbBottom.setFillHeight(true);
        HBox.setHgrow(hbBottom, Priority.ALWAYS);

        GridPane gpBottom = new GridPane();
        hbBottom.getChildren().add(gpBottom);

        final Insets padding = new Insets(5);
        {
            Label genLbl = new Label();
            genLbl.setPadding(padding);
            genLbl.setText(String.format("%3d", 0));

            Button loadBtn = new Button();
            loadBtn.setPadding(padding);
            loadBtn.setText("Load");
            loadBtn.setOnAction(new LoadHandler(this));
            gpBottom.add(loadBtn, 0, 1);

            Separator vs0 = new Separator(Orientation.VERTICAL);
            vs0.setVisible(false);
            gpBottom.add(vs0, 1, 1);

            Button clearBtn = new Button();
            clearBtn.setPadding(padding);
            clearBtn.setText("Clear");
            clearBtn.setOnAction(new ClearHandler(this, genLbl));
            gpBottom.add(clearBtn, 2, 1);

            Separator vs1 = new Separator(Orientation.VERTICAL);
            vs1.setVisible(false);
            gpBottom.add(vs1, 3, 1);

            Button nextBtn = new Button();
            nextBtn.setPadding(padding);
            nextBtn.setText("Next Generation");
            nextBtn.setOnAction(new NextHandler(this, genLbl));
            gpBottom.add(nextBtn, 4, 1);

            Separator vs2 = new Separator(Orientation.VERTICAL);
            vs2.setVisible(false);
            gpBottom.add(vs2, 5, 1);

            gpBottom.add(genLbl, 6, 1);

            Separator vs3 = new Separator(Orientation.VERTICAL);
            vs3.setVisible(false);
            gpBottom.add(vs3, 7, 1);

            controlSldr = new Slider(0.0, 100.0, 0.0);
            controlSldr.setMaxWidth(Double.MAX_VALUE);
            controlSldr.setPadding(padding);
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(10);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));
            controlSldr.setValue(60);
            HBox.setHgrow(controlSldr, Priority.ALWAYS);

            hbBottom.getChildren().add(controlSldr);
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
                        gridModel.getNumCols() * gamePane.cellAtGridCoords(0, 0).getHeight() + 150,
                        gridModel.getNumRows() * gamePane.cellAtGridCoords(0, 0).getHeight() + 200);
        stage.setTitle("Life GUI 1.0");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
