class Solution {
    public int maxSubArray(int[] nums) {
      int maxSum = Integer.MIN_VALUE;
      int sum = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];

        if (sum > maxSum) {
          maxSum = sum;
        }

        if (sum < 0) {
          sum = 0;
        }
    }
    return maxSum;
    }
}










class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = prefix[j + 1] - prefix[i];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
