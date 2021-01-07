import java.util.*;

/** Examples of different ways to loop in Java */
public class Loops {

    /**
     * User defined data type that can be iterated
     *
     * <p>This is used to demonstrate use of 'foreach' loop with user defined data types
     */
    static class UserDefinedIterable implements Iterable<Integer> {
        final Collection<Integer> collection;

        public UserDefinedIterable(int[] data) {
            collection = new ArrayList<>(data.length);
            Arrays.stream(data).forEach(collection::add);
        }

        /**
         * Implements <code>Iterable</code> to allow this class work with 'foreach' loop
         *
         * @return Iterator to go through the list contents
         */
        @Override
        public Iterator<Integer> iterator() {
            return collection.iterator();
        }
    }

    public static void main(String[] args) {

        {
            /*
             * for-loop
             * for (initialize variables; evaluate condition; update iteration state) {
             *      // Code to execute if condition is true
             * }
             */

            System.out.println("for-loop:");

            for (int ii = 0,
                            jj = 3 /* Declare and initialize multiple variables BUT of same type */;
                    ii < jj /* Condition check, can combine multiple using logical AND or OR */;
                    ++ii /* Updated variables, supports updating multiple variables separated by ',' */) {
                System.out.printf("\tfor-looping: %d of %d%n", ii, jj);
            }
        }

        {
            /*
             * while-loop
             * while (evaluate condition) {
             *      // Code to execute if condition is true
             * }
             */

            System.out.println("while-loop:");

            int val = 3;
            while (val > 0) {
                System.out.printf("\twhile-looping: %d%n", val);

                // Note: If you forget to update this variable, the condition check will always be
                // true resulting in an infinite loop
                --val;
            }
        }

        {
            /*
             * foreach-loop:
             * for (type var : iterable) {
             *      // Code to execute on each value in the iterable collection
             * }
             *
             * This type of loop can be used ONLY with collections of data like arrays
             * or Java collections (list, map, set...) OR any user defined type that
             * implements <code>Iterable</code>
             */

            System.out.println("foreach-loop:");

            int[] data = {1, 2, 3};
            for (int val : data) {
                System.out.printf("\tforeach-looping: %d%n", val);
            }

            System.out.println();

            // Same 'foreach' loop but with user defined data type/class
            UserDefinedIterable iterable = new UserDefinedIterable(data);
            for (var val : iterable) {
                System.out.printf("\tforeach-looping used defined iterable: %s%n", val);
            }
        }
    }
}
