class Solution {
    public int[] getBiggestThree(int[][] g) {

        int n = g.length;
        int m = g[0].length;

        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                set.add(g[i][j]); 

                for(int k=1; k<50; k++){

                    if(i + 2*k >= n || j-k < 0 || j+k >= m) break;

                    int sum = 0;

                    int x = i;
                    int y = j;

                    // top -> left
                    for(int p=0;p<k;p++){
                        x++;
                        y--;
                        sum += g[x][y];
                    }

                    // left -> bottom
                    for(int p=0;p<k;p++){
                        x++;
                        y++;
                        sum += g[x][y];
                    }

                    // bottom -> right
                    for(int p=0;p<k;p++){
                        x--;
                        y++;
                        sum += g[x][y];
                    }

                    // right -> top
                    for(int p=0;p<k;p++){
                        x--;
                        y--;
                        sum += g[x][y];
                    }

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] ans = new int[size];

        for(int i = 0; i < size; i++){
            ans[i] = set.pollLast();
        }

        return ans;
    }
}