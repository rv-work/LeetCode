class Solution {
    public int eraseOverlapIntervals(int[][] in) {
        int n = in.length;
        Arrays.sort(in , (a ,b) -> a[0]-b[0]);

        int k = 0;
        for(int i = 1; i<n; i++){
            if(in[i][0] < in[k][1]){
                in[k][1] = Math.min(in[k][1] , in[i][1]);
            } else {
                k++;
                in[k][0] = in[i][0];
                in[k][1] = in[i][1];
            }
        }

        return n-k-1;
    }
}