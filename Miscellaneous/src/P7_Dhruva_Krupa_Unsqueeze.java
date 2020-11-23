/*
 * Name: Krupa Dhruva
 * Date: November 22, 2020
 * Period: 7
 * Time Taken: 40 minutes
 *
 * Lab Reflection:
 * I had fun doing this lab, especially working with Delimiters, String
 * Tokenizers, and Try Catch statements. I used a delimiter the read the
 * line until "\n" or LF was found and I used a tokenizer to extract words
 * separated by spaces in the line. Overall, this was a fun and interesting
 * lab to do.
 */

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

        // Reads line until LF ("\n") is found. Handles CRLF as well (by preserving CR or "\r")
        scan.useDelimiter("\n");

        while (scan.hasNext()) {
            String line = scan.next();

            // Extracts space separated words in a line
            StringTokenizer token = new StringTokenizer(line, " ");

            // Checks if there is a word, only interested in the first word
            if (token.hasMoreTokens()) {
                // The first word is the number of tabs needed
                String numStr = token.nextToken();

                // Converts the string representation of number to an int type
                int num = Integer.parseInt(numStr);

                // Create tabs based on num to prefix the line
                String tabs = "\t".repeat(num);

                // We want to remove the encoding of tabs and get the actual line
                // Find the end of first word
                int idx = line.indexOf(" ");

                // Removes the first word (number of tabs) & the following space
                String remainingLine = line.substring(idx + 1);

                // Prepend tabs & the original line
                String unSqueezedLine = String.format("%s%s\n", tabs, remainingLine);

                // Write the unsqueezed line to the output
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
