class Solution {
    public int minPatches(int[] nums, int n) {
        int add = 0;
        long maxReach = 0;
        int i = 0;

        while(maxReach < n){
            long nextNeeded = maxReach+1;
            if(i< nums.length && nums[i] <= nextNeeded){ 



// check if next num is what we needed.. so that we should not miss..
// like if macR = 12 and nums[i] is 13 or less then ok .. but if nums[i] 14 or bigger .. means hm 12 ke baad se nums[i] tak ke miss krne vale to uske liye add krenge manually 

// if maxR is = 12 , nums[i] is 14 so we will add 13 to nums..and then next maxR = 25.


                maxReach += nums[i];
                i++;
            } else {
                maxReach += nextNeeded;
                add++;
            }
        }

        return add;
    }
}