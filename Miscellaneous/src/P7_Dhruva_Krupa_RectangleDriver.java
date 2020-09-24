import gpdraw.SketchPad;

public class P7_Dhruva_Krupa_RectangleDriver {

    private static String rectangleToString(Rectangle rectangle) {
        return String.format(
                "Height=%.2f, Width=%.2f, Perimeter=%.2f, Area=%.2f",
                rectangle.getHeight(),
                rectangle.getWidth(),
                rectangle.calcPerimeter(),
                rectangle.calcArea());
    }

    public static void main(String[] args) {
        SketchPad pad = new SketchPad(800, 600);

        Rectangle rect1 = new Rectangle(0, 0, 240, 240, pad);
        Rectangle rect2 = new Rectangle(80, 0, 80, 240, pad);
        Rectangle rect3 = new Rectangle(0, 80, 240, 80, pad);

        System.out.println(P7_Dhruva_Krupa_RectangleDriver.rectangleToString(rect1));
        System.out.println(P7_Dhruva_Krupa_RectangleDriver.rectangleToString(rect2));
        System.out.println(P7_Dhruva_Krupa_RectangleDriver.rectangleToString(rect3));

        rect1.draw();
        rect2.draw();
        rect3.draw();
    }
}
