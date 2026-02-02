class Solution {

    static class Pair {
        int val, idx;
        Pair(int v, int i) { val = v; idx = i; }
    }

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        
        Comparator<Pair> cmp = (a, b) -> {
            if (a.val != b.val) return a.val - b.val;
            return a.idx - b.idx;
        };

        TreeSet<Pair> selected = new TreeSet<>(cmp);   // k-1 smallest
        TreeSet<Pair> reserve  = new TreeSet<>(cmp);   // remaining

        long currentSum = 0;
        int target = k - 1;

        // -----------------------
        // INITIAL WINDOW BUILDING
        // -----------------------
        for (int i = 1; i <= dist + 1 && i < n; i++) {
            reserve.add(new Pair(nums[i], i));
        }

        // Move k-1 smallest into selected
        while (selected.size() < target && !reserve.isEmpty()) {
            Pair p = reserve.pollFirst();
            selected.add(p);
            currentSum += p.val;
        }

        long ans = currentSum;

        // -----------------------
        // SLIDE WINDOW
        // -----------------------
        for (int right = dist + 2; right < n; right++) {
            int left = right - (dist + 1);

            Pair out = new Pair(nums[left], left);

            // remove out element
            if (selected.remove(out)) {
                currentSum -= out.val;

                if (!reserve.isEmpty()) {
                    Pair smallestReserve = reserve.pollFirst();
                    selected.add(smallestReserve);
                    currentSum += smallestReserve.val;
                }
            } else {
                reserve.remove(out);
            }

            // add incoming element
            Pair in = new Pair(nums[right], right);
            reserve.add(in);

            // rebalance (if needed)
            if (!selected.isEmpty() && !reserve.isEmpty()) {
                Pair largestSel = selected.last();
                Pair smallestRes = reserve.first();

                if (smallestRes.val < largestSel.val) {
                    selected.pollLast();
                    reserve.pollFirst();

                    selected.add(smallestRes);
                    reserve.add(largestSel);

                    currentSum -= largestSel.val;
                    currentSum += smallestRes.val;
                }
            }

            // ensure selected size = target
            while (selected.size() < target && !reserve.isEmpty()) {
                Pair p = reserve.pollFirst();
                selected.add(p);
                currentSum += p.val;
            }

            ans = Math.min(ans, currentSum);
        }

        return ans + nums[0];
    }
}







// Flow....


// Add new element to reserve
// Compare reserve.first() with selected.last()
// Swap if needed
// Fill selected to size target



// Problems with PriorityQueue

// contains() is O(n)
// remove(x) is O(n)
// Rebalancing becomes unpredictable
// Duplicate values break logic



// TreeSet advantages

// add() → O(log n)
// remove() → O(log n)
// pollFirst(), pollLast() → O(log n)
// Duplicate handling using (value, index) pair
// Immediate access to smallest / largest