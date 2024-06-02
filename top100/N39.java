package algorithmbasic.leetcode.top100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new LinkedList<>();// 收集最后的结果
        List<Integer> result = new LinkedList<>();// 间断性收集结果
        int res = 0;// 比较值
        backtracking(candidates, target, 0, result, ans, res);
        return ans;
    }

    private void backtracking(int[] candidates, int target, int startIndex, List<Integer> result,
                              List<List<Integer>> ans, int res) {
        // 横向单节点处理 判断
        for (int i = startIndex; i < candidates.length; i++) {
            if (res + candidates[i] == target) {
                result.add(candidates[i]);
                ans.add(new LinkedList(result));
                result.remove(result.size() - 1);// 回溯的核心
                return;
            }
            if (res + candidates[i] > target) {
                break;
            }

            result.add(candidates[i]);
            res += candidates[i];
            backtracking(candidates, target, i, result, ans, res);
            result.remove(result.size() - 1);
            res -= candidates[i];
        }

        return;
    }
}
