import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P7_Dhruva_Krupa_SortingTest {

    public static void main(String[] args) {

        Person a = new Person("Wong", 16, 5.7);
        Person b = new Person("Wong", 16, 5.2);
        Person c = new Person("Shuo", 16, 6.1);
        Person d = new Person("Shuo", 14, 5.9);
        Person e = new Person("Datta", 18, 6.2);
        Person f = new Person("Kumar", 12, 4.8);

        Person[] list = {a, b, c, d, e, f};
        bubbleSort(list);

        ArrayList<Integer> arr = new ArrayList<Integer>();
        Collections.addAll(arr, 6, 3, 8, 1, 5);
        System.out.println("Before: " + arr);
        spaceSort(arr);
        System.out.println("After:  " + arr);
    }

    public static void bubbleSort(Person[] arr) {
        for (int ii = 0; ii < arr.length - 1; ++ii) {
            for (int jj = arr.length - 1; jj > ii; --jj) {
                if (arr[jj].compareTo(arr[jj - 1]) < 0) {
                    Person tmp = arr[jj];
                    arr[jj] = arr[jj - 1];
                    arr[jj - 1] = tmp;
                }
            }

            System.out.println(Arrays.toString(arr));
        }
    }

    // Helper Method 1
    // Adds spaces as described in Step 2 of the Space Sort directions
    public static void addSpaces(ArrayList<Integer> list) {
        for (int ii = list.size() - 1, spaces = 1; ii >= 0; --ii, ++spaces) {
            for (int cc = 0; cc < spaces; ++cc) {
                list.add(ii, null);
            }
        }
    }

    // Helper Method 2
    // Removes al blank (null) elements as shown in Step 5 of the Space Sort directions
    public static void removeBlanks(ArrayList<Integer> list) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (var ent : list) {
            if (ent != null) {
                tmp.add(ent);
            }
        }

        list.clear();
        list.addAll(tmp);
    }

    public static void spaceSort(ArrayList<Integer> arr) {
        // Your code here
        addSpaces(arr);

        System.out.println(arr);
        for (int ii = 0; ii < arr.size(); ++ii) {
            Integer firstNum = arr.get(ii);
            if (firstNum == null) {
                continue;
            }

            for (int jj = 0; jj < arr.size(); ++jj) {
                Integer secondNum = arr.get(jj);
                if (secondNum != null) {
                    if (secondNum > firstNum) {
                        arr.set(ii - 1, secondNum);
                        arr.set(jj, null);
                    }
                    break;
                }
            }
        }

        removeBlanks(arr);
    }
}

// Finish this class
class Person implements Comparable<Person> {
    private final String lastName;
    private final int age;
    private final double height;

    public Person(String lastName, int age, double height) {
        this.lastName = lastName;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d, %f)", lastName, age, height);
    }

    @Override
    public int compareTo(Person other) {
        // Compare based on number of vowels
        int selfNumVowels = lastName.length() - lastName.replace("aeiouAEIOU", "").length();
        int otherNumVowels =
                other.lastName.length() - other.lastName.replace("aeiouAEIOU", "").length();
        if (selfNumVowels != otherNumVowels) {
            return selfNumVowels - otherNumVowels;
        }

        // Compare based on height/age ration
        double selfHbyA = age / height;
        double otherHbyA = other.age / other.height;
        int hbya = Double.compare(selfHbyA, otherHbyA);
        if (hbya != 0) {
            return hbya;
        }

        // Compare the decimal portion of height
        return Double.compare(height - (int) height, other.height - (int) other.height);
    }
}
