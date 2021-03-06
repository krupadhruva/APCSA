public class UselessItem extends Item {
    public UselessItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        System.out.printf("You cannot use the %s.%n", getName());
    }
}
