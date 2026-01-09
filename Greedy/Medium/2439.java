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
