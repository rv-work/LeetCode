import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {

        // 1. Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int[] count = new int[n];

        // Available rooms (Min-Heap by room index)
        PriorityQueue<Integer> available = new PriorityQueue<>();
        
        // Busy rooms (Min-Heap by end time, then by room index)
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] == b[0]) {
                    return Long.compare(a[1], b[1]); // Tie-breaker: Room number
                }
                return Long.compare(a[0], b[0]);     // Primary: End time
            }
        );

        // Shuru me saare rooms available hain
        for (int i = 0; i < n; i++) available.add(i);

        for (int[] m : meetings) {
            int start = m[0], end = m[1];

            // Jo rooms current meeting ke start hone tak free ho gaye, unhe available me dalo
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                long[] t = busy.poll();
                available.add((int) t[1]);
            }

            if (available.isEmpty()) {
                // Agar koi room free nahi hai, to jo sabse pehle free hoga uska wait karo
                long[] t = busy.poll();  
                long newEnd = t[0] + (end - start); // Original duration add karo
                int room = (int) t[1];

                busy.add(new long[]{newEnd, room});
                count[room]++;
            } else {
                // Agar room available hai, to sabse chhota room pick karo
                int room = available.poll();
                busy.add(new long[]{end, room});
                count[room]++;
            }
        }

        // Jis room me sabse jyada meetings hui, uska index nikalo
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[ans]) {
                ans = i;
            }
        }

        return ans;
    }
}