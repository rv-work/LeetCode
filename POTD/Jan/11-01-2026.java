class Solution {
    public int maximalRectangle(char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // LEFT → RIGHT prefix (width build)
        for (int i = 0; i < n; i++) {
            int pre = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == '0') pre = 0;
                else pre++;
                mat[i][j] = (char)(pre); 
            }
        }

        int max = 0;

        // FIX COLUMN, GO DOWN
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (mat[i][j] == 0) continue;

                int minWidth = Integer.MAX_VALUE;
                for (int k = i; k < n && mat[k][j] != 0; k++) {
                    minWidth = Math.min(minWidth, mat[k][j]); 
                    int height = k - i + 1;
                    max = Math.max(max, minWidth * height);
                }
            }
        }
        return max;
    }
}






// Main pehle matrix ki har row ko left-to-right prefix me convert karta hoon, jahan har cell yeh store karta hai ki us point par continuous 1s ki maximum width kitni hai. Iske baad main har column ko fix karke neeche ki taraf rectangle expand karta hoon. Neeche jaate waqt main minWidth maintain karta hoon, jo batata hai ki ab tak include ki gayi sabhi rows me sabse chhoti available width kya hai, kyunki rectangle ki width har row me same honi chahiye. Saath hi height calculate hoti hai jo rectangle ki vertical length (number of rows) batati hai. Har step par main area = minWidth × height nikalta hoon aur maximum area update karta hoon.


// Exa...............................




// 📌 Example Input
// matrix =
// 1 1 0
// 1 1 1
// 1 1 1


// n = 3 , m = 3

// 🔹 1️⃣ First Loop
// (LEFT → RIGHT prefix → width build)

// Har row me continuous 1s ki width store karte hain.

// Row 0
// 1 1 0
// ↓
// 1 2 0

// Row 1
// 1 1 1
// ↓
// 1 2 3

// Row 2
// 1 1 1
// ↓
// 1 2 3

// ✅ Matrix after 1st loop
// 1 2 0
// 1 2 3
// 1 2 3


// Ab har cell bata raha hai:

// “agar rectangle yahin khatam ho, to max width kitni ho sakti hai”

// 🔹 2️⃣ Second Loop
// (Har column fix → neeche expand)
// 📍 Column j = 0

// Column values:

// 1
// 1
// 1

// i = 0

// k = 0 → minWidth = 1, height = 1 → area = 1

// k = 1 → minWidth = 1, height = 2 → area = 2

// k = 2 → minWidth = 1, height = 3 → area = 3

// i = 1

// k = 1 → minWidth = 1, height = 1 → area = 1

// k = 2 → minWidth = 1, height = 2 → area = 2

// i = 2

// k = 2 → minWidth = 1, height = 1 → area = 1

// ➡️ Max from column 0 = 3

// 📍 Column j = 1

// Column values:

// 2
// 2
// 2

// i = 0

// k = 0 → minWidth = 2, height = 1 → area = 2

// k = 1 → minWidth = 2, height = 2 → area = 4

// k = 2 → minWidth = 2, height = 3 → area = 6 ✅

// i = 1

// k = 1 → minWidth = 2, height = 1 → area = 2

// k = 2 → minWidth = 2, height = 2 → area = 4

// i = 2

// k = 2 → minWidth = 2, height = 1 → area = 2

// ➡️ Max from column 1 = 6

// 📍 Column j = 2

// Column values:

// 0
// 3
// 3

// i = 0

// value = 0 → skip

// i = 1

// k = 1 → minWidth = 3, height = 1 → area = 3

// k = 2 → minWidth = 3, height = 2 → area = 6

// i = 2

// k = 2 → minWidth = 3, height = 1 → area = 3

// ➡️ Max from column 2 = 6

// 🏁 Final Answer
// Maximum Rectangle Area = 6

// 🧠 Final Intuition (1 line)

// Pehle har cell me us point tak ki width store karte hain,
// phir har column se neeche jaate hue minimum width aur height ka rectangle bana-bana kar maximum area nikalte hain.