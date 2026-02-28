
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




// ------------------------------------------------------------
// GRAND SUMMARY — SUM OF DISTANCES IN TREE (Re-rooting DP)
// ------------------------------------------------------------

// Goal: For every node, compute sum of distances to all other nodes
//       in O(N) using two DFS passes.

// ------------------------------------------------------------
// 1) Data Structures:
//    res[node]   = final answer for each node (sum of distances)
//    count[node] = size of subtree of 'node' (including itself)
//    tree        = adjacency list representation of the tree
// ------------------------------------------------------------

// ------------------------------------------------------------
// 2) First DFS (dfsBase) — Post-order (Bottom-Up):
//    Purpose:
//      - Compute subtree sizes (count[node])
//      - Compute sum of distances from root (0) to all nodes
//
//    Steps:
//      count[node] = 1 (count itself)
//      For each child:
//         - Recursively solve child
//         - Add child subtree size to current count
//         - Add child's res + subtree size to current res
//
//    Meaning of:
//      res[node] += res[child] + count[child]
//      -> Every node in child's subtree is 1 step further from "node"
//         than from "child"
// ------------------------------------------------------------

// ------------------------------------------------------------
// 3) After dfsBase:
//    res[0] contains sum of distances from root (0) to all nodes
//    count[] contains subtree sizes for every node
// ------------------------------------------------------------

// ------------------------------------------------------------
// 4) Second DFS (dfsReroot) — Pre-order (Top-Down):
//    Purpose:
//      - Reroot the tree at every child
//      - Compute res[child] from res[parent] in O(1)
//
//    Formula:
//      res[child] = res[node] - count[child] + (n - count[child])
//
//    Explanation:
//      - Moving root from "node" to its child:
//          Nodes in child's subtree → distance decreases by 1
//          Nodes outside subtree   → distance increases by 1
//
//      - count[child] = subtree size
//      - (n - count[child]) = nodes outside that subtree
// ------------------------------------------------------------

// ------------------------------------------------------------
// 5) After dfsReroot:
//    res[i] contains sum of distances from node i to all nodes.
//    Entire solution runs in O(N) time.
// ------------------------------------------------------------