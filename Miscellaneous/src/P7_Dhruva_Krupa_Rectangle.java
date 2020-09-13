import gpdraw.DrawingTool;
import gpdraw.SketchPad;

/*
 * Name: Krupa Dhruva
 * Date: September 13, 2020
 * Period: 7
 * Time Taken: 25 minutes
 *
 * Lab Reflection:
 * I learned that initializing static variables
 * in the constructor leads to over writing since everytime I create
 * a new rectangle object, the SketchPad and DrawingTool gets over
 * written, hence, I initialized it at class level.
 * I also learned that when I created 2 instances of the rectangle (with static
 * variables initialized in the constructor) and then invoking draw() on them,
 * I could see both rectangles. That was because the draw method was using the
 * same overwritten values int the static variables (SketchPad and DrawingTool).
 */

public class P7_Dhruva_Krupa_Rectangle {
    private double myX;
    private double myY;
    private double myWidth;
    private double myHeight;

    private static SketchPad paper = new SketchPad(500, 500, 5L);
    private static DrawingTool pen = new DrawingTool(paper);

    P7_Dhruva_Krupa_Rectangle(double x, double y, double width, double height) {
        this.myX = x;
        this.myY = y;
        this.myWidth = width;
        this.myHeight = height;
    }

    P7_Dhruva_Krupa_Rectangle() {
        this(0, 0, 0, 0);
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_Rectangle rectA = new P7_Dhruva_Krupa_Rectangle();
        rectA.draw();

        P7_Dhruva_Krupa_Rectangle rectB = new P7_Dhruva_Krupa_Rectangle(0, -80, 400, 160);
        rectB.draw();

        P7_Dhruva_Krupa_Rectangle rectC = new P7_Dhruva_Krupa_Rectangle(100, -100, 20, 300);
        rectC.draw();
    }

    public double getPerimeter() {
        return 2 * (myWidth + myHeight);
    }

    public double getArea() {
        return (myWidth * myHeight);
    }

    public void draw() {
        pen.up();
        pen.move(myX + (myWidth / 2), myY + (myHeight / 2));
        pen.down();

        pen.drawRect(myWidth, myHeight);
        pen.up();
    }
}
