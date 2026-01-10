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












class Solution {

    int count = 0;

    void mergeSort(int[] nums, int l, int r) {
        if (l >= r)
            return;
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private void merge(int[] nums, int l, int mid, int r) {

        int n1 = mid - l + 1;
        int n2 = r - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = nums[l + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = nums[mid + 1 + j];
        }
        // ans calculate krenge phle fir merge ....
        int j = 0;
        for (int i = 0; i < n1; i++) {
            while (j < n2 && (long) left[i] > 2L * right[j]) {
                j++;
            }
            count += j;
        }

        int i = 0;
        j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        while (i < n1) {
            nums[k++] = left[i++];
        }

        while (j < n2) {
            nums[k++] = right[j++];
        }
    }

    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }
}