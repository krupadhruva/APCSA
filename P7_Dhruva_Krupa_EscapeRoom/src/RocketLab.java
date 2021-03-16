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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RocketLab extends Room {
    private Instant startTime;
    private Duration totalTimeToEscape;
    private boolean aborted;
    private final RocketRecipe rocketRecipe;
    private final Random rand;
    private boolean blastOff;

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

        rand = new Random();
        aborted = false;
        blastOff = false;
        totalTimeToEscape = Duration.ofSeconds(timeToEscape);
        rocketRecipe = new RocketRecipe(this);

        add(rocketRecipe);
        add(new PauseClock(180, 5));
        add(new UselessItem("monitor", "External camera display"));

        Container chest =
                new ToolChest("tool_chest", "Box of tools and other goodies", "blast off");
        add(chest);

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

        add(
                new ManualItem(
                        "manual",
                        "Blueprint to build the rocket, don't hesitate to read often.",
                        manual));
    }

    public Random getRandom() {
        return rand;
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
                "%n[You have %d seconds left to save humanity]%n", remainingTime().getSeconds());
    }

    @Override
    public void onCommandAttempted(String command, boolean handled) {
        if (!aborted) {
            if (handled) {
                System.out.print("\nGood progress! ");
            } else {
                System.out.print("\nYou are losing precious time... ");
            }
        }

        System.out.printf(
                "elapsed time %d seconds%n", totalTimeToEscape.minus(remainingTime()).getSeconds());
    }

    @Override
    public boolean escaped() {
        // Define state for winning
        return blastOff && rocketRecipe.isCombined();
    }

    @Override
    public boolean failed() {
        // Check if you have exhausted allowed time
        return aborted || remainingTime().isNegative();
    }

    @Override
    public void onEscaped() {
        long totalTimeTaken = Duration.between(startTime, Instant.now()).getSeconds();
        System.out.printf(
                "%nCongratulations on saving humanity from extinction! You completed the mission"
                        + " in %d seconds with %d seconds left%n",
                totalTimeTaken, remainingTime().getSeconds());
    }

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
        if (command.isEmpty()) {
            return false;
        }

        if (startTime == null) {
            startTime = Instant.now();
        }

        if (command.compareToIgnoreCase("abort") == 0) {
            System.out.println("Mission aborted!");
            aborted = true;
            return true;
        }

        // Reward interactions since we penalize on inaction
        addTime(Duration.ofSeconds(rand.nextInt(60 - 5) + 5));

        if (command.compareToIgnoreCase("help") == 0) {
            printHelp();
            return true;
        }

        if (command.compareToIgnoreCase("manual") == 0) {
            getItem("manual").use();
            return true;
        }

        if (command.compareToIgnoreCase("look") == 0) {
            List<Item> owned = new ArrayList<>();
            List<Item> avail = new ArrayList<>();

            for (Item item : getItems()) {
                if (item instanceof ValuedItem && ((ValuedItem) item).isOwned()) {
                    owned.add(item);
                } else {
                    avail.add(item);
                }
            }

            System.out.println("List of owned items:");
            for (Item item : owned) {
                System.out.printf("\t%s - %s%n", item.getName(), item.getDescription());
            }

            System.out.println("List of available items:");
            for (Item item : avail) {
                System.out.printf("\t%s - %s%n", item.getName(), item.getDescription());
            }

            return true;
        }

        // Multi word commands
        try (Scanner scan = new Scanner(command)) {
            if (scan.hasNext()) {
                String cmd = scan.next().toLowerCase();

                if (cmd.equals("buy") || cmd.equals("return") || cmd.equals("exchange")) {
                    final boolean isBuy = cmd.equals("buy");
                    final boolean isReturn = cmd.equals("return");

                    do {
                        if (!scan.hasNext()) {
                            break;
                        }

                        ValuedItem fromItem = getValuedItem(scan.next());
                        if (fromItem == null) {
                            System.out.printf(
                                    "\"%s\" operation not possible, item to exchange not found%n",
                                    command);
                            break;
                        }

                        ValuedItem toItem;
                        if (isReturn) {
                            // Restore value on return to ensure we reimburse the full amount
                            if (fromItem instanceof LeaseValuedItem) {
                                ((LeaseValuedItem) fromItem).resetUsage();
                            }

                            // Having 0 value time item will ensure 'remaining' will be full value
                            toItem = new ValuedItem("time", "Purchasing time", 0);
                        } else if (isBuy) {
                            toItem = fromItem;
                            fromItem = new ValuedItem("time", "Pay in time", toItem.getValue());
                            fromItem.setOwned(true);
                        } else {
                            if (!scan.hasNext()) {
                                break;
                            }
                            toItem = getValuedItem(scan.next());
                        }

                        if (toItem == null) {
                            System.out.printf(
                                    "\"%s\" operation not possible, item not found%n", command);
                            break;
                        }

                        if (!fromItem.isOwned() || toItem.isOwned()) {
                            System.out.printf(
                                    "\"%s\" operation not possible, invalid item ownership%n",
                                    command);
                            break;
                        }

                        int remaining = fromItem.compareTo(toItem);
                        if (remaining < 0) {
                            System.out.println(
                                    "Cannot exchange item of lower value with higher value");
                            break;
                        }

                        toItem.setOwned(fromItem.isOwned());
                        fromItem.setOwned(false);
                        if (fromItem instanceof LeaseValuedItem) {
                            ((LeaseValuedItem) fromItem).resetUsage();
                        }

                        // Finally, debit the time
                        if (isBuy) {
                            subTime(Duration.ofSeconds(toItem.getValue()));
                        }

                        // We encourage exchanges. Since player might exchange something they might
                        // need in the future, let us given them an incentive by increasing gains
                        if (!(isBuy || isReturn)) {
                            remaining = Math.max(remaining, (int) fromItem.getValue() / 2);
                            remaining *= 2;
                        }

                        // Additional value after exchange is converted to time
                        addTime(Duration.ofSeconds(remaining));

                        return true;
                    } while (false);

                    System.out.printf(
                            "%s did not meet criteria, please retry with valid entries.", command);
                    return true;
                } else if (cmd.equals("blast")) {
                    if (scan.hasNext() && scan.next().compareToIgnoreCase("off") == 0) {
                        blastOff = rocketRecipe.isCombined();
                        return true;
                    }
                }
            }
        }

        return super.execute(command);
    }

    @Override
    public void printHelp() {
        super.printHelp();
        System.out.println(
                "buy item\n"
                        + "\tBuy an item by paying with remaining time, you get more value for"
                        + " time you trade");
        System.out.println(
                "return item\n"
                        + "\tReturn an item and you get back full value even for a used leased"
                        + " item");
        System.out.println(
                "exchange item item\n"
                    + "\tExchange an item you own with an item you need. The value of item you own"
                    + " should be greater than the item you need. Twice the remaining value is"
                    + " converted to remaining time");
        System.out.println(
                "blast off\n" + "\tFinal step to escape earth and save humanity from extinction");
        System.out.println("abort\n" + "\t To scuttle mission");
    }
}
