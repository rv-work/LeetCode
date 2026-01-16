class Solution {
    int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        int[] ps = new int[n]; // previous smaller
        int[] ns = new int[n]; // next smaller

        Stack<Integer> st = new Stack<>();

        // -------------------------------
        // 1️⃣ PREVIOUS SMALLER ELEMENT (strictly smaller)
        // -------------------------------
        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            ps[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        // clear stack for next pass
        st.clear();

        // -------------------------------
        // 2️⃣ NEXT SMALLER ELEMENT (<= condition)
        // -------------------------------
        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            ns[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        // -------------------------------
        // 3️⃣ COMPUTE FINAL RESULT
        // -------------------------------
        long ans = 0;

        for (int i = 0; i < n; i++) {

            long leftSpan = i - ps[i];
            long rightSpan = ns[i] - i;

            long contribution = (arr[i] * leftSpan % MOD) * rightSpan % MOD;
            ans = (ans + contribution) % MOD;
        }

        return (int) ans;
    }
}







// 💡 HINT #1 — Every element contributes as the minimum for some subarrays

// For each index i, tumhe yeh nikalna hai:

// ✔ How many subarrays exist

// where arr[i] is the minimum?

// Uska formula:

// arr[i] * (left span) * (right span)


// Bas yeh 2 spans nikalne hain.











// 💡 HINT #2 — For each element, find:
// 1) Previous Smaller Element (PSE)

// = index j on the left such that:

// arr[j] < arr[i] and j is nearest to left

// 2) Next Smaller Element (NSE)

// = index k on the right such that:

// arr[k] <= arr[i] and k is nearest to right


// Why ≤ on right?
// Tie-breaking to avoid double counting.




// 💡 HINT #3 — These two are found by USING Monotonic Increasing Stack

// 2 passes:

// PASS 1:

// Find previous smaller (PSE) using strictly monotonic increasing stack.

// PASS 2:

// Find next smaller (NSE) using monotonic increasing stack (≤ condition).

// 💡 HINT #4 — Spans calculation

// If:

// PSE = j
// NSE = k


// Then:

// leftSpan = i - j
// rightSpan = k - i

// 💡 HINT #5 — Final Answer

// Add the contribution:

// sum += arr[i] * leftSpan * rightSpan


// MOD 1e9+7 of course.

// 💡 HINT #6 — No DP. No two-pointer. No brute force.

// Is problem ko solve karne ka sirf ek optimal way hai:

// Monotonic Increasing Stack to find boundaries.