class Solution {
  public long totalCost(int[] costs, int k, int candidates) {
    int n = costs.length;

    PriorityQueue<int[]> left = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0])
        return a[0] - b[0];
      else
        return a[1] - b[1];
    });
    PriorityQueue<int[]> right = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0])
        return a[0] - b[0];
      else
        return a[1] - b[1];
    });

    for (int i = 0; i < n && i < candidates; i++) {
      left.add(new int[] { costs[i], i });
    }

    if (2 * candidates < n) {
      for (int i = n - 1; i >= n - candidates; i--) {
        right.add(new int[] { costs[i], i });
      }
    } else {
      for (int i = candidates; i < n; i++) {
        left.add(new int[] { costs[i], i });
      }
    }

    long sum = 0;
    int i = candidates;
    int j = n - candidates - 1;
    while (k > 0 && j >= i && !left.isEmpty() && !right.isEmpty()) {
      if (left.peek()[0] <= right.peek()[0]) {
        sum += left.poll()[0];
        left.add(new int[] { costs[i], i });
        i++;
      } else {
        sum += right.poll()[0];
        right.add(new int[] { costs[j], j });
        j--;
      }
      k--;
    }

    while (k-- > 0) {
      if (left.size() == 0)
        sum += right.poll()[0];
      else if (right.size() == 0)
        sum += left.poll()[0];
      else {
        if (left.peek()[0] <= right.peek()[0]) {
          sum += left.poll()[0];
        } else {
          sum += right.poll()[0];
        }
      }

    }

    return sum;

  }
}









class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int n = costs.length;

        int i = 0;
        int j = n - 1;

        while (i < candidates && i <= j) {
            left.add(costs[i++]);
        }

        while (j >= n - candidates && i <= j) {
            right.add(costs[j--]);
        }
        long ans = 0;

        for (int x = 0; x < k; x++) {
            int lelem = left.isEmpty() ? Integer.MAX_VALUE : left.peek();
            int relem = right.isEmpty() ? Integer.MAX_VALUE : right.peek();

            if (lelem <= relem) {
                ans += left.poll();
                if (i <= j)
                    left.add(costs[i++]);
            } else {
                ans += right.poll();
                if (i <= j)
                    right.add(costs[j--]);
            }
        }
        return ans;
    }
}