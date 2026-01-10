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
