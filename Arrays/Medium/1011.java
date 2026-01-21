class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int left = 0, right = 0;

        // left = max(weights), right = sum(weights)
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canShip(weights, days, mid)) {
                right = mid;   // try smaller capacity
            } else {
                left = mid + 1; // need bigger capacity
            }
        }

        return left;
    }

    private boolean canShip(int[] weights, int days, int capacity) {
        int neededDays = 1;   // start at day 1
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                neededDays++;     // next day
                currentLoad = w;  // start new load
                if (neededDays > days) return false;
            } else {
                currentLoad += w;
            }
        }

        return true;
    }
}
