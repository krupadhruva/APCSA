/*
 * Name: Krupa Dhruva
 * Date: March 07, 2021
 * Period: 7
 * Time Taken: 80 minutes
 *
 * Lab Reflection:
 * The first part of the lab involved understanding the game and the following
 * the instructions closely. It was easy to make mistakes and so I had to pay extra
 * attention to follow every detail. I developed a high level understanding of interface
 * and abstract methods.
 * Implementing lookup of items in a list honoring order rules was challenging. I
 * learned an approach to implement it and would like to explore other alternatives.
 * I was able to semi-automate running the game by sending inputs from a file. With this,
 * I was able to cover more alternatives. Overall, the game was interesting and enjoyable.
 */

public class WizardLabDriver {

    public static void main(String[] args) {
        String intro =
                "Welcome to the Wizard Laboratory!\n"
                        + "You have just broken into your magic professor's laboratory\n"
                        + "(without his knowledge!) in the early hours of the morning.\n"
                        + "Unfortunately, the door magically seals itself behind you\n"
                        + "and you estimate that you have a couple of hours to explore\n"
                        + "and escape before he wakes up.  Get what you need and get out!";
        WizardsLab lab = new WizardsLab("This is a wizards lab.", intro, 15);
        new EscapeApp(lab).runGame();
    }
}
