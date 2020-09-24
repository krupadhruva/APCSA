/*
 * Name: Krupa Dhruva
 * Date: September 22, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Note: The default side length was not specified, hence, I assumed 0.
 *
 * Overall, this was a good exposure to the trignometric functions in the
 * math library. I also learned to avoid using degrees since the math
 * library functions expect radians.
 * Using a helper function to print the polygon details helped me avoid
 * code duplication.
 */

public class P7_Dhruva_Krupa_RegularPolygon {

    /** Number of sides in a polygon */
    private final int myNumSides;
    /** Length of a side in a polygon */
    private final double mySideLength;
    /** Radius of inscribed circle inside the polygon */
    private double myr;
    /** Radius of circumscribed circle around the polygon */
    private double myR;

    /**
     * Default constructor
     *
     * <p>Constructs a polygon with 3 sides of length 0.0
     */
    public P7_Dhruva_Krupa_RegularPolygon() {
        this(3, 0.0);
    }

    /**
     * Custom constructor taking in the polygon attributes
     *
     * @param numSides Number of sides in a polygon (&gt; 2)
     * @param sideLen Length of the polygon side (&ge; 0.0)
     */
    public P7_Dhruva_Krupa_RegularPolygon(int numSides, double sideLen) {
        if (sideLen > 0.0) {
            mySideLength = sideLen;
        } else {
            mySideLength = 0.0;
        }

        if (numSides > 2) {
            myNumSides = numSides;
        } else {
            myNumSides = 3;
        }

        calcr();
        calcR();
    }

    /**
     * Get the number of sides in a polygon
     *
     * @return Number of sides
     */
    public int getMyNumSides() {
        return myNumSides;
    }

    /**
     * Get the length of a side of the polygon
     *
     * @return Length of side
     */
    public double getMySideLength() {
        return mySideLength;
    }

    /**
     * Get the radius of the inscribed circle
     *
     * @return Radius of inscribed circle
     */
    public double getMyr() {
        return myr;
    }

    /**
     * Get the radius of the circumscribed circle
     *
     * @return Radius of circumscribed circle
     */
    public double getMyR() {
        return myR;
    }

    /** Compute the radius of the inscribed circle */
    private void calcr() {
        myr = 0.5 * getMySideLength() / Math.tan(Math.PI / getMyNumSides());
    }

    /** Compute the radius of the circumscribed circle */
    private void calcR() {
        myR = 0.5 * getMySideLength() / Math.sin(Math.PI / getMyNumSides());
    }

    /**
     * Get the included angle between 2 adjacent sides of the polygon
     *
     * @return Included angle at a vertex in degrees
     */
    public double vertexAngle() {
        return 180.0 - (360.0 / getMyNumSides());
    }

    /**
     * Compute the polygon perimeter
     *
     * @return Perimeter of the polygon
     */
    public double perimeter() {
        return getMySideLength() * getMyNumSides();
    }

    /**
     * Compute the area of a polygon
     *
     * @return Area of polygon
     */
    public double area() {
        return 0.5
                * getMyNumSides()
                * Math.pow(getMyR(), 2.0)
                * Math.sin(2 * Math.PI / getMyNumSides());
    }

    /**
     * String representation for printing the polygon details
     *
     * <p>Used for debugging
     *
     * @return Formatted string with polygon information
     */
    @Override
    public String toString() {
        return String.format(
                "n=%d,"
                        + "l=%.2f,"
                        + "angle=%.2f,"
                        + "r=%.2f,"
                        + "R=%.2f,"
                        + "Perimeter=%.2f,"
                        + "Area=%.2f",
                getMyNumSides(),
                getMySideLength(),
                vertexAngle(),
                getMyr(),
                getMyR(),
                perimeter(),
                area());
    }

    /**
     * Driver to test the polygon class
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        /*
         * Test data:
         * Square: number of sides = 4, length of side = 10
         * Octagon: number of sides = 8, length of side = 5.75
         * Enneadecagon: number of sides = 19, length of side = 2
         * Enneacontakaihenagon: number of sides = 91, length of side = 0.5
         */

        P7_Dhruva_Krupa_RegularPolygon polygon = new P7_Dhruva_Krupa_RegularPolygon();
        P7_Dhruva_Krupa_RegularPolygon square = new P7_Dhruva_Krupa_RegularPolygon(4, 10);
        P7_Dhruva_Krupa_RegularPolygon octagon = new P7_Dhruva_Krupa_RegularPolygon(8, 5.75);
        P7_Dhruva_Krupa_RegularPolygon enneadecagon = new P7_Dhruva_Krupa_RegularPolygon(19, 2);
        P7_Dhruva_Krupa_RegularPolygon enneacontakaihenagon =
                new P7_Dhruva_Krupa_RegularPolygon(91, 0.5);

        System.out.println(polygon);
        System.out.println(square);
        System.out.println(octagon);
        System.out.println(enneadecagon);
        System.out.println(enneacontakaihenagon);
    }
}
