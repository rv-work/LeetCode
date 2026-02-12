
class Solution {
    public int numTrees(int n) {
        // dp[i] stores the number of unique BSTs with 'i' nodes
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // Empty tree = 1 way
        dp[1] = 1; // Single node = 1 way

        // Calculate for all sizes from 2 up to n
        for (int nodes = 2; nodes <= n; nodes++) {
            // Try making every number 'root' from 1 to 'nodes'
            for (int root = 1; root <= nodes; root++) {
                
                // Left side has (root - 1) nodes
                int left = dp[root - 1];
                
                // Right side has (total nodes - root) nodes
                int right = dp[nodes - root];
                
                // Cartesian product: combination = left * right
                dp[nodes] += left * right;
            }
        }

        return dp[n];
    }
}








class Solution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    int solve(int n, int[] dp) {
        // Base case
        if (n <= 1) return 1;

        // Already computed
        if (dp[n] != -1) return dp[n];

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int left = solve(i - 1, dp);
            int right = solve(n - i, dp);
            sum += left * right;
        }

        return dp[n] = sum;
    }
}





class Solution {
    public int numTrees(int n) {
        long res = 1;

        for (int i = 0; i < n; i++) {
            res = res * 2 * (2 * i + 1) / (i + 2);
        }

        return (int) res;
    }
}
