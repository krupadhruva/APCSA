import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P7_Dhruva_Krupa_Unsqueeze {
    public static void unSqueeze(InputStream in, OutputStream out) throws IOException {
        Scanner scan = new Scanner(in);
        scan.useDelimiter("\n");

        while (scan.hasNext()) {
            String line = scan.next();
            StringTokenizer token = new StringTokenizer(line, " ");

            if (token.hasMoreTokens()) {
                String numStr = token.nextToken();
                int num = Integer.parseInt(numStr);
                String tabs = "\t".repeat(num);
                int idx = line.indexOf(" ");
                String remainingLine = line.substring(idx + 1);
                String unSqueezedLine = String.format("%s%s\n", tabs, remainingLine);

                out.write(unSqueezedLine.getBytes());
            }
        }
    }

    public static void main(String[] args) {
        // Finding class name without hardcoding it
        // Source: https://stackoverflow.com/a/29477085
        final String className = MethodHandles.lookup().lookupClass().getName();

        FileInputStream in;
        FileOutputStream out;

        try {
            in = new FileInputStream("P7_Dhruva_Krupa_Squeeze_output.txt");
            out = new FileOutputStream(className + "_output.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("failed to open file for read/write: " + ex.getMessage());
            return;
        }

        try {
            P7_Dhruva_Krupa_Unsqueeze.unSqueeze(in, out);
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("failed to read/write to file: " + ex.getMessage());
        }
    }
}
