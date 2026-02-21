class Solution {

    void merge(int[] nums, int left, int mid, int right,
               int[] indexes, int[] count) {

        int[] temp = new int[right - left + 1];
        int[] tempIndex = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;
        int smallerCountRight = 0;

        while (i <= mid && j <= right) {
            if (nums[j] < nums[i]) {
                smallerCountRight++;
                temp[k] = nums[j];
                tempIndex[k] = indexes[j];
                j++;
            } else {
                count[indexes[i]] += smallerCountRight;
                temp[k] = nums[i];
                tempIndex[k] = indexes[i];
                i++;
            }
            k++;
        }

        while (i <= mid) {
            count[indexes[i]] += smallerCountRight;
            temp[k] = nums[i];
            tempIndex[k] = indexes[i];
            i++; k++;
        }

        while (j <= right) {
            temp[k] = nums[j];
            tempIndex[k] = indexes[j];
            j++; k++;
        }

        for (int x = 0; x < temp.length; x++) {
            nums[left + x] = temp[x];
            indexes[left + x] = tempIndex[x];
        }
    }

    void mergeSort(int[] nums, int left, int right,
                   int[] indexes, int[] count) {

        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(nums, left, mid, indexes, count);
        mergeSort(nums, mid + 1, right, indexes, count);

        merge(nums, left, mid, right, indexes, count);
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] indexes = new int[n];

        for (int i = 0; i < n; i++) indexes[i] = i;

        mergeSort(nums, 0, n - 1, indexes, count);

        List<Integer> ans = new ArrayList<>();
        for (int c : count) ans.add(c);
        return ans;
    }
}