/*
 * Name: Krupa Dhruva
 * Date: September 20, 2020
 * Period: 7
 * Time Taken: 30 minutes
 *
 * Lab Reflection:
 */

public class P7_Dhruva_Krupa_Taxes {

    private static final double PERCENTAGE_FEDERAL_TAX = 15.4;
    private static final double PERCENTAGE_FICA_TAX = 7.75;
    private static final double PERCENTAGE_STATE_TAX = 4.0;

    private double hoursWorked;
    private double hourlyPay;

    /** Default constructor initializing variables */
    public P7_Dhruva_Krupa_Taxes() {
        this(0.0, 0.0);
    }

    /**
     * Custom constructor taking required data
     *
     * @param hoursWorked Total hours worked
     * @param hourlyPay Pay per hour or hourly rate
     */
    public P7_Dhruva_Krupa_Taxes(double hoursWorked, double hourlyPay) {
        setHoursWorked(hoursWorked);
        setHourlyPay(hourlyPay);
    }

    /**
     * Validate input and set hours worked
     *
     * @param hoursWorked Total hours worked (>=0)
     */
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked >= 0.0) {
            this.hoursWorked = hoursWorked;
        }
    }

    /**
     * Validate input and set hourly pay/rate
     *
     * @param hourlyPay Per hour pay/rate (>=0)
     */
    public void setHourlyPay(double hourlyPay) {
        if (hourlyPay >= 0.0) {
            this.hourlyPay = hourlyPay;
        }
    }

    /**
     * Get federal tax in percent
     *
     * @return Federal tax in percent
     */
    public static double getPercentageFederalTax() {
        return P7_Dhruva_Krupa_Taxes.PERCENTAGE_FEDERAL_TAX;
    }

    /**
     * Get FICA or social security tax in percent
     *
     * @return FICA tax in percent
     */
    public static double getPercentageFicaTax() {
        return P7_Dhruva_Krupa_Taxes.PERCENTAGE_FICA_TAX;
    }

    /**
     * Get state tax in percent
     *
     * @return State tax in percent
     */
    public static double getPercentageStateTax() {
        return P7_Dhruva_Krupa_Taxes.PERCENTAGE_STATE_TAX;
    }

    /**
     * Get total hours worked
     *
     * @return Total hours worked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Get hourly pay/rate
     *
     * @return Hourly pay/rate
     */
    public double getHourlyPay() {
        return hourlyPay;
    }

    /**
     * Get total gross pay (pay before tax deductions)
     *
     * @return Gross pay
     */
    public double getGrossPay() {
        return hoursWorked * hourlyPay;
    }

    /**
     * Get federal tax component on gross pay
     *
     * @return Federal tax component
     */
    public double getFederalTax() {
        return getGrossPay() * getPercentageFederalTax() / 100.0;
    }

    /**
     * Get FICA or social security tax component on gross pay
     *
     * @return FICA tax component
     */
    public double getFICATax() {
        return getGrossPay() * getPercentageFicaTax() / 100.0;
    }

    /**
     * Get state tax component on gross pay
     *
     * @return State tax component
     */
    public double getStateTax() {
        return getGrossPay() * getPercentageStateTax() / 100.0;
    }

    /**
     * Get net pay after deducting taxes from gross pay
     *
     * @return Net pay
     */
    public double getNetPay() {
        return getGrossPay() - getFederalTax() - getFICATax() - getStateTax();
    }

    /**
     * Print the tax details
     *
     * <p>Note: Prints 2 decimals using format specifiers
     */
    public void printTaxes() {
        System.out.println(
                String.format(
                        "Hours worked: %.2f%n"
                                + "Hourly rate: %.2f%n"
                                + "%nGross pay: %.2f%n"
                                + "%nFederal Tax (%.2f%%): %.2f%n"
                                + "FICA Tax (%.2f%%): %.2f%n"
                                + "State Tax (%.2f%%): %.2f%n"
                                + "%nNet pay: %.2f",
                        getHoursWorked(),
                        getHourlyPay(),
                        getGrossPay(),
                        getPercentageFederalTax(),
                        getFederalTax(),
                        getPercentageFicaTax(),
                        getFICATax(),
                        getPercentageStateTax(),
                        getStateTax(),
                        getNetPay()));
    }

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
