class Solution {
  public int maxEvents(int[][] events) {
    Arrays.sort(events, (a, b) -> a[0] - b[0]); /// start time
    int n = events.length;
    PriorityQueue<Integer> pq = new PriorityQueue<>(); // min end time....
    int maxDayReq = Integer.MIN_VALUE;
    int startDay = Integer.MAX_VALUE;
    for (int e[] : events) {
      maxDayReq = Math.max(maxDayReq, e[0]);
      startDay = Math.min(startDay, e[0]);
    }
    int day = startDay;
    int i = 0;
    int cnt = 0;
    while (day <= maxDayReq || day <= n + maxDayReq) {
      while (i < n && events[i][0] == day) {
        pq.add(events[i][1]);
        i++;
      }
      while (!pq.isEmpty() && pq.peek() < day)
        pq.poll();

      if (!pq.isEmpty()) {
        cnt++;
        pq.poll();
      }

      day++;

    }

    return cnt;

  }
}