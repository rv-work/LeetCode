class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int len = m*n;
        int l = 0; int r = len-1;
        while(l<=r){
            int mid = (l+r)/2;
            int i = mid / n;   // row index
            int j = mid % n;   // column index

            int val = matrix[i][j];

            if(val == target) return true;
            else if (val > target) r = mid-1;
            else l = mid+1;
        }
        return false;
    }
}