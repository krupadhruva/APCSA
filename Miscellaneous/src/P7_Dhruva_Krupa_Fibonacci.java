/*
 * Name: Krupa Dhruva
 * Date: October 7, 2020
 * Period: 7
 * Time Taken: 60 minutes
 *
 * Lab Reflection:
 * Overall, this lab offered a lot of practice working with recursive calls.
 * At first, I found this concept to be very confusing and it was hard to
 * solve the in-class problems on peardeck but after reading through the Lesson
 * and taking notes, I was able to break down the logic. This lab, alongside, the
 * peardeck slides, and the worksheets, helped me solidify my understanding of recursion.
 *
 * I used 500 for the Fibonacci method and when I ran my code, I never got the
 * results back (or at least I did not wait long enough).
 */

public class P7_Dhruva_Krupa_Fibonacci {
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    public static int positiveMultiply(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }

        return x + positiveMultiply(x, y - 1);
    }

    public static int anyMultiply(int x, int y) {
        int res = positiveMultiply(Math.abs(x), Math.abs(y));
        if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
            return -res;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 0;
        System.out.printf("Fib(%d) = %d%n", n, fibonacci(n));

        n = 3;
        System.out.printf("Fib(%d) = %d%n", n, fibonacci(n));

        n = 7;
        System.out.printf("Fib(%d) = %d%n", n, fibonacci(n));

        int x = 7;
        int y = 8;
        System.out.printf("%d*%d = %d%n", x, y, anyMultiply(x, y));

        x = 5;
        y = 1;
        System.out.printf("%d*%d = %d%n", x, y, anyMultiply(x, y));

        x = 5;
        y = 0;
        System.out.printf("%d*%d = %d%n", x, y, anyMultiply(x, y));
    }
}
