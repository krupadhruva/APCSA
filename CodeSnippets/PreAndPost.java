/**
 * Show the effect of pre and post increment
 *
 * <p>Pre increment first increments and give the new value
 *
 * <p>Post increment stores the old value, increments and gives the old value
 */
public class PreAndPost {
    public static void main(String[] args) {
        int val = 0;
        int prev_val = val;

        // Notice the 'after' remains unchanged (old value is sent) when sent to 'printf' function
        System.out.printf(
                "Post increment: previous=%d, after=%d, actual=%d%n", prev_val, val++, val);
        prev_val = val;

        // Notice the 'after' changes and new value is sent to 'printf' function
        System.out.printf(
                "Pre  increment: previous=%d, after=%d, actual=%d%n", prev_val, ++val, val);
    }
}
