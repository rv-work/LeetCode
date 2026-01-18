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










class Solution {

    public int largestMagicSquare(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;

        // Row prefix sums
        int[][] rowSum = new int[R][C];
        for (int r = 0; r < R; r++) {
            rowSum[r][0] = mat[r][0];
            for (int c = 1; c < C; c++) {
                rowSum[r][c] = rowSum[r][c - 1] + mat[r][c];
            }
        }

        // Column prefix sums
        int[][] colSum = new int[R][C];
        for (int c = 0; c < C; c++) {
            colSum[0][c] = mat[0][c];
            for (int r = 1; r < R; r++) {
                colSum[r][c] = colSum[r - 1][c] + mat[r][c];
            }
        }

        int maxSize = 1;

        // Try every cell as top-left
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int maxPossible = Math.min(R - r, C - c);

                // Try bigger squares first
                for (int size = maxPossible; size > maxSize; size--) {
                    if (isMagic(r, c, size, mat, rowSum, colSum)) {
                        maxSize = size;
                        break;
                    }
                }
            }
        }
        return maxSize;
    }

    private boolean isMagic(
            int sr, int sc, int size,
            int[][] mat, int[][] rowSum, int[][] colSum) {

        // Target sum from first row
        int target = rowSum[sr][sc + size - 1]
                   - (sc > 0 ? rowSum[sr][sc - 1] : 0);

        // Check rows
        for (int r = sr; r < sr + size; r++) {
            int sum = rowSum[r][sc + size - 1]
                    - (sc > 0 ? rowSum[r][sc - 1] : 0);
            if (sum != target) return false;
        }

        // Check columns
        for (int c = sc; c < sc + size; c++) {
            int sum = colSum[sr + size - 1][c]
                    - (sr > 0 ? colSum[sr - 1][c] : 0);
            if (sum != target) return false;
        }

        // Main diagonal
        int d1 = 0;
        for (int k = 0; k < size; k++)
            d1 += mat[sr + k][sc + k];
        if (d1 != target) return false;

        // Anti-diagonal
        int d2 = 0;
        for (int k = 0; k < size; k++)
            d2 += mat[sr + size - 1 - k][sc + k];

        return d2 == target;
    }
}