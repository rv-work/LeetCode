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








class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // dp[idx][last+1] = best subset from idx when last chosen = last
        List<Integer>[][] dp = new List[n+1][n+1];

        // ---- BASE CASE: idx == n ----
        // From idx = n, answer is empty list for ANY last
        for (int last = -1; last < n; last++) {
            dp[n][last + 1] = new ArrayList<>();
        }

        // ---- FILL TABLE bottom-up ----
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int last = idx - 1; last >= -1; last--) {

                // -------- NOT TAKE --------
                List<Integer> notTake = dp[idx + 1][last + 1];

                // -------- TAKE --------
                List<Integer> take = new ArrayList<>();
                if (last == -1 || nums[idx] % nums[last] == 0) {
                    take.add(nums[idx]);
                    take.addAll(dp[idx + 1][idx + 1]);
                }

                // -------- BEST --------
                if (take.size() > notTake.size()) {
                    dp[idx][last + 1] = take;
                } else {
                    dp[idx][last + 1] = notTake;
                }
            }
        }

        // answer = dp[0][-1+1] = dp[0][0]
        return dp[0][0];
    }
}







class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n =  nums.length;
        int dp[] = new int[n];
        int prev[] = new int[n];

        Arrays.fill(dp , 1);
        Arrays.fill(prev , -1);

        int maxLen = 0; int index = -1;


        for(int i = 0; i<n; i++){
            for(int j = 0; j<i; j++){
                if(nums[i] % nums[j] == 0 && dp[j]+1 > dp[i]){
                    dp[i] = dp[j]+1;
                    prev[i] = j;

                    // if(maxLen < dp[i]){
                    //     maxLen = Math.max(maxLen , dp[i]);
                    //     index = i;
                    // } .. ye galat ho jayega .. kyunki hme best chahiye .. pure j = 0 to i-1 tak me 
                   
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                index = i;
            }
        }

        List<Integer> ans = new ArrayList<>();

        while(index > -1){
            ans.add(nums[index]);
            index = prev[index];
        }

        return ans;
    }
}