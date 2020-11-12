/*
 * Name: Krupa Dhruva
 * Date: November 11, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * I had fun doing this lab as I got to work with multiple cases using
 * if-else statements.
 *
 * I learnt a new approach to count a given character's occurrences in
 * a given string. Computing the difference between the original string
 * length and the resulting string length after removing all occurrences
 * of the given character results in the count of a given character in
 * a string.
 */

import java.util.Scanner;

public class P7_Dhruva_Krupa_Grades {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter grades for all academic classes: ");
        String inputGrades = scan.nextLine();

        int count_A = inputGrades.length() - inputGrades.replace("A", "").length();
        int count_B = inputGrades.length() - inputGrades.replace("B", "").length();
        int count_C = inputGrades.length() - inputGrades.replace("C", "").length();
        int count_D = inputGrades.length() - inputGrades.replace("D", "").length();
        int count_F = inputGrades.length() - inputGrades.replace("F", "").length();

        int total_classes = count_A + count_B + count_C + count_D + count_F;
        double gpa =
                ((count_A * 4.0) + (count_B * 3.0) + (count_C * 2.0) + (count_D * 1.0))
                        / total_classes;

        if (count_F == 0 && gpa >= 2.0 && total_classes >= 4) {
            System.out.println("Eligible");
            return;
        }

        if (total_classes < 4) {
            System.out.println("Ineligible, taking less than 4 classes");
        } else if (gpa < 2.0) {
            if (count_F > 0) {
                System.out.println("Ineligible, gpa below 2.0 and has F grade");
            } else {
                System.out.println("Ineligible, gpa below 2.0");
            }
        } else if (count_F > 0) {
            System.out.println("Ineligible, gpa above 2.0 but has F grade");
        }
    }
}
