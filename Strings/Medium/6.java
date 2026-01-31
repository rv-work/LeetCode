class Solution {
    public String convert(String s, int numRows) {
        int m = s.length();
        int n = numRows;
        if(n == 1) return s; // s = "AB" , n = 1.. only way is ... "AB"


        char[][] mat = new char[n][m];
        for (char[] row : mat)
            Arrays.fill(row, '#');

        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            while (i < n) {
                if(k == m) break;
                mat[i++][j] = s.charAt(k++);
            }
            i -= 2;
            j++;
            while (i >= 0) {
                if(k == m) break;
                mat[i--][j++] = s.charAt(k++);
            }
            j--;
            i += 2;
            if(k == m) break;
        }

        StringBuilder str = new StringBuilder();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (mat[x][y] != '#') {
                    str.append(mat[x][y]); 
                }
            }
        }

        return str.toString();

    }
}




class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int idx = 0;
        int step = 1; // direction: +1 down, -1 up
        

        for (char c : s.toCharArray()) {
            rows[idx].append(c);

            if (idx == 0) step = 1;               // go down
            else if (idx == numRows - 1) step = -1; // go up

            idx += step;
        }

        // merge all rows
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) ans.append(sb);

        return ans.toString();
    }
}
