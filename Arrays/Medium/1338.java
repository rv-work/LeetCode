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



class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr) map.put(x, map.getOrDefault(x, 0) + 1);

        PriorityQueue<Integer> pq =
            new PriorityQueue<>((a, b) -> b - a);

        pq.addAll(map.values());

        int removed = 0, count = 0, half = arr.length / 2;
        while (removed < half) {
            removed += pq.poll();
            count++;
        }

        return count;
    }
}