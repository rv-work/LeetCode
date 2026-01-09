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


class Solution {
    public boolean canJump(int[] nums) {
       int reachable = 0;
       for(int i = 0; i < nums.length; i ++) {
           if(i > reachable) return false;
           reachable = Math.max(reachable, i + nums[i]);
       } 
       return true;
    }
}






class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length==1) return true;
        boolean zero = false;
        int requiredJump = 0;

        for(int i = nums.length-2; i >= 0; i--){
            if(!zero && nums[i] == 0){
                requiredJump = 2;
                zero=true;
                continue;
            }
            if(zero && nums[i]>=requiredJump ) zero = false;
            else requiredJump++;
               
        }
      return !zero;
    }
}