class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0; int r = n-1;
        while(l <= r){
            int mid = (l+r)/2;
            if(mid % 2 == 0){
                if(mid+1 < n && nums[mid] == nums[mid+1]) l = mid+1;
                else if ( mid-1 >= 0  && nums[mid] == nums[mid-1]) r = mid-1;
                else return nums[mid];
            } else {
                if(mid+1 < n && nums[mid] == nums[mid+1]) r = mid-1;
                else if ( mid-1 >= 0  && nums[mid] == nums[mid-1]) l = mid+1;
                else return nums[mid];
            }
            
        }

        return -1;
    }
}