class Solution {

    List<Integer> res(int idx , int last , int [] nums,int n , List<Integer> dp[][]){
        if(idx >= n) return new ArrayList<>();

        if(dp[idx][last+1] != null) return dp[idx][last+1];

        List<Integer> take = new ArrayList<>();
        List<Integer> notTake = res(idx+1 , last, nums , n , dp);

        if(last == -1 || nums[idx] % nums[last] == 0){
            take.add(nums[idx]);
            take.addAll(res(idx+1 , idx, nums , n , dp));
        }

        return dp[idx][last+1] = take.size() > notTake.size() ? take : notTake;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> dp[][] = new List[n][n+1]; // last from -1 hai to +1 krenge hr jagah..
        return res(0,-1,nums,n , dp);
    }
}