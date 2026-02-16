class Solution {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;

        int pre = 1;
        int suff = 1;

        for (int i = 0; i < n; i++) {
            pre *= nums[i];
            suff *= nums[n - 1 - i];
            ans = Math.max(ans, Math.max(pre, suff));

            if (nums[i] == 0) {

                pre = 1;
            }
            if (nums[n - 1 - i] == 0)
                suff = 1;

        }

        return ans;
    }
}

class Solution {
    int res(int l, int r, int[] nums) {
        if (l == r)
            return nums[l];
        int max = Integer.MIN_VALUE;

        int pr = 1;
        for (int i = l; i <= r; i++) {
            pr *= nums[i];
            max = Math.max(max, pr);
        }

        pr = 1;
        for (int i = r; i >= l; i--) {
            pr *= nums[i];
            max = Math.max(max, pr);
        }

        return max;
    }

    public int maxProduct(int[] nums) {
        List<Integer> zeros = new ArrayList<>();
        zeros.add(-1);
        int n = nums.length;
        if (n == 1)
            return nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                zeros.add(i);
        }
        zeros.add(n);

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < zeros.size() - 1; i++) {
            max = Math.max(max, res(zeros.get(i) + 1, zeros.get(i + 1) - 1, nums));
        }

        return zeros.size() > 2 && max < 0 ? 0 : max;
    }
}

// split by zeros and then in that zone ..... if even -ve's then multiply all ..
// if odd then either skip the first or the last whichever gives max...