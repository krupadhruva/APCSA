import java.awt.Point;

public class P7_Dhruva_Krupa_MathDriver {
    /**
     * Driver to test the various static utility methods
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Temperature Converter:");
        double fromTemp = 212;
        double toTemp = P7_Dhruva_Krupa_Math.fToC(fromTemp);
        System.out.println(P7_Dhruva_Krupa_Math.temperatureToString(fromTemp, toTemp, true));

        fromTemp = 98.6;
        toTemp = P7_Dhruva_Krupa_Math.fToC(fromTemp);
        System.out.println(P7_Dhruva_Krupa_Math.temperatureToString(fromTemp, toTemp, true));

        fromTemp = 37;
        toTemp = P7_Dhruva_Krupa_Math.cToF(fromTemp);
        System.out.println(P7_Dhruva_Krupa_Math.temperatureToString(fromTemp, toTemp, false));

        fromTemp = -15;
        toTemp = P7_Dhruva_Krupa_Math.cToF(fromTemp);
        System.out.println(P7_Dhruva_Krupa_Math.temperatureToString(fromTemp, toTemp, false));

        // Volume of sphere
        System.out.println();

        double radius = 1.0;
        double volume = P7_Dhruva_Krupa_Math.sphereVolume(radius);
        System.out.println(P7_Dhruva_Krupa_Math.sphereVolumeToString(radius, volume));

        radius = 6.8;
        volume = P7_Dhruva_Krupa_Math.sphereVolume(radius);
        System.out.println(P7_Dhruva_Krupa_Math.sphereVolumeToString(radius, volume));

        // Hypotenuse of right angle triangle
        System.out.println();

        double sideA = 3;
        double sideB = 4;
        double hypotenous = P7_Dhruva_Krupa_Math.hypotenuse(sideA, sideB);
        System.out.println(P7_Dhruva_Krupa_Math.hypotenousToString(sideA, sideB, hypotenous));

        sideA = 2.5;
        sideB = 9.25;
        hypotenous = P7_Dhruva_Krupa_Math.hypotenuse(sideA, sideB);
        System.out.println(P7_Dhruva_Krupa_Math.hypotenousToString(sideA, sideB, hypotenous));

        // Atomic mass
        System.out.println();

        double amu = 55.85;
        double mass = 0.75;
        String element = "Fe";
        double atoms = P7_Dhruva_Krupa_Math.gramsToAtoms(element, amu, mass);
        System.out.println(P7_Dhruva_Krupa_Math.gramsToAtomsToString(element, atoms, mass));

        amu = 196.9665;
        mass = 5.24;
        element = "Au";
        atoms = P7_Dhruva_Krupa_Math.gramsToAtoms(element, amu, mass);
        System.out.println(P7_Dhruva_Krupa_Math.gramsToAtomsToString(element, atoms, mass));

        amu = 20.1797;
        mass = 2.0;
        element = "Ne";
        atoms = P7_Dhruva_Krupa_Math.gramsToAtoms(element, amu, mass);
        System.out.println(P7_Dhruva_Krupa_Math.gramsToAtomsToString(element, atoms, mass));

        // Bounded random numbers
        System.out.println();

        for (int count = 0; count < 6; ++count) {
            System.out.printf(
                    "A random number between 7 and 9 is: %d\n",
                    P7_Dhruva_Krupa_Math.boundedRandom(7, 9));
        }

        // Perimeter of triangle
        System.out.println();

        Point ptA = new Point(1, 2);
        Point ptB = new Point(3, 4);
        Point ptC = new Point(5, 1);
        double perimeter = P7_Dhruva_Krupa_Math.perimeter(ptA, ptB, ptC);
        System.out.println(P7_Dhruva_Krupa_Math.perimeterToString(ptA, ptB, ptC, perimeter));
    }
}
