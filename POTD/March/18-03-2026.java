class Solution {
    public int countSubmatrices(int[][] g, int k) {
        int n = g.length;
        int  m = g[0].length;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int sum = g[i][j];

                if(i-1  >= 0){
                    sum += g[i-1][j];
                }
                if(j-1  >= 0){
                    sum += g[i][j-1];
                }
                if(i-1 >= 0 && j -1 >= 0){
                    sum -= g[i-1][j-1];
                }

                g[i][j] = sum;
            }
        }

        int cnt = 0;

        for(int arr [] : g){
            for(int num : arr){
                cnt += num <= k ? 1 : 0;
            }
        }

        return cnt;
    }
}