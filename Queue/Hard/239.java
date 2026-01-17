class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        ans[0] = pq.peek()[0];

        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});

            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }



            // confuse nhi hona hai ... yes ye har bari remove kre aisa jaruri nhi.. ho skta hai ek time pr pq me k se jyada element ho .. but vo shi ans dega kyunki vo k se extra vale effect nhi dalenge aur jaise hi dalne ki koshish krenge to bhaar nikal jayenge

// isse samjho



// ⭐ Key line jo yaad rakhni hai

// PQ me jo element top pe hota hai, wahi answer ban sakta hai.
// Jo niche hai, jab tak top pe nahi aata, tab tak answer ko affect hi nahi karta.

// Step-by-step example (slow & clear)
// nums = [1, 3, 2, 5]
// k = 3

// Window 0–2

// Elements:

// (3,1)   <- top
// (1,0)
// (2,2)


// Answer = 3 ✅

// Window slide → index = 3

// Add:

// (5,3)


// PQ now:

// (5,3)   <- top
// (3,1)
// (2,2)
// (1,0)   <- OUTDATED (window se bahar)


// 👉 Ab window se bahar element = (1,0)
// 👉 par wo top pe nahi hai

// Ab tumhara doubt wala part 👇
// while (pq.peek()[1] <= i - k)


// pq.peek() = (5,3)

// 3 <= 0 ❌ false

// loop break ✔️

// (1,0) remove nahi hua

// 🔴 Question: problem kyun nahi hui?
// 💡 ANSWER (MOST IMPORTANT)

// Kyuki (1,0) max hai hi nahi.
// Wo answer ban hi nahi sakta.

// 👉 Jab tak (1,0) top pe nahi aata,
// 👉 wo answer ko affect hi nahi karta.

// ❗ Jab problem hoti hai?

// Problem tab hoti hai jab outdated element top pe aa jaye

// Example:

// nums = [5, 1, 1, 1]
// k = 3

// Window 0–2
// (5,0) <- top
// (1,1)
// (1,2)

// Slide → index = 3

// Add (1,3)

// PQ:

// (5,0) <- OUTDATED & TOP ❌


// Now:

// pq.peek()[1] <= i - k   // 0 <= 0 ✔️


// 👉 poll() hota hai
// 👉 outdated max hata diya
// 👉 correct next max milta hai

// 🔥 Final intuition (1 line me)

// Hum outdated elements ko tabhi remove karte hain
// jab wo answer banne wale ho (top pe ho).

// Isse:

// Correctness bhi rehti hai

// Performance bhi (O(log n))



            ans[i - k + 1] = pq.peek()[0];
        }

        return ans;
    }
}
