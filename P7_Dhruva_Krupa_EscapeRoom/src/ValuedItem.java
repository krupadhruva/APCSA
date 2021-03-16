import java.time.Duration;

public class ValuedItem extends Item implements Comparable<ValuedItem> {
    private int value;
    private boolean owned;

    /**
     * Create an item with the given name and description.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param value The value of the item, used for barter/exchange
     * @throws IllegalArgumentException if the name contains white space
     */
    public ValuedItem(String name, String description, int value) {
        super(name, description);
        this.value = value;
        owned = false;
    }

    public static Duration valueToTime(int value) {
        return Duration.ofSeconds(value * 60L);
    }

    @Override
    public int compareTo(ValuedItem other) {
        return getValue() - other.getValue();
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isOwned() {
        return owned;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void use() {
        System.out.printf("%s item has %d value, can be used to barter%n", getName(), value);
    }
}
