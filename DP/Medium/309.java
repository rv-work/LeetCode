class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int i = n - 1; i >= 0; i--) {

            // canBuy = 1
            dp[i][1] = Math.max(
                    -prices[i] + dp[i + 1][0],
                    dp[i + 1][1]
            );

            // canBuy = 0
            dp[i][0] = Math.max(
                    prices[i] + dp[i + 2][1], // cooldown
                    dp[i + 1][0]
            );
        }

        return dp[0][1];
    }
}
