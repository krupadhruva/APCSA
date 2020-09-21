import gpdraw.SketchPad;

public class P7_Dhruva_Krupa_MathDriver {
    public static void main(String[] args) {
        SketchPad pad = new SketchPad(800, 600);

        P7_Dhruva_Krupa_Math rect1 = new P7_Dhruva_Krupa_Math(0, 0, 240, 240, pad);
        P7_Dhruva_Krupa_Math rect2 = new P7_Dhruva_Krupa_Math(80, 0, 80, 240, pad);
        P7_Dhruva_Krupa_Math rect3 = new P7_Dhruva_Krupa_Math(0, 80, 240, 80, pad);

        rect1.draw();
        rect2.draw();
        rect3.draw();
    }
}
