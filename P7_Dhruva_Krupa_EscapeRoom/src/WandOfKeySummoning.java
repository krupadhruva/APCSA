public class WandOfKeySummoning extends Item {
    public WandOfKeySummoning(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        System.out.println("Adding a gold key into the room.");
        getRoom().add(new UselessItem("gold_key", "Gold key"));
    }
}
