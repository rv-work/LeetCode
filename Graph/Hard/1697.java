class Solution {

    class DSU{
        int parent[];

        DSU( int n ){
            parent = new int[n];
            for(int i = 0; i<n; i++) parent[i] = i; // suru me sab khud ke ulimate parent hain
        }

        int find(int node){ // ultimate ( top ) parent
            if(parent[node] != node) parent[node] = find(parent[node]);
            return parent[node];
        }
        void union(int a , int b){ // agar dono same me nhi hain to kisi bhi rk me dusra add krdo.
            int rootA = find(a); 
            int rootB = find(b); 
            if(rootA != rootB) parent[rootA] = rootB;
        }
        
    }


    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int Q = queries.length;
        
        boolean ans[] = new boolean[Q];

        int[][] SQ = new int[Q][4];

        for(int i = 0; i<Q; i++){
            SQ[i][0] = queries[i][0]; // src
            SQ[i][1] = queries[i][1]; // tar
            SQ[i][2] = queries[i][2]; // lim
            SQ[i][3] = i;
        }

        Arrays.sort(SQ, (a,b) -> Integer.compare(a[2] , b[2])); // by limit
        Arrays.sort(edgeList, (a,b) -> Integer.compare(a[2] , b[2])); // by weight
        int edgeIndex = 0;
        DSU dsu = new DSU(n);

// sort kiya taki ... hm chhoti limit ke grp bnayenge phle to bdi limit to usm add kr hi skte .. but reverse is not true...... if lim 10 is ok then 0..9 ye sba bhi ok hain ... but 11,12.. ye sab nhi aur 

// edgeIndex ise lekar chalenge kyunki .... chhoti vali chije jo phle process ho chuki hain .. uske aage hi dekhenge hm hmesha ...... kyunki jab limbdi aayegi to .. usse chhoti limit me jo process ho chuke hain vo to ho hi jayenge for sure.... 


        for(int q [] : SQ){
            int u = q[0];
            int v = q[1];
            int lim = q[2];
            int idx = q[3];
            
            while(edgeIndex < edgeList.length && edgeList[edgeIndex][2] < lim){
                int from = edgeList[edgeIndex][0];
                int to = edgeList[edgeIndex][1];
// ab kyunki yha ek connection hai .. matlab in dono ko jod skte hain to .. inke parent same honge to kuch nhi hoga .. varna jud jayenge dono ke grps... varna nya grp bn jayega....
                dsu.union(from , to);
                edgeIndex++;
            }

            if(dsu.find(u) == dsu.find(v)) ans[idx] = true;
        }


        return ans;
    }
}