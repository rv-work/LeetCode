class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int ans[] = new int[n];

        for(int i = 0; i<n; i++){
            int num = nums.get(i);
            if(num == 2){
                ans[i] = -1;
                continue;
            }

            int j = 0;
            while( (num  & (1<<j) ) > 0) j++; // finding first zero from right... <--

            int prevToZero = j-1; // this will be 1..just behind zero
            int val = num ^ (1<<prevToZero); // making 1 to 0...
            // so that hme vo smallest number mile jiska +1 se or krne pr ye num mile 
            ans[i] = val;
        }

        return ans;
    }
}