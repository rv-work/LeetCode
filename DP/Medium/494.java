class Solution {
    int res(int [] nums , int target , int idx){
        if(idx == nums.length){
            if(target == 0) return 1;
            else return 0;
        }

        int plus = res(nums , target - nums[idx] , idx+1);
        int minus = res(nums , target + nums[idx] , idx+1);

        return plus + minus;

    }
    public int findTargetSumWays(int[] nums, int target) {
        
        return res(nums , target , 0);
    }
}