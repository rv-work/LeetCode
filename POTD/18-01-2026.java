class Solution {

    boolean check(int[][] grid, int k, int m, int n) {

        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {

                int target = 0;

                // first row sum as target
                for (int c = j; c < j + k; c++) {
                    target += grid[i][c];
                }

                // check all rows
                for (int r = i; r < i + k; r++) {
                    int sum = 0;
                    for (int c = j; c < j + k; c++) {
                        sum += grid[r][c];
                    }
                    if (sum != target) {
                        target = -1;
                        break;
                    }
                }
                if (target == -1) continue;

                // check all columns
                for (int c = j; c < j + k; c++) {
                    int sum = 0;
                    for (int r = i; r < i + k; r++) {
                        sum += grid[r][c];
                    }
                    if (sum != target) {
                        target = -1;
                        break;
                    }
                }
                if (target == -1) continue;

                // main diagonal
                int d1 = 0;
                for (int t = 0; t < k; t++) {
                    d1 += grid[i + t][j + t];
                }
                if (d1 != target) continue;

                // anti diagonal
                int d2 = 0;
                for (int t = 0; t < k; t++) {
                    d2 += grid[i + t][j + k - 1 - t];
                }
                if (d2 != target) continue;
    
                return true; // valid magic square
            }
        }
        return false;
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int k = Math.min(m, n); k >= 1; k--) {
            if (check(grid, k, m, n)) return k;
        }

        return 1;
    }
}
