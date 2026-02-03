class Solution {
    public boolean isTrionic(int[] nums) {
        int n =nums.length;

        int p = 0;
        while(p<n-1 && nums[p+1] > nums[p])p++;
        if(p==0) return false;

        int q = p;
        while(q < n-1 && nums[q+1] < nums[q])q++;
        if(q == p || q == n-1) return false;

        while(q < n-1 && nums[q+1] > nums[q])q++;
        if(q != n-1) return false;

        return true;
    }
}