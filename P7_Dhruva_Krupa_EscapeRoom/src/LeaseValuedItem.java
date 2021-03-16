/**
 * Implements an Item with value that can be leased. You have temporary ownership of the item.
 * Ownership expires when you have exhausted its life. You will have to acquire it again by either
 * exchanging or buying it.
 */
public abstract class LeaseValuedItem extends ValuedItem {
    private final int life;
    private int usage;

    /**
     * Create an item with the given name and description.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param value The value of the item, used for barter/exchange
     * @param life Number of times this item can be used
     * @throws IllegalArgumentException if the name contains white space
     */
    public LeaseValuedItem(String name, String description, int value, int life) {
        super(name, description, value);
        this.life = life;
        this.usage = 0;
    }

    public void resetUsage() {
        usage = 0;
    }

    /**
     * Computes the depreciated value of an item based on use
     *
     * @return Depreciated value
     */
    @Override
    public long getValue() {
        return super.getValue() / Math.max(1, usage);
    }

    /**
     * Derived class to implement specific behavior on what needs to be done when this leased item
     * is used
     *
     * <p>This function is invoked only on valid use of a leased item - before expiry
     */
    abstract void useAction();

    @Override
    public void use() {
        if (this.usage < this.life && isOwned()) {
            ++usage;
            super.use();
        } else {
            // Add back into the pool
            usage = 0;
            setOwned(false);
            System.out.printf(
                    "you no longer own %s, buy/exchange if you want to use it%n", getName());
        }
    }

    @Override
    public String getDescription() {
        return String.format(
                "%s. This is an expendable item that gets consumed everytime you use. You have"
                        + " %d/%d left",
                super.getDescription(), usage, life);
    }
}
