class NumArray {
        int n;
        int tree[];

        void build(int idx , int nums[] , int left , int right){
          if(left == right ) {
            tree[idx] = nums[left];
            return;
          }
            

          int mid = (left+right)>>1;

          build(2*idx+1,nums , left , mid);
          build(2*idx+2,nums , mid+1 , right);

          tree[idx] = tree[2*idx+1]+tree[2*idx+2];
        }


        void updateSeg(int idx , int start , int end , int target, int val){
            if(start == end){
                tree[idx] = val;
                return ;
            }



            int mid = (start+end)/2;

            if(target <= mid){
                updateSeg(2*idx+1,start,mid,target,val);
            } else {
                 updateSeg(2*idx+2,mid+1,end,target,val);
            }

            tree[idx] = tree[2*idx+1]+tree[2*idx+2];
        }

        int qSeg(int idx , int start , int end , int l , int r){
            if(start > r || end < l) return 0;

            if(start <= l && r <= end) return tree[idx];

            int mid = (l+r)/2;
            int left = qSeg(2*idx+1,start,end,l,mid);
            int right = qSeg(2*idx+2,start,end,mid+1,r);

            return left+right;
        }


    public NumArray(int[] nums) {
        n = nums.length;
        tree  = new int[4*n];

        build(0,nums,0,n-1);
    }
    
    public void update(int index, int val) {
        updateSeg(0,0,n-1,index,val);
    }
    
    public int sumRange(int left, int right) {
        return qSeg(0,left,right,0,n-1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */