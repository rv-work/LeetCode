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