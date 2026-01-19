class Solution {
    
    
    int res(List<List<Integer>> g , int i , int j , int n , int m  , int dp [][]){
        if(i >= n && j >= m) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        
        int down = Integer.MIN_VALUE; int right = Integer.MIN_VALUE;
        if(i+1 < m) down =  g.get(i+1).get(j) - g.get(i).get(j) + res(g , i+1 , j , n , m , dp);
        if(j+1 < n) right =  g.get(i).get(j+1) - g.get(i).get(j) + res(g , i , j+1 , n , m , dp);

        return dp[i][j] =  Math.max(0 , Math.max(down , right));

    }

    public int maxScore(List<List<Integer>> g) {
        int m = g.size();
        int n = g.get(0).size();

        int dp [][] = new int[m][n];
        for(int [] arr : dp) Arrays.fill(arr , -1);

        
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                max2 = Math.max(max2 , res(g , i , j , n , m , dp));
                if(j+1 < n) max1 = Math.max(max1 , g.get(i).get(j+1) - g.get(i).get(j));
                if(i+1 < m) max1 = Math.max(max1 , g.get(i+1).get(j) - g.get(i).get(j));
            }
        }

        if(max2==0) return max1;

        return max2;

       
    }
}














// why this adjacent works.........

// Yes, it is ALWAYS possible.
// 100% cases.
// Kabhi fail nahi hota.
// Kyunki subtraction “telescopic” hoti hai.

// Beech ke saare terms cancel ho jaate hain.
// Only start value aur end value bachta hai.

// Isliye:

// direct jump score  
// =  
// combined adjacent score


// ALWAYS TRUE.










// 🌟 WHY is jump score = adjacent score ALWAYS?

// Imagine tum ek number line par chal rahe ho → values steps ki height jaisi hain.

// Example row:

// 5   1   100


// Tum jab jump karte ho:

// 5 → 100  
// score = 100 - 5 = +95

// Ab socho tum beech ke steps se walking karte ho:
// 5 → 1 → 100


// Score:

// (1 - 5) + (100 - 1)
// = -4 + 99
// = 95


// 💥 SAME RESULT.

// 🧠 CORE INTUITION (Without Math):

// 👉 Jab tum ek number se doosre number tak ja rahe ho,
// to final difference sirf “starting” aur “ending” numbers par depend karta hai.
// Beech me kya hua, matter hi nahi karta.

// Why?

// Because every “up” you gain on a step is compensated when you go “down” later.

// Let's visualize the cancellation.

// 🎨 SUPER VISUAL EXAMPLE (Best Explanation)

// Row:

// a   b   c   d


// Walking path score:

// (b - a) + (c - b) + (d - c)


// Ab dekh cancellation LIVE:

//  b - a  
//  c - b  
//  d - c
// ---------
//  -a + b - b + c - c + d  


// Middle values CUT-CUT-CUT-CUT 😂

// Bach kya?

// d - a


// ⭐ EXACTLY equal to jumping a → d.

// 🌈 DEKH, ek real life analogy

// Tum 5th floor par ho → tumko 15th floor jana hai.

// OPTION A (Lift):

// 5 → 15  
// Difference = +10 floors


// OPTION B (Stairs one floor at a time):

// 5 → 6 → 7 → 8 → ... → 15


// Net change?

// +1 +1 +1 + … (10 times) = +10 floors


// 🤷 Same end-floor difference = same net gain.

// Beech me tum niche/uppar jaao, end points hi matter karte hain.

// 🧩 Ek Ajeeb Example jo proof karta hai

// Row:

// 50, 2, 999, 3, 1000


// Jump:

// 1000 - 50 = +950


// Adjacent:

// 2 - 50 = -48  
// 999 - 2 = +997  
// 3 - 999 = -996  
// 1000 - 3 = +997  
// Total = -48 + 997 - 996 + 997 = 950


// ✔ EXACT SAME.

// Chahe values kaise bhi crazy ho jaayein —
// net difference bas first aur last par depend karta hai.