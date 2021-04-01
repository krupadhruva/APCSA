/*
 * Name: Krupa Dhruva
 * Date: March 31, 2021
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Implementing event handlers as separate classes makes it easier to test the handlers independently.
 * It also taught me to better decompose the main JavaFx class and isolate GUI and handler logic.
 *
 * I had previous use simple lambda functions for basic handlers and had missed the opportunity
 * to implement them as reusable event/change handler classes. For anything complex, I now prefer
 * using dedicated classes over lambda functions.
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class P7_Dhruva_Krupa_GUI_3 extends Application {
    private static final String IMAGE_URL =
            "https://i.pinimg.com/originals/6f/c6/31/6fc63119e8e3e2c46f0d3b621f38a91b.jpg";

    /** Helper class to implement event handler to exit the JavaFx app */
    static class ExitHandler implements EventHandler<ActionEvent> {
        private final Stage stage;

        public ExitHandler(Stage stage) {
            this.stage = stage;
        }

        @Override
        public void handle(ActionEvent event) {
            stage.close();
        }
    }

    /** Helper class implementing handler when delay expires */
    static class PauseHandler implements EventHandler<ActionEvent> {
        private final Popup popup;

        PauseHandler(Popup popup) {
            this.popup = popup;
        }

        @Override
        public void handle(ActionEvent event) {
            popup.hide();
        }
    }

    /** Helper class displaying popup message on event */
    static class DisplayMessageHandler implements EventHandler<ActionEvent> {
        private final Stage stage;
        private final Canvas canvas;
        private final String message;

        DisplayMessageHandler(Stage stage, Canvas canvas, String message) {
            this.stage = stage;
            this.canvas = canvas;
            this.message = message;
        }

        @Override
        public void handle(ActionEvent event) {
            stage.setAlwaysOnTop(false);
            Popup popup = new Popup();
            popup.setAutoFix(true);
            popup.setAutoHide(true);

            Label label = new Label(message);
            label.setPadding(new Insets(20));
            label.setAlignment(Pos.CENTER);
            label.setTextFill(Color.BLUE);
            label.setStyle(" -fx-background-color: white;");
            label.setMinHeight(50);
            popup.getContent().add(label);

            Point2D pt = canvas.localToScene(0.0, 0.0);
            popup.setX(stage.getX() + pt.getX() + 100);
            popup.setY(stage.getY() + pt.getY() + 100);

            if (!popup.isShowing()) {
                // Hide popup after 2 seconds:
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(new PauseHandler(popup));

                popup.show(stage);
                delay.play();
            }
        }
    }

    /**
     * Helper class implementing exclusive enabling of radio button among a group of radio buttons.
     * This ensures only a single option is enabled at any point.
     */
    static class RadioButtonHandler implements EventHandler<ActionEvent> {
        private final RadioButton[] radios;

        RadioButtonHandler(RadioButton... radios) {
            this.radios = radios;
        }

        @Override
        public void handle(ActionEvent event) {
            if (event.getTarget() instanceof RadioButton) {
                RadioButton triggered = (RadioButton) event.getTarget();
                for (RadioButton radio : radios) {
                    if (triggered != radio) {
                        radio.setSelected(false);
                    }
                }
            }
        }
    }

    /**
     * Helper class implementing change listener for slider. Updates a label with the current value
     * of the slider
     */
    static class SliderHandler implements ChangeListener<Number> {
        private final Label label;

        SliderHandler(Label label) {
            this.label = label;
        }

        @Override
        public void changed(
                ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            label.setText(String.format("%.2f", newValue.doubleValue()));
        }
    }

    @Override
    public void start(Stage rootStage) throws Exception {
        // Create a new stage and set it to modal - we want focus
        Stage stage = new Stage();

        // Draw the image on a canvas - we can redraw when we support actual image editing
        Canvas canvas = new Canvas(500.0, 500.0);

        stage.initOwner(rootStage);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.WINDOW_MODAL);

        Image image = new Image(IMAGE_URL);
        if (!image.isError()) {
            canvas.setWidth(image.getWidth());
            canvas.setHeight(image.getHeight());
            canvas.getGraphicsContext2D().drawImage(image, 0, 0);
        }

        // Example menu bar with support to pick image and exit app
        MenuBar menuBar = new MenuBar();

        Menu optionsMenu = new Menu("Editor");
        MenuItem selectItm = new MenuItem("Image path");

        // Exit app from menu bar
        MenuItem exitItm = new MenuItem("Exit");
        exitItm.setOnAction(new ExitHandler(stage));
        optionsMenu.getItems().addAll(selectItm, exitItm);

        Menu aboutMenu = new Menu("About");
        aboutMenu.getItems().addAll(new MenuItem("Help"), new MenuItem("Version"));
        menuBar.getMenus().addAll(optionsMenu, aboutMenu);

        // Center - image to be edited occupies the main part of the app window
        VBox vbCenter = new VBox();

        // Bottom - apply/cancel
        HBox hbBottom = new HBox();
        hbBottom.setAlignment(Pos.BOTTOM_RIGHT);

        // Image editor controls
        GridPane gridPane = new GridPane();
        // gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.BOTTOM_LEFT);

        // Top most pane containing rest of the layouts
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5)); // space between elements and window border
        root.setTop(menuBar);
        root.setCenter(vbCenter);
        root.setBottom(hbBottom);

        // Create scene since we have the image to set the size of scene
        Scene scene = new Scene(root, canvas.getWidth(), canvas.getHeight() + 150);
        stage.setTitle("Image Editor");
        stage.setScene(scene);
        stage.sizeToScene();

        // Load image for editing
        {
            // Using a scroll pane allows us to pan the image
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(canvas);
            vbCenter.getChildren().add(scrollPane);

            Separator hSpace = new Separator();
            hSpace.setPadding(new Insets(5));
            vbCenter.getChildren().addAll(hSpace, gridPane);
        }

        // Add various image controls
        int rows = 0;
        {
            {
                CheckBox persistChkBox = new CheckBox("Persist changes");
                persistChkBox.setPadding(new Insets(2));
                gridPane.add(persistChkBox, 0, rows++);

                Separator hs1 = new Separator();
                hs1.setVisible(false);
                hs1.setPadding(new Insets(10));
                gridPane.add(hs1, 0, rows++);

                RadioButton rawRbtn = new RadioButton("RAW");
                rawRbtn.setPadding(new Insets(2));
                gridPane.add(rawRbtn, 0, rows++);

                RadioButton jpgRbtn = new RadioButton("JPEG");
                jpgRbtn.setPadding(new Insets(2));
                gridPane.add(jpgRbtn, 0, rows++);

                RadioButton heicRbtn = new RadioButton("HEIC");
                heicRbtn.setPadding(new Insets(2));
                gridPane.add(heicRbtn, 0, rows++);

                jpgRbtn.setSelected(true);
                rawRbtn.setOnAction(new RadioButtonHandler(jpgRbtn, rawRbtn, heicRbtn));
                jpgRbtn.setOnAction(new RadioButtonHandler(jpgRbtn, rawRbtn, heicRbtn));
                heicRbtn.setOnAction(new RadioButtonHandler(jpgRbtn, rawRbtn, heicRbtn));

                Separator hs2 = new Separator();
                hs2.setVisible(false);
                hs2.setPadding(new Insets(10));
                gridPane.add(hs2, 0, rows++);
            }

            {
                Text effectsTxt = new Text("Effects");
                gridPane.add(effectsTxt, 0, rows);

                ComboBox<String> effectsCbox = new ComboBox<>();
                effectsCbox.getItems().addAll("Blur", "Light", "Sepia");
                gridPane.add(effectsCbox, 1, rows);
                effectsCbox.getSelectionModel().selectFirst();

                Separator vs = new Separator(Orientation.VERTICAL);
                gridPane.add(vs, 2, rows);
                vs.setVisible(false);
                vs.setPadding(new Insets(10));

                Slider controlSldr = new Slider(0, 1, 0);
                gridPane.add(controlSldr, 3, rows);
                controlSldr.setPadding(new Insets(2));
                controlSldr.setMin(0.0);
                controlSldr.setMax(100.0);
                controlSldr.setSnapToTicks(true);
                controlSldr.setShowTickLabels(true);

                Separator vs1 = new Separator(Orientation.VERTICAL);
                gridPane.add(vs1, 4, rows);
                vs1.setVisible(false);
                vs1.setPadding(new Insets(10));

                Label controlLbl = new Label();
                gridPane.add(controlLbl, 5, rows);
                controlLbl.setPadding(new Insets(10));
                controlLbl.setText("0.0");

                controlSldr.valueProperty().addListener(new SliderHandler(controlLbl));
            }
        }

        // The final apply/cancel buttons to commit or exit the app
        {
            Button cancelBtn = new Button();
            cancelBtn.setPadding(new Insets(5));
            cancelBtn.setText("Cancel");
            cancelBtn.setOnAction(new ExitHandler(stage));

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button applyBtn = new Button();
            applyBtn.setPadding(new Insets(5));

            applyBtn.setText("Apply");
            applyBtn.setOnAction(new DisplayMessageHandler(stage, canvas, "Applied changes"));

            hbBottom.getChildren().addAll(cancelBtn, vSpace, applyBtn);
        }

        stage.show();
    }
}
