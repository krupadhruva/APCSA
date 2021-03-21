import java.time.Duration;

public class ValuedItem extends Item implements Comparable<ValuedItem> {
    private Duration value;
    private boolean owned;

    /**
     * Create an item with the given name and description.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param value The value of the item, used for barter/exchange
     * @throws IllegalArgumentException if the name contains white space
     */
    public ValuedItem(String name, String description, long value) {
        super(name, description);
        this.value = Duration.ofSeconds(value);
        owned = false;
    }

    @Override
    public int compareTo(ValuedItem other) {
        return Long.compare(getValue(), other.getValue());
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isOwned() {
        return owned;
    }

    public long getValue() {
        return value.getSeconds();
    }

    public void setValue(long value) {
        this.value = Duration.ofSeconds(value);
    }

    @Override
    public void use() {
        System.out.printf(
                "%s item has %d value, can be used to barter%n", getName(), value.getSeconds());
    }

    public String getDescription() {
        return String.format(
                "%s, has value=%d, owned=%s", super.getDescription(), getValue(), isOwned());
    }
}
