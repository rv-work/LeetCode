class Solution {
    int check(int [] price , int gap){
        int count = 1; int target = price[0] + gap;
        for(int i = 1; i< price.length; i++){
            if(price[i] >= target){
                count++;
                target = price[i] + gap;
            }
        }


        return count;

    }
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int  n = price.length;

        int l = Integer.MAX_VALUE;
        int r = price[n-1] - price[0];

        for(int i = 0; i<n-1; i++){
          l = Math.min(l , price[i+1]-price[i]);
        }


        while(l < r){
            int mid = (l+r+1)/2;
            int possible = check(price , mid);
            if(possible >= k) l = mid;
            else r = mid-1;
        }



        return l;
        
    }
}