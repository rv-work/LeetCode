
class Solution {
    
    int solve(int i, int j, int[] nums, int[][] dp) {
        // Base case: Agar bache hi nahi, to 0 coins
        if (i > j) return 0;
        
        if (dp[i][j] != -1) return dp[i][j];
        
        int max = Integer.MIN_VALUE;
        
        // k wo gubbara hai jo is (i to j) range me SABSE LAST me footega
        for (int k = i; k <= j; k++) {
            
            // k ko last me fodne ka reward:
            // (kyunki i se k-1 foot chuke, aur k+1 se j foot chuke,
            // to k ke padosi direct i-1 aur j+1 banenge)
            int coins = nums[i - 1] * nums[k] * nums[j + 1];
            
            // Left aur Right subproblems ko call karo
            int totalCoins = coins + solve(i, k - 1, nums, dp) + solve(k + 1, j, nums, dp);
            
            max = Math.max(max, totalCoins);
        }
        
        return dp[i][j] = max;
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // Step 1: Array ke aage aur peeche '1' laga do
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        newNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }
        
        // Step 2: DP array banao aur -1 se fill karo
        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        // Step 3: Pura array (1 se lekar n tak) solve karne ko bhej do
        return solve(1, n, newNums, dp);
    }
}