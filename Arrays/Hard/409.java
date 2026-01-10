class Solution {
    public int reversePairs(int[] nums) {

        ArrayList<Long> list = new ArrayList<>();
        int count = 0;

        for (int x : nums) {

            long val = (long) x;
            long target = 2L * val;

            // first index where element > target
            int idx = upperBound(list, target);

            count += list.size() - idx;

            // current element ko sorted order me insert
            int insertPos = lowerBound(list, val);
            list.add(insertPos, val);
        }

        return count;
    }

    // first index where element > key
    private int upperBound(ArrayList<Long> list, long key) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) <= key) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    // first index where element >= key
    private int lowerBound(ArrayList<Long> list, long key) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) < key) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}











