/*
 * Name: Krupa Dhruva
 * Date: October 4, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * It was straight forward and a good practice for using if-ele statement.
 * I learnt swapping variables through the use of a temporary (tmp) variable.
 * In general, this was fairly simple but I enjoy puzzle problems as such.
 */

import java.util.Scanner;

public class P7_Dhruva_Krupa_CheckMail {
    private static final double MAX_PACKAGE_SIZE = 100.0;
    private static final double MAX_PACKAGE_WEIGHT = 70.0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the first dimension of package in inches: ");
        double dim1 = scan.nextDouble();

        System.out.print("Enter the second dimension of package in inches: ");
        double dim2 = scan.nextDouble();
        if (dim2 > dim1) {
            double tmp = dim1;
            dim1 = dim2;
            dim2 = tmp;
        }

        System.out.print("Enter the third dimension of package in inches: ");
        double dim3 = scan.nextDouble();
        if (dim3 > dim1) {
            double tmp = dim1;
            dim1 = dim3;
            dim3 = tmp;
        }

        System.out.print("Enter the weight of package in pounds: ");
        final double weight = scan.nextDouble();

        final boolean tooBig = ((dim1 + (2 * (dim2 + dim3))) > MAX_PACKAGE_SIZE);
        final boolean tooHeavy = (weight > MAX_PACKAGE_WEIGHT);

        if (tooBig && tooHeavy) {
            System.out.println("Package is too large and too heavy.");
        } else if (tooBig) {
            System.out.println("Package is too large.");
        } else if (tooHeavy) {
            System.out.println("Package is too heavy.");
        } else {
            System.out.println("Package is acceptable.");
        }
    }
}
