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
