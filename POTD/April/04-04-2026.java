class Solution {
    public String decodeCiphertext(String et, int n) {
        int m = et.length() / n;
        char[][] mat = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = et.charAt(i * m + j);
            }
        }

        StringBuilder str = new StringBuilder();

        int col = 0;
        while (col < m) {
            int i = 0;
            int j = col;
            while (i < n && j < m) {
                str.append(mat[i][j]);
                i++;
                j++;
            }
            col++;
        }

        return str.toString().stripTrailing();

    }
}