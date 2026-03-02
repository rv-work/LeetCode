class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int zeroCount = 0;
        int product = 1;

        for (int x : nums) {
            if (x == 0) zeroCount++;
            else product *= x;
        }

        int ans[] = new int[n];

        // Case 1: More than one zero → all products = 0
        if (zeroCount > 1) {
            return ans; // all 0s by default
        }

        // Case 2: Exactly one zero
        if (zeroCount == 1) {
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    ans[i] = product;  // only the zero index gets product
                } else {
                    ans[i] = 0;
                }
            }
            return ans;
        }

        // Case 3: No zero → normal division logic
        for (int i = 0; i < n; i++) {
            ans[i] = product / nums[i];
        }

        return ans;
    }
}