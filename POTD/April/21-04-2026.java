




class Solution {

    void dfs(int node, List<Integer>[] graph, int[] indexToGroup, int groupId) {
        indexToGroup[node] = groupId; 
        
        for (int neighbor : graph[node]) {
            if (indexToGroup[neighbor] == -1) { 
                dfs(neighbor, graph, indexToGroup, groupId);
            }
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] swap : allowedSwaps) {
            graph[swap[0]].add(swap[1]);
            graph[swap[1]].add(swap[0]); 
        }
        

        int[] indexToGroup = new int[n];
        Arrays.fill(indexToGroup, -1); 
        int currentGroupId = 0;
        
        for (int i = 0; i < n; i++) {
            if (indexToGroup[i] == -1) {
                dfs(i, graph, indexToGroup, currentGroupId);
                currentGroupId++; 
            }
        }
        

        Map<Integer, Map<Integer, Integer>> groupToFreq = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int gId = indexToGroup[i];
            
            groupToFreq.putIfAbsent(gId, new HashMap<>());
            Map<Integer, Integer> freqMap = groupToFreq.get(gId);
            
            freqMap.put(source[i], freqMap.getOrDefault(source[i], 0) + 1);
        }
        
 
        int hammingDistance = 0;
        
        for (int i = 0; i < n; i++) {
            int gId = indexToGroup[i];
            Map<Integer, Integer> freqMap = groupToFreq.get(gId);
            int targetVal = target[i];
            
            if (freqMap != null && freqMap.containsKey(targetVal) && freqMap.get(targetVal) > 0) {
                freqMap.put(targetVal, freqMap.get(targetVal) - 1); 
            } else {
                hammingDistance++; 
            }
        }
        
        return hammingDistance;
    }
}



