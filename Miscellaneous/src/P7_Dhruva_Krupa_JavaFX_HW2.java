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

    @Override
    public void start(Stage rootStage) throws Exception {
        // Create a new stage and set it to modal - we want focus
        Stage stage = new Stage();
        stage.initOwner(rootStage);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.WINDOW_MODAL);

        // Load a default image to edit
        Image image = new Image(IMAGE_URL);
        double imageWidth = image.getWidth();
        double imageHeight = image.getWidth();

        // If we fail to load the image, show a helpful message instead of blank
        if (image.isError()) {
            imageWidth = 600;
            imageHeight = 600;
            String err =
                    String.format("Failed to load: %s, select image from menu", image.getUrl());
            image = new Text(err).snapshot(null, null);
        }

        // Draw the image on a canvas - we can redraw when we support actual image editing
        final Canvas canvas = new Canvas(imageWidth, imageHeight);
        canvas.getGraphicsContext2D().drawImage(image, 0, 0);

        // Example menu bar with support to pick image and exit app
        MenuBar menuBar = new MenuBar();

        Menu optionsMenu = new Menu("Options");
        MenuItem selectItm = new MenuItem("Image path");
        selectItm.setOnAction(
                event -> {
                    stage.setAlwaysOnTop(false);
                    FileChooser imagePicker = new FileChooser();
                    imagePicker.setTitle("Select image");
                    FileChooser.ExtensionFilter filter =
                            new FileChooser.ExtensionFilter(
                                    "Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
                    imagePicker.getExtensionFilters().add(filter);
                    try {
                        File file = imagePicker.showOpenDialog(stage.getOwner());
                        Image pickedImage = new Image(file.toURI().toURL().toString());
                        canvas.getGraphicsContext2D().drawImage(pickedImage, 0, 0);
                        canvas.setWidth(pickedImage.getWidth());
                        canvas.setHeight(pickedImage.getHeight());
                    } catch (MalformedURLException e) {
                        System.out.println("Image loading error:" + e);
                    } finally {
                        stage.setAlwaysOnTop(true);
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
        Scene scene = new Scene(root, imageWidth, imageHeight);
        stage.setTitle("Image Editor");
        stage.setScene(scene);

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
                effectsCbox.getItems().addAll("Sepia", "Blur", "Light");
                effectsCbox.getSelectionModel().selectFirst();
                gridPane.add(effectsCbox, 1, rows);

                Separator vs = new Separator(Orientation.VERTICAL);
                vs.setVisible(false);
                vs.setPadding(new Insets(10));
                gridPane.add(vs, 2, rows);

                Slider controlSldr = new Slider(0, 1, 0);
                controlSldr.setPadding(new Insets(2));
                gridPane.add(controlSldr, 3, rows);
            }
        }

        // The final apply/cancel buttons to commit or exit the app
        {
            Button cancelBtn = new Button();
            cancelBtn.setPadding(new Insets(5));
            cancelBtn.setText("Cancel");
            cancelBtn.setOnAction(event -> stage.close());

            Separator vSpace = new Separator(Orientation.VERTICAL);
            vSpace.setVisible(false);

            Button applyBtn = new Button();
            applyBtn.setPadding(new Insets(5));
            applyBtn.setText("Apply");
            applyBtn.setOnAction(
                    event -> {
                        stage.setAlwaysOnTop(false);
                        Popup popup = new Popup();
                        popup.setAutoFix(true);
                        popup.setAutoHide(true);

                        Label label = new Label("Applied changes");
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
                            delay.setOnFinished(e -> popup.hide());

                            popup.show(stage);
                            delay.play();
                        }
                    });

            hbBottom.getChildren().addAll(cancelBtn, vSpace, applyBtn);
        }

        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
