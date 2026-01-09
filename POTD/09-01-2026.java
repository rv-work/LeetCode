class Solution {

    TreeNode ans = null;  

    boolean isParent(TreeNode root, List<TreeNode> deepestNodes) {
        if (root == null && deepestNodes.size() != 0) return false;

        if (deepestNodes.contains(root)) {
            deepestNodes.remove(root);
        }

        if (deepestNodes.size() == 0) return true;

        return isParent(root.left, deepestNodes) ||
               isParent(root.right, deepestNodes);
    }

    void findParent(TreeNode root, List<TreeNode> deepestNodes) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (isParent(node, new ArrayList<>(deepestNodes))) {
                ans = node;   
            }

            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        List<TreeNode> deepestNodes = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            deepestNodes.clear();  

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                deepestNodes.add(node);

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        findParent(root, deepestNodes);
        return ans;
    }
}








class Solution {

    TreeNode ans = null;

    int countMatches(TreeNode root, Set<TreeNode> deepestSet) {
        if (root == null) return 0;

        int count = deepestSet.contains(root) ? 1 : 0;

        count += countMatches(root.left, deepestSet);
        count += countMatches(root.right, deepestSet);

        return count;
    }

    void findParent(TreeNode root, Set<TreeNode> deepestSet) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int need = deepestSet.size();

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (countMatches(node, deepestSet) == need) {
                ans = node;
            }

            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        Set<TreeNode> deepestSet = new HashSet<>();

        while (!q.isEmpty()) {
            int size = q.size();
            deepestSet.clear();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                deepestSet.add(node);

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        findParent(root, deepestSet);
        return ans;
    }
}








/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        parent.put(root, null);

        List<TreeNode> lastLevel = new ArrayList<>();

       
        while (!q.isEmpty()) {
            int size = q.size();
            lastLevel.clear();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                lastLevel.add(node);

                if (node.left != null) {
                    parent.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    parent.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }


        while(lastLevel.size() > 1){
            List<TreeNode> upper = new ArrayList<>();
            for(int i = 0; i<lastLevel.size(); i++){
                TreeNode node = parent.get(lastLevel.get(i));
                if(!upper.contains(node)) upper.add(node);
            }
            lastLevel = upper;
        }

        return lastLevel.get(0);
    }
}












/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        parent.put(root, null);

        List<TreeNode> lastLevel = new ArrayList<>();

       
        while (!q.isEmpty()) {
            int size = q.size();
            lastLevel.clear();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                lastLevel.add(node);

                if (node.left != null) {
                    parent.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    parent.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }


        while (lastLevel.size() > 1) {
            
           Set<TreeNode> upper = new HashSet<>();
           for (TreeNode node : lastLevel) {
               upper.add(parent.get(node));  
           }
           lastLevel = new ArrayList<>(upper);
        }       

        return lastLevel.get(0);
    }
}









/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        parent.put(root, null);

        Set<TreeNode> lastLevel = new HashSet<>();

      
        while (!q.isEmpty()) {
            int size = q.size();
            lastLevel.clear();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                lastLevel.add(node);

                if (node.left != null) {
                    parent.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    parent.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }

        
        while (lastLevel.size() > 1) {
            Set<TreeNode> upper = new HashSet<>();
            for (TreeNode node : lastLevel) {
                upper.add(parent.get(node));
            }
            lastLevel = upper;
        }

        return lastLevel.iterator().next();
    }
}













class Solution {

    int max = 0;

    void dfsDepth(TreeNode root , Map<TreeNode , Integer> depth , int d){
        if(root == null) return ;
       
       max = Math.max(max , d);
       depth.put(root , d);
       dfsDepth(root.left , depth, d+1);
       dfsDepth(root.right , depth, d+1);
    }

    TreeNode dfsAns(TreeNode root , Map<TreeNode , Integer> depth){
        if(root == null) return null;
        if(depth.get(root) == max) return root;

        TreeNode left = dfsAns(root.left , depth);
        TreeNode right = dfsAns(root.right , depth);
        
        if(left != null && right != null) return root;
        if(left == null && right == null) return null;
        return left == null ? right : left;

    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode , Integer> depth = new HashMap<>();

        dfsDepth(root , depth , 0);
        
        return dfsAns(root , depth);
        
    }
}









/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    class Out{
        int height;
        TreeNode node;
        Out(int h ,TreeNode node){
            this.height = h;
            this.node = node;
        }
    }
        
    
        Out dfsAns(TreeNode root){
            if(root == null) return new Out(0 , null);
    
            Out left = dfsAns(root.left );
            Out right = dfsAns(root.right );
            
            if(left.height == right.height ) return new Out(right.height+1 , root);
            if(left.height > right.height ) return new Out(left.height+1 , left.node);
            else return new Out(right.height+1 , right.node);
    
        }
    
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            
            
            return dfsAns(root).node;
            
        }
    }