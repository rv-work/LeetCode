class Solution {
    public int findPeakElement(int[] nums) {
         int n = nums.length;
         if(n==1) return 0;
        int l = 0; int r = n-1;
        while(l <= r){
            int mid = (l+r)/2;

                if(mid == 0){
                    if(nums[mid] > nums[mid+1]) return mid;
                    else l = mid+1;
                } else if (mid==n-1){
                    if(nums[mid] > nums[mid-1]) return mid;
                    else r = mid-1;
                } else {
                    if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return mid;
                    else if(nums[mid] > nums[mid-1]) l = mid+1;
                    else if(nums[mid] > nums[mid+1]) r = mid-1;
                    else l = mid+1; // here element dono side se chhota hai to  yha pr 
                    // l = mid+1 bhi theek hai and r = mid-1 bhi [9,7,3,7,8]..at 3..
                }
        }
        return -1;
    }
}











class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}