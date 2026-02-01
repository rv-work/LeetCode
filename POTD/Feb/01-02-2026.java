class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        int minI = -1;
        int  min = Integer.MAX_VALUE;
        int  min2 = Integer.MAX_VALUE;
        int minII = -1;

        for(int i = 1; i<n; i++){
            if(min >= min2 && nums[i]< min){
                minI = i;
                min = nums[i];
            } else if(min < min2 && nums[i]< min2) {
                minII = i;
                min2 = nums[i];
            }
        }

        sum += nums[minI];
        sum += nums[minII];
        return sum;
    }
}