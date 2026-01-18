class Solution {

    int rec(int[] prices, int idx, int canBuy, int fee , int[][]dp) {

        if (idx >= prices.length) return 0;

        if(dp[idx][canBuy] != -1) return dp[idx][canBuy];

        if (canBuy == 1) {

            int buy = -prices[idx] - fee + rec(prices, idx + 1, 0, fee , dp);
            int notBuy = rec(prices, idx + 1, 1, fee , dp);
            dp[idx][canBuy] =  Math.max(buy, notBuy);

        } else {

            int sell = prices[idx]  + rec(prices, idx + 1, 1, fee , dp);
            int notSell = rec(prices, idx + 1, 0, fee , dp);
            dp[idx][canBuy] =  Math.max(sell, notSell);

        }


        return dp[idx][canBuy];
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp[][] = new int[n+1][2];

       
       for(int idx = n-1; idx>=0; idx--){
        for(int canBuy = 1; canBuy >=0; canBuy--){

            if (canBuy == 1) {
            int buy = -prices[idx] - fee + dp[idx + 1][0];
            int notBuy = dp[idx + 1][1];
            dp[idx][canBuy] =  Math.max(buy, notBuy);

            } else {
                int sell = prices[idx]  + dp[idx + 1][1];
                int notSell = dp[idx + 1][0];
                dp[idx][canBuy] =  Math.max(sell, notSell);
    
            }
        }
       }

       return dp[0][1];
       
    }
}
