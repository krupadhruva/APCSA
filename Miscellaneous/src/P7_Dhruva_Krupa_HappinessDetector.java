/*
 * Name: Krupa Dhruva
 * Date: September 30, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Writing the logic was fairly simple but condensing the code took majority of my time.
 * This was a good practice for using logical operator statements and also developing the
 * skill to simplify code. Overall, I thoroughly enjoyed this brain-teaser like problem
 * and how I had to pay attention to details and exceptions. I would love to do more
 * problems like this in the future!
 */

import com.controlStructures.HappinessDetector;
import com.controlStructures.Tester;

public class P7_Dhruva_Krupa_HappinessDetector implements HappinessDetector {
    @Override
    public boolean isHappy(int num, int a, int b) {
        if (num < 0) {
            return false;
        }

        if (num > 30 && num < 54) {
            return true;
        }

        if (num >= 81 && num <= 99 && num % 2 != 0) {
            return true;
        }

        boolean genRule = false;
        if (num % a == 0 && num % b != 0) {
            genRule = true;
        }

        return (num < 16) != genRule;
    }

    public static void main(String[] args) {
        P7_Dhruva_Krupa_HappinessDetector detector = new P7_Dhruva_Krupa_HappinessDetector();
        Tester.runTests(detector);
    }
}
