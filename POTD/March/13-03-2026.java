class Solution {

    boolean check(int mh, int[] wt, long mid) {

        for (int w : wt) {
            long x = (long) ((Math.sqrt(1.0 + 8.0 * mid / w) - 1.0) / 2.0);
            mh -= x;
            if (mh <= 0) {
                return true;
            }
        }

        return false;
    }

    // boolean check(int mh, int[] wt, long mid) {
    //     long totalHeightReduced = 0;

    //     for (int w : wt) {
    //         long low = 1, high = mh, canBreak = 0;

    //         while (low <= high) {
    //             long h = low + (high - low) / 2;
    //             long timeNeeded = (long) w * h * (h + 1) / 2;

    //             if (timeNeeded <= mid) {
    //                 canBreak = h;
    //                 low = h + 1;
    //             } else {
    //                 high = h - 1;
    //             }
    //         }

    //         totalHeightReduced += canBreak;
    //         if (totalHeightReduced >= mh)
    //             return true;
    //     }
    //     return false;
    // }

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long maxWorkerTime = 0;
        for (int time : workerTimes) {
            maxWorkerTime = Math.max(maxWorkerTime, time);
        }

        long H = mountainHeight;
        long high = maxWorkerTime * (H * (H + 1)) / 2;
        long low = 1;

        long ans = -1;

        while (low <= high) {
            long mid = low + ((high - low) >> 1);
            if (check(mountainHeight, workerTimes, mid)) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }

        return ans;
    }
}










class Solution {
    public long minNumberOfSeconds(int mh, int[] wt) {
        PriorityQueue<long []> pq = new PriorityQueue<>
        ((a,b) -> Long.compare(a[0]*a[2]+1+a[1] , b[0]*b[2]+1+b[1]));

        long ans = Long.MIN_VALUE;

        for(int time : wt) pq.add(new long[]{time , 0 , 1});

        while(mh-- != 0){
            long top[] = pq.poll();
            long time = top[0]*top[2]+top[1];
            top[1] = time; 
            top[2]++; 
            pq.add(top);
            ans = Math.max(ans ,time);
        }

        return ans;
    }
}