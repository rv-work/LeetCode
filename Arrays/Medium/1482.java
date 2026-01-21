class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long)m * k > bloomDay.length) return -1;

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int d : bloomDay) {
            low = Math.min(low, d);
            high = Math.max(high, d);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canMake(int[] bloomDay, int day, int m, int k) {
        int bouquets = 0, flowers = 0;

        for (int d : bloomDay) {
            if (d <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
            if (bouquets >= m) return true;
        }

        return false;
    }
}
