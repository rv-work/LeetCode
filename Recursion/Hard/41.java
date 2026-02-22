class Solution {

    // check if we can place Queen at (i, j)
    boolean check(int i, int j, char[][] table) {

        int n = table.length;

        // check column
        for (int r = 0; r < i; r++) {
            if (table[r][j] == 'Q') return false;
        }

        // upper-left diagonal
        for (int r = i - 1, c = j - 1; r >= 0 && c >= 0; r--, c--) {
            if (table[r][c] == 'Q') return false;
        }

        // upper-right diagonal
        for (int r = i - 1, c = j + 1; r >= 0 && c < n; r--, c++) {
            if (table[r][c] == 'Q') return false;
        }

        return true;
    }

    // recursion on row i
    void res(List<List<String>> ans, char[][] table, int i, int n) {

        if (i == n) {
            List<String> li = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                StringBuilder row = new StringBuilder();
                for (int c = 0; c < n; c++) {
                    row.append(table[r][c]);
                }
                li.add(row.toString());
            }
            ans.add(li);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (check(i, j, table)) {
                table[i][j] = 'Q';
                res(ans, table, i + 1, n);
                table[i][j] = '.';
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {

        char[][] table = new char[n][n];
        for (int r = 0; r < n; r++) Arrays.fill(table[r], '.');

        List<List<String>> ans = new ArrayList<>();

        res(ans, table, 0, n);

        return ans;
    }
}








class Solution {

    void solve(int row, int n, char[][] board,
               Set<Integer> cols,
               Set<Integer> diag1,
               Set<Integer> diag2,
               List<List<String>> ans) {

        if (row == n) {
            List<String> curr = new ArrayList<>();
            for (char[] r : board) curr.add(new String(r));
            ans.add(curr);
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row + col;            // major diagonal (/)
            int d2 = row - col;            // minor diagonal (\)

            // if any attack present → skip
            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2))
                continue;

            // place queen
            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);

            // next row
            solve(row + 1, n, board, cols, diag1, diag2, ans);

            // backtrack
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();      // row + col
        Set<Integer> diag2 = new HashSet<>();      // row - col

        List<List<String>> ans = new ArrayList<>();

        solve(0, n, board, cols, diag1, diag2, ans);

        return ans;
    }
}