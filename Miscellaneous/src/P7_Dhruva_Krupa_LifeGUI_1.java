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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

    /** Handler to flip selected cell color when a cell in grid is clicked */
    static class GamePaneHandler implements EventHandler<MouseEvent> {
        private final P7_Dhruva_Krupa_LifeGUI_1 lifeApp;

        GamePaneHandler(P7_Dhruva_Krupa_LifeGUI_1 app) {
            this.lifeApp = app;
        }

        @Override
        public void handle(MouseEvent event) {
            int row = lifeApp.gamePane.rowForYPos(event.getY());
            int col = lifeApp.gamePane.colForXPos(event.getX());

            Boolean currVal = lifeApp.gridModel.getValueAt(row, col);
            lifeApp.gridModel.setValueAt(row, col, !currVal);
            lifeApp.gamePane.cellChanged(row, col, currVal, !currVal);

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
            label.setText(String.format("%d", life.lifeModel.getGeneration()));
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

        gamePane.setTileSize(50);
        gamePane.setModel(gridModel);
        gamePane.setOnMouseClicked(new GamePaneHandler(this));

        // Horizontal bottom panel
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(false);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);

        // Note: This is very important to get different columns in a grid pane resize when window
        // size changes. We want the slider to resize only and that is the last column.
        // https://stackoverflow.com/questions/14753793/javafx-get-gridpane-to-fit-parent
        ColumnConstraints sldrCol = new ColumnConstraints();
        sldrCol.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints()
                .addAll(
                        new ColumnConstraints(),
                        new ColumnConstraints(),
                        new ColumnConstraints(),
                        new ColumnConstraints(),
                        sldrCol);

        final Insets padding = new Insets(5);
        {
            Label genLbl = new Label();
            genLbl.setPadding(padding);
            genLbl.setText(String.format("%d", 0));

            Button loadBtn = new Button();
            loadBtn.setPadding(padding);
            loadBtn.setText("Load");
            loadBtn.setOnAction(new LoadHandler(this));
            gridPane.add(loadBtn, 0, 1);

            Button clearBtn = new Button();
            clearBtn.setPadding(padding);
            clearBtn.setText("Clear");
            clearBtn.setOnAction(new ClearHandler(this, genLbl));
            gridPane.add(clearBtn, 1, 1);

            Button nextBtn = new Button();
            nextBtn.setPadding(padding);
            nextBtn.setText("Next Generation");
            nextBtn.setOnAction(new NextHandler(this, genLbl));
            gridPane.add(nextBtn, 2, 1);

            Label genTitleLbl = new Label("Generation");
            gridPane.add(genTitleLbl, 3, 0);
            GridPane.setHalignment(genLbl, HPos.CENTER);
            gridPane.add(genLbl, 3, 1);

            controlSldr = new Slider(0.0, 100.0, 0.0);
            controlSldr.setValue(gamePane.getTileSize());
            controlSldr.setMaxWidth(Double.MAX_VALUE);
            controlSldr.setPadding(padding);
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(10);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));

            Label sldrTitleLbl = new Label("Cell size");
            GridPane.setHalignment(sldrTitleLbl, HPos.CENTER);
            gridPane.add(sldrTitleLbl, 4, 0);
            gridPane.add(controlSldr, 4, 1);
        }

        // Top most pane containing rest of the layouts
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        // Hinge everything to top right corner (for center: root.setCenter(gamePane))
        root.setTop(gamePane);
        root.setBottom(gridPane);

        // Create scene since we have the grid to set the size of scene
        final Scene scene =
                new Scene(
                        root,
                        // TODO: Need to understand how to get the horizontal box height & width
                        gamePane.getLayoutBounds().getWidth() + gamePane.getTileSize() / 2,
                        gamePane.getLayoutBounds().getHeight() + gamePane.getTileSize() * 2);
        stage.setTitle("Life GUI 1.0");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
