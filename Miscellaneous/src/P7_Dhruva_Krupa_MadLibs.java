/*
 * Name: Krupa Dhruva
 * Date: September 13, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * Implementing MadLibs was a fun activity in terms of being creative, funny
 * and getting the right combination of user inputs to fit the story template.
 * Scanner class is very powerful and versatile to get input. I can see this
 * becoming an important class for anything that requires taking input at runtime.
 *
 * Would it be better to use '%n' instead of '\n'?
 * https://www.baeldung.com/java-string-newline#3-using-platform-independent-new-lines
 */

import java.util.Scanner;

/**
 * Class with driver to take inputs from user using Scanner class and print a funny story on console
 *
 * <p>The intent is to explore Scanner class
 */
public class P7_Dhruva_Krupa_MadLibs {
    /**
     * Entry function for taking user input and printing the story
     *
     * @param args Command line arguments (ignored)
     */
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);

        System.out.println("Please enter an emotion: ");
        String emotion1 = scan.next();

        System.out.println("Please enter a verb in past tense: ");
        String pastVerb = scan.next();

        System.out.println("Please enter a noun: ");
        String noun1 = scan.next();

        System.out.println("Please enter a number up to 4 decimals: ");
        Double minutes = scan.nextDouble();

        System.out.println("Please enter an adverb: ");
        String adverb = scan.next();

        System.out.println("Please enter verb: ");
        String verb = scan.next();

        System.out.println("Please enter a noun: ");
        String noun3 = scan.next();

        System.out.println("Please enter a verb that ends with \"ing\": ");
        String ingVerb = scan.next();

        System.out.println("Please enter a number: ");
        Integer missedCalls = scan.nextInt();

        System.out.println("Please enter a number between 1 to 12: ");
        Integer hour = scan.nextInt();

        System.out.println("Please enter a verb that ends with \"ing\": ");
        String ingVerb2 = scan.next();

        System.out.println("Please enter a class or course name: ");
        String courseName = scan.next();

        System.out.println("Please enter a number with 2 decimals: ");
        Double coursePrice = scan.nextDouble();

        System.out.printf(
                "Thanks! Here's your MadliB Story: \n"
                    + "\n"
                    + "Crazy Dreams by Krupa Dhruva\n"
                    + "It was Sunday morning when I %s woke up from my sleep, scared that I had %s"
                    + " to attend the first\n"
                    + "day of classes. After I got ready and looked at the clock, I ran outside my"
                    + " to door so that I wouldn't miss the %s. \n"
                    + "I waited for %.4f minutes when I realized that there was no class because"
                    + " of the pandemic. Feeling stupid, \n"
                    + "I %s went back into my house and got ready to %s. Little did I know that"
                    + " when I happily slept, my %s\n"
                    + "was %s with %d missed calls from all of my teachers. I woke up at %d"
                    + " o'clock and I was extremely confused as to why\n"
                    + "I had so many notifications. Then it hit me. \"CLASS WAS ONLINE!!\" When I"
                    + " started to panic, my alarm clock rang, \n"
                    + "%s me awake, and that it when I realized it was only a dream! Man, am I"
                    + " glad that was it was \n"
                    + "just a dream or else I would have been un-enrolled from my first day of %s"
                    + " with no refund for $%.2f!\n"
                    + "\n"
                    + "Note: This happened to my cousin last Monday so I thought it would be fun"
                    + " to share :D",
                emotion1,
                pastVerb,
                noun1,
                minutes,
                adverb,
                verb,
                noun3,
                ingVerb,
                missedCalls,
                hour,
                ingVerb2,
                courseName,
                coursePrice);
    }
}
