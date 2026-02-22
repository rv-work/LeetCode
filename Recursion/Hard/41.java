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