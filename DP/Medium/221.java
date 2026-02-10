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







class Solution {

    int res(int i, int j, char[][] matrix, int[][] dp) {

        int n = matrix.length;
        int m = matrix[0].length;

        // Out of bounds
        if (i >= n || j >= m) return 0;

        // Already computed
        if (dp[i][j] != -1) return dp[i][j];

        // If current is 0, cannot form square
        if (matrix[i][j] == '0') {
            dp[i][j] = 0;
            return 0;
        }

        // Recursive calls:
        int right = res(i, j + 1, matrix, dp);
        int down = res(i + 1, j, matrix, dp);
        int diag = res(i + 1, j + 1, matrix, dp);

        // current cell contributes +1
        int best = 1 + Math.min(right, Math.min(down, diag));

        dp[i][j] = best;
        return best;
    }

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];  // 0 = uncomputed
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = 0; 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, res(i, j, matrix, dp));
            }
        }

        return ans * ans;     // return AREA
    }

   
}














class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int maxSide = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1; // Boundary handling
                    } else {
                        // Min of (Top, Left, Top-Left) + 1
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide; // Area return karna hai
    }
}

// ye kaam krta hai kyunki 

// let say 

// 0 0
// 0 ?  =====> is case me .min = 0 so .. 1 aayega ok ..

// 1 0
// 0 ?  =====> is case me .. min = 0... 1 aayega ok ..

// 1 1
// 0 ?  =====> is case me .. min = 0... 1 aayega ok ..

// 1 1
// 1 ?  =====> is case me .. min = 0... 2 aayega ok ..


// agar koi 1 bhi zero hai matlab vo bn nhi payeag to vo 0+1 = 1 hi dega sirf khud ko 

// ye 2 sirf aur sirf tabhi hoga sab uske teeno 1 honge which is also fine na .... ki 2 ka bn skta for sure.... thats it/.....

// this is how/..



// If even one of the neighbors is 0, you cannot extend the square, so dp becomes 1 only.
// To get 2, all three must be ≥ 1.

// Exactly true.

// So the formula naturally enforces this rule:

// If any side is broken: you stay at 1

// If all are strong: you grow +1