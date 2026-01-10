class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals , (a,b) -> a[0]-b[0]);
        int n = intervals.length;

        int i = 0;
        int k = 1;
        while(k<n){
          if(intervals[k][0] <= intervals[i][1]){
            intervals[i][1] = Math.max(intervals[k][1], intervals[i][1]);
          } else {
            i++;
            intervals[i][0] = intervals[k][0];
            intervals[i][1] = intervals[k][1];
          }

          k++;

        }

        return Arrays.copyOf(intervals, i + 1);


    }
}