

class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        // 1️⃣ find index of maximum value
        int peak = 0;
        for (int i = 1; i < n; i++) {
            if (maxHeights.get(i) > maxHeights.get(peak)) {
                peak = i;
            }
        }

        long sum = 0;

        // 2️⃣ include peak
        sum += maxHeights.get(peak);

        // 3️⃣ go left from peak
        int carry = maxHeights.get(peak);
        for (int i = peak - 1; i >= 0; i--) {
            carry = Math.min(carry, maxHeights.get(i));
            sum += carry;
        }

        // 4️⃣ go right from peak
        carry = maxHeights.get(peak);
        for (int i = peak + 1; i < n; i++) {
            carry = Math.min(carry, maxHeights.get(i));
            sum += carry;
        }

        return sum;
    }
}

// this fails because : 

// may be other gives greater value

// like
// maxHeights =
// [3,5,3,5,1,5,4,4,4]

// Output
// 19
// Expected
// 22







class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        Stack<Integer> st = new Stack<>();

        long max = Long.MIN_VALUE;

        int[] pse = new int[n];
        int[] nse = new int[n];

        // -------- PSE (previous smaller OR equal) ----------
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && maxHeights.get(st.peek()) >= maxHeights.get(i)) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        st.clear();

        // -------- NSE (next smaller) ----------
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && maxHeights.get(st.peek()) > maxHeights.get(i)) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        // -------- YOUR EXACT SUM LOGIC ----------
        for (int i = 0; i < n; i++) {

            long sum = maxHeights.get(i); // peak add

            int ps = pse[i];
            int ns = nse[i];

            // LEFT SIDE — EXACTLY what you said
            while (ps >= 0) {
                sum += (long)(ps - pse[ps]) * maxHeights.get(ps);
                ps = pse[ps];
            }

            // RIGHT SIDE — EXACTLY what you said
            while (ns < n) {
                sum += (long)(nse[ns] - ns) * maxHeights.get(ns);
                ns = nse[ns];
            }

            max = Math.max(max, sum);
        }

        return max;
    }
}
