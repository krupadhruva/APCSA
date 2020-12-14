import java.util.*;

public class Database {

    private Customer[] database;
    // No other attributes are allowed

    // Precondition: initialCapacity > 0
    public Database(int initialCapacity) {
        database = new Customer[initialCapacity];
    }

    // Needed to iterate full array
    public int getTotalSize() {
        return database.length;
    }

    public int numGoldMembers() {
        // Your code here
        int count = 0;
        for (Customer customer : database) {
            if (customer != null && customer.isGoldMember()) {
                ++count;
            }
        }

        return count;
    }

    public boolean isSorted() {
        // Your code here
        ArrayList<String> sorted = new ArrayList<>();
        ArrayList<String> lastNames = new ArrayList<>();

        for (Customer customer : database) {
            if (customer != null) {
                sorted.add(customer.getLastName());
                lastNames.add(customer.getLastName());
            }
        }

        sorted.sort(String::compareTo);

        return lastNames.equals(sorted);
    }

    public Customer get(int index) {
        return database[index];
    }
    
    public int size() {
        // Your code here
        int count = 0;
        for (Customer customer : database) {
            if (customer != null) {
                ++count;
            }
        }

        return count;
    }

    public void add(Customer c) {
        // Your code here
        for (int cc = 0; cc < database.length; ++cc) {
            if (database[cc] == null) {
                database[cc] = c;
                return;
            }
        }

        Customer[] arr = new Customer[database.length * 3];
        for (int cc = 0; cc < database.length; ++cc) {
            arr[cc] = database[cc];
        }

        arr[database.length] = c;
        database = arr;
    }
    
    public String toString() {
        return Arrays.toString(database);
    }
}