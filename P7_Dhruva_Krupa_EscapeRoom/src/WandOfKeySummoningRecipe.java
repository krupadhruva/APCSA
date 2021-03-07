public class WandOfKeySummoningRecipe extends Recipe {
    private static final String[] INGREDIENTS = {
        "runed_stick", "phoenix_feather", "sapphire", "unicorn_tears"
    };

    public WandOfKeySummoningRecipe() {
        super(false, INGREDIENTS);
    }

    @Override
    public void combineInRoom(Room room) {
        room.add(new WandOfKeySummoning("summon_key", "Creates a golden key"));
        removeIngredientsFromRoom(room);
    }
}
