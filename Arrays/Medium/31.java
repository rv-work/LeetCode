
// class Solution {

//     void swap(int[] nums, int i, int j) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }

//     public void nextPermutation(int[] nums) {

//         int i = nums.length - 1;

//         while (i >= 1) {
//             int j = i - 1;
            
//             while (j >= 0 && nums[j] >= nums[i]) {
//                 j--;
//             }

//             if (j >= 0 && j != i) {
//                 swap(nums, j, i);
//                 Arrays.sort(nums, j + 1, nums.length);
//                 return; 
//             }
            
//             i--; 
//         }

//         Arrays.sort(nums); 
//     }
// }




// [4,2,0,2,3,2,0]
//1st
// [4,2,2,2,3,0,0]
//2nd
// [4,2,2,0,0,2,3] ......wrong
// kyunki may be isse chhota gap mil skta tah jab i aur pichhe aata uske .. like 2,3



















class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: Find the first decreasing element from right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            // Step 2: Find element just greater than nums[i]
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the array from i+1 to end
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}


//.. right is [4,2,0,3,0,2,2].... to if i first find ki kaha pr bde se chhota ho rha then i can swap and after that ... also i do not need to sort ... kyunki reverse krke bhi ho jayega 


