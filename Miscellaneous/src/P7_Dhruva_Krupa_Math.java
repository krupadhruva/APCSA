/*
 *  This class represents a rectangle with (x, y) in the lower left corner
 *  and a width/height specified by the client in the constructor.  It can
 *  calculate the area and perimeter as well as draw a graphical representation
 *  of the rectangle.
 */

import gpdraw.*;

public class P7_Dhruva_Krupa_Math {

    private double myX; // lower left x-coord in pixels
    private double myY; // lower left y-coord in pixels
    private double myWidth; // width of rectangle in pixels
    private double myHeight; // height of rectangle in pixels
    private SketchPad myPad;
    private DrawingTool pen;

    // (x, y) is the lower left corner of the rectangle
    public P7_Dhruva_Krupa_Math(double x, double y, double width, double height, SketchPad pad) {
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;
        myPad = pad;
        pen = new DrawingTool(pad);
    }

    // returns the perimeter of the rectangle
    public double calcPerimeter() {
        return (2 * myWidth) + (2 * myHeight);
    }

    // returns the area of the rectangle
    public double calcArea() {
        return myWidth * myHeight;
    }

    // draws the rectangle in the SketchPad window
    public void draw() {
        pen.up();
        pen.move(myX, myY);
        pen.down();
        pen.setDirection(0);
        pen.forward(myWidth);
        pen.setDirection(90);
        pen.forward(myHeight);
        pen.setDirection(180);
        pen.forward(myWidth);
        pen.setDirection(270);
        pen.forward(myHeight);
    }

    // returns the width of the rectangle
    public double getWidth() {
        return myWidth;
    }

    // returns the height of the rectangle
    public double getHeight() {
        return myHeight;
    }
}
