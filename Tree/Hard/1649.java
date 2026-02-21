class Solution {
    int[] tree;
    int MAX = 100001; // instructions ki max value ke hisaab se
    int MOD = 1000000007;

    public int createSortedArray(int[] instructions) {
        // Segment tree ka size 4 * N hota hai
        tree = new int[4 * MAX];
        int cost = 0;

        for (int x : instructions) {
            // 1. x se strictly chote numbers ka sum (Range: 0 to x-1)
            int strictlyLess = query(0, 0, MAX - 1, 0, x - 1);
            
            // 2. x se strictly bade numbers ka sum (Range: x+1 to MAX-1)
            int strictlyGreater = query(0, 0, MAX - 1, x + 1, MAX - 1);

            // 3. Minimum cost add karo
            cost = (cost + Math.min(strictlyLess, strictlyGreater)) % MOD;

            // 4. x ki frequency Segment Tree me update (+1) kar do
            update(0, 0, MAX - 1, x);
        }

        return cost;
    }

    // Point Update: index 'idx' ki frequency 1 bada do
    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            tree[node]++;
            return;
        }
        
        int mid = start + (end - start) / 2;
        
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx);
        } else {
            update(2 * node + 2, mid + 1, end, idx);
        }
        
        // Dono bacchon ka sum parent me daalo
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range Query: [l, r] ke beech ka total sum nikalo
    private int query(int node, int start, int end, int l, int r) {
        // No overlap (Bahar nikal gaye)
        if (r < start || l > end) {
            return 0; 
        }
        
        // Complete overlap (Pura andar hai)
        if (l <= start && end <= r) {
            return tree[node]; 
        }
        
        // Partial overlap (Aadha andar, aadha bahar)
        int mid = start + (end - start) / 2;
        int p1 = query(2 * node + 1, start, mid, l, r);
        int p2 = query(2 * node + 2, mid + 1, end, l, r);
        
        return p1 + p2;
    }
}









class Solution {
    int[] tree;
    int[] lazy;
    int MAX = 100001;
    int MOD = 1000000007;

    public int createSortedArray(int[] instructions) {
        // Tree aur Lazy array initialize karo
        tree = new int[4 * MAX];
        lazy = new int[4 * MAX];
        
        int cost = 0;

        for (int x : instructions) {
            // 1. x se strictly chote numbers ka sum
            int strictlyLess = query(0, 0, MAX - 1, 0, x - 1);
            
            // 2. x se strictly bade numbers ka sum
            int strictlyGreater = query(0, 0, MAX - 1, x + 1, MAX - 1);

            // 3. Minimum cost add karo
            cost = (cost + Math.min(strictlyLess, strictlyGreater)) % MOD;

            // 4. Update karo (Range update logic use kar rahe hain, par range sirf [x, x] hai)
            update(0, 0, MAX - 1, x, x, 1); 
        }

        return cost;
    }

    // Lazy pending updates ko niche push karne ka function
    private void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            // Sum tree hai, to agar poori range me 'lazy' add ho raha hai, 
            // to total sum me (lazy * range_length) add hoga
            tree[node] += lazy[node] * (end - start + 1);

            // Agar leaf node nahi hai, to bachon ko pass on karo
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            // Current node ka kaam ho gaya, isko 0 kar do
            lazy[node] = 0;
        }
    }

    // Range Update Function
    private void update(int node, int start, int end, int l, int r, int val) {
        // Pehle pending lazy updates clear karo
        push(node, start, end);

        // No overlap
        if (start > r || end < l) return;

        // Complete overlap
        if (l <= start && end <= r) {
            lazy[node] += val; // Lazy me daal do
            push(node, start, end); // Aur turant update kar lo
            return;
        }

        // Partial overlap
        int mid = start + (end - start) / 2;
        update(2 * node + 1, start, mid, l, r, val);
        update(2 * node + 2, mid + 1, end, l, r, val);

        // Dono bachon ka sum parent me daalo
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range Query Function
    private int query(int node, int start, int end, int l, int r) {
        // Query karne se pehle pending updates clear karna zaruri hai
        push(node, start, end);

        // No overlap
        if (start > r || end < l) return 0;

        // Complete overlap
        if (l <= start && end <= r) return tree[node];

        // Partial overlap
        int mid = start + (end - start) / 2;
        int p1 = query(2 * node + 1, start, mid, l, r);
        int p2 = query(2 * node + 2, mid + 1, end, l, r);

        return p1 + p2;
    }
}








class Solution {
    int[] less;
    int[] greater;

    void mergeSort(int[][] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    void merge(int[][] arr, int left, int mid, int right) {
        int pLess = left;
        int pGreater = left;

        // Right half ke har element ke liye left half me check karenge
        for (int i = mid + 1; i <= right; i++) {
            // 1. Strictly Less count karo
            // Jab tak left ka element chhota hai, pointer aage badhao
            while (pLess <= mid && arr[pLess][0] < arr[i][0]) {
                pLess++;
            }
            // pLess - left = Total elements in left half strictly less than arr[i]
            less[arr[i][1]] += (pLess - left);

            // 2. Strictly Greater count karo
            // Jab tak left ka element chhota ya barabar hai, pointer aage badhao
            while (pGreater <= mid && arr[pGreater][0] <= arr[i][0]) {
                pGreater++;
            }
            greater[arr[i][1]] += (mid - pGreater + 1);
        }

        int[][] temp = new int[right - left + 1][2];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        less = new int[n];
        greater = new int[n];
        
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = instructions[i];
            arr[i][1] = i;
        }

        mergeSort(arr, 0, n - 1);

        long cost = 0;
        int MOD = 1000000007;

        for (int i = 0; i < n; i++) {
            cost = (cost + Math.min(less[i], greater[i])) % MOD;
        }

        return (int) cost;
    }
}