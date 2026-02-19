class Solution {
    class Segment {
        int tree[];

        Segment(int n) {
            tree = new int[4 * n];
        }

        void update (int node , int start , int end , int l , int r , int val){
            if(start > r || end < l) return ;
            if(start == end){
                tree[node] = Math.max(tree[node],val);
                return;
            }
            int mid = (start+end)>>1;
            update(2*node+1,start , mid , l , r, val);
            update(2*node+2,mid+1 , end , l , r, val);
            tree[node] = Math.max(tree[2*node+1],tree[2*node+2]);
        }

        int query(int node, int start, int end, int idx) {
            if (start == end)
                return tree[node];

            int mid = (start + end) >> 1;

            if (idx <= mid) {
                return query(2 * node + 1, start, mid, idx);
            } else {
                return query(2 * node + 2, mid + 1, end, idx);
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        int n = buildings.length;

        // Collect coordinates (sorted automatically)
        Set<Integer> set = new HashSet<>();
        for (int[] b : buildings) {
            set.add(b[0]);
            set.add(b[1]);
        }

        // Move into sorted array
        int size = set.size();
        int[] arr = new int[size];
        int idx = 0;
        for (int x : set) {
            arr[idx++] = x;
        }

        Arrays.sort(arr);

        // Build compression map
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(arr[i], i);
        }

        //  Create segment tree
        Segment seg = new Segment(size);

        //  Update using compressed indices
        for (int[] b : buildings) {

            int left = map.get(b[0]);
            int right = map.get(b[1]) - 1; // kyunki end vale me iska effect nhi ho rha hai 

            seg.update(0, 0, size - 1, left, right, b[2]);
        }

        List<List<Integer>> ans = new ArrayList<>();

        int last = -1;
        for (int i = 0; i < size; i++) {
            int building = arr[i];
            int height = seg.query(0, 0, size - 1, i);
            if (last == height)
                continue;

            ans.add(Arrays.asList(building, height));
            last = height;
        }

        return ans;
    }
}











class Solution {
    class Segment {
        int tree[];
        int lazy[];

        Segment(int n) {
            tree = new int[4 * n];
            lazy = new int[4*n];
        }

       void push(int node, int start, int end) {

        if(lazy[node] != 0) {

            tree[node] = Math.max(tree[node], lazy[node]);

            if(start != end) {  // not leaf
                lazy[2*node+1] = Math.max(lazy[2*node+1], lazy[node]);
                lazy[2*node+2] = Math.max(lazy[2*node+2], lazy[node]);
            }

            lazy[node] = 0;
        }
    }

        void update (int node , int start , int end , int l , int r , int val){
            push(node, start , end);
            if(start > r || end < l) return ;
           
            if(l <= start && end <= r) {

            tree[node] = Math.max(tree[node], val);

            if(start != end) {
                lazy[2*node+1] = Math.max(lazy[2*node+1], val);
                lazy[2*node+2] = Math.max(lazy[2*node+2], val);
            }
            return;
        }
            
            int mid = (start+end)>>1;
            update(2*node+1,start , mid , l , r, val);
            update(2*node+2,mid+1 , end , l , r, val);
            tree[node] = Math.max(tree[2*node+1],tree[2*node+2]);
        }

        int query(int node, int start, int end, int idx) {
            push(node , start , end );
            if (start == end)
                return tree[node];

            int mid = (start + end) >> 1;

            if (idx <= mid) {
                return query(2 * node + 1, start, mid, idx);
            } else {
                return query(2 * node + 2, mid + 1, end, idx);
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        int n = buildings.length;

        // Collect coordinates (sorted automatically)
        Set<Integer> set = new HashSet<>();
        for (int[] b : buildings) {
            set.add(b[0]);
            set.add(b[1]);
        }

        // Move into sorted array
        int size = set.size();
        int[] arr = new int[size];
        int idx = 0;
        for (int x : set) {
            arr[idx++] = x;
        }

        Arrays.sort(arr);

        // Build compression map
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(arr[i], i);
        }

        //  Create segment tree
        Segment seg = new Segment(size);

        //  Update using compressed indices
        for (int[] b : buildings) {

            int left = map.get(b[0]);
            int right = map.get(b[1]) - 1; // kyunki end vale me iska effect nhi ho rha hai 

            seg.update(0, 0, size - 1, left, right, b[2]);
        }

        List<List<Integer>> ans = new ArrayList<>();

        int last = -1;
        for (int i = 0; i < size; i++) {
            int building = arr[i];
            int height = seg.query(0, 0, size - 1, i);
            if (last == height)
                continue;

            ans.add(Arrays.asList(building, height));
            last = height;
        }

        return ans;
    }
}











class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> points = new ArrayList<>();

        for(int b [] : buildings){
            points.add(new int[]{b[0] , -b[2]});
            points.add(new int[]{b[1] , b[2]});
        }

        Collections.sort(points , (a,b) -> {
            if(a[0] == b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        pq.add(0);
        int prevMax = 0;
        for(int p [] : points){
            int x = p[0];
            int h = p[1];

            if(h < 0) { // matlab ye start hai to add
             pq.add(-h);
            } else {
                pq.remove(h);
            }


            int currMax = pq.peek();

            if(currMax != prevMax){
                ans.add(Arrays.asList(x , currMax));
                prevMax = currMax;
            }
        }


        return ans;
    }
}









class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> points = new ArrayList<>();

        // Step 1: Same as before - Start ko negative aur End ko positive
        for (int[] b : buildings) {
            points.add(new int[]{b[0], -b[2]});
            points.add(new int[]{b[1], b[2]});
        }

        // Step 2: Same Sorting Logic (x coordinate pehle, fir height trick)
        Collections.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1]; 
        });

        // Step 3: Use TreeMap instead of PQ <Height, Frequency>
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1); // Ground level hamesha active rahega (Height 0, Count 1)
        
        int prevMax = 0;

        // Step 4: Sweep Line
        for (int[] p : points) {
            int x = p[0];
            int h = p[1];

            if (h < 0) {
                // START Event: Height add karo ya count badhao
                int height = -h; // asli height nikali
                map.put(height, map.getOrDefault(height, 0) + 1);
            } else {
                // END Event: Height ka count kam karo
                int count = map.get(h);
                if (count == 1) {
                    // Agar ek hi building thi is height ki, to map se hi hata do
                    map.remove(h);
                } else {
                    // Varna bas count - 1 kar do
                    map.put(h, count - 1);
                }
            }

            // Current Max Height = TreeMap ki sabse badi Key (lastKey)
            int currMax = map.lastKey();

            // Agar Raja (Max Height) badal gaya hai, to Skyline me add karo
            if (currMax != prevMax) {
                ans.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return ans;
    }
}