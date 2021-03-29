/*
 * Name: Krupa Dhruva
 * Date: March 28, 2021
 * Period: 7
 * Time Taken: 120 minutes
 *
 * Lab Reflection:
 * JavaFx has a very rich collection of building blocks for rich graphic user interfaces.
 * Exploring the different containers, controls other widgets took a lot of time. I still
 * have to explore lot more. I referred to online documentation and samples from Stackoverflow.
 *
 * As a digital photo editing enthusiast, I decided to explore building a simple image editor.
 * Positioning the various controls is very time consuming. I would like to explore if there
 * are easier ways to build a basic layout. Overall, I found this interesting and would like
 * to continue and explore image editing through this app.
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;

public class P7_Dhruva_Krupa_JavaFX_HW2 extends Application {
    private static final String IMAGE_URL =
            "https://i.pinimg.com/originals/6f/c6/31/6fc63119e8e3e2c46f0d3b621f38a91b.jpg";
    private static final ImageEffect[] IMAGE_EFFECTS =
            new ImageEffect[] {
                new ImageEffect("Blur", 0.0),
                new ImageEffect("Light", 0.0),
                new ImageEffect("Sepia", 0.0)
            };

    private Stage stage;
    private Canvas canvas;

    private static class ImageEffect {
        ImageEffect(String name, Double value) {
            this.name = name;
            this.value = value;
        }

        public String name;
        public Double value;
    }

    private boolean loadImageFile() {
        return loadImageFile(null);
    }

    private boolean loadImageFile(Image pickedImage) {
        if (pickedImage == null) {
            stage.setAlwaysOnTop(false);
            FileChooser imagePicker = new FileChooser();
            imagePicker.setTitle("Select image");
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter(
                            "Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
            imagePicker.getExtensionFilters().add(filter);

            File file = imagePicker.showOpenDialog(stage.getOwner());
            try {
                if (file != null) {
                    pickedImage = new Image(file.toURI().toURL().toString());
                }
            } catch (MalformedURLException ex) {
                popupNotification("Image loading error:" + ex, Duration.seconds(2));
            } finally {
                stage.setAlwaysOnTop(true);
            }
        }

        if (pickedImage == null || pickedImage.isError()) {
            return false;
        }

        canvas.setWidth(pickedImage.getWidth());
        canvas.setHeight(pickedImage.getHeight());
        canvas.getGraphicsContext2D().drawImage(pickedImage, 0, 0);

        return true;
    }

    private void popupNotification(String message, Duration pause) {
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
            PauseTransition delay = new PauseTransition(pause);
            delay.setOnFinished(e -> popup.hide());

            popup.show(stage);
            delay.play();
        }
    }

    private void applyImageEffects(int index, Slider controlSldr, Label controlLbl) {
        ImageEffect effect = IMAGE_EFFECTS[index];
        controlLbl.setText(String.format("%.2f", controlSldr.getValue()));
        IMAGE_EFFECTS[index].value = controlSldr.getValue();

        switch (effect.name) {
            case "Blur":
                canvas.setEffect(new GaussianBlur(effect.value / 10.0));
                break;
            case "Light":
                ColorAdjust br = new ColorAdjust();
                br.setBrightness(effect.value / 100.0);
                canvas.setEffect(br);
                break;
            case "Sepia":
                canvas.setEffect(new SepiaTone(effect.value / 100.0));
                break;
        }
    }

    @Override
    public void start(Stage rootStage) throws Exception {
        // Create a new stage and set it to modal - we want focus
        stage = new Stage();

        // Draw the image on a canvas - we can redraw when we support actual image editing
        canvas = new Canvas(500.0, 500.0);

        stage.initOwner(rootStage);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.WINDOW_MODAL);

        final boolean imageLoaded = loadImageFile(new Image(IMAGE_URL));

        // Example menu bar with support to pick image and exit app
        MenuBar menuBar = new MenuBar();

        Menu optionsMenu = new Menu("Editor");
        MenuItem selectItm = new MenuItem("Image path");
        selectItm.setOnAction(
                event -> {
                    if (!loadImageFile()) {
                        popupNotification("Failed to load image", Duration.seconds(2));
                    }
                });

        // Exit app from menu bar
        MenuItem exitItm = new MenuItem("Exit");
        exitItm.setOnAction(event -> stage.close());
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

                jpgRbtn.setSelected(true);
                rawRbtn.setOnAction(event -> jpgRbtn.setSelected(false));
                jpgRbtn.setOnAction(event -> rawRbtn.setSelected(false));

                Separator hs2 = new Separator();
                hs2.setVisible(false);
                hs2.setPadding(new Insets(10));
                gridPane.add(hs2, 0, rows++);
            }

            {
                Text effectsTxt = new Text("Effects");
                gridPane.add(effectsTxt, 0, rows);

                ComboBox<String> effectsCbox = new ComboBox<>();
                for (ImageEffect itm : IMAGE_EFFECTS) {
                    effectsCbox.getItems().add(itm.name);
                }
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

                effectsCbox.setOnAction(
                        event -> {
                            int index = effectsCbox.getSelectionModel().getSelectedIndex();
                            double value = IMAGE_EFFECTS[index].value;
                            controlSldr.setValue(value);
                            applyImageEffects(index, controlSldr, controlLbl);
                        });

                controlSldr.setOnMouseDragged(
                        event ->
                                applyImageEffects(
                                        effectsCbox.getSelectionModel().getSelectedIndex(),
                                        controlSldr,
                                        controlLbl));
            }
        }

        // The final apply/cancel buttons to commit or exit the app
        {
            Button cancelBtn = new Button();
            cancelBtn.setPadding(new Insets(5));
            cancelBtn.setText("Cancel");
            cancelBtn.setOnAction(event -> exitItm.fire());

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button applyBtn = new Button();
            applyBtn.setPadding(new Insets(5));

            applyBtn.setText("Apply");
            applyBtn.setOnAction(
                    event -> popupNotification("Applied changes", Duration.seconds(2)));

            hbBottom.getChildren().addAll(cancelBtn, vSpace, applyBtn);
        }

        stage.show();

        // Show hint to open image for editing
        if (!imageLoaded) {
            popupNotification("Open image to edit", Duration.seconds(5));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
