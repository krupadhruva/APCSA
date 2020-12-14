import java.util.*;

public class MasterCollection {

    ArrayList<Database> master;

    public MasterCollection() {
        master = new ArrayList<Database>();
    }
    
    public void add(Database d) {
        master.add(d);
    }

    public Database allGoldMembers() {
        // Your code here
        ArrayList<Customer> goldMembers = new ArrayList<>();
        for (Database database : master) {
            for (int cc = 0; cc < database.getTotalSize(); ++cc) {
                Customer customer = database.get(cc);
                if (customer != null && customer.isGoldMember()) {
                    goldMembers.add(customer);
                }
            }
        }

        Database result = new Database(goldMembers.size());
        for (Customer customer : goldMembers) {
            result.add(customer);
        }

        return result;
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < master.size(); i++) {
            out += i + ": " + master.get(i) + "\n";
        }
        return out;
    }
}