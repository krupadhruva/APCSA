/*
 * Name: Krupa Dhruva
 * Date: September 20, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 * The lab was fairly simple but it was a nice way to practice working with setter and getter methods.
 * I learnt how to avoid code duplication by error handling in setters and resuing them in constructors.
 * I also got to practice working with format specifiers during this lab.
 */

public class P7_Dhruva_Krupa_Driver {
    /**
     * Simple driver for testing
     *
     * @param ignored Command line arguments (not used)
     */
    public static void main(String[] ignored) {
        P7_Dhruva_Krupa_Taxes taxes = new P7_Dhruva_Krupa_Taxes(30, 12.35);
        taxes.printTaxes();
    }
}
