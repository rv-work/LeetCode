class Solution {
    int[] bit;

    int OFFSET = 40000; 
    
    // BIT ka size offset + max possible positive diff se bada hona chahiye
    // Max diff = 20000, max target = 20000 + 10000 = 30000.
    // 30000 + 40000 (offset) = 70000. 100000 is perfectly safe.
    int MAX_VAL = 100000; 

     // BIT: Point Update (index par val add karo)
    void update(int index, int val) {
        while (index < MAX_VAL) {
            bit[index] += val;
            index += (index & -index);
        }
    }

    // BIT: Prefix Sum (1 se lekar index tak ka sum)
    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index -= (index & -index);
        }
        return sum;
    }

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        bit = new int[MAX_VAL];
        long totalPairs = 0; 
        int n = nums1.length;

        for (int j = 0; j < n; j++) {
            int c_j = nums1[j] - nums2[j];
            int target = c_j + diff;

            // 1. Pehle count karo ki array me abhi tak kitne elements <= target aaye hain
            totalPairs += query(target + OFFSET);

            // 2. Fir current element c_j ko BIT me add kar do
            update(c_j + OFFSET, 1);
        }

        return totalPairs;
    }

   
}






// hybrid problem
// f(i, j) = f(j, i) pair problem + Inversions problem
// First try to solve the pair issues
// then count the number of elements in right subarray which are greater than element in left subarray
// diff is annoying, i think we need 2 different merge functions depending on diff
// EDIT : didnt need 2 merge functions

class Solution {

    long npairs = 0, d; // inversion count and diff

    void merge(int[] nums, int low, int high){
        int mid = low + (high - low)/2;
        int size = high - low + 1;

        int[] temp = new int[size];

        int k = 0, i = low, j = mid+1;

        // check for requirement and get the inversion counts here
        while(i <= mid && j <= high){
            // since this is inside the merge function
            // the first and the latter halves are individually sorted
            // hence if nums[j] on the right is found to be greater than any number nums[i] on the left
            // then all the numbers after the j are also greater than nums[i]
            // hence += (high - j + 1)
            if(nums[i] <= nums[j] + d){
                npairs += (high - j + 1);
                ++i;
            }
            else ++j;
        }

        // carry on with your merge function as usual
        // ascending order
        i = low;
        j = mid+1;
        while(i <= mid && j <= high){
            if(nums[i] < nums[j]){
                temp[k++] = nums[i++];
            }
            else temp[k++] = nums[j++];
        }

        // one of the array was exhausted
        while(i <= mid) temp[k++] = nums[i++];
        while(j <= high) temp[k++] = nums[j++];

        // copy back
        for(i = low, k = 0; i <= high; ++i, ++k){
            nums[i] = temp[k];
        }
    }

    void mergeSort(int[] nums, int low, int high){
        if(low == high) return;

        int mid = low + (high - low)/2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid+1, high);
        merge(nums, low, high);
    }

    long numberOfPairs(int[] nums1, int[] nums2, int diff){
        d = diff;
        // ignore diff for now
        // rearrange nums1[i] - nums1[j] <= nums2[i] - nums2[j] to
        // nums1[i] - nums2[i] <= nums1[j] - nums2[j]
        // create new vector

        int n = nums1.length;
        int[] nums = new int[n];
        for(int i = 0; i < n; ++i){
            nums[i] = nums1[i] - nums2[i];
        }

        // now find j's for each i such that the element in j is greater.
        // now employ the inversion merge function
        mergeSort(nums, 0, n-1);

        return npairs;
    }
}