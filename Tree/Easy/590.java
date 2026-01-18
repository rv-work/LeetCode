/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    void dfs(Node node, List<Integer> ans) {
        if (node == null) return;

        for (Node child : node.children) {
            dfs(child, ans);
        }

        ans.add(node.val);
    }
}









/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Stack<Node> s = new Stack<>();
        s.push(root);

        while(!s.isEmpty()){
            Node node = s.pop();

            for(Node child : node.children){
                if(child != null) s.push(child);
            }

            ans.add(node.val);
        }
        
        Collections.reverse(ans);
        return ans;
    }
}
