// class Solution {

//     int res(int n, int minProfit, int[] group, int[] profit, int idx) {
//         if (idx == group.length) {
//             return minProfit <= 0 ? 1 : 0;
//         }

//         // Base case me n >= 0 check karna zaroori nahi hai — aur reason bahut simple hai:
//         // Reason: n kabhi negative ho hi nahi sakta
//         // Aapka code sirf ek jagah n ko modify karta hai:
//         // if (group[idx] <= n) {
//         //     take = res(n - group[idx], ...);
//         // }

//         // Here you ONLY do recursion if group[idx] <= n.

//         // That means:

//         // You NEVER call recursion with n < 0
//         // n is ALWAYS valid
//         // So n >= 0 check karna base case me redundant hai.

//         int notTake = res(n, minProfit, group, profit, idx + 1);

//         int take = 0;
//         if (group[idx] <= n) {
//             take = res(n - group[idx], Math.max(0, minProfit - profit[idx]), group, profit, idx + 1);
//         }

//         return notTake + take;
//     }

//     public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//         return res(n, minProfit, group, profit, 0);
//     }
// }








class Solution {
    static final int MOD = 1_000_000_007;


    int solve(int idx, int workersLeft, int minProfit, int[] group, int[] profit, int[][][] dp) {

        // if all jobs checked
        if (idx == group.length) {
            return (minProfit <= 0) ? 1 : 0;
        }


        // Base case me n >= 0 check karna zaroori nahi hai — aur reason bahut simple hai:
        // Reason: n kabhi negative ho hi nahi sakta
        // Aapka code sirf ek jagah n ko modify karta hai:
        // if (group[idx] <= n) {
        //     take = res(n - group[idx], ...);
        // }

        // Here you ONLY do recursion if group[idx] <= n.

        // That means:

        // You NEVER call recursion with n < 0
        // n is ALWAYS valid
        // So n >= 0 check karna base case me redundant hai.



        if (dp[idx][workersLeft][minProfit] != -1) {
            return dp[idx][workersLeft][minProfit];
        }

        long ways = 0;

        // ---- NOT TAKE ----
        ways += solve(idx + 1, workersLeft, minProfit, group, profit, dp);

        // ---- TAKE ----
        if (group[idx] <= workersLeft) {
            int newProfit = Math.max(0, minProfit - profit[idx]);
            ways += solve(idx + 1, workersLeft - group[idx], newProfit, group, profit, dp);
        }

        return dp[idx][workersLeft][minProfit] = (int)(ways % MOD);
    }

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;

        // dp[idx][n][minProfit]
        int[][][] dp = new int[m][n + 1][minProfit + 1];

        // initialize dp with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, n, minProfit, group, profit, dp);
    }
}









// class Solution {

//     int res(int n, int minProfit, int[] group, int[] profit, int idx) {
//         if (idx == group.length) {
//             return minProfit <= 0 ? 1 : 0;
//         }

//         // Base case me n >= 0 check karna zaroori nahi hai — aur reason bahut simple hai:
//         // Reason: n kabhi negative ho hi nahi sakta
//         // Aapka code sirf ek jagah n ko modify karta hai:
//         // if (group[idx] <= n) {
//         //     take = res(n - group[idx], ...);
//         // }

//         // Here you ONLY do recursion if group[idx] <= n.

//         // That means:

//         // You NEVER call recursion with n < 0
//         // n is ALWAYS valid
//         // So n >= 0 check karna base case me redundant hai.

//         int notTake = res(n, minProfit, group, profit, idx + 1);

//         int take = 0;
//         if (group[idx] <= n) {
//             take = res(n - group[idx], Math.max(0, minProfit - profit[idx]), group, profit, idx + 1);
//         }

//         return notTake + take;
//     }

//     public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//         return res(n, minProfit, group, profit, 0);
//     }
// }








// class Solution {
//     static final int MOD = 1_000_000_007;


//     int solve(int idx, int workersLeft, int minProfit, int[] group, int[] profit, int[][][] dp) {

//         // if all jobs checked
//         if (idx == group.length) {
//             return (minProfit <= 0) ? 1 : 0;
//         }


//         // Base case me n >= 0 check karna zaroori nahi hai — aur reason bahut simple hai:
//         // Reason: n kabhi negative ho hi nahi sakta
//         // Aapka code sirf ek jagah n ko modify karta hai:
//         // if (group[idx] <= n) {
//         //     take = res(n - group[idx], ...);
//         // }

//         // Here you ONLY do recursion if group[idx] <= n.

//         // That means:

//         // You NEVER call recursion with n < 0
//         // n is ALWAYS valid
//         // So n >= 0 check karna base case me redundant hai.



//         if (dp[idx][workersLeft][minProfit] != -1) {
//             return dp[idx][workersLeft][minProfit];
//         }

//         long ways = 0;

//         // ---- NOT TAKE ----
//         ways += solve(idx + 1, workersLeft, minProfit, group, profit, dp);

//         // ---- TAKE ----
//         if (group[idx] <= workersLeft) {
//             int newProfit = Math.max(0, minProfit - profit[idx]);
//             ways += solve(idx + 1, workersLeft - group[idx], newProfit, group, profit, dp);
//         }

//         return dp[idx][workersLeft][minProfit] = (int)(ways % MOD);
//     }

//     public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//         int m = group.length;

//         // dp[idx][n][minProfit]
//         int[][][] dp = new int[m][n + 1][minProfit + 1];

//         // initialize dp with -1
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 Arrays.fill(dp[i][j], -1);
//             }
//         }

//         return solve(0, n, minProfit, group, profit, dp);
//     }
// }






class Solution {
    static final int MOD = 1_000_000_007;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;

        // dp[w][p] = number of schemes using "w" workers and achieving "p" profit
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;  

        for (int idx = 0; idx < m; idx++) {
            int members = group[idx];
            int earn = profit[idx];

            // iterate BACKWARDS to avoid overwriting states
            for (int w = n; w >= members; w--) {
                for (int p = minProfit; p >= 0; p--) {
                    int newProfit = Math.min(minProfit, p + earn);
                    dp[w][newProfit] = (dp[w][newProfit] + dp[w - members][p]) % MOD;
                }
            }
        }

        long result = 0;

        // total schemes using ANY number of workers but achieving >= minProfit
        for (int w = 0; w <= n; w++) {
            result = (result + dp[w][minProfit]) % MOD;
        }

        return (int) result;
    }
}
