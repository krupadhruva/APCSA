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
import javafx.scene.layout.BorderPane;
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
                lifeApp.gamePane.setTileSize(600.0 / lifeModel.getBoard().length);
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

            life.gridModel.setGrid(grid);
            life.gamePane.setModel(life.gridModel);
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
            if (life.lifeModel.isGameOver()) {
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
        final Boolean[][] grid = new Boolean[5][5];
        for (Boolean[] row : grid) {
            Arrays.fill(row, false);
        }

        gamePane = new BooleanGridPane();
        gridModel = new GridModel<>(grid);

        gamePane.setTileSize(10);
        gamePane.setModel(gridModel);
        // gamePane.setOnMouseClicked(new GamePaneHandler(gamePane, gridModel));

        // Horizontal bottom panel
        HBox hbBottom = new HBox();
        {
            Label genLbl = new Label();
            genLbl.setPadding(new Insets(10));
            genLbl.setText(String.format("%3d", 0));

            Button loadBtn = new Button();
            loadBtn.setPadding(new Insets(5));
            loadBtn.setText("Load");
            loadBtn.setOnAction(new LoadHandler(this));

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button clearBtn = new Button();
            clearBtn.setPadding(new Insets(5));
            clearBtn.setText("Clear");
            clearBtn.setOnAction(new ClearHandler(this, genLbl));

            Button nextBtn = new Button();
            nextBtn.setPadding(new Insets(5));
            nextBtn.setText("Next Generation");
            nextBtn.setOnAction(new NextHandler(this, genLbl));

            Separator vs = new Separator(Orientation.VERTICAL);
            vs.setVisible(false);
            vs.setPadding(new Insets(10));

            Separator vs1 = new Separator(Orientation.VERTICAL);
            vs1.setVisible(false);
            vs1.setPadding(new Insets(10));

            Slider controlSldr = new Slider(0.0, 100.0, 0);
            controlSldr.setPadding(new Insets(2));
            controlSldr.setMin(0.0);
            controlSldr.setMax(100.0);
            controlSldr.setMinorTickCount(1);
            controlSldr.setShowTickLabels(true);
            controlSldr.setShowTickMarks(true);
            controlSldr.valueProperty().addListener(new SliderHandler(gamePane));
            controlSldr.setValue(60);

            hbBottom.getChildren()
                    .addAll(loadBtn, vSpace, clearBtn, nextBtn, vs, genLbl, vs1, controlSldr);
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
                        gridModel.getNumCols() * gamePane.cellAtGridCoords(0, 0).getHeight() + 150,
                        gridModel.getNumRows() * gamePane.cellAtGridCoords(0, 0).getHeight() + 200);
        stage.setTitle("Life GUI 1.0");
        stage.setScene(scene);
        stage.sizeToScene();

        stage.show();
    }
}
