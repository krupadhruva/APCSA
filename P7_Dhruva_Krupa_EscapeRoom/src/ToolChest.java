import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToolChest extends PasswordLockedContainer {
    private final List<ValuedItem> tools;
    private final List<String> passwords;

    public ToolChest(String name, String description, String password) {
        super(name, description, password);

        tools = new ArrayList<>();
        passwords = new ArrayList<>();

        passwords.add(password);
        passwords.add("earth");
        passwords.add("rocket");

        tools.add(new ValuedItem("hammer", "Hammer for pounding", 15));
        tools.add(new ValuedItem("spanner", "Spanner for nuts and bolts", 15));
        tools.add(new ValuedItem("bolts", "Collection of bolts", 20));
        tools.add(new ValuedItem("glue", "Super glue to bind anything", 20));
        tools.add(new ValuedItem("generator", "Power generator", 25));
        tools.add(new ValuedItem("coolant", "Coolant for cutting metals", 15));
        tools.add(new ValuedItem("gloves", "Hand gloves", 10));
        tools.add(new ValuedItem("fan", "Exhaust fan", 30));
        tools.add(new ValuedItem("gears", "Gears for machines", 25));
        tools.add(new ValuedItem("time_60s", "60s is money, trade it for anything useful", 60));
        tools.add(new ValuedItem("time_120s", "120s is money, trade it for anything useful", 120));

        addRandomTools(4);
    }

    private void addRandomTools(int count) {
        Collections.shuffle(tools);
        for (int ii = 0; ii < count && ii < tools.size(); ++ii) {
            add(tools.get(ii));
        }
    }

    @Override
    protected void onPasswordSuccess() {
        super.onPasswordSuccess();

        // Let us reward on unlocking tool chest
        Room room = getRoom();
        if (room instanceof RocketLab) {
            RocketLab lab = (RocketLab) room;
            lab.addTime(Duration.ofSeconds(lab.getRandom().nextInt(300 - 30) + 30));
        }

        for (Item item : getContents()) {
            if (item instanceof ValuedItem) {
                ValuedItem valuedItem = (ValuedItem) item;
                valuedItem.setOwned(true);
            }

            // Add item to room only if not present
            if (room.getItem(item.getName()) == null) {
                room.add(item);
            }

            remove(item);
        }

        lock();
        addRandomTools(3);
        Collections.shuffle(passwords);
        setPassword(passwords.get(0));
    }
}
