class Solution {
    int res(int [] nums , int target , int idx){
        if(idx == nums.length){
            if(target == 0) return 1;
            else return 0;
        }

        int plus = res(nums , target - nums[idx] , idx+1);
        int minus = res(nums , target + nums[idx] , idx+1);

        return plus + minus;

    }
    public int findTargetSumWays(int[] nums, int target) {
        
        return res(nums , target , 0);
    }
}




import java.util.Arrays;

class Solution {
    int res(int[] nums, int target, int idx, int[][] dp) {
        // Base Case: If we processed all numbers
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }

        // Boundary Check: prevent index out of bounds
        // Since sum(nums) <= 1000, any target outside [-1000, 1000] is impossible.
        if (target < -1000 || target > 1000) {
            return 0;
        }

        // Memoization Check
        if (dp[idx][target + 1000] != -1) {
            return dp[idx][target + 1000];
        }

        // Recursive Calls
        // Case 1: Add current number to sum (subtract from target)
        int plus = res(nums, target - nums[idx], idx + 1, dp);
        
        // Case 2: Subtract current number from sum (add to target)
        int minus = res(nums, target + nums[idx], idx + 1, dp);

        // Store and return
        return dp[idx][target + 1000] = plus + minus;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        // Size is 2001 to handle range -1000 to +1000 (offset by +1000)
        int[][] dp = new int[n][2001]; 
        
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        
        return res(nums, target, 0, dp);
    }
}


