/**
 * Main is a class with a 'main' function
 *
 * <p>The name of the class is irrelevant but 'main' function is special 'main' is the entry point
 * when you run a Java application.
 *
 * <p>Running a Java application requires providing the class name containing 'main' function.
 *
 * <p>If there are multiple classes with 'main' function, Java will run 'main' from the class
 * specified
 *
 * <pre>
 *     # To compile
 *     javac Main.java
 *
 *     # Run the compiled class
 *     java Main
 *
 *     # Run the compiled class with command line arguments
 *     java Main Some random arguments
 * </pre>
 */
public class Main {
    /**
     * Special function that acts as entry point to Java application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Hello World: " + String.join(",", args));
        } else {
            System.out.println("Hello World");
        }
    }
}
