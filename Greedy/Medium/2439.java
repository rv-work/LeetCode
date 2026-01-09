class Solution {
    public int minimizeArrayValue(int[] nums) {
        long sum = 0;
        int answer = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // minimum max needed so that prefix can be balanced
            int needed = (int) ((sum + i) / (i + 1)); // ceil
            answer = Math.max(answer, needed);
        }

        return answer;
    }
}















class Solution {
    public int minimizeArrayValue(int[] nums) {
        long sum = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

          
            int avg = (int) Math.ceil((double) sum / (i + 1));
            // or avg = sum % (i+1) != 0 ? (sum / (i+1)) + 1 : (sum / (i+1));
            // or avg = (sum + i) / (i + 1);.. isse ye hoga ki agar perfectly divisible hoga to ye +1 effect nhi krega kyunki ... let hm i add kr rhe i+1 nhi ... matlab 12/4 = 3 and 12+3 / 4 = 3 .... same but agar perfectly nhi huaa to vo uske boundry se upar phuch dega jisse +1 ho jayega.. like 13/4 = 3 but 13+3/4 = 4.......thats how


            ans = Math.max(ans, avg);
        }

        return ans;
    }
}




















class Solution {

    boolean check(int[] nums , int maxLimit){
      long maxDistribution = 0;   
      for(int num : nums){
          if(num < maxLimit) maxDistribution += (long)maxLimit - num;
          else maxDistribution -= (long)num - maxLimit;
          if(maxDistribution < 0) return false;
      }
      return true;
    }

    public int minimizeArrayValue(int[] nums) {
        int low = 0, high = 0;
        for (int num : nums) high = Math.max(high, num);

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(nums, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}










class Solution {

    boolean check(int[] nums, int maxLimit) {
        long sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > (long) maxLimit * (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public int minimizeArrayValue(int[] nums) {
        int low = 0, high = 0;
        for (int num : nums) high = Math.max(high, num);

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(nums, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
