public class Solution {
    int res(int odd , int even , int idx , int[] nums, Set<Integer> set){
        int n = nums.length;
        if(idx >= n) return Integer.MIN_VALUE;

        if(!set.contains(nums[idx])){
            set.add(nums[idx]);
            if(nums[idx] % 2 == 0) even++;
            else odd++;
        }

        int take = Integer.MIN_VALUE;
        int notTake =  Integer.MIN_VALUE;
        if(odd == even){
            take = idx;
        } 
        notTake = res(odd , even , idx+1,nums,set);
    

        return Math.max(take , notTake);
        
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            Set<Integer> set = new HashSet<>();
            int result =  res(0,0,i,nums,set);
            if(result != Integer.MIN_VALUE)
            max = Math.max(max , result - i + 1);
        }


        return max == Integer.MIN_VALUE ? 0 : max;
    }
} 10-02-2026 {
  
}
