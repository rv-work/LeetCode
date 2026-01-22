class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int i = 0;
        int farthest  = 0;
        int till = 0;

        while(i<n){
            
           if(farthest >= n-1) return jumps;
           till = farthest;
           jumps++;

           while(i < n && i <= till){
             farthest = Math.max(farthest , i+nums[i]);
             i++;
           }

        }



        return -1;
    }
}






    
// this is becase... currentEnd tak hm phuch chuke the ab agar usse aage jaa rhe hain next iteration me matlab ek jump leni pdei hogi ... aur ye jump mujhe  currentEnd = farthest;.. tak le jaa skti ... again jab iss end pr phuch jayenge to aage jana ho to ek aur jump leni pdegi kyunki i n-1 ke phle tak jaa rha ....