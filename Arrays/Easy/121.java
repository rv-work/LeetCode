class Solution {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for(int num : prices){
            max = Math.max(max , num-min);
            min = Math.min(min, num);
        }


        return max;
    }
}











class Solution {

    public int maxProfit(int[] prices) {
        return solve(prices, 0, false, false);
    }

    int solve(int[] prices, int idx, boolean bought, boolean sold) {

        // base case
        if (idx == prices.length || sold)
            return 0;

        int profit = 0;

        // option: skip
        profit = solve(prices, idx + 1, bought, sold);

        // option: buy (only if not bought yet)
        if (!bought) {
            profit = Math.max(
                profit,
                -prices[idx] + solve(prices, idx + 1, true, sold)
            );
        }

        // option: sell (only if bought and not sold)
        if (bought && !sold) {
            profit = Math.max(
                profit,
                prices[idx] + solve(prices, idx + 1, false, true)
            );
        }

        return profit;
    }
}

class Solution {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][2];
        return solve(prices, 0, 0, 0, dp);
    }

    int solve(int[] prices, int idx, int bought, int sold, Integer[][][] dp) {

        // base case
        if (idx == prices.length || sold == 1)
            return 0;

        if (dp[idx][bought][sold] != null)
            return dp[idx][bought][sold];

        // option 1: skip
        int profit = solve(prices, idx + 1, bought, sold, dp);

        // option 2: buy (only once)
        if (bought == 0) {
            profit = Math.max(
                profit,
                -prices[idx] + solve(prices, idx + 1, 1, sold, dp)
            );
        }

        // option 3: sell (only once)
        if (bought == 1 && sold == 0) {
            profit = Math.max(
                profit,
                prices[idx] + solve(prices, idx + 1, 0, 1, dp)
            );
        }

        return dp[idx][bought][sold] = profit;
    }
}
