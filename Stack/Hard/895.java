class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> group;
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);

        group.putIfAbsent(f, new Stack<>());
        group.get(f).push(val);

        maxFreq = Math.max(maxFreq, f);
    }

    public int pop() {
        int val = group.get(maxFreq).pop();
        freq.put(val, freq.get(val) - 1);

        if (group.get(maxFreq).isEmpty()) {
            maxFreq--;
        }


//         Bahut solid observation — ye doubt har strong coder ko aata hai.
// Aur iska jawab intuition se bhi clear hota hai, aur mathematically bhi.

// Chalo step-by-step clear karte hain 👇

// ✅ QUESTION

// Agar maxFreq = 6 empty ho gaya,
// to kya guarantee hai ki frequency 5 ka koi element hoga hi?

// Answer:
// 👉 HAMESHA hoga — logically impossible hai ki na ho.

// ⭐ WHY? — DEEPEST INTUITION
// 🔥 FACT:

// Agar kisi element ki frequency 6 thi,
// to us element ki frequency 5 → 4 → 3 → 2 → 1 pe woh kabhi exist KARTA HI THA.

// Matlab:

// freq 6 stack empty huye bina

// freq 5 stack empty HO HI nahi sakta

// Because:

// Before an element could reach frequency 6, it MUST have been pushed into frequency 5 bucket.

// That “previous freq 5 push” never magically disappears.

// It stays there until popped.

// 🔥 WHY freq 5 MUST have elements?

// Assume an element x has freq = 6 at some point.

// Pushes:

// After 1st push → goes to group[1]
// After 2nd push → goes to group[2]
// After 3rd push → group[3]
// After 4th push → group[4]
// After 5th push → group[5]
// After 6th push → group[6]


// To get freq 6 entry:

// It MUST have pushed into all previous buckets.

// So:

// group[6] empty → means:

// You popped the element that had freq 6, so now:

// frequency[x] = 5


// So now it belongs to group[5] again.

// ⭐ But what if group[5] was empty BEFORE?

// Impossible.

// Because:

// You can't remove entries from lower buckets without popping from higher buckets FIRST.

// group[5] only loses elements when you pop from maxFreq = 5.

// But when maxFreq was 6, you were never popping from 5.

// So group[5] CANNOT be empty while group[6] had something.

// Visual example (easy)

// Push order:

// 5 → freq1
// 5 → freq2
// 5 → freq3
// 5 → freq4
// 5 → freq5   ← bucket 5 has [5]
// 5 → freq6   ← bucket 6 has [5]


// Now pop:

// pop from bucket 6 → remove 5
// freq[5] becomes 5 again
// bucket 6 empty
// → maxFreq--, now use bucket 5


// Bucket 5 still has element 5 (it was never removed)

// 🧠 Summary

// For an element to reach frequency K,
// it must have created entries in all buckets 1 to K−1 earlier.
// Those entries stay unless popped.

// So group[maxFreq−1] MUST contain something
// when group[maxFreq] becomes empty.

        return val;
    }
}
