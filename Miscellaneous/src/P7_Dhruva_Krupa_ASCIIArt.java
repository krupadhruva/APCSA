/*
 * Name: Krupa Dhruva
 * Date: May 29, 2021
 * Period: 7
 * Time Taken: 120 minutes
 *
 * Lab Reflection:
 * I was surprised there was no built in method to convert color to a single
 * RGB value. Breaking the image to tiles and finding average values was a
 * good tip to prevent a dominant part of an image to affect the whole image.
 *
 * Learned an approach to retain the color distribution in an image when
 * generating ASCII art image based on relative distances.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P7_Dhruva_Krupa_ASCIIArt extends Application {
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

    private int colorToRGB(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return ((r << 16) + (g << 8) + b);
    }

    /**
     * Returns ASCII representation for a given image by dividing image to tiles based on given
     * width and height of tile
     *
     * <p>Performs image normalizing by finding distance of each pixel sorted list of unique pixels
     * and finding ASCII representation at same relative distance. This helps maintain relative
     * color distribution from original image to ASCII representation
     *
     * @param image Image to convert to ASCII
     * @param asciiWidth Width of tile for getting average grey code
     * @param asciiHeight Height of tile for getting average grey code
     * @return String representation of image
     */
    public String toASCII(final Image image, int asciiWidth, int asciiHeight) {
        final char[] asciiRep = {'@', '#', '8', '&', 'o', ':', '*', '.', ' '};

        int width = (int) Math.round(image.getWidth());
        int height = (int) Math.round(image.getHeight());
        final PixelReader reader = image.getPixelReader();
        final int[][] asciiGrid =
                new int[(int) Math.round((double) height / asciiHeight)]
                        [(int) Math.round((double) width / asciiWidth)];

        // Required to normalize image by finding relative distance among pixels and ASCII map
        final Set<Integer> unique = new HashSet<>();

        // Traverse pixels in image by tile boundary
        for (int y = 0; y < height; y += asciiHeight) {
            for (int x = 0; x < width; x += asciiWidth) {
                // Find average pixel value of a given tile
                int avgRGB = 0;
                for (int yy = y; yy < y + asciiHeight; ++yy) {
                    for (int xx = x; xx < x + asciiWidth; ++xx) {
                        avgRGB += colorToRGB(reader.getColor(x, y).grayscale());
                    }
                }

                // Compute average pixel value for the tile
                avgRGB /= (asciiWidth * asciiHeight);

                // Since we round off, the array boundary might not be exact
                try {
                    asciiGrid[y / asciiHeight][x / asciiWidth] = avgRGB;
                    unique.add(avgRGB);
                } catch (IndexOutOfBoundsException ignored) {
                    break;
                }
            }
        }

        // Sort pixels in ascending order to find distance of color in range
        // Once we have distance of color in range, we find the ASCII value at similar
        // distance from ASCII char range
        final List<Integer> pixels = new ArrayList<>(unique);
        pixels.sort(Comparator.naturalOrder());

        final StringBuilder sb = new StringBuilder((width + 1) * height);

        // Fill output string with ASCII chars that have the same relative distance
        // as average pixel value has among other average pixels in the image
        char[] tile = new char[width];
        for (int[] row : asciiGrid) {
            int offset = 0;
            for (int val : row) {
                float idx = pixels.indexOf(val);
                char ch = asciiRep[Math.round(idx * (asciiRep.length - 1) / (pixels.size() - 1))];

                Arrays.fill(tile, offset, offset + asciiWidth, ch);
                offset += asciiWidth;
            }

            for (int r = 0; r < asciiHeight; ++r) {
                sb.append(tile);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    private void loadImageFile(ImageView imageView, Image pickedImage) {
        if (pickedImage == null) {
            FileChooser imagePicker = new FileChooser();
            imagePicker.setTitle("Select image");
            FileChooser.ExtensionFilter filter =
                    new FileChooser.ExtensionFilter(
                            "Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
            imagePicker.getExtensionFilters().add(filter);

            File file = imagePicker.showOpenDialog(imageView.getScene().getWindow());
            try {
                if (file != null) {
                    pickedImage = new Image(file.toURI().toURL().toString());
                }
            } catch (MalformedURLException ex) {
                System.err.println("Image loading error: " + ex);
                return;
            }
        }

        if (pickedImage == null || pickedImage.isError()) {
            return;
        }

        imageView.setImage(pickedImage);
        imageView.setFitWidth(pickedImage.getWidth());
        imageView.setFitHeight(pickedImage.getHeight());
    }

    @Override
    public void start(Stage rootStage) throws Exception {
        // Create a new stage and set it to modal - we want focus
        Stage stage = new Stage();
        stage.initOwner(rootStage);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.WINDOW_MODAL);

        Image image = new Image(IMAGE_URL);
        ImageView imageView = new ImageView();
        if (!image.isError()) {
            loadImageFile(imageView, image);
        }

        // Example menu bar with support to pick image and exit app
        MenuBar menuBar = new MenuBar();

        Menu optionsMenu = new Menu("Editor");
        MenuItem selectItm = new MenuItem("Image path");
        selectItm.setOnAction(event -> loadImageFile(imageView, null));

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
        gridPane.setAlignment(Pos.BOTTOM_LEFT);

        // Top most pane containing rest of the layouts
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5)); // space between elements and window border
        root.setTop(menuBar);
        root.setCenter(vbCenter);
        root.setBottom(hbBottom);

        // Create scene since we have the image to set the size of scene
        Scene scene = new Scene(root, imageView.getFitWidth(), imageView.getFitHeight() + 150);
        stage.setTitle("Image to ASCII");
        stage.setScene(scene);
        stage.sizeToScene();

        // Load image for editing
        // Using a scroll pane allows us to pan the image
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(imageView);
        vbCenter.getChildren().add(scrollPane);

        Separator hSpace = new Separator();
        hSpace.setPadding(new Insets(5));

        HBox hb1 = new HBox();
        hb1.setAlignment(Pos.CENTER_LEFT);
        Text ttw = new Text("Tile width  ");
        Spinner<Integer> tileWidth = new Spinner<>(1, 100, 1);
        hb1.getChildren().addAll(ttw, tileWidth);

        Separator hSpace1 = new Separator();
        hSpace1.setPadding(new Insets(5));

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER_LEFT);
        Text tth = new Text("Tile height ");
        Spinner<Integer> tileHeight = new Spinner<>(1, 100, 1);
        hb2.getChildren().addAll(tth, tileHeight);

        Separator hSpace2 = new Separator();
        hSpace2.setPadding(new Insets(5));

        vbCenter.getChildren().addAll(hSpace, gridPane, hSpace1, hb1, hSpace2, hb2);

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

            applyBtn.setText("Convert");
            applyBtn.setOnAction(
                    event -> {
                        // TODO: Tile size should be customizable
                        final String ascii =
                                toASCII(
                                        imageView.getImage(),
                                        tileWidth.getValue(),
                                        tileHeight.getValue());
                        final Image asciiImg =
                                new Text(ascii).snapshot(new SnapshotParameters(), null);
                        imageView.setImage(asciiImg);
                    });

            hbBottom.getChildren().addAll(cancelBtn, vSpace, applyBtn);
        }

        stage.show();
    }
}