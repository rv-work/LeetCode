class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') { // empty cell
                    for (char c = '1'; c <= '9'; c++) {

                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (solve(board)) return true; // solved
                            else board[i][j] = '.';        // backtrack
                        }
                    }

                    return false; // no valid number -> backtrack
                }

            }
        }
        return true; // all filled
    }

    boolean isValid(char[][] board, int r, int c, char ch) {
        // row and column check
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == ch) return false; // row
            if (board[i][c] == ch) return false; // col
        }

        // 3x3 box check
        int br = (r / 3) * 3;
        int bc = (c / 3) * 3;

        for (int i = br; i < br + 3; i++) {
            for (int j = bc; j < bc + 3; j++) {
                if (board[i][j] == ch) return false;
            }
        }

        return true;
    }
}