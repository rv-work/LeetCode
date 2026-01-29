import java.util.*;

class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // 2. Distance Arrays to track min and second min
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);

        // 3. PQ stores {currentTime, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Start at node 1 with time 0
        pq.offer(new int[]{0, 1});
        dist1[1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int timePassed = curr[0];
            int node = curr[1];

            // Optimization: If we pulled a stale value greater than the second min, skip
            if (timePassed > dist2[node]) continue;
            
            // If we reached target 'n' and found the second min, return it
            if (node == n && dist2[n] != Integer.MAX_VALUE) return dist2[n];

            // 4. Calculate Departure Time (Handle Traffic Light)
            // If (timePassed / change) is odd, it's RED. Wait.
            int wait = 0;
            if ((timePassed / change) % 2 == 1) {
                wait = change - (timePassed % change);
            }
            int arrivalTime = timePassed + wait + time;

            // 5. Explore Neighbors
            for (int nei : adj.get(node)) {
                // Case 1: Found a new shortest time
                if (arrivalTime < dist1[nei]) {
                    // The old shortest becomes the new second shortest
                    dist2[nei] = dist1[nei]; 
                    dist1[nei] = arrivalTime;
                    
                    pq.offer(new int[]{dist1[nei], nei});
                    // Also push the old shortest (now second shortest) if it was valid
                    if(dist2[nei] != Integer.MAX_VALUE) {
                        pq.offer(new int[]{dist2[nei], nei});
                    }
                    
                } 
                // Case 2: Found a strictly larger second shortest time
                else if (arrivalTime > dist1[nei] && arrivalTime < dist2[nei]) {
                    dist2[nei] = arrivalTime;
                    pq.offer(new int[]{dist2[nei], nei});
                }
            }
        }
        return 0;
    }
}

// bahot important hai 2nd min ko bhi add krna kyunki we dont care about path.... hme bss time chahiye ....so ye 2nd min ho skta hai lead kre aage kisi better ans pr ... thats why......