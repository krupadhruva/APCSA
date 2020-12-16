import java.util.*;

// Note:  If you can't get this to compile, delete the implements... part
public class Customer implements Comparable<Customer> {

    private String firstName;
    private String lastName;
    private int customerID;
    private boolean goldMember;
    
    public Customer(String fName, String lName, int id, boolean isGold) {
        firstName = fName;
        lastName = lName;
        customerID = id;
        goldMember = isGold;
    }

    /* Getters and setters */
    public String getFirstName() { return firstName; };
    public String getLastName() { return lastName; };
    public int getID() { return customerID; };
    public boolean isGoldMember() { return goldMember; };

    @Override
    public int compareTo(Customer o) {
        return this.customerID - o.customerID;
    }

    @Override
    public String toString() {
        return String.format("%c%s%s",
                getFirstName().toLowerCase().charAt(0),
                getLastName(),
                String.valueOf(getID() + "000").substring(0, 3));
    }
}