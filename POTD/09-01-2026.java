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
