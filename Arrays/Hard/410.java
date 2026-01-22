class Solution {

    int subarraysNeeded(int[] nums, int maxAllowed){
        int sub = 1;
        int curr = 0;

        for(int x : nums){
            if(curr + x <= maxAllowed){
                curr += x;
            } else {
                sub++;
                curr = x;
            }
        }
        return sub;
    }

    public int splitArray(int[] nums, int k) {

        int max = 0, sum = 0;

        for(int x : nums){
            max = Math.max(max, x);
            sum += x;
        }

        int l = max, r = sum;
        int ans = sum;

        while(l <= r){
            int mid = l + (r-l)/2;

            int needed = subarraysNeeded(nums, mid);

            if(needed <= k){
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}
