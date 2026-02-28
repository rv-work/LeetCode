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

                // Agar bucket khali thi, aur pehla hi number aage chalkar fail ho gaya,
                // to aage check karne ka koi fayda nahi, seedha return false!
                if (target == bucket) {
                    return false;
                }

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











class Solution {
    

    boolean res(int index, int[] nums, int k, int target, int bucket, int mask, Boolean[] dp) {
        
        if (k == 1) return true; 

        if (target == 0) {
            // Nayi bucket shuru, index wapas 0
            return res(0, nums, k - 1, bucket, bucket, mask, dp);
        }

        if (dp[mask] != null) {
            return dp[mask];
        }

        for (int i = index; i < nums.length; i++) {
            
            // !taken[i] ko bitmask se check kiya: 
            // (1 << i) ka matlab hai 'i' th position par 1 baaki sab 0. 
            // AND (&) karne par 0 aane ka matlab hai wo bit abhi off (0) hai.
            if ((mask & (1 << i)) == 0 && nums[i] <= target) {
                
                // Naya mask banaya: OR (|) operator bit ko ON (1) kar deta hai
                int newMask = mask | (1 << i);
                
                boolean poss = res(i + 1, nums, k, target - nums[i], bucket, newMask, dp);
                if (poss) {
                    return dp[mask] = true; // State save karke return true
                }

                // Empty bucket fail
                if (target == bucket) {
                    break; // break the loop, aage check mat karo
                }

                //  Skip duplicates
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        // Agar koi bhi combination kaam nahi aaya, to is mask state ko false save kar do
        return dp[mask] = false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        int n = nums.length;
  
        Boolean[] dp = new Boolean[1 << n];
        
        return res(0, nums, k, sum / k, sum / k, 0, dp);
    }
}