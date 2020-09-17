import gpdraw.DrawingTool;
import gpdraw.SketchPad;

import java.awt.Color;
import java.awt.Point;

/*
 * Name: Krupa Dhruva
 * Date: September 11, 2020
 * Period: 7
 * Time Taken: About 4 hours
 *
 * Lab Reflection:
 * I learned one way to avoid code duplication by reusing constructors.
 * Overall, I enjoyed exploring the different ways to draw a sphere but the most challenging
 * part that I did not figure out yet is how to evenly shade the filled in sphere if the
 * light source is not directly hitting the center of the sphere. I am really proud of the wired
 * sphere where I used multiple ovals to draw the wired frames. I also proud of using multiple circles
 * with decreasing radius to show how the gradients are smoothly transitioning.
 */

/**
 * Class implementing sphere appearing like wireframe or solid.
 *
 * <p>Wireframe is generated by drawing intersecting ovals along X and Y axis. Ovals give an
 * appearance of rotated circle along Z axis.
 *
 * <p>Solid appearing sphere is rendered by drawing reducing sized filled circles with color
 * transitioning from actual sphere color to white. This simulates a light shining on a sphere.
 */
public class P7_Dhruva_Krupa_Sphere {
    /** Pen to draw the shape */
    private final DrawingTool pen;
    /** Origin of the sphere */
    private final Point origin;
    /** View point */
    private final Point viewPoint;
    /** Sphere radius */
    private final int radius;
    /** Sphere color */
    private final Color color;
    /** Number of wire frames to draw */
    private final int numberOfFrames;

    /**
     * Constructs a solid sphere using shading
     *
     * @param pen Instance of DrawingTool
     * @param origin Sphere origin/location
     * @param viewPoint Point of view or focus of light source
     * @param radius Radius of sphere
     * @param color Color of sphere
     */
    public P7_Dhruva_Krupa_Sphere(
            DrawingTool pen, Point origin, Point viewPoint, int radius, Color color) {
        this(pen, origin, radius, color, 0);

        // Ensure view point in within the sphere
        if (origin.distance(viewPoint) <= (double) radius) {
            this.viewPoint.setLocation(viewPoint);
        }
    }

    /**
     * Constructs a sphere with wire frames
     *
     * @param pen Instance of DrawingTool
     * @param origin Sphere origin/location
     * @param radius Radius of sphere
     * @param color Color of sphere
     * @param numberOfFrames Number of wire frames. Can be 0 for solid rendering
     */
    public P7_Dhruva_Krupa_Sphere(
            DrawingTool pen, Point origin, int radius, Color color, int numberOfFrames) {
        this.pen = pen;
        this.origin = origin;
        this.viewPoint = origin.getLocation();
        this.radius = radius;
        this.color = color;
        this.numberOfFrames = numberOfFrames;
    }

    /**
     * Simple driver to test the sphere
     *
     * @param args Array of strings - command line arguments
     */
    public static void main(String[] args) {
        SketchPad pad = new SketchPad(800, 600, 5L);
        DrawingTool pen = new DrawingTool(pad);
        Color color = Color.BLACK;

        P7_Dhruva_Krupa_Sphere wire =
                new P7_Dhruva_Krupa_Sphere(pen, new Point(-200, 0), 100, color, 30);
        wire.draw();

        Point origin = new Point(200, 0);
        Point viewPoint = new Point(150, 50);
        P7_Dhruva_Krupa_Sphere solid =
                new P7_Dhruva_Krupa_Sphere(pen, origin, viewPoint, 100, color);
        solid.draw();
    }

    /**
     * Implements 2 versions of drawing a sphere based on constructor used to instantiate an object
     *
     * <p>Wireframe: Draws a wireframe model of a sphere built with perpendicular intersecting ovals
     * of varying minor/major axis
     *
     * <p>Solid: Draws a shaded sphere drawn by filling gradually reducing circles with color
     * changing from actual color at the periphery to white at the center to simulate a focused
     * light source
     */
    public void draw() {
        pen.up();
        pen.move(origin.getX(), origin.getY());
        pen.down();

        if (numberOfFrames > 0) {
            pen.setColor(color);
            int majorAxis = 2 * radius;
            for (int count = 0; count < numberOfFrames; ++count) {
                int minorAxis = majorAxis - (count * majorAxis / numberOfFrames);
                pen.drawOval(majorAxis, minorAxis);
                pen.drawOval(minorAxis, majorAxis);
            }
        } else {
            // Determine RGBA so that we incrementally transition from that color
            // starting outside to white inside
            float[] rgba = color.getRGBComponents(null);

            // Draw circles from darker outer to lighter inside
            int chunks = Math.max((int) (radius), 200);
            double viewOffset = origin.distance(viewPoint);

            double currentRadius = radius;
            double xDelta = (viewPoint.getX() - origin.getX()) / chunks;
            double yDelta = (viewPoint.getY() - origin.getY()) / chunks;
            for (int count = 0; count < chunks; ++count) {
                pen.setColor(
                        new Color(
                                rgba[0] + (1.0f - rgba[0]) * count / chunks,
                                rgba[1] + (1.0f - rgba[1]) * count / chunks,
                                rgba[2] + (1.0f - rgba[2]) * count / chunks,
                                rgba[3]));
                pen.fillCircle(currentRadius);
                currentRadius -= ((double) radius / chunks);

                // Move origin towards view point at same rate as reducing radius
                if (viewOffset > 0.0) {
                    pen.up();
                    pen.move(pen.getXPos() + xDelta, pen.getYPos() + yDelta);
                    pen.down();
                }
            }
        }
    }
}