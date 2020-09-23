import gpdraw.SketchPad;

public class P7_Dhruva_Krupa_RectangleDriver {
    public static void main(String[] args) {
        SketchPad pad = new SketchPad(800, 600);

        Rectangle rect1 = new Rectangle(0, 0, 240, 240, pad);
        Rectangle rect2 = new Rectangle(80, 0, 80, 240, pad);
        Rectangle rect3 = new Rectangle(0, 80, 240, 80, pad);

        System.out.println(rect1);
        System.out.println(rect2);
        System.out.println(rect3);

        rect1.draw();
        rect2.draw();
        rect3.draw();
    }
}
