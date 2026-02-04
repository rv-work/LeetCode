class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int or = 0;
        int min = Integer.MAX_VALUE;
        int[] cnt = new int[32];
        
        int l = 0;
        for (int r = 0; r < n; r++) {

            // Add nums[r]
            or |= nums[r];
            for (int i = 0; i < 32; i++) {
                cnt[i] += ((nums[r] >> i) & 1);
            }

            // Shrink window
            while (l <= r && or >= k) {
                min = Math.min(min, r - l + 1);

                // Remove nums[l]
                for (int i = 0; i < 32; i++) {
                    int bit = (nums[l] >> i) & 1;
                    cnt[i] -= bit;
                    if (cnt[i] == 0) {
                        or &= ~(1 << i); // turn that bit off
                    }
                }
                l++;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
