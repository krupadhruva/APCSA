import java.util.Arrays;

/**
 * Bubble sort implementation
 *
 * <p>The basic idea is to find the right position for each entry in a collection. Since entries get
 * swapped as part of finding the right position, some entries get bubbled up. Hence, the inner loop
 * will always have to start from the top.
 */
public class BubbleSort {
    private static void trace(int iteration, String msg) {
        System.out.printf("\t%02d. Iteration: %s%n", iteration, msg);
    }

    /**
     * Implements sorting using Bubble sort algorithm
     *
     * <p>Since sorting is possible only on data types that can be compared, we have made the
     * implementation generic to support sorting any data type that can be compared via <code>
     * Comparable</code> interface implementation.
     *
     * <p>For supporting sorting for custom data types (used defined classes), implement <code>
     * compareTo()</code> from the <code>Comparable</code> interface
     *
     * @param data Array of data type implementing Comparable interface to be sorted
     * @param ascending Boolean flag, <code>true</code> for sorting in ascending order
     * @param log Boolean flag, <code>true</code> for verbose logging
     * @param <T> Data type of array, implicitly inferred by Java compiler
     */
    public static <T extends Comparable<T>> void bubbleSort(
            T[] data, boolean ascending, boolean log) {
        // Used only for tracing iterations, not part of sort algorithm
        int iterations = 0;

        // Log only if requested to avoid cluttering the output
        if (log) {
            trace(iterations++, Arrays.toString(data));
        }

        // Let us find the right position for each entry in collection
        for (int ii = 0; ii < data.length - 1; ++ii) {
            // Let us find the right position for each entry in collection starting from top
            // If the entry is at desired position, we just skip to next pair
            for (int jj = 0; jj < data.length - ii - 1; ++jj) {
                // Handle ascending or descending by changing how we compare
                boolean shouldSwap =
                        ascending
                                ? data[jj].compareTo(data[jj + 1]) > 0
                                : data[jj].compareTo(data[jj + 1]) < 0;

                // Based on comparison, do we have to swap and bubble down the entry?
                if (shouldSwap) {
                    // Swap the entries
                    var tmp = data[jj];
                    data[jj] = data[jj + 1];
                    data[jj + 1] = tmp;

                    if (log) {
                        trace(iterations++, Arrays.toString(data));
                    }
                }
            }
        }
    }

    // Note: 'static' since Java does not allow access to inner classes
    // Or: All inner classes *must* be static
    static class UserDefinedIntegerType implements Comparable<UserDefinedIntegerType> {
        final int value;

        public UserDefinedIntegerType(int value) {
            this.value = value;
        }

        /**
         * Implements Comparable interface for user defined class
         *
         * @param other Instance to compare with
         * @return -ve value is less than, +ve value if greater than OR 0 if equal
         */
        @Override
        public int compareTo(UserDefinedIntegerType other) {
            return value - other.value;
        }

        /**
         * Override <code>toString()</code> on user defined class to print the actual information we
         * are interested in seeing and not use the default implementation in <code>Object</code>
         * that prints the address of the object
         *
         * @return String representation of the object (value in our implementation)
         */
        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        {
            Integer[] data = {84, 50, 62, 82, 83, 15};

            System.out.println("Before: " + Arrays.toString(data));
            bubbleSort(data, true, true);
            System.out.println("After : " + Arrays.toString(data));
        }
        System.out.println();

        {
            UserDefinedIntegerType[] data = {
                new UserDefinedIntegerType(84),
                new UserDefinedIntegerType(50),
                new UserDefinedIntegerType(62),
                new UserDefinedIntegerType(82),
                new UserDefinedIntegerType(83),
                new UserDefinedIntegerType(15)
            };

            System.out.println("Before: " + Arrays.toString(data));
            bubbleSort(data, true, true);
            System.out.println("After : " + Arrays.toString(data));
        }
        System.out.println();

        {
            String[] data = {"zinc", "uranium", "cobalt", "iron", "copper"};
            System.out.println("Before: " + Arrays.toString(data));
            bubbleSort(data, true, true);
            System.out.println("After : " + Arrays.toString(data));
        }
    }
}
