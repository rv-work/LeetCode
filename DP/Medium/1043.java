import java.util.Arrays;

class Solution {
    
    int res(int i, int k, int[] arr, int[] dp) {
        int n = arr.length;
        
        if (i >= n) return 0;

        if (dp[i] != -1) return dp[i];

        int maxEle = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        int len = 0;

        for (int j = i; j < Math.min(n, i + k); j++) {
            len++; 
            maxEle = Math.max(maxEle, arr[j]); 
            
            int sum = (len * maxEle) + res(j + 1, k, arr, dp);
            
            maxAns = Math.max(maxAns, sum);
        }

        return dp[i] = maxAns;
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); 
        
        return res(0, k, arr, dp);
    }
}