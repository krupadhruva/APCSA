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
 *
 * Details:
 * To compile:
 * javac --module-path ~/installs/javafx-sdk-16/lib --add-modules javafx.controls,javafx.media,javafx.swing P7_Dhruva_Krupa_ASCIIArt.java
 *
 * To run:
 * java --module-path ~/installs/javafx-sdk-16/lib --add-modules javafx.controls,javafx.media,javafx.swing P7_Dhruva_Krupa_ASCIIArt --source=eagle.jpg
 *
 * Converting 'eagle.jpg' to ASCII image 'eagle.jpg.out.png'
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class P7_Dhruva_Krupa_ASCIIArt extends Application {
    private static final char[] ASCII_MAP = {'@', '#', '8', '&', 'o', ':', '*', '.', ' '};
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
    public String imageToString(final Image image, int asciiWidth, int asciiHeight) {
        int width = (int) Math.round(image.getWidth());
        int height = (int) Math.round(image.getHeight());
        final PixelReader reader = image.getPixelReader();

        // TODO: Can we eliminate the need for this grid by processing each tile
        // and translating it to output string immediately
        final int[][] asciiGrid = new int[height + 1][width + 1];

        // Required to normalize image by finding relative distance among pixels and ASCII map
        final Set<Integer> unique = new HashSet<>();

        // Traverse pixels in image by tile boundary
        for (int y = 0; y < height; y += asciiHeight) {
            for (int x = 0; x < width; x += asciiWidth) {
                // Find average pixel value of a given tile
                int avgRGB = 0;
                for (int yy = 0; yy < asciiHeight && y + yy < height; ++yy) {
                    for (int xx = 0; xx < asciiWidth && x + xx < width; ++xx) {
                        avgRGB += colorToRGB(reader.getColor(x + xx, y + yy).grayscale());
                    }
                }

                // Compute average pixel value for the tile
                avgRGB /= (asciiWidth * asciiHeight);
                unique.add(avgRGB);

                for (int yy = 0; yy < asciiHeight && y + yy < height; ++yy) {
                    for (int xx = 0; xx < asciiWidth && x + xx < width; ++xx) {
                        asciiGrid[y + yy][x + xx] = avgRGB;
                    }
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
        for (int ii = 0; ii < height; ++ii) {
            for (int jj = 0; jj < width; ++jj) {
                // Find the distance of averaged gray value in sorted list
                double idx = pixels.indexOf(asciiGrid[ii][jj]);

                // Find ASCII equivalent at the same relative distance in ASCII char map
                int rel = (int) Math.round(idx * (ASCII_MAP.length - 1) / (pixels.size() - 1));
                sb.append(ASCII_MAP[rel]);
            }

            sb.append('\n');
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

    // Source: https://yo-dave.com/2015/07/27/finding-mono-spaced-fonts-in-javafx/
    private ObservableList<String> getMonoFontFamilyNames() {
        // Compare the layout widths of two strings. One string is composed
        // of "thin" characters, the other of "wide" characters. In mono-spaced
        // fonts the widths should be the same.

        final char[] dots = new char[ASCII_MAP.length];
        Arrays.fill(dots, 0, ASCII_MAP.length / 2, '.');
        Arrays.fill(dots, ASCII_MAP.length / 2, ASCII_MAP.length, ' ');

        final Text thinTxt = new Text(new String(dots)); // note the space
        final Text thikTxt = new Text(new String(ASCII_MAP));

        List<String> fontFamilyList = Font.getFamilies();
        List<String> monoFamilyList = new ArrayList<>();

        Font font;

        for (String fontFamilyName : fontFamilyList) {
            font = Font.font(fontFamilyName, FontWeight.NORMAL, FontPosture.REGULAR, 14.0d);
            thinTxt.setFont(font);
            thikTxt.setFont(font);
            if (thinTxt.getLayoutBounds().getWidth() == thikTxt.getLayoutBounds().getWidth()) {
                monoFamilyList.add(fontFamilyName);
            }
        }

        return FXCollections.observableArrayList(monoFamilyList);
    }

    private void runCLI(Map<String, String> args, Font defaultFont) {
        final String imageFormat = "png";

        String src = args.get("source");
        Image source = new Image(new File(src).toURI().toString());

        int tileWidth = Integer.parseInt(args.getOrDefault("tile-width", "1"));
        int tileHeight = Integer.parseInt(args.getOrDefault("tile-height", "1"));
        Font font = Font.font(args.getOrDefault("font", defaultFont.getFamily()));
        boolean resize = Boolean.parseBoolean(args.getOrDefault("resize", "false"));
        File target = new File(args.getOrDefault("target", src + ".out." + imageFormat));

        System.out.printf("Converting '%s' to ASCII image '%s'%n", src, target.getName());

        final String ascii = imageToString(source, tileWidth, tileHeight);
        final Text txtField = new Text(ascii);
        txtField.setTextAlignment(TextAlignment.CENTER);
        txtField.setFont(font);
        Image asciiImage = txtField.snapshot(null, null);

        // Resize image to match the source image
        if (resize) {
            SnapshotParameters params = new SnapshotParameters();
            params.setTransform(
                    Transform.scale(
                            source.getWidth() / asciiImage.getWidth(),
                            source.getHeight() / asciiImage.getHeight()));
            asciiImage = txtField.snapshot(params, null);
        }

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(asciiImage, null), imageFormat, target);
        } catch (IOException ex) {
            System.err.println("failed to convert image with exception: " + ex);
        }
    }

    @Override
    public void start(Stage rootStage) throws Exception {
        final ObservableList<String> monoFonts = getMonoFontFamilyNames();
        final Font defaultFont = Font.font(monoFonts.get(0));

        Map<String, String> args = getParameters().getNamed();
        if (!args.isEmpty()) {
            runCLI(args, defaultFont);
            System.exit(0);
        }

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
        ttw.setFont(defaultFont);
        Spinner<Integer> tileWidth = new Spinner<>(1, 100, 1);
        hb1.getChildren().addAll(ttw, tileWidth);

        Separator hSpace1 = new Separator();
        hSpace1.setPadding(new Insets(5));

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER_LEFT);
        Text tth = new Text("Tile height ");
        tth.setFont(defaultFont);
        Spinner<Integer> tileHeight = new Spinner<>(1, 100, 1);
        hb2.getChildren().addAll(tth, tileHeight);

        Separator hSpace2 = new Separator();
        hSpace2.setPadding(new Insets(5));

        HBox hb3 = new HBox();
        hb3.setAlignment(Pos.CENTER_LEFT);
        Text ttf = new Text("ASCII font  ");
        ttf.setFont(defaultFont);
        ComboBox<String> asciiFonts = new ComboBox<>(monoFonts);
        asciiFonts.setValue(defaultFont.getFamily());
        hb3.getChildren().addAll(ttf, asciiFonts);

        Separator hSpace3 = new Separator();
        hSpace3.setPadding(new Insets(5));

        vbCenter.getChildren().addAll(hSpace, gridPane, hSpace1, hb1, hSpace2, hb2, hSpace3, hb3);

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
                        final String ascii =
                                imageToString(
                                        // Use snapshot to support converting multiple times
                                        imageView.snapshot(null, null),
                                        tileWidth.getValue(),
                                        tileHeight.getValue());

                        final Text txtField = new Text(ascii);
                        txtField.setTextAlignment(TextAlignment.CENTER);
                        txtField.setFont(Font.font(asciiFonts.getValue()));

                        imageView.setImage(txtField.snapshot(null, null));
                    });

            hbBottom.getChildren().addAll(cancelBtn, vSpace, applyBtn);
        }

        stage.show();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help")) {
                String usage =
                        "Convert image to ASCII image with smoothening using tile and following"
                                + " options\n\n"
                                + "  --source\tPath to image that needs to be converted\n"
                                + "  --target\tOptional path to generated ASCII image. Defaults to"
                                + " source image name appended with '.out.png'.\n"
                                + "  --tile-width\tOptional width of tile to average RGB value in"
                                + " source image. Defaults to 1.\n"
                                + "  --tile-height\tOptional height of tile to average RGB value in"
                                + " source image. Defaults to 1.\n"
                                + "  --font\tOptional name of font family to use. Defaults to"
                                + " first available monospace font.\n"
                                + "  --resize\tOptional boolean indicating if output ASCII image"
                                + " size should be resized to source image size. Defaults to"
                                + " false.\n\n"
                                + "example: "
                                + new Object() {}.getClass().getEnclosingClass().getSimpleName()
                                + " --source=/path/to/source.jpeg --target=/path/to/output.png"
                                + " --tile-width=3 --tile-height=3 --font=Monaco"
                                + " --resize=true\n\n";
                System.out.print(usage);
                System.exit(0);
            }
        }

        launch(args);
    }
}
