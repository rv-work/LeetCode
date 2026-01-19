class Solution {

    int upperBound(int[][] machines , int target , int l){
        int r = machines.length - 1;
        int ans = -1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(machines[mid][0] < target){
                ans = mid;
                l = mid + 1;          
            } else {
                r = mid - 1;
            }
        }

        return ans;   
    }

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = capacity.length;
        int[][] machines = new int[n][2];

        for(int i = 0;i<n; i++){
            machines[i][0] = costs[i];
            machines[i][1] = capacity[i];
        }

        Arrays.sort(machines , (a,b) -> a[0] - b[0]);

        int ans = 0;

        for(int i = 0; i < n; i++){
           if(machines[i][0] > budget) break;

           int maxCap = machines[i][1];
           int target = budget - machines[i][0];
           if(target <= 0) continue;

           int ub = upperBound(machines , target , i + 1);
           if(ub == -1 ) {
             ans = Math.max(ans , maxCap);
             continue;
           } 

           int cap = 0;
           for(int range = i + 1; range <= ub; range++){ 
               cap = Math.max(cap , machines[range][1]);
           }

           maxCap += cap;
           ans = Math.max(ans , maxCap);
        }

        return ans;
    }
}















class Solution {

    class Pair{
        int idx;
        int cap;
        Pair(int idx , int cap){
            this.idx = idx;
            this.cap = cap;
        }
    }

    int upperBound(int[][] machines , int target , int l){
        int r = machines.length - 1;
        int ans = -1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(machines[mid][0] < target){
                ans = mid;
                l = mid + 1;          
            } else {
                r = mid - 1;
            }
        }

        return ans;   
    }

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = capacity.length;
        int[][] machines = new int[n][2];

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.cap - a.cap);


        for(int i = 0;i<n; i++){
            machines[i][0] = costs[i];
            machines[i][1] = capacity[i];
           
        }

        Arrays.sort(machines , (a,b) -> a[0] - b[0]);
        for(int i = 0; i<n; i++){
             pq.add(new Pair(i , machines[i][1]));
        }

        int ans = 0;

        for(int i = 0; i < n; i++){
           if(machines[i][0] > budget) break;

           int maxCap = machines[i][1];
           int target = budget - machines[i][0];
           if(target <= 0) continue;

           int ub = upperBound(machines , target , i + 1);
           if(ub == -1 ) {
             ans = Math.max(ans , maxCap);
             continue;
           } 

           int cap = 0;

        //    for(int range = i + 1; range <= ub; range++){ 
        //        cap = Math.max(cap , machines[range][1]);
        //    }  this is costly and because of this ... TLE...

           // and dekha jaye to iska koi sense nhi huaa kyunki ultimately hm traverse hi hi kr rhe hain to vo O(n) he le rha to sath sath check krke jao aur MaxCap bhi lete jao same hi hai... binary search is of no use.... at the end just waste /.....


            while(pq.peek().idx <= i || pq.peek().idx > ub){
              pq.poll();
            }

            cap = pq.peek().cap;

            maxCap += cap;
            ans = Math.max(ans , maxCap);
        }

        return ans;
    }
}
