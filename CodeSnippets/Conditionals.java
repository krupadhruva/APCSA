/** Descriptive examples covering various conditionals supported in Java and sample uses */
public class Conditionals {

    public static void main(String[] args) {
        // 'if' based condition check in Java ALWAYS expects a 'boolean' to check
        // 'if' without 'else' is used if we are interested in a single state
        // Ex: If 'not raining', go for a walk
        //     We are not interested in 'else' case when it is 'raining'
        {
            System.out.println("Example use of 'if'");
            int val = 10;
            boolean condition = (val == 10);

            // Notice the boolean 'condition' we use here
            if (condition) {
                System.out.printf("\t(condition) is true%n");
            }

            // 'val == 10' evaluates to a boolean result which is checked by 'if'
            if (val == 10) {
                System.out.printf("\t(val == 10) is true%n");
            }
        }

        // 'if-else' to handle the 2 states of a boolean
        // Use 'if-else' when we are interested in both states
        // Ex: If 'not raining, go for a walk, else (if 'raining'), watch a movie

        // NOTE: 'not' of the 'if' condition will be 'else'
        // 'if' condition and 'else' condition are mutually exclusive. They will never be satisfied
        // at the same point
        {
            System.out.println("Example use of 'if-else'");
            int val = 10;

            if (val == 10) {
                System.out.println("\tif-else: true");
            } else {
                System.out.println("\tif-else: false");
            }

            /*
             * NOTE: In a function, if you are returning in 'if' block, the
             * 'else' block is not required. Code outside the 'if' block will
             * get executed only when you do not enter 'if' block. Since, if
             * you enter 'if' block, you will return from the function
             *
             * The following 2 examples are equivalent:
             *
             * With explicit/redundant 'if-else':
             * public void foo(boolean flag) {
             *      if (flag == true) {
             *          System.out.println("true");
             *          return;
             *      } else {
             *          System.out.println("not true!");
             *      }
             * }
             *
             * Just 'if' with return:
             * public void foo(boolean flag) {
             *      if (flag == true) {
             *          System.out.println("true");
             *          return;
             *      }
             *
             *      System.out.println("not true!");
             * }
             */
        }

        // Chained 'if-else-if-else...' to handle multiple states/conditions and actions
        // Note: Conditions are always evaluated top-down. The first matching condition
        // block is executed. There is no fall-through to continue evaluating
        {
            System.out.println("Example use of 'if-else-if-else... chain'");
            int val = 5;
            if (val == 10) {
                System.out.println("\tif 10: true");
            } else if (val == 5) {
                System.out.println("\tif 5: true");
            } else if (val == 0) {
                System.out.println("\tif 0: true");
            } else {
                System.out.println("\telse: not interested");
            }

            System.out.println("\tCommon call: Since we do not return 'if-else-if-...");
        }

        // Nested 'if-else' to handle sub conditions
        {
            System.out.println("Example use of nested 'if-if-else...'");
            int val = 15;

            // Note: Conditions are always evaluated top-down. The first matching condition
            // block is executed. There is no fall-through to continue evaluating
            if (val > 10) {
                System.out.println("\tif > 10: true");
                if (val == 12) {
                    System.out.println("\tif 12: true");
                } else {
                    System.out.println("\tif other than 12 but greater than 10: true");

                    if (val == 15) {
                        System.out.println("\tif 15: true");
                    }
                }
            } else if (val == 15) {
                System.out.println("\tNote: Will not reach here since 1st condition is true");
            }
        }

        // switch-case based conditional
        // Note: This is very similar to 'if-else-if-else...' chain BUT with 1 major difference
        // By default, there is FALL THROUGH. All cases after a matching case is executed without
        // evaluating the condition UNLESS there is an explicit 'break' to stop the FALLTHROUGH
        {
            System.out.println("Example use of 'switch-case'");
            int val = 10;
            switch (val) {
                case 5:
                    System.out.printf("\tswitch 5: value=%d, fallthrough=%s%n", val, val != 5);
                case 10:
                    // Since val is 10, execution comes here first. We do not have 'break'
                    // and hence, should execute all lines below till it encounters 'break' OR
                    // default cause. There is nothing after 'default' case to execute and marks
                    // the end of 'switch' block
                    System.out.printf("\tswitch 10: value=%d, fallthrough=%s%n", val, val != 10);
                case 20:
                    // Though val is 10, we come here since there is no explicit 'break'
                    System.out.printf("\tswitch 20: value=%d, fallthrough=%s%n", val, val != 20);

                    // Since we have a 'break' here, we break the FALLTHROUGH
                    break;
                case 30:
                    System.out.printf("\tswitch 30: value=%d, fallthrough=%s%n", val, val != 30);
                default:
                    System.out.printf(
                            "\tswitch default: value=%d, fallthrough=%s%n",
                            val, val == 5 || val == 10 || val == 20 || val == 30);
            }
        }
    }
}
