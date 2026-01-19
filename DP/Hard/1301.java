class Solution { 

    final int MOD = 1_000_000_007;

    boolean check(int i , int j , int n , List<String> b){
        return i >= 0 && i<n && j>= 0 && j< n && b.get(i).charAt(j) != 'X';
    }

    int getMax(int i, int j,List<String> b , int dpSum[][] ){
      int n = b.size();
      
      if(i == n-1 && j == n-1) return 0; 

      if(dpSum[i][j] != -1) return dpSum[i][j];

      int right = -1;
      int down = -1;
      int diag = -1;
      if(check(i+1 , j , n , b)) down = getMax(i+1 , j , b , dpSum);
      if(check(i , j+1 , n , b)) right = getMax(i , j+1 , b , dpSum);
      if(check(i+1 , j+1 , n , b)) diag = getMax(i+1 , j+1 , b , dpSum);

      if(right == -1 && down == -1 && diag == -1) return dpSum[i][j] = -1;

      int score = b.get(i).charAt(j) - '0';
      if(i == 0 && j == 0) score = 0;

      return dpSum[i][j] = score + Math.max(diag , Math.max(right , down));
    }


    int countPaths(int i, int j, List<String> b, int target, int[][][] dpCount){
        int n = b.size();

        if(i == n-1 && j == n-1)
            return target == 0 ? 1 : 0;

        if(target < 0) return 0;

        if(dpCount[i][j][target] != -1)
            return dpCount[i][j][target];

        int val = b.get(i).charAt(j) - '0';
        if(i == 0 && j == 0) val = 0;

        long ways = 0;

        if(check(i+1, j, n, b))
            ways = (ways + countPaths(i+1, j, b, target - val, dpCount)) % MOD;

        if(check(i, j+1, n, b))
            ways = (ways + countPaths(i, j+1, b, target - val, dpCount)) % MOD;

        if(check(i+1, j+1, n, b))
            ways = (ways + countPaths(i+1, j+1, b, target - val, dpCount)) % MOD;

        return dpCount[i][j][target] = (int)(ways % MOD);
    }


    public int[] pathsWithMaxScore(List<String> b) {
        int n = b.size();

        int[][] dpSum = new int[n][n];
        for(int[] row : dpSum) Arrays.fill(row , -1);

        int max = getMax(0,0,b,dpSum);

        if(max == -1) return new int[]{0,0};

        int[][][] dpCount = new int[n][n][max + 1];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                Arrays.fill(dpCount[i][j], -1);

        int count = countPaths(0,0,b,max,dpCount);

        return new int[]{max , count};
    }
}
