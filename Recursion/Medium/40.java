class Solution {

    void res(List<List<Integer>> ans, int[] candidates, int target, int idx, List<Integer> li) {

        if (target == 0) {
            ans.add(new ArrayList<>(li));
            return;
        }

        if (idx >= candidates.length) return;

        // SKIP duplicates on the SAME recursion level
        int nextIdx = idx + 1;
        while (nextIdx < candidates.length && candidates[nextIdx] == candidates[idx])
            nextIdx++;

        // OPTION 1 → skip ALL duplicates of this value
        res(ans, candidates, target, nextIdx, li);

        // OPTION 2 → take this element ONCE (allowed only if <= target)
        if (candidates[idx] <= target) {
            li.add(candidates[idx]);
            res(ans, candidates, target - candidates[idx], idx + 1, li);
            li.remove(li.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);  // required for duplicate removal

        List<List<Integer>> ans = new ArrayList<>();
        res(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }
}