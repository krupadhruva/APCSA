/*
 * Name: Krupa Dhruva
 * Date: March 15, 2021
 * Period: 7
 * Time Taken: 720 minutes
 *
 * Lab Reflection:
 * Understanding the code flow in existing escape room game took a long time. This was very important
 * to identify classes that needs to be extended and functions that had to be overridden.
 *
 * I wanted to model a game that is time critical rather than number of turns. This took a while for
 * me to understand how I could keep track of time. Finding ways to make it more interesting and favor
 * the player was a challenge. Since the game is time critical, I decided to use time as currency.
 *
 * Overall, coming up with a theme and game strategy was quite challenging. I am still evolving this game
 * and am finding this very interesting.
 */

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class RocketLab extends Room {
    private Instant startTime;
    private Duration totalTimeToEscape;
    private boolean aborted;

    /**
     * Creates a room with the given description and intro
     *
     * @param description The room description
     * @param intro the introduction describing the scenario for this room
     */
    public RocketLab(String description, String intro, int timeToEscape) {
        super(description, intro);

        // Clock starts ticking once player interacts with the game
        startTime = null;

        aborted = false;
        totalTimeToEscape = Duration.ofSeconds(timeToEscape);

        add(new UselessItem("monitor", "External camera display"));

        final String manual =
                "To escape earth, you need to build a rocket and eject. Time is the essence and"
                        + " time is money!\n"
                        + "There are 4 parts in a rocket:\n"
                        + "1. Cone at the tip\n"
                        + "2. Rocket body: Long cylindrical portion\n"
                        + "3. Rocket engine\n"
                        + "4. Fins for stabilizing the rocket during flight\n\n"
                        + "Find the different parts in the room and finally assemble them to make a"
                        + " rocket.\n"
                        + "You might have to purchase some of the items by trading with time.\n";
        final String rocket =
                "\n\n"
                        + "                  .─.\n"
                        + "                 ╱   ╲\n"
                        + "                ;     :\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                :     ;\n"
                        + "                │╲   ╱│\n"
                        + "                │ ╲ ╱ │\n"
                        + "                │  '  │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "                │     │\n"
                        + "               *│┌...┐│*\n"
                        + "              **││...││**\n"
                        + "              **││...││***\n"
                        + "            ****││...││****\n"
                        + "           *****││...││*****\n"
                        + "          ******││...││******\n"
                        + "         *******│└...┘│*******\n"
                        + "        ********└──*──┘********\n"
                        + "       *********   *   *********\n"
                        + "      ********     *     ********\n"
                        + "     ********      *      ********\n"
                        + "\n\n";

        add(
                new ManualItem(
                        "manual",
                        "Blueprint to build the rocket, don't hesitate to read often.",
                        manual + rocket));
    }

    private Duration remainingTime() {
        if (startTime == null) {
            return totalTimeToEscape;
        }

        return totalTimeToEscape.minus(Duration.between(startTime, Instant.now()));
    }

    public void addTime(Duration life) {
        totalTimeToEscape = totalTimeToEscape.plus(life);
    }

    public void subTime(Duration life) {
        totalTimeToEscape = totalTimeToEscape.minus(life);
    }

    @Override
    public void printRoomPrompt() {
        System.out.printf(
                "%n[You have %s seconds left to save humanity]%n", remainingTime().getSeconds());
    }

    @Override
    public void onCommandAttempted(String command, boolean handled) {
        if (!aborted) {
            if (handled) {
                System.out.print("Good progress! ");
            } else {
                System.out.printf("%nYou are losing precious time... ");
            }
        }

        System.out.printf(
                "elapsed time %d seconds%n", totalTimeToEscape.minus(remainingTime()).getSeconds());
    }

    @Override
    public boolean escaped() {
        // Define state for winning
        return false;
    }

    @Override
    public boolean failed() {
        // Check if you have exhausted allowed time
        return aborted || remainingTime().isNegative();
    }

    @Override
    public void onEscaped() {}

    @Override
    public void onFailed() {
        System.out.printf(
                "%nYou were the last hope for humanity, unfortunately, the mission was a"
                        + " failure...%nWait for another opportunity, be brave and retry...");
    }

    // Helper method to get valued items for specific commands
    private ValuedItem getValuedItem(String name) {
        Item item = getItem(name);
        if (item != null) {
            if (item instanceof ValuedItem) {
                return (ValuedItem) item;
            }
        }

        return null;
    }

    @Override
    public boolean execute(String command) {
        if (startTime == null) {
            startTime = Instant.now();
        }

        if (command.isEmpty()) {
            return false;
        }

        if (command.compareToIgnoreCase("abort") == 0) {
            System.out.println("Mission aborted!");
            aborted = true;
            return true;
        }

        if (command.compareToIgnoreCase("help") == 0) {
            addTime(Duration.ofSeconds(15));
            printHelp();
            return true;
        }

        if (command.compareToIgnoreCase("manual") == 0) {
            getItem("manual").use();
            return true;
        }

        // Multi word commands
        try (Scanner scan = new Scanner(command)) {
            if (scan.hasNext()) {
                String cmd = scan.next().toLowerCase();

                if (cmd.equals("buy") || cmd.equals("exchange")) {
                    final boolean isBuy = cmd.equals("buy");

                    do {
                        if (!scan.hasNext()) {
                            break;
                        }

                        ValuedItem fromItem = null;
                        if (isBuy) {
                            int val = scan.nextInt();
                            if (remainingTime().compareTo(ValuedItem.valueToTime(val)) > 0) {
                                fromItem = new ValuedItem("time", "Paying in time", 10 * val);
                                fromItem.setOwned(true);
                            }
                        } else {
                            fromItem = getValuedItem(scan.next());
                        }

                        if (!scan.hasNext()) {
                            break;
                        }
                        String to = scan.next();
                        ValuedItem toItem = getValuedItem(to);

                        if (fromItem == null || toItem == null) {
                            System.out.printf("exchange items not found%n");
                            break;
                        }

                        if (!fromItem.isOwned()) {
                            System.out.printf(
                                    "cannot exchange item %s, not owned%n", fromItem.getName());
                            break;
                        }

                        int remaining = fromItem.compareTo(toItem);
                        if (remaining < 0) {
                            System.out.println(
                                    "Cannot exchange item of lower value with higher value");
                            break;
                        }

                        // Finally, debit the time
                        if (isBuy) {
                            subTime(ValuedItem.valueToTime(fromItem.getValue()));
                        }

                        // Additional value after exchange is converted to time
                        addTime(ValuedItem.valueToTime(remaining * 2));

                        return true;
                    } while (false);

                    System.out.printf(
                            "%s did not meet criteria, please retry with valid entries.", command);
                    return true;
                }
            }
            if (command.compareToIgnoreCase("exchange") == 0) {
                getItem("manual").use();
                return true;
            }
        }

        return super.execute(command);
    }

    @Override
    public void printHelp() {
        super.printHelp();
        System.out.println("abort\n" + "\t To scuttle mission");
        System.out.println(
                "buy value item\n"
                        + "\tBuy items by paying with remaining time, you get more value for"
                        + " time you trade");
        System.out.println(
                "exchange itemOwned itemNeeded\n"
                    + "\tExchange an item you own with an item you need. The value of item you own"
                    + " should be greater than the item you need. Twide the remaining value is"
                    + " converted to remaining time");
    }
}
