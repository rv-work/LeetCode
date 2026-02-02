
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
     
        Map<Integer, TreeSet<String>> ans = new HashMap<>(); 
        
        // Map: Email -> Group ID
        Map<String, Integer> etg = new HashMap<>(); 
        
        // Map: Group ID -> Name
        Map<Integer, String> gtn = new HashMap<>(); 
        
        int cnt = 0; // Group ID counter

        for(List<String> row : accounts){
            String name = row.get(0);
            int groupID = -1;

            // 1. First Pass: Check if any email in this row already belongs to a group
            for(int i = 1; i < row.size(); i++){
                String email = row.get(i);
                if(etg.containsKey(email)){
                    int existingID = etg.get(email);
                    
                    // If we haven't found a group yet, take this one
                    if (groupID == -1) {
                        groupID = existingID;
                    } 
                    // CRITICAL FIX: If we found a DIFFERENT group ID in the same row,
                    // we must MERGE the old group into the current groupID.
                    else if (groupID != existingID) {
                        // This loop replaces "Union Find". It updates all emails 
                        // that pointed to 'existingID' to now point to 'groupID'
                        for (String key : etg.keySet()) {
                            if (etg.get(key) == existingID) {
                                etg.put(key, groupID);
                            }
                        }
                    }
                }
            }

            // 2. Determine final ID for this row
            if(groupID == -1){
                groupID = cnt++; // Create new Group ID
            }

            // 3. Second Pass: Assign all emails in this row to the determined groupID
            gtn.put(groupID, name); // Store name for this group
            for(int i = 1; i < row.size(); i++){
                String email = row.get(i);
                etg.put(email, groupID);
            }
        }

        // 4. Collect results (Group by ID, not Name)
        // We iterate the map we built to group emails
        for(Map.Entry<String, Integer> entry : etg.entrySet()){
            String email = entry.getKey();
            int id = entry.getValue();
            
            ans.putIfAbsent(id, new TreeSet<>());
            ans.get(id).add(email);
        }

        // 5. Format Output
        List<List<String>> res = new ArrayList<>();
        for(Integer id : ans.keySet()){
            List<String> row = new ArrayList<>();
            row.add(gtn.get(id)); // Add Name first
            row.addAll(ans.get(id)); // Add sorted emails
            res.add(row);
        }
        
        return res;
    }
}