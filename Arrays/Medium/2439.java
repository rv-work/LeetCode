class Solution {
    boolean check(int nums [], int max){
        long buffer = 0;
        for(int i = 0; i< nums.length; i++){
           buffer += max - nums[i];
           if(buffer < 0) return false;
        }

        return true;
    }

    public int minimizeArrayValue(int[] nums) {

        int l = nums[0];
        int r = Arrays.stream(nums).max().getAsInt();

        
        int ans = -1;
        while(l <= r){
            int mid = l + ( r-l )/2;
            if(check(nums , mid)){
                ans = mid;
                 r = mid-1;
            } else l = mid+1;
        }

        return  ans;
    }
}