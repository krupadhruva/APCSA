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
    }
}
