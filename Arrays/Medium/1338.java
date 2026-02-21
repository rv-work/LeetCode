class Solution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        
        int[] freq = new int[100001];
        for (int x : arr) freq[x]++;
        
        List<Integer> list = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) list.add(f);
        }
        
        list.sort((a, b) -> b - a);
        
        int removed = 0;
        int count = 0;
        
        for (int f : list) {
            removed += f;
            count++;
            if (removed >= n / 2) break;
        }
        
        return count;
    }
}