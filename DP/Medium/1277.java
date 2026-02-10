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
