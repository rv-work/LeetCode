class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int top = (i > 0) ? matrix[i - 1][j] : 0;
                int left = (j > 0) ? matrix[i][j - 1] : 0;
                int diag = (i > 0 && j > 0) ? matrix[i - 1][j - 1] : 0;

                matrix[i][j] = matrix[i][j] + top + left - diag;
            }
        }

        int ans = 0;
        int maxK = Math.min(m, n);

        // Check squares of every size
        for (int k = 1; k <= maxK; k++) {
            int target = k * k;
            for (int i = 0; i + k - 1 < m; i++) {
                for (int j = 0; j + k - 1 < n; j++) {

                    int r2 = i + k - 1;
                    int c2 = j + k - 1;

                    int total = matrix[r2][c2];
                    int up = (i > 0) ? matrix[i - 1][c2] : 0;
                    int left = (j > 0) ? matrix[r2][j - 1] : 0;
                    int diag = (i > 0 && j > 0) ? matrix[i - 1][j - 1] : 0;

                    int sum = total - up - left + diag;

                    if (sum == target) ans++;
                }
            }
        }

        return ans;
    }
}









class Solution {

    int countSquaresAt(int rs, int cs, int[][] pref) {
        int n = pref.length - 1;
        int m = pref[0].length - 1;
        int count = 0;

        for (int k = 1; rs + k <= n && cs + k <= m; k++) {
            int r2 = rs + k, c2 = cs + k;
            int r1 = rs, c1 = cs;

            int sum = pref[r2][c2] - pref[r1][c2] - pref[r2][c1] + pref[r1][c1];
            if (sum == k * k) count++;
            else break;
        }
        return count;
    }

    public int countSquares(int[][] mat) {
        int n = mat.length, m = mat[0].length;

        int[][] pref = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i + 1][j + 1] = mat[i][j] + pref[i][j + 1] + pref[i + 1][j] - pref[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += countSquaresAt(i, j, pref);
            }
        }

        return ans;
    }
}







class Solution {
    public int countSquares(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] dp = new int[n][m];
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                        Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    total += dp[i][j];
                }
            }
        }

        return total;
    }
}










// Ye dp ka formula isliye kaam karta hai kyunki:

// dp[i][j] basically batata hai ki yahan end hone wala
// largest continuous 1s ka square kitne size ka ho sakta hai.

// Ab socho 3 direction check ho rahe hain:
// top (dp[i-1][j])
// left (dp[i][j-1])
// top-left diagonal (dp[i-1][j-1])

// Square extend karne ke liye teeno jagah par
// at least us size ka square possible hona chahiye.

// Warna sirf 1x1 square hi banega.


// Example cases:

// 0 X
// X ?  --> top-left zero, matlab square extend nahi ho sakta → dp = 1

// 1 0
// X ?  --> left zero → extend nahi → dp = 1

// 1 1
// 0 ?  --> top zero → extend nahi → dp = 1

// 1 1
// 1 ?  --> ab top, left, top-left teeno >= 1
//          matlab yahan ek 2x2 ka square ban sakta hai → dp = 2


// Simple rule:

// ✓ Agar koi bhi neighbor (top, left, top-left) zero hai → square toot gaya → sirf 1x1 milega.
// ✓ Agar teeno >= 1 ho → iska matlab ek perfect square extend ho raha hai → dp = min(...) + 1.

// dp naturally ensure karta hai ki 
// sirf wahi square grow ho jahan continuity maintained ho.
// Warna automatically 1 assign ho jata hai.

// Isliye min(top, left, diag) + 1 perfect logic hai.

