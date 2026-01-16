class Solution {
    int MOD = 1000000007;

    private int getMaxContinuous(int total, int[] fences) {
        Arrays.sort(fences);

        int max = 0;
        int curr = 0;

        // Check start boundary (from 1)
        if (fences[0] == 2)
            curr = 1;

        for (int i = 1; i < fences.length; i++) {
            if (fences[i] - fences[i - 1] == 1) {
                curr++;
            } else {
                curr = 0;
            }
            max = Math.max(max, curr);
        }

        // Check end boundary (to total)
        if (total - fences[fences.length - 1] == 1) {
            curr++;
            max = Math.max(max, curr);
        }

        return max;
    }

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {

        int hMax = getMaxContinuous(m, hFences);
        int vMax = getMaxContinuous(n, vFences);

        int side = Math.min(hMax, vMax);

        // why we dont need to calculate only Longest consecutive
        // kyunki let se longest nikla horizontal me 7 aur vertical me 6
        // ab agar hm soche ki 7 hai horizontal me to vo max dega but utna vertical me nhi to 
        // ab usme 6 hai to ..... mujhe 2nd longest nikalne ki jarurat nhi kyunki vo 100% exit kr rha hoga kyuni jo 7 form huaa hai vo consecuive se hi form huaa hoga.. jisse hm ye kh skyte ki 
        // hm max nikal kr min jo bhi hoga vhi ans hoga
        // in short agar maxHor = 6 nikalta hai aur maxVer = 4
        // to possible & valid hor are = 1,2,3,4,5,6
        // and possible & valid ver are = 1,2,3,4
        

        if (side <= 0)
            return -1;

        int area = (side * side) % MOD;
        return area;
    }
}


// but this is not enough :


// ✅ Why this consecutive-only logic is NOT enough (important explanation)

// Tumhara current approach sirf longest consecutive unit gaps check karta hai.

// Lekin problem me square banane ke liye yeh kaafi nahi hai.

// ❌ Reason 1: Square banne ke liye opposite sides ka distance same hona zaroori hai

// Square ka side length =
// Exact same distance between two horizontal cuts
// AND
// exact same distance between two vertical cuts.

// Consecutive +1 gaps sirf chhote squares detect karte hain.
// Lekin non-consecutive fences bhi bada square bana sakte hain.

// ❌ Reason 2: Large distances bhi possible hote hain without consecutive fences

// Example:

// hFences = [2]
// → Points are 1, 2, 4
// Distances: 1, 2, 3  (3 = 4 − 1)


// Yahan 3 length ka square possible hai,
// BUT tumhara consecutive logic sirf 1 return karega.

// ❌ **Reason 3: Consecutive se sirf unit-gaps ka streak milta hai,

// but problem ko ALL distances chahiye**

// Distance banne ke liye sirf adjacent hona zaroori nahi.

// Example:

// 1 ---- 2 ---- 4


// Even though 2 to 4 is NOT consecutive,
// distance = 2 possible hai.

// ❌ Reason 4: Vertical aur horizontal dono me SAME distance match karna zaroori hai

// Agar horizontal me 3 length mile
// aur vertical me bhi 3 mile
// → valid square.

// Tumhara code:

// horizontal streak = 1

// vertical streak = 2
// → 3 ko completely ignore kar deta hai.

// ❌ Real failing test case (official)
// m=4, n=4
// hFences=[2] → distances = 1,2,3
// vFences=[2,3] → distances = 1,2,3

// Correct side = 3 → area = 9


// Tumhara logic:

// hMax = 1

// vMax = 2

// side = 1
// ❌ Wrong

// 📌 Final Summary to Add in Your Code Comment
// NOTE: This consecutive-gap logic is NOT enough for this problem.

// Because square formation requires matching distances between ANY two fences 
// (not only consecutive fences). Even non-adjacent fences can create valid and 
// larger distances. Therefore, only checking the longest streak of +1 differences 
// fails on test cases where large distances come from non-consecutive fences.

// Hence we must compute ALL pair distances and take the largest common distance 
// from horizontal and vertical cuts.







// 🧠 Test Case
// m = 4
// n = 4

// hFences = [2]
// vFences = [2, 3]


// Grid ko assume karo:

// Rows → horizontal

// Columns → vertical

// Boundary hamesha 1 se start hoti hai aur m / n par end hoti hai

// 🔹 Step 1: Horizontal fences ko visualize karo
// Horizontal positions:
// 1 -------- 2 -------- 4


// (1 aur 4 boundary hain, 2 fence hai)

// Possible horizontal distances:

// Between 1 and 2 → 1

// Between 2 and 4 → 2

// Between 1 and 4 → 3

// 👉 Horizontal distances = {1, 2, 3}

// ⚠️ Important:

// Distance 3 bana hai non-consecutive fences se

// Tumhara current code sirf 1 (consecutive) dekhta hai,
// 3 ko ignore kar deta hai

// 🔹 Step 2: Vertical fences ko visualize karo
// Vertical positions:
// 1 ---- 2 ---- 3 ---- 4


// (boundaries + fences)

// Possible vertical distances:

// 1 ↔ 2 → 1

// 2 ↔ 3 → 1

// 3 ↔ 4 → 1

// 1 ↔ 3 → 2

// 2 ↔ 4 → 2

// 1 ↔ 4 → 3

// 👉 Vertical distances = {1, 2, 3}

// 🔹 Step 3: Square ka rule (MOST IMPORTANT)

// 📌 Square banne ke liye condition:

// Same distance horizontal me bhi ho
// Same distance vertical me bhi ho

// Matlab:

// Common distances = intersection(horizontal, vertical)
//                 = {1, 2, 3}


// 👉 Largest possible side = 3

// 🔹 Step 4: Actual square ko imagine karo
// (1,1) ┌───────────┐ (4,1)
//       │           │
//       │           │   ← side = 3
//       │           │
// (1,4) └───────────┘ (4,4)


// Left boundary → 1

// Right boundary → 4

// Top boundary → 1

// Bottom boundary → 4

// ✔ Valid square
// ✔ Fence positions respected
// ✔ Side = 3
// ✔ Area = 9

// ❌ Tumhara current logic yahan kyun fail hota hai?

// Tum kya check kar rahe ho:

// Sirf consecutive (+1) gaps


// Horizontal:

// 1 → 2 → 4
// Only 1 consecutive gap
// hMax = 1


// Vertical:

// 1 → 2 → 3 → 4
// Two consecutive gaps
// vMax = 2


// 👉 Tum bolte ho:

// side = min(1, 2) = 1


// ❌ But distance 3 to dono direction me exist karta tha,
// use tumne kabhi consider hi nahi kiya.

// 🧠 Core Insight (yaad rakhne wali line)

// Square side consecutive hone se nahi,
// same distance hone se banta hai.

// Non-adjacent fences bhi valid aur bigger square bana sakte hain.

// 📌 One-line intuition (interview gold)

// “We must consider distances between every pair of fences, because the largest square can be formed using non-consecutive cuts.”














class Solution {
    int MOD = 1000000007;

    // Collect all possible distances between fence positions
    private Set<Integer> getAllDistances(int total, int[] fences) {
        int len = fences.length;

        // Include boundaries
        int[] arr = new int[len + 2];
        arr[0] = 1;
        arr[len + 1] = total;

        for (int i = 0; i < len; i++) {
            arr[i + 1] = fences[i];
        }

        Arrays.sort(arr);

        Set<Integer> distances = new HashSet<>();

        // Calculate all pair distances
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                distances.add(arr[j] - arr[i]);
            }
        } // this works because 1 and m or n are only fixed... there is nothing fixed in between... there are only removeable only if there are ... so we can assume ki agar gap b/w 1 and 2 then 1 and 3 .. kyunki hm maan rhe ki 1 & 3 ke biche me bhi ht skte hain honge ydi , ya fir honge hi nhi ... thats it

        return distances;
    }

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {

        Set<Integer> hDistances = getAllDistances(m, hFences);
        Set<Integer> vDistances = getAllDistances(n, vFences);

        int maxSide = 0;

        // Find largest common distance
        for (int d : hDistances) {
            if (vDistances.contains(d)) {
                maxSide = Math.max(maxSide, d);
            }
        }

        if (maxSide == 0) return -1;

        return (int) ((1L * maxSide * maxSide) % MOD);
    }
}
