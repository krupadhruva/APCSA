/*
 * Name: Krupa Dhruva
 * Date: November 18, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * This was a fun project to learn basic compression methods. I can imagine how this can be extended to
 * store repeating words in a text file and replace them with a unique number instead. However, the characters
 * in the number should be smaller than the word replaced to show any benefits.
 * I have been using String.replaceAll() to count any sequence of characters in a string by taking the
 * difference before and after removing. This makes the implementation easy but not sure if it is the best.
 * I learned finding class name to generate the output file name programmatically and not hardcoding it.
 *
 * As a follow up project, I will try to un-squeeze the file and compare with the original. I should not see
 * any difference and this will check correctness of both squeeze and un-squeeze.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class P7_Dhruva_Krupa_Squeeze {
    /**
     * Compresses a file and writes the output by replacing leading tabs with count of tabs followed
     * by a space and the remaining line.
     *
     * @param in Input file to squeeze/compress
     * @param out Output file with squeezed/compressed data
     * @throws IOException
     */
    public static void squeezeLeadingTabs(FileInputStream in, FileOutputStream out)
            throws IOException {
        Scanner scan = new Scanner(in);
        while (scan.hasNext()) {
            // Read the line w/o leading tabs
            String line = scan.nextLine();

            // Squeeze: Remove all leading tabs in a line
            String squeezed = line.replaceAll("^\t+", "");

            // Original line length minus the squeezed line length gives us the number of tabs
            int tabCount = line.length() - squeezed.length();

            // Form the encoded squeezed line with new line
            squeezed = String.format("%d %s%n", tabCount, squeezed);

            System.out.print(squeezed);
            out.write(squeezed.getBytes());
        }

        scan.close();
    }

    public static void main(String[] args) {
        // Finding class name without hardcoding it
        // Source: https://stackoverflow.com/a/29477085
        final String className = MethodHandles.lookup().lookupClass().getName();

        FileInputStream in;
        FileOutputStream out;

        try {
            in = new FileInputStream("LeetSpeak.java");
            out = new FileOutputStream(className + "_output.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("failed to open file for read/write: " + ex.getMessage());
            return;
        }

        try {
            P7_Dhruva_Krupa_Squeeze.squeezeLeadingTabs(in, out);
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("failed to read/write to file: " + ex.getMessage());
        }
    }
}
