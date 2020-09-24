/*
 * Name: Krupa Dhruva
 * Date: September 23, 2020
 * Period: 7
 */

import java.awt.Point;

/**
 * Class containing math utility static methods
 *
 * <p>Covers a variety of math computations using constants, Math library functions and custom
 * formatting of output values.
 *
 * <p>Note: Took help from Stack overflow for implementing trimming trailing redundant zeros from
 * float/double numbers
 */
public class P7_Dhruva_Krupa_Math {
    /** Unicode representation of degree symbol */
    private static final char DEGREE_SYMBOL = '\u00B0';

    /** Avagadro's number - a constant required for calculating atoms in a mass of element */
    private static final double AVAGADRO_NUMBER = 6.02214076E23;

    /**
     * Converts temperature in Fahrenheit to Centigrade
     *
     * @param temperature Temperature in Fahrenheit
     * @return Temperature in Centigrade
     */
    public static double fToC(double temperature) {
        return (5.0 * (temperature - 32.0)) / 9.0;
    }

    /**
     * Converts temperature in Centigrade to Fahrenheit
     *
     * @param temperature Temperature in Centigrade
     * @return Temperature in Fahrenheit
     */
    public static double cToF(double temperature) {
        return ((9.0 * temperature) / 5.0) + 32.0;
    }

    /**
     * Helper method to strip trailing 0s from string representation of a double
     *
     * @param number Input value
     * @return Trimmed result
     */
    private static String prettyNumber(double number) {
        String strNumber;

        // From: https://stackoverflow.com/a/14126736
        // Check if the input temperature is a long or double to avoid printing redundant
        // decimal with a single 0
        if (number == (long) number) {
            strNumber = String.format("%d", (long) number);
        } else {
            strNumber = String.format("%.20f", number);

            // Trim trailing 0s
            // From: https://stackoverflow.com/a/25691614
            strNumber = strNumber.replaceAll("0+$", "");
        }

        return strNumber;
    }

    /**
     * Helper to print the temperature conversion results
     *
     * <p>Uses a constant to store unicode representation of degree
     *
     * @see P7_Dhruva_Krupa_Math#DEGREE_SYMBOL
     * @param fromTemp Input temperature
     * @param toTemp Converted temperature
     * @param fromF True if conversion from Fahrenheit to Centigrade
     * @return Formatted string representation of conversion
     */
    public static String temperatureToString(double fromTemp, double toTemp, boolean fromF) {
        String fromTempStr = P7_Dhruva_Krupa_Math.prettyNumber(fromTemp);

        if (fromF) {
            return String.format(
                    "%s%cF --> %.1f%cC",
                    fromTempStr,
                    P7_Dhruva_Krupa_Math.DEGREE_SYMBOL,
                    toTemp,
                    P7_Dhruva_Krupa_Math.DEGREE_SYMBOL);
        } else {
            return String.format(
                    "%s%cC --> %.1f%cF",
                    fromTempStr,
                    P7_Dhruva_Krupa_Math.DEGREE_SYMBOL,
                    toTemp,
                    P7_Dhruva_Krupa_Math.DEGREE_SYMBOL);
        }
    }

    /**
     * Calculate volume of a sphere
     *
     * @param radius Radius of the sphere
     * @return Volume of the sphere
     */
    public static double sphereVolume(double radius) {
        return (4.0 * Math.PI * Math.pow(radius, 3.0)) / 3.0;
    }

    /**
     * Helper method for string representation the volume of the sphere
     *
     * @param radius Radius of the sphere
     * @param volume Volume of the sphere
     * @return Formatted string to print volume of sphere
     */
    public static String sphereVolumeToString(double radius, double volume) {
        return String.format(
                "Volume of a sphere with radius %.1f is %s",
                radius, P7_Dhruva_Krupa_Math.prettyNumber(volume));
    }

    /**
     * Calculate hypotenuse of a right angle triangle based on Pythagorean theorem
     *
     * @param a Length of a side of a right angle triangle
     * @param b Length of a side of a right angle triangle
     * @return Hypotenuse of the right angle triangle
     */
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0));
    }

    /**
     * Helper method for formatted string representation of computing hypotenuse
     *
     * @param a Length of a side of a right angle triangle
     * @param b Length of a side of a right angle triangle
     * @param h Hypotenuse of the right angle triangle
     * @return Formatted string for printing the calculation
     */
    public static String hypotenousToString(double a, double b, double h) {
        return String.format(
                "A right triangle with sides %s and %s has hypotenuse %s",
                P7_Dhruva_Krupa_Math.prettyNumber(a),
                P7_Dhruva_Krupa_Math.prettyNumber(b),
                P7_Dhruva_Krupa_Math.prettyNumber(h));
    }

    /**
     * Compute the number of atoms in a given mass of an element
     *
     * <p>Uses Avagadro's number
     *
     * @see P7_Dhruva_Krupa_Math#AVAGADRO_NUMBER
     * @param element Name of the element
     * @param atomicMass Atomic mass of the element from periodic table
     * @param mass Mass in grams of the element
     * @return Number of atoms in the given mass of an element
     */
    public static double gramsToAtoms(String element, double atomicMass, double mass) {
        return (mass * P7_Dhruva_Krupa_Math.AVAGADRO_NUMBER) / atomicMass;
    }

    /**
     * Helper method for formatted string for the calculation of number of atoms
     *
     * @param element Name of the element
     * @param atoms Number of atoms of the element
     * @param mass Mass in grams of the element
     * @return Formatted string representing the calculation
     */
    public static String gramsToAtomsToString(String element, double atoms, double mass) {
        return String.format(
                "%sg of \"%s\" contains %.15E atoms",
                P7_Dhruva_Krupa_Math.prettyNumber(mass), element, atoms);
    }

    /**
     * Compute a random number (int) between 2 numbers (both inclusive)
     *
     * @param lower Lower bound (inclusive)
     * @param upper Upper bound (inclusive)
     * @return Bounded random number
     */
    public static int boundedRandom(int lower, int upper) {
        // Math.random() returns a random n umber between 0.0 to 1.0 (inclusive)
        // We get a factor based on random on the difference and add it to lower
        // to get a random number
        return (int) Math.round(lower + ((Math.random()) * (upper - lower)));
    }

    /**
     * Compute the perimeter of a triangle given its vertices
     *
     * @param ptA Coordinate of a vertex
     * @param ptB Coordinate of a vertex
     * @param ptC Coordinate of a vertex
     * @return Perimeter of the triangle
     */
    public static double perimeter(Point ptA, Point ptB, Point ptC) {
        return ptA.distance(ptB) + ptB.distance(ptC) + ptC.distance(ptA);
    }

    /**
     * Helper method for formatted representation of triangle perimeter calculation
     *
     * @param ptA Coordinate of a vertex
     * @param ptB Coordinate of a vertex
     * @param ptC Coordinate of a vertex
     * @param perimeter Perimeter of the triangle
     * @return Formatted string with perimeter calculation details
     */
    public static String perimeterToString(Point ptA, Point ptB, Point ptC, double perimeter) {
        String perimeterStr = P7_Dhruva_Krupa_Math.prettyNumber(perimeter);
        return String.format(
                "A triangle with vertices:\n"
                        + "\t%s\n"
                        + "\t%s\n"
                        + "\t%s\n"
                        + "has perimeter %s\n",
                ptA, ptB, ptC, perimeterStr);
    }

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
