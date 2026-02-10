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
}  {
  
}











class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        // Har index 'i' ko start point maano
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            int odd = 0;
            int even = 0;

            // 'j' ko end point maano aur aage badhte jao
            for (int j = i; j < n; j++) {
                // Agar number pehle se set me nahi hai, tabhi count update karo
                if (!set.contains(nums[j])) {
                    set.add(nums[j]);
                    if (nums[j] % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                }

                // Agar distinct odd aur distinct even barabar hain, to length check karo
                if (odd == even) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}



