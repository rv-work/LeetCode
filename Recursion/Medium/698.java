class Solution {
    boolean res(int index, int[] nums, int k, int target, int bucket, boolean[] taken) {
        if (k == 1) return true; 

        if (target == 0) {
            // Nayi bucket shuru
            return res(0, nums, k - 1, bucket, bucket, taken);
        }

        for (int i = index; i < nums.length; i++) {
            if (!taken[i] && nums[i] <= target) {
                
                taken[i] = true;
                boolean poss = res(i + 1, nums, k, target - nums[i], bucket, taken);
                if (poss) return true;
                taken[i] = false;

                // --- PRO HACK 1: Empty Bucket Fail ---
                // Agar bucket khali thi, aur pehla hi number aage chalkar fail ho gaya,
                // to aage check karne ka koi fayda nahi, seedha return false!
                if (target == bucket) {
                    return false;
                }

                // --- PRO HACK 2: Duplicate Skip ---
                // Agar current number fail ho gaya, aur agla number bhi bilkul same hai,
                // to wo bhi fail hi hoga. Use skip kar do! (Kyunki array sorted hai)
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        
        // --- PRO HACK 3: Reverse Sorting ---
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        boolean[] taken = new boolean[nums.length];
        
        return res(0, nums, k, sum / k, sum / k, taken);
    }
}