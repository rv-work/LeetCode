class Solution {
    public boolean canPartitionGrid(int[][] g) {
        int n = g.length;
        int m = g[0].length;

        long row [] = new long [n];
        long col [] = new long [m];
        
        long sum = 0;
        for (int i = 0; i<n; i++){
            for(int j = 0; j < m; j++){
                sum += g[i][j];
            }
        }

        for (int i = 0; i<n; i++){
            long rowSum = 0;
            for(int j = 0; j < m; j++){
                rowSum += g[i][j];
            }
            row[i] = rowSum;
        }
        
        for (int i = 0; i<m; i++){
            long colSum = 0;
            for(int j = 0; j < n; j++){
                colSum += g[j][i];
            }
            col[i] = colSum;
        }



        long pre = 0;
        for(int i = 0; i<n; i++){
            pre += row[i];
            if(sum-pre == pre) return true;
        }
        
        pre = 0;
        for(int i = 0; i<m; i++){
            pre += col[i];
            if(sum-pre == pre) return true;
        }


        return false;
        
    }
}