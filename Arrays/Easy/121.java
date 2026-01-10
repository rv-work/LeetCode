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