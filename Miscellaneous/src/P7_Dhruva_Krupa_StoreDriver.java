/*
 * Name: Krupa Dhruva
 * Date: January 31, 2021
 * Period: 7
 * Time Taken: 45 minutes
 *
 * Lab Reflection:
 * This lab involved building on topics we had covered earlier. Reading from file
 * using a Scanner and merge sort. Adding error handling when opening a file
 * and on potentially encountering wrong data in the file required additional thought.
 *
 * I now understand the importance of making algorithms work on abstractions like
 * operating on a list of Comparable instead of more concrete types like Integer
 * or String. This makes algorithm implementations more reusable.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class P7_Dhruva_Krupa_StoreDriver {
    public static void main(String[] args) {
        Store s = new Store("file50.txt");
        s.sort();
        s.displayStore();
    }
}

class Item implements Comparable<Item> {
    private final int myId;
    private final int myInv;

    /**
     * Constructor for the Item object
     *
     * @param id id value
     * @param inv inventory value
     */
    public Item(int id, int inv) {
        myId = id;
        myInv = inv;
    }

    /**
     * Gets the id attribute of the Item object
     *
     * @return The id value
     */
    public int getId() {
        return myId;
    }

    /**
     * Gets the inv attribute of the Item object
     *
     * @return The inv value
     */
    public int getInv() {
        return myInv;
    }

    /**
     * Compares this item to another item based on id number. Returns the difference between this
     * item's id and the other item's id. A difference of zero means the items' ids are equal in
     * value.
     *
     * @param other Item object to compare to
     * @return positive int if myId > other.myId 0 if myId == other.myId negative int if myId <
     *     other.myId
     */
    public int compareTo(Item other) {
        return myId - other.myId;
    }

    /**
     * Compares the Item to the specified object
     *
     * @param other Object Item object to compare to
     * @return true if equal, false otherwise
     */
    public boolean equals(Item other) {
        return compareTo(other) == 0 && myInv == other.myInv;
    }

    /**
     * Overrides the default toString() of Object. Returns a String representation of this object.
     * It's up to you exactly what this looks like.
     */
    public String toString() {
        return String.format("id=%d,inv=%d", myId, myInv);
    }
}

class Store {

    private final ArrayList<Item> myStore = new ArrayList<Item>();

    /**
     * Creates a Store object from data stored in the given file name
     *
     * @param fName name of the file containing id/inv pairs of data
     */
    public Store(String fName) {
        loadFile(fName);
    }

    /**
     * Reads a file containing id/inv data pairs one pair per line.
     *
     * @param inFileName name of file containing id/inv pairs of data
     */
    private void loadFile(String inFileName) {
        int line = 0;
        File input = new File(inFileName);
        try {
            Scanner scan = new Scanner(input);

            try {
                while (scan.hasNext()) {
                    ++line;
                    int id = scan.nextInt();
                    if (!scan.hasNext()) {
                        break;
                    }

                    int inv = scan.nextInt();

                    myStore.add(new Item(id, inv));
                }
            } catch (InputMismatchException e) {
                System.err.printf("error: invalid entry \"%s\" at line %d%n", scan.next(), line);
                System.exit(-1);
            } finally {
                scan.close();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("error: file \"%s\" not found%n", inFileName);
            System.exit(-1);
        }
    }

    /** Prints the store contents in the format shown below Line # Id Inv 1 184 14 2 196 60 */
    public void displayStore() {
        int line = 0;
        System.out.printf("%15s%10s%n", "Id", "Inv");
        for (Item itm : myStore) {
            System.out.printf("%5d%10d%10d%n", ++line, itm.getId(), itm.getInv());
            if (line % 10 == 0) {
                System.out.println();
            }
        }
    }

    /** Sorts the store ArrayList using recursive mergesort */
    public void sort() {
        mergeSort(myStore, 0, myStore.size() - 1);
    }

    private void merge(ArrayList<Item> a, int first, int mid, int last) {
        for (int ii = first; ii <= mid && mid < last; ++ii) {
            final Item self = a.get(ii);

            final Item other = a.get(mid + 1);

            // If entry in greater than first entry in other half of list,
            // move the entry in other half above current entry
            // This will result in advancing cursor and end of top half (mid)
            // by 1 since we have inserted an entry above the current position
            if (self.compareTo(other) > 0) {
                a.remove(mid + 1);
                a.add(ii, other);
                ++mid;
            }
        }
    }

    /**
     * Recursive mergesort of an ArrayList of Items
     *
     * @param a reference to an ArrayList of Items to be sorted
     * @param first starting index of range of values to be sorted
     * @param last ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList<Item> a, int first, int last) {
        // Boundary condition
        if (first >= last) {
            return;
        }

        // Find the index of mid point
        int mid = first + (last - first) / 2;

        // Left part of the list
        mergeSort(a, first, mid);

        // Right part of the list
        mergeSort(a, mid + 1, last);

        // Merge the 2 halves
        merge(a, first, mid, last);
    }
}
