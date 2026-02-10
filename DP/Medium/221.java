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













class Solution {
    int res(int rs, int cs, int[][] pref) {
        int n = pref.length - 1; 
        int m = pref[0].length - 1;
        int maxSq = 0;

        for (int k = 1; rs + k <= n && cs + k <= m; k++) {
         
            int r2 = rs + k;
            int c2 = cs + k;
            
            int r1 = rs;
            int c1 = cs;


            int totalOnes = pref[r2][c2] - pref[r1][c2] - pref[r2][c1] + pref[r1][c1];

            int req = k * k; 

            if (totalOnes == req) {
                maxSq = Math.max(maxSq, req);
            } else {
               
                break; 
            }
        }
        return maxSq;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        
   
        int[][] pref = new int[n + 1][m + 1];

        //2D Prefix Sum
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i + 1][j + 1] = (matrix[i][j] - '0') 
                                   + pref[i][j + 1] 
                                   + pref[i + 1][j] 
                                   - pref[i][j];
            }
        }

        int maxArea = 0;

        // 3. Check every cell as a top-left corner
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxArea = Math.max(maxArea, res(i, j, pref));
            }
        }

        return maxArea;
    }
}