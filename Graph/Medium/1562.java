import java.util.*;

class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        
        // Map: Index -> Group ID (We only strictly need to map the endpoints)
        Map<Integer, Integer> indexToGroup = new HashMap<>();
        
        // List: Group ID -> int[]{start, end}
        List<int[]> groups = new ArrayList<>();
        
        int mCount = 0; // Tracks how many groups of size 'm' exist
        int latestStep = -1;

        for (int i = 0; i < n; i++) {
            int pos = arr[i];
            
            int start = pos;
            int end = pos;

            // 1. Check Left Neighbor (pos - 1)
            if (indexToGroup.containsKey(pos - 1)) {
                int leftGrpId = indexToGroup.get(pos - 1);
                int[] leftGrp = groups.get(leftGrpId);
                
                // If the left group was size m, we are about to break/merge it, so decrement
                if (leftGrp[1] - leftGrp[0] + 1 == m) {
                    mCount--;
                }
                
                // Update our start to be the left group's start
                start = leftGrp[0];
            }

            // 2. Check Right Neighbor (pos + 1)
            if (indexToGroup.containsKey(pos + 1)) {
                int rightGrpId = indexToGroup.get(pos + 1);
                int[] rightGrp = groups.get(rightGrpId);
                
                // If the right group was size m, decrement
                if (rightGrp[1] - rightGrp[0] + 1 == m) {
                    mCount--;
                }
                
                // Update our end to be the right group's end
                end = rightGrp[1];
            }

            // 3. Create the New Merged Group
            int newGrpId = groups.size();
            groups.add(new int[]{start, end});

            // 4. Update Map: Only the boundaries (start & end) need to point to the new ID
            // This ensures future merges find the correct current group
            indexToGroup.put(start, newGrpId);
            indexToGroup.put(end, newGrpId);
            // Optionally put 'pos' too, though only boundaries matter for logic
            indexToGroup.put(pos, newGrpId); 

            // 5. Check if the new merged group is size m
            if (end - start + 1 == m) {
                mCount++;
            }

            if (mCount > 0) {
                latestStep = i + 1;
            }
        }

        return latestStep;
    }
}











class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        // length[i] stores the length of the group ending or starting at index i
        int[] length = new int[n + 2]; /// diff Array type.....
        int count = 0; // Tracks how many groups of size 'm' currently exist
        int latestStep = -1;

        for (int i = 0; i < n; i++) {
            int pos = arr[i];
            
            // 1. Get lengths of adjacent groups (if they exist)
            int left = length[pos - 1];
            int right = length[pos + 1];
            
            // 2. Calculate the new merged length
            int newLen = left + right + 1;
            
            // 3. Update the "Start" and "End" of this new large group
            // pos - left is the start index of the left group
            // pos + right is the end index of the right group
            length[pos - left] = newLen;
            length[pos + right] = newLen;

            // 4. Update the count of groups of size m
            // If an adjacent group had size m, it's now merged (destroyed), so decrement
            if (left == m) count--;
            if (right == m) count--;
            // If the new merged group is size m, increment
            if (newLen == m) count++;

            // 5. If groups of size m exist, update latestStep
            if (count > 0) {
                latestStep = i + 1;
            }
        }
        
        return latestStep;
    }
}