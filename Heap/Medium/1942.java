class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        
        // attach original index
        int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++){
            arr[i][0] = times[i][0]; // arrival
            arr[i][1] = times[i][1]; // leaving
            arr[i][2] = i;           // original index
        }
        
        // sort by arrival time
        Arrays.sort(arr, (a,b) -> a[0] - b[0]);
        
        // min heap based on leaving time -> {leaveTime, chairNo}
        PriorityQueue<int[]> filled = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        
        // min heap for available chairs
        PriorityQueue<Integer> available = new PriorityQueue<>();
        
        int chairNo = 0;
        
        for(int i = 0; i < n; i++){
            int arrival = arr[i][0];
            int leave = arr[i][1];
            int originalIndex = arr[i][2];
            
            // free chairs
            while(!filled.isEmpty() && filled.peek()[0] <= arrival){
                available.add(filled.poll()[1]);
            }
            
            int assignedChair;
            
            if(!available.isEmpty()){
                assignedChair = available.poll();
            } else {
                assignedChair = chairNo++;
            }
            
            // if this is target friend → return chair
            if(originalIndex == targetFriend){
                return assignedChair;
            }
            
            // mark chair as filled
            filled.add(new int[]{leave, assignedChair});
        }
        
        return -1;
    }
}
