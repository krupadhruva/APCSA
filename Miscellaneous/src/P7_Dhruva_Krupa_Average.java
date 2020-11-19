/*
 * Name: Krupa Dhruva
 * Date: November 18, 2020
 * Period: 7
 * Time Taken: 120 minutes
 *
 * Lab Reflection:
 * Getting list of files under a directory was something I had not done before. I searched on internet and
 * found a few examples. I took help from my parent to explain the examples I found online.
 * The next challenge was extracting integers from a line of text mixed with non numeric fields.
 * StackOverflow had an example using a pattern in scanner as delimiter. I found a link to documentation
 * of Java regular expressions and found "\\D+" is a pattern for non-integer.
 *
 * I was aware of formatting double to print specific decimal precision. Since String class has the same
 * formatting method 'format()', I used it to store a formatted string representation of computed average
 * and use it for both console and file output.
 *
 * Overall, I learned fetching files from a directory, using scanner with a custom delimiter and formatting
 * output and storing it as string to use with file IO.
 *
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class P7_Dhruva_Krupa_Average {
    /**
     * Gets all files with '.txt' extension from a given parent directory
     *
     * @param parent Path to parent directory
     * @return Array of Path type of files with '.txt' extension
     * @throws IOException
     */
    public static Path[] getTextFiles(String parent) throws IOException {
        // Source: https://www.baeldung.com/java-list-directory-files
        // Note: Do not understand the exact syntax but have a high level understanding
        Path path = Path.of(parent);
        return Files.list(path)
                .filter(p -> p.toFile().isFile() && p.toString().endsWith(".txt"))
                .toArray(Path[]::new);
    }

    /**
     * Compute the average of numbers in files
     *
     * @param paths Array (type Path) of files with numbers
     * @return Average
     * @throws FileNotFoundException
     */
    public static double getAverages(Path[] paths) throws FileNotFoundException {
        int count = 0;
        double total = 0.0;

        for (Path path : paths) {
            Scanner scanner = new Scanner(path.toFile());

            // Source: https://stackoverflow.com/a/59285080
            // https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
            scanner.useDelimiter("\\D+");

            while (scanner.hasNextInt()) {
                total += scanner.nextInt();
                ++count;
            }

            scanner.close();
        }

        return total / count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter path to directory with text files containing numbers: ");
        String directory = input.nextLine();
        input.close();

        try {
            // Get all files with '.txt' extension under the given parent directory
            Path[] paths = P7_Dhruva_Krupa_Average.getTextFiles(directory);

            // If there are files of interest in parent directory, process tem
            if (paths.length > 0) {
                // Get the average across all files with '.txt' extension under parent directory
                double average = P7_Dhruva_Krupa_Average.getAverages(paths);

                // Format the output for console and file output
                String averageStr = String.format("%.2f%n", average);
                System.out.print(averageStr);

                // Open output file for write
                FileOutputStream output = new FileOutputStream("output.txt");

                // Since write() expects a byte array, get byte array from formatted string
                output.write(averageStr.getBytes());
                output.close();
            }
        } catch (IOException ex) {
            System.out.println("file/directory not found: " + ex.getMessage());
        }
    }
}
