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