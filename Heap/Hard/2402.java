class Solution {
    public int mostBooked(int n, int[][] meetings) {

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Count meetings for each room
        int[] count = new int[n];

        // Min-heap of ALL available rooms (store room numbers)
        PriorityQueue<Integer> available = new PriorityQueue<>();

        // Min-heap for rooms currently busy → {endTime, room}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Initially all rooms are free
        for (int i = 0; i < n; i++) {
            available.add(i);
        }

        for (int[] m : meetings) {
            int start = m[0];
            int end = m[1];

            // Free all rooms whose meeting ended before current start
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                long[] temp = busy.poll();
                available.add((int) temp[1]);
            }

            // If no room is free → take the earliest finishing room
            if (available.isEmpty()) {
                long[] temp = busy.poll();
                long endTime = temp[0];
                int room = (int) temp[1];

                // This meeting starts exactly after this room gets free
                long newEnd = endTime + (end - start);

                busy.add(new long[]{newEnd, room});
                count[room]++;
            } 
            else {
                int room = available.poll();
                busy.add(new long[]{end, room});
                count[room]++;
            }
        }

        // Find room with maximum usage
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[ans]) ans = i;
        }
        return ans;
    }
}
