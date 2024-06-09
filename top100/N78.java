package algorithmbasic.leetcode.top100;

import java.util.LinkedList;
import java.util.List;

public class N78 {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> result = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backTracking(nums, 0);
        ans.add(new LinkedList<>());
        return ans;
    }

    private void backTracking(int[] nums, int startIndex) {
        //纵向结尾处理
        if(startIndex == nums.length) {
            return;
        }

        //单层横向处理
        for(int i = startIndex; i < nums.length; i++) {
            result.add(nums[i]);
            ans.add(new LinkedList<>(result));
            backTracking(nums, i + 1);
            result.remove(result.size() - 1);
        }
    }
}
