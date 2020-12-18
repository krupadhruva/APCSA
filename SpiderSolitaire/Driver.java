/*
 * Name: Krupa Dhruva
 * Date: December 17, 2020
 * Period: 7
 * Time Taken: 120 minutes
 *
 * Lab Reflection:
 * Saving and restoring the state of a program allowed me to explore the file picker
 * to select the file to save/load. Limiting the user to select only a file and
 * not a directory is another feature of file picker I learned.
 *
 * I explored different approaches to saving the state of a file. It starts with saving
 * it in a text file then reads it back by interpreting each line followed by constructing
 * the card objects, and finally, puts them in a collection.
 * I learned Java supports storing objects and can be used to save state of the game
 *     https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
 * This allowed be to implement saving & loading the game using built in Java support
 * for saving objects and collections.
 */
public class Driver {
    public static void main(String[] args) {
        new SpiderSolitaire().play();
    }
}
