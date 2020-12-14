import java.util.Arrays;

public class P7_CodingBat_Arrays {
    public int[] middleWay(int[] a, int[] b) {
        int[] result = {a[1], b[1]};
        return result;
    }

    public int[] makeEnds(int[] nums) {
        int[] results = {nums[0], nums[nums.length - 1]};
        return results;
    }

    public boolean has23(int[] nums) {
        return (nums[0] == 2 || nums[0] == 3 || nums[1] == 2 || nums[1] == 3);
    }

    public boolean no23(int[] nums) {
        return !(nums[0] == 2 || nums[0] == 3 || nums[1] == 2 || nums[1] == 3);
    }

    public int[] makeLast(int[] nums) {
        int[] results = new int[nums.length * 2];
        results[results.length - 1] = nums[nums.length - 1];
        return results;
    }

    public boolean double23(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        return (nums[0] == nums[1] && (nums[0] == 2 || nums[0] == 3));
    }

    public boolean unlucky1(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        if (nums[0] == 1 && nums[1] == 3) {
            return true;
        }

        if (nums[1] == 1 && nums[2] == 3) {
            return true;
        }

        if (nums[nums.length - 2] == 1 && nums[nums.length - 1] == 3) {
            return true;
        }

        return false;
    }

    public int[] make2(int[] a, int[] b) {
        int[] results = {0, 0};
        int filled = 0;

        for (int ii = 0; ii < a.length && filled < results.length; ++ii) {
            results[filled++] = a[ii];
        }

        for (int ii = 0; ii < b.length && filled < results.length; ++ii) {
            results[filled++] = b[ii];
        }

        return results;
    }

    public int[] front11(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) {
            int[] result = new int[0];
            return result;
        }

        if (a.length == 0) {
            int[] result = {b[0]};
            return result;
        }

        if (b.length == 0) {
            int[] result = {a[0]};
            return result;
        }

        int[] result = {a[0], b[0]};
        return result;
    }

    public boolean modThree(int[] nums) {
        boolean isEven = false;
        int count = 0;

        for (int val : nums) {
            if (val % 2 == 0) {
                if (isEven) {
                    ++count;
                } else {
                    isEven = true;
                    count = 1;
                }
            } else {
                if (!isEven) {
                    ++count;
                } else {
                    isEven = false;
                    count = 1;
                }
            }

            if (count == 3) {
                return true;
            }
        }

        return false;
    }

    public boolean haveThree(int[] nums) {
        int count = 0;
        boolean wasThree = false;

        for (int val : nums) {
            if (val == 3) {
                if (wasThree) {
                    return false;
                } else {
                    wasThree = true;
                    ++count;
                }
            } else {
                wasThree = false;
            }
        }

        return count == 3;
    }

    public int[] post4(int[] nums) {
        int[] out = {};
        for (int cc = nums.length - 1; cc >= 0; --cc) {
            if (nums[cc] == 4) {
                out = new int[nums.length - cc - 1];
                for (int yy = cc + 1, idx = 0; yy < nums.length; ++yy, ++idx) {
                    out[idx] = nums[yy];
                }

                break;
            }
        }

        return out;
    }

    public int[] notAlone(int[] nums, int val) {
        int[] out = nums.clone();

        if (nums.length < 3) {
            return out;
        }

        for (int xx = 1; xx < nums.length - 1; ++xx) {
            if (nums[xx - 1] != val && nums[xx] == val && nums[xx + 1] != val) {
                out[xx] = Math.max(nums[xx - 1], nums[xx + 1]);
            }
        }

        return out;
    }

    // Note: Fill non-zeros from back so that 0s are all at front (new array is initialized to 0)
    public int[] zeroFront(int[] nums) {
        int[] out = new int[nums.length];
        for (int xx = 0, idx = nums.length; xx < nums.length; ++xx) {
            if (nums[xx] != 0) {
                out[--idx] = nums[xx];
            }
        }

        return out;
    }

    // Note: Very similar to above zeroFront()
    public int[] withoutTen(int[] nums) {
        int[] out = new int[nums.length];
        for (int xx = 0, idx = 0; xx < nums.length; ++xx) {
            if (nums[xx] != 10) {
                out[idx++] = nums[xx];
            }
        }

        return out;
    }

    // https://codingbat.com/prob/p187050
    // Since we need the max odd on right, traverse from end to start
    public int[] zeroMax(int[] nums) {
        int[] out = nums.clone();
        int maxOdd = 0;
        for (int xx = nums.length - 1; xx >= 0; --xx) {
            if (nums[xx] == 0 && maxOdd != 0) {
                out[xx] = maxOdd;
            }

            if (nums[xx] % 2 != 0) {
                maxOdd = Math.max(maxOdd, nums[xx]);
            }
        }

        return out;
    }

    // Return an array that contains the exact same numbers as the given array,
    // but rearranged so that all the even numbers come before all the odd numbers.
    // Other than that, the numbers can be in any order. You may modify and return
    // the given array, or make a new array.
    public int[] evenOdd(int[] nums) {
        int[] out = new int[nums.length];

        // Note: Fill array from both ends depending on even/odd
        for (int cc = 0, ee = 0, oo = nums.length - 1; cc < nums.length; ++cc) {
            if (nums[cc] % 2 == 0) {
                out[ee++] = nums[cc];
            } else {
                out[oo--] = nums[cc];
            }
        }

        return out;
    }

    public String[] fizzBuzz(int start, int end) {
        String[] out = new String[end - start];
        for (int cc = start, idx = 0; cc < end; ++cc, ++idx) {
            if (cc % 3 == 0 && cc % 5 == 0) {
                out[cc - start] = "FizzBuzz";
            } else if (cc % 3 == 0) {
                out[cc - start] = "Fizz";
            } else if (cc % 5 == 0) {
                out[cc - start] = "Buzz";
            } else {
                out[cc - start] = String.valueOf(cc);
            }
        }

        return out;
    }

    // Consider the leftmost and righmost appearances of some value in an array.
    // We'll say that the "span" is the number of elements between the two inclusive.
    // A single value has a span of 1. Returns the largest span found in the given array.
    // (Efficiency is not a priority.)
    //
    //    maxSpan([1, 2, 1, 1, 3]) → 4
    //    maxSpan([1, 4, 2, 1, 4, 1, 4]) → 6
    //    maxSpan([1, 4, 2, 1, 4, 4, 4]) → 6

    public int maxSpan(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int maxSpan = 1;
        for (int ii = 0; ii < nums.length; ++ii) {
            for (int jj = nums.length - 1; jj > ii; --jj) {
                // Search for same number from end
                if (nums[ii] == nums[jj]) {
                    // Add 1 since the span includes both ends
                    maxSpan = Math.max(maxSpan, jj - ii + 1);
                    break;
                }
            }
        }

        return maxSpan;
    }

    public int[] fix34(int[] nums) {
        int[] fours = new int[nums.length];
        boolean[] needed = new boolean[nums.length];
        for (int cc = 0, idx = 0; cc < nums.length; ++cc) {
            if (nums[cc] == 4) {
                if (cc == 0 || nums[cc - 1] != 3) {
                    fours[idx++] = cc;
                }
            } else if (cc < nums.length - 1 && nums[cc] == 3 && nums[cc + 1] != 4) {
                needed[cc + 1] = true;
            }
        }

        for (int cc = 0, idx = 0; cc < nums.length; ++cc) {
            if (needed[cc]) {
                nums[fours[idx++]] = nums[cc];
                nums[cc] = 4;
            }
        }

        return nums;
    }

    public int[] fix45(int[] nums) {
        int[] fives = new int[nums.length];
        boolean[] needed = new boolean[nums.length];
        for (int cc = 0, idx = 0; cc < nums.length; ++cc) {
            if (nums[cc] == 5) {
                if (cc == 0 || nums[cc - 1] != 4) {
                    fives[idx++] = cc;
                }
            } else if (cc < nums.length - 1 && nums[cc] == 4 && nums[cc + 1] != 5) {
                needed[cc + 1] = true;
            }
        }

        for (int cc = 0, idx = 0; cc < nums.length; ++cc) {
            if (needed[cc]) {
                nums[fives[idx++]] = nums[cc];
                nums[cc] = 5;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        P7_CodingBat_Arrays arr = new P7_CodingBat_Arrays();
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        System.out.println(Arrays.toString(arr.middleWay(a, b)));

        System.out.println(Arrays.toString(arr.makeEnds(a)));

        System.out.println(arr.has23(a));

        System.out.println(arr.no23(a));

        System.out.println(Arrays.toString(arr.makeLast(a)));

        System.out.println(arr.double23(a));

        System.out.println(arr.unlucky1(a));

        System.out.println(Arrays.toString(arr.make2(a, b)));

        int[] a34 = {3, 1, 1, 3, 4, 4};
        System.out.println(Arrays.toString(arr.fix34(a34)));
    }
}
