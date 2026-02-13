
class Solution {

  public int longestBalanced(int[] nums) {

    // Length of array
    int n = nums.length;

    // Map to store the last position of each number
    // Used to detect duplicates
    Map<Integer, Integer> lastPos = new HashMap<>();

    // Stores maximum length of valid balanced subarray
    int maxLen = 0;

    // sum[i] represents:
    // Contribution (odd-even difference) of subarray from index i to current
    // 'right'
    int[] sum = new int[n];

    // Expanding window using right pointer
    for (int right = 0; right < n; right++) {

      // Current number
      int num = nums[right];

      // Get previous occurrence of this number (if any)
      // If not present, return -1
      int prev = lastPos.getOrDefault(num, -1);

      // Convert number into contribution:
      // Odd -> +1
      // Even -> -1
      // Balanced subarray means total sum = 0
      int val = (num % 2 != 0) ? 1 : -1;

      // -----------------------------
      // Step 1: Handle duplicate
      // -----------------------------
      // If this number appeared before,
      // remove its earlier contribution from all prefixes
      // up to its previous position.
      if (prev != -1) {
        for (int i = 0; i <= prev; i++) {
          sum[i] -= val; // Cancel previous effect
        }
      }

      // -----------------------------
      // Step 2: Add current contribution
      // -----------------------------
      // Add +1 or -1 to all prefixes from 0 to right
      // Because every subarray ending at 'right'
      // will include this value
      for (int i = 0; i <= right; i++) {
        sum[i] += val;
      }

      // -----------------------------
      // Step 3: Check for balanced subarray
      // -----------------------------
      // If sum[i] == 0
      // It means subarray from i to right
      // has equal number of odd and even elements
      for (int i = 0; i <= right; i++) {
        if (sum[i] == 0) {

          // Update maximum length
          maxLen = Math.max(maxLen, right - i + 1);

          // Break because smaller i gives larger length,
          // so first zero encountered gives max length for this right
          break;
        }
      }

      // Update last position of current number
      lastPos.put(num, right);
    }

    return maxLen;
  }
}

/*
 * =========================================================
 * THOUGHT PROCESS / MOTIVE OF THIS APPROACH
 * =========================================================
 * 
 * 1. We convert the problem into a prefix sum problem:
 * - Odd numbers contribute +1
 * - Even numbers contribute -1
 * 
 * 2. If total contribution of a subarray becomes 0,
 * it means number of odds = number of evens.
 * That subarray is balanced.
 * 
 * 3. To avoid counting duplicate elements in a subarray,
 * we track last occurrence of each number.
 * If duplicate appears, we remove its previous contribution
 * before adding new one.
 * 
 * 4. For every index 'right', we:
 * - Adjust duplicate contribution (if needed)
 * - Add current value's contribution
 * - Check if any prefix becomes zero
 * 
 * 5. Time Complexity:
 * O(n^2) because for each 'right' index,
 * we loop from 0 to right multiple times.
 * 
 * This is a brute-force prefix simulation approach.
 * =========================================================
 */
