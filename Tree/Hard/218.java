class Solution {
    class Segment{
        int tree [];

        Segment(int n ){
            tree = new int[4*n];
        }

        void update (int node , int start , int end , int l , int r , int val){
            if(start > r || end < l) return ;
            if(start == end){
                tree[node] = Math.max(tree[node],val);
                return;
            }
            int mid = (start+end)>>1;
            update(2*node+1,start , mid , l , r val);
            update(2*node+2,mid+1 , end , l , r val);
            tree[node] = Math.max(tree[2*node+1],tree[2*node+2]);
        }

        int query(int node , int start , int end , int idx){
            if(start == end) return tree[node];

            int mid = (start+end)>>1;

            if(idx <= mid){
                return query(2*node+1,start , mid , l , r);
            }else {
                return query(2*node+2,mid+1 ,end , l , r);
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
        int right = map.get(b[1]) - 1;  // kyunki end vale me iska effect nhi ho rha hai 

        seg.update(0, 0, size - 1, left, right, b[2]);
    }

    List<List<Integer>> ans = new ArrayList<>();

    int last = -1;
    for(int i = 0; i<size; i++){
        int building = arr[i];
        int height = seg.query(0,0,size - 1 , i);
        if(last == height) continue;
        
        ans.add(Arrays.asList(building, height));
        last = height;
    }


    return ans;
}
}