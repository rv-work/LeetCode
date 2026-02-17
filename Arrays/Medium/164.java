class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int val  : nums){
            max = Math.max(max , val);
            min = Math.min(min , val);
        }


        if (min == max) return 0;

        int bucketSize = (int)Math.ceil((double)(max - min) / (n - 1));

        // or int bucketSize = ((double)max-min + n-2) / n-1;

        int buckets = (max-min)/bucketSize +1;

        int minBucket[] = new int[buckets];
        int maxBucket[] = new int[buckets];

        Arrays.fill(minBucket , Integer.MAX_VALUE);
        Arrays.fill(maxBucket , Integer.MIN_VALUE);

        for(int val : nums){
            int index = (val-min)/bucketSize;
            minBucket[index] = Math.min( minBucket[index] , val);
            maxBucket[index] = Math.max( maxBucket[index] , val);
        }


        // int ans = 0;
        // for(int i = 1; i<buckets; i++){
        //     if(minBucket[i] == Integer.MAX_VALUE) continue; // empty bucket
        //     ans = Math.max(ans , minBucket[i] - maxBucket[i-1]);
        // } 
        // ye wrong dega kyunki i-1 empty ho skti ..... so hme vo leke rkhna pdega jo tha actually me last me ..
        // kyunki .... current bucket min - previous NON-EMPTY bucket max;

         int ans = 0;
         int prev = maxBucket[0];
        for(int i = 0; i<buckets; i++){
            if(minBucket[i] == Integer.MAX_VALUE) continue; // empty bucket
            ans = Math.max(ans , minBucket[i] - prev);
            prev = maxBucket[i];
        } 

        return ans;

    }
}