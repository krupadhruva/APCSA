import gpdraw.DrawingTool;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Class to draw an illusion of parallel lines appearing as converging lines
 *
 * <p>Using contrasting colors will help highlight the effects of illusion
 *
 * <p>Usage:
 *
 * <pre>
 *     P7_Dhruva_Krupa_Illusion illusion = new P7_Dhruva_Krupa_Illusion(pen, 50, 50,
 *                                                                      Color.BLACK, Color.WHITE);
 *     illusion.draw();
 * </pre>
 */
public class P7_Dhruva_Krupa_Illusion {
    private final DrawingTool pen;
    private double rectWidth;
    private double rectHeight;
    private Color background;
    private Color foreground;

    /**
     * Sets the width of the individual rectangle
     *
     * @param rectWidth Width of the individual rectangle
     */
    public void setRectWidth(double rectWidth) {
        this.rectWidth = rectWidth;
    }

    /**
     * Sets the height of the individual rectangle
     *
     * <p>Note: The actual height of rectangle drawn will be the adjusted height
     *
     * @see P7_Dhruva_Krupa_Illusion#getAdjustedSquareHeight(DrawingTool, double)
     * @param rectHeight Raw height of the individual rectangle
     */
    public void setRectHeight(double rectHeight) {
        this.rectHeight = rectHeight;
    }

    /**
     * Sets the color of the dark rectangle
     *
     * @param background Color of the darker rectangle
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * Sets the color of the lighter rectangle
     *
     * @param foreground Color of the lighter rectangle
     */
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    /**
     * Constructs a fully function illusion class instance with user provided details
     *
     * @param pen Drawing tool for drawing the illusion
     * @param rectWidth Width of the individual rectangle
     * @param rectHeight Height of the individual rectangle
     * @param background Color of the darker rectangle
     * @param foreground Width of the lighter rectangle
     */
    public P7_Dhruva_Krupa_Illusion(
            DrawingTool pen,
            double rectWidth,
            double rectHeight,
            Color background,
            Color foreground) {
        this.pen = pen;

        setRectWidth(rectWidth);
        setRectHeight(rectHeight);
        setBackground(background);
        setForeground(foreground);
    }

    /**
     * Simple constructor with default settings
     *
     * <p>rectWidth = 50.0, rectHeight = 50.0, background = Color.BLACK and foreground = Color.WHITE
     *
     * @param pen Instance of a DrawingTool
     */
    public P7_Dhruva_Krupa_Illusion(DrawingTool pen) {
        this(pen, 50, 50, Color.BLACK, Color.WHITE);
    }

    /**
     * Get the lower left corner of the drawing area.
     *
     * <p>Used for positioning the pen for drawing
     *
     * @param pen Instance of DrawingTool
     * @return Dimension with width se to X co-ordinate and height set to Y co-ordinate
     */
    private Dimension getLowerLeftCornerOffset(DrawingTool pen) {
        Dimension drawingSize = getDrawingDimension(pen);
        drawingSize.setSize(-drawingSize.getWidth() / 2, -drawingSize.getHeight() / 2);
        return drawingSize;
    }

    /**
     * Get the available drawing area
     *
     * @param pen Instance of DrawingTool
     * @return Dimension of the drawing area
     */
    private Dimension getDrawingDimension(DrawingTool pen) {
        return pen.getPadPanel().getSize();
    }

    /**
     * Adjusts the height of rectangle to ensure there is no row with rectangles of partial height
     *
     * @param pen Instance of DrawingTool
     * @param height Raw height of rectangle
     * @return Adjusted height of rectangle
     */
    private double getAdjustedSquareHeight(DrawingTool pen, double height) {
        Dimension dim = getDrawingDimension(pen);
        int rows = (int) (dim.getHeight() / height);

        return height + (dim.getHeight() % height) / rows;
    }

    /** Draws the illusion using the data stored in attributes */
    public void draw() {
        Dimension drawingSize = getDrawingDimension(pen);
        Dimension lowerLeftCorner = getLowerLeftCornerOffset(pen);

        double leftX = lowerLeftCorner.getWidth();
        double leftY = lowerLeftCorner.getHeight();
        double adjustedSqHeight = getAdjustedSquareHeight(pen, rectHeight);

        // Draw the large background to avoid having to draw individually
        pen.up();
        pen.home();
        pen.setColor(background);
        pen.down();
        pen.fillRect(drawingSize.getWidth(), drawingSize.getHeight());

        // Iterate over the rows
        for (int row = 0; row < drawingSize.getHeight() / adjustedSqHeight; ++row) {
            double rectX = 0.0;
            double rectY = leftY + (adjustedSqHeight * row + (adjustedSqHeight / 2));

            pen.up();
            pen.setColor(foreground.darker());
            pen.move(rectX, rectY);
            pen.down();
            pen.drawRect(drawingSize.getWidth(), adjustedSqHeight);

            // Offset for the starting rectangle in each row
            double initialOffset = (row % 3) * rectWidth / 4;

            // Draw the individual rectangles in a row
            for (int count = 0; count < drawingSize.width / rectWidth; ++count) {
                rectX = leftX + initialOffset + (rectWidth / 2) + (count * 2 * rectWidth);

                pen.up();
                pen.move(rectX, rectY);
                pen.down();
                pen.setColor(foreground);
                pen.fillRect(rectWidth, adjustedSqHeight);
            }
        }
    }
}
