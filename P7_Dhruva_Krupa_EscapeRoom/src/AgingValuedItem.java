public class AgingValuedItem extends ValuedItem {

    /**
     * Create an item with the given name and description.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param value The value of the item, used for barter/exchange
     * @param life Number of times this item can be used
     * @throws IllegalArgumentException if the name contains white space
     */
    public AgingValuedItem(String name, String description, int value, int life) {
        super(name, description, value);
    }
}
