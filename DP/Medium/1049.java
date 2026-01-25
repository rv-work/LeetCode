class Solution {

    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for (int s : stones) totalSum += s;

        int n = stones.length;
        int capacity = totalSum / 2;

        // dp[idx][capacity]
        int[][] dp = new int[n][capacity + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        int maxSubsetSum = help(n - 1, capacity, stones, dp);

        return totalSum - 2 * maxSubsetSum;
    }


    int help(int idx, int capacity, int[] stones, int[][] dp) {
        if (idx < 0) return 0;

        if (dp[idx][capacity] != -1) return dp[idx][capacity];

        int notTake = help(idx - 1, capacity, stones, dp);

        int take = 0;
        if (stones[idx] <= capacity) {
            take = stones[idx] + help(idx - 1, capacity - stones[idx], stones, dp);
        }

        dp[idx][capacity] = Math.max(take, notTake);
        return dp[idx][capacity];
    }
}
