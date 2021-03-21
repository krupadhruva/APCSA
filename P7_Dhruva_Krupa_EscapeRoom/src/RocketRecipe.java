import java.util.List;

public class RocketRecipe extends Recipe {
    private static final String[] ingredients = {"cone", "body", "fins", "engine"};

    private boolean combined;

    /**
     * Creates a recipe with the given orderMatters and list of ingredients. Recipes must contain at
     * least two ingredients and each ingredient must be unique.
     *
     * @throws IllegalArgumentException if the recipe does not contain at least two ingredients or
     *     if the names are not unique
     */
    public RocketRecipe(Room room) {
        super(false, RocketRecipe.ingredients);
        room.add(
                new ValuedItem(
                        "cone",
                        "The tip of the rocket made of strong heat resistant material",
                        125));
        room.add(
                new ValuedItem(
                        "body",
                        "Slender main portion of the rocket that houses fuel tanks, supplies and"
                                + " equipment",
                        150));
        room.add(new ValuedItem("fins", "Balance and trajectory depends on quality of fins", 100));
        room.add(
                new ValuedItem(
                        "engine",
                        "Powerhouse propelling the rocket in quest to save humanity from"
                                + " destruction",
                        300));
        combined = false;
    }

    @Override
    public void combineInRoom(Room room) {
        for (String name : getIngredients()) {
            Item item = room.getItem(name);
            if (item instanceof ValuedItem && !((ValuedItem) item).isOwned()) {
                System.out.println("cannot combine, some items are not owned by you");
                return;
            }
        }

        removeIngredientsFromRoom(room);
        System.out.println(
                "you have successfully assembled a rocket, bon voyage and remember to blast off!");
        combined = true;

        room.add(
                new UselessItem(
                        "rocket", "The rocket you built, remember to \"blast off\" in time!"));
    }

    // Ensure all matched items are owned and all items required are provided
    public boolean matchesItems(List<Item> items) {
        if (!super.matchesItems(items)) {
            return false;
        }

        List<String> ingredients = getIngredients();
        for (Item item : items) {
            if (item instanceof ValuedItem && !((ValuedItem) item).isOwned()) {
                return false;
            }

            ingredients.remove(item.getName());
        }

        return ingredients.isEmpty();
    }

    public boolean isCombined() {
        return combined;
    }
}
