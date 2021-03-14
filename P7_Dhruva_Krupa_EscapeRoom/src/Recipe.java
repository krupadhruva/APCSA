import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class Recipe {
    /** The ingredients required for this recipe */
    private List<String> ingredients;

    /** Whether or not the order of the items listed in the recipe matters */
    private boolean orderMatters;

    /**
     * Creates a recipe with the given orderMatters and list of ingredients. Recipes must contain at
     * least two ingredients and each ingredient must be unique.
     *
     * @param orderMatters whether the order the ingredients are listed in matters
     * @param ingredients the list of ingredients
     * @throws IllegalArgumentException if the recipe does not contain at least two ingredients or
     *     if the names are not unique
     */
    public Recipe(boolean orderMatters, String... ingredients) {
        if (ingredients.length < 2) {
            throw new IllegalArgumentException("Recipes must have at least two ingredients");
        }
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
        if (new HashSet<String>(this.ingredients).size() < this.ingredients.size()) {
            throw new IllegalArgumentException(
                    "Each item name in a recipe must be unique. Ingredients: " + this.ingredients);
        }
        this.orderMatters = orderMatters;
    }

    public abstract void combineInRoom(Room room);

    /**
     * Returns true if the names of the items match the ingredients (including the order if it
     * matters) and false otherwise. Each item name in a recipe must be unique and each item in a
     * room must have a unique name, so we don't need to worry about multiple items or ingredients
     * with the same name. The items must be exactly the correct items with no extra items though.
     *
     * @param items the list of items to compare with the recipe ingredients
     * @return true if the names of the items match the ingredients (including the order if it
     *     matters) and false otherwise
     */
    public boolean matchesItems(List<Item> items) {
        // TODO: Implement the method as described in the javadoc comment above

        // Ensure unique items
        HashSet<String> unique = new HashSet<>(items.size());
        for (Item item : items) {
            if (unique.contains(item.getName())) {
                return false;
            }

            unique.add(item.getName());
        }

        if (orderMatters()) {
            // Find the item preserving the order
            int previousFoundIndex = 0;

            for (Item item : items) {
                int pos = ingredients.indexOf(item.getName());
                //  No new item should be found before the previously found item
                // If item is not found, indexOf() returns -1 and that will be less than
                // value of previousFoundIndex
                if (pos < previousFoundIndex) {
                    return false;
                }

                previousFoundIndex = pos;
            }

            return true;
        }

        return ingredients.containsAll(unique);
    }

    /**
     * removes the ingredients in this recipe from the given room. The combineInRoom method may call
     * this if the items are supposed to be consumed in the recipe.
     *
     * @param room the room to remove the items from
     */
    public void removeIngredientsFromRoom(Room room) {
        for (String ingredient : ingredients) {
            room.remove(room.getItem(ingredient));
        }
    }

    /**
     * Returns a list of the ingredients in this recipe.
     *
     * @return a list of the ingredients in this recipe.
     */
    protected List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Returns whether or not the order of the items matters in the recipe
     *
     * @return whether or not the order of the items matters in the recipe
     */
    public boolean orderMatters() {
        return orderMatters;
    }
}
