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









import java.util.Arrays;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int total = 0;
        for (int x : nums) total += x;

        // If target is impossible to reach
        if (target > total || target < -total) return 0;

        int offset = total;               // shift negative indices
        int range = 2 * total + 1;        // from -total to +total

        int[][] dp = new int[n + 1][range];

        // Base case: if idx == n, target 0 counts as 1 way
        dp[n][offset] = 1;

        // Bottom-up
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int t = -total; t <= total; t++) {

                int ways = 0;

                // +nums[idx]  → means next target is t + nums[idx]
                int tPlus = t + nums[idx];
                if (tPlus >= -total && tPlus <= total) {
                    ways += dp[idx + 1][tPlus + offset];
                }

                // -nums[idx] → means next target is t - nums[idx]
                int tMinus = t - nums[idx];
                if (tMinus >= -total && tMinus <= total) {
                    ways += dp[idx + 1][tMinus + offset];
                }

                dp[idx][t + offset] = ways;
            }
        }

        return dp[0][target + offset];
    }
}


