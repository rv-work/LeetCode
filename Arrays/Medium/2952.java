class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        
        Arrays.sort(coins);
        int add = 0;
        long maxReach = 0;
        int i = 0;

        while(maxReach < target){
            long nextNeeded = maxReach+1;
            if(i< coins.length && coins[i] <= nextNeeded){ 



// check if next num is what we needed.. so that we should not miss..
// like if macR = 12 and coins[i] is 13 or less then ok .. but if coins[i] 14 or bigger .. means hm 12 ke baad se coins[i] tak ke miss krne vale to uske liye add krenge manually 

// if maxR is = 12 , coins[i] is 14 so we will add 13 to coins..and then next maxR = 25.


                maxReach += coins[i];
                i++;
            } else {
                maxReach += nextNeeded;
                add++;
            }
        }

        return add;
    }
}