class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int ans = 0;
        for(int i = 0; i<n-1; i++){
            int x = Math.abs(points[i+1][0] -  points[i][0]);
            int y = Math.abs(points[i+1][1] -  points[i][1]);
            int min = Math.min(x , y);
            ans += x + y - min; // ans += min , ans += x-min , ans += y-min
        }

        return ans;
    }
}