class Solution {
    int res(int rs, int cs, int[][] sum) {
        int n = sum.length;
        int m = sum[0].length;
        int ans = 0; 

        int i = rs; 
        int j = cs;
        
        while(i < n && j < m) {
            int size = i - rs + 1;
            int req = size * size; 

            int val = sum[i][j];

            if (rs > 0) {
                val -= sum[rs - 1][j]; 
            }
            if (cs > 0) {
                val -= sum[i][cs - 1]; 
            }
            if (rs > 0 && cs > 0) {
                val += sum[rs - 1][cs - 1];
            }

            if (val == req) {
                ans = Math.max(ans, size * size);
            } else {
                break; 
            }
            
            i++;
            j++;
        }
        return ans;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;

        int[][] sum = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sum[i][j] = matrix[i][j] - '0'; 
                if (j > 0) {
                    sum[i][j] += sum[i][j - 1];
                }
            }
        }

        for(int j = 0; j < m; j++) {
            for(int i = 1; i < n; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                max = Math.max(max, res(i, j, sum));
            }
        }

        return max;
    }
}