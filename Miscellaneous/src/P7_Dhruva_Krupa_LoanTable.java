/*
 * Name: Krupa Dhruva
 * Date: November 8, 2020
 * Period: 7
 * Time Taken: 110 minutes
 *
 * Lab Reflection:
 * It was a pretty straight forward problem but after checking multiple times with the given fomrula,
 * I was still unable to reach the same results shown in the problem description. I'm not entirely
 * sure where I went wrong since I believe that the logic and variables align with the variables
 * stated in the fomrula provided. I would like to work more to find out where I went wrong.
 */
import java.util.Scanner;

public class P7_Dhruva_Krupa_LoanTable {
    private double principal;
    private double lengthYears;
    private double lowIntRate;
    private double highIntRate;

    public P7_Dhruva_Krupa_LoanTable() {
        principal = 0.0;
        lengthYears = 0.0;
        lowIntRate = 0.0;
        highIntRate = 0.0;
    }

    public void dataInput() {
        System.out.printf("Mortgage Problem%n%n");
        Scanner scan = new Scanner(System.in);

        System.out.print("Principal/Amount of loan: ");
        principal = scan.nextDouble();

        System.out.print("The length of the loan in years: ");
        lengthYears = scan.nextDouble();

        System.out.print("A low interest rate in %: ");
        lowIntRate = scan.nextDouble();

        System.out.print("A high interest rate in %: ");
        highIntRate = scan.nextDouble();
    }

    public void printMonthlyPayment() {
        System.out.printf("%25s %25s%n", "Annual Interest", "Rate Monthly Payment");
        for (double annualIntRate = lowIntRate;
                annualIntRate <= highIntRate;
                annualIntRate += 0.25) {
            double k = (annualIntRate / 12.0) / 100.0;
            double n = lengthYears * 12;
            double c = Math.pow(1 + k, n);
            double monthlyPayment = ((principal * k * c) / (c - 1.0));
            System.out.printf("%25.2f %25.2f%n", annualIntRate, monthlyPayment);
        }
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_LoanTable loanTable = new P7_Dhruva_Krupa_LoanTable();
        loanTable.dataInput();
        loanTable.printMonthlyPayment();
    }
}
