class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;

        int i = 0;
        int farthest = nums[i] + i;

        while(i < n){
            if(farthest >= n-1) return true;
            int maxLi = farthest;
            for(int j = i; j<= maxLi; j++){
                farthest = Math.max(farthest , j + nums[j]);
            }
            i++;

        }
        return false;
    }
}