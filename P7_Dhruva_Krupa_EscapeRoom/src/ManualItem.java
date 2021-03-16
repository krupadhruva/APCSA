import java.time.Duration;

public class ManualItem extends TextItem {
    private long usage = 10;

    public ManualItem(String name, String description, String text) {
        super(name, description, text);
    }

    @Override
    public void use() {
        Room room = getRoom();
        if (room instanceof RocketLab) {
            RocketLab lab = (RocketLab) room;
            lab.addTime(Duration.ofSeconds(usage * 6));

            if (usage > 1) {
                --usage;
            }
        }

        System.out.printf("we encourage focus on details and you gain!%n%n");
        super.use();
    }
}
