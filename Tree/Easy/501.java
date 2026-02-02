class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();

    public void func(TreeNode root) {
        if(root==null) return;
        func(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        func(root.right);
    }

    public int[] findMode(TreeNode root) {

        if(root==null) return null;
        func(root);

        int max = 0;
        int size = map.size();
        int arr[][] = new int[size][2];
        int i=0;

        for (int key : map.keySet()) {
            arr[i][0] = key;
            arr[i][1] = map.get(key);
            i++;
        }
        Arrays.sort(arr,(a,b)->{
            return b[1] - a[1];
        });
        i = 0;
        int j=1;

        while(j<size && arr[j][1]==arr[i][1]){
            j++;
        }

        int ans[] = new int[j];

        for(int x=0;x<j;x++){
            ans[x] = arr[x][0];
        }
        return ans;
    }
}