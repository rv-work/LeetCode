class Solution {
    int rev(int num){
        int r = 0;
        while(num % 10 == 0){
            num /= 10;
        }

        while(num > 0){
            r *= 10;
            r += num % 10;
            num /= 10;
        }

        return r;
    }

    public int minMirrorPairDistance(int[] nums) {
        Map<Integer , Integer> map = new HashMap<>();
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            int num = nums[i];
            if(map.containsKey(num)){
                ans = Math.min(ans , i - map.get(num));
            }
            map.put(rev(num) , i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}