
class Solution {
    int[] res;
    int[] count;
    List<List<Integer>> tree;

    void dfsBase(int node, int parent) {
      count[node] = 1;
      for(int child : tree.get(node)){
        if(child != parent){
            dfsBase(child , node);
            count[node] += count[child];
            res[node] += res[child] + count[child];
        }
      }
    }

    void dfsReroot(int node, int parent, int n) {
        for (int child : tree.get(node)) {
            if (child != parent) {
                res[child] = res[node] - count[child] + (n - count[child]);
                dfsReroot(child, node, n);
            }
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        tree = new ArrayList<>();
        res = new int[n];
        count = new int[n];
        
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        
        dfsBase(0, -1);
        
        dfsReroot(0, -1, n);
        
        return res;
    }
}