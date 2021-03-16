import java.time.Duration;

public class PauseClock extends LeaseValuedItem {

    /**
     * Create an item with the given name and description.
     *
     * @param value The value of the item, used for barter/exchange
     * @param life Number of times this item can be used
     * @throws IllegalArgumentException if the name contains white space
     */
    public PauseClock(int value, int life) {
        super(
                "pause",
                String.format(
                        "Use this to pause clock upto %d seconds for %d times from ticking.",
                        value, life),
                value,
                life);
    }

    /**
     * Make this much cheaper than the original value to increase the gains compared to initial
     * investment when buying
     *
     * @return Adjusted/discounted value
     */
    @Override
    public long getValue() {
        return super.getValue() / 8;
    }

    /** Add time on every use till the lease is valid */
    @Override
    void useAction() {
        Room room = getRoom();
        if (room instanceof RocketLab) {
            ((RocketLab) room).addTime(Duration.ofSeconds(super.getValue()));
        }
    }
}
