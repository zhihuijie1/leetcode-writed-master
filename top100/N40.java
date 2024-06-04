package algorithmbasic.leetcode.top100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();// 收集最终的结果
        List<Integer> result = new LinkedList<>();// 记录中间结果
        int startIndex = 0;// 保证无重复
        int sum = 0;// 加和的比较值

        Arrays.sort(candidates);
        backtracking(candidates, target, startIndex, sum, result, ans);
        return ans;
    }

    private void backtracking(int[] candidates, int target, int startIndex, int sum, List<Integer> result,List<List<Integer>> ans) {
        for (int i = startIndex; i < candidates.length; i++) {
            if(i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //(i > startIndex && candidates[i] != candidates[i - 1] && sum + candidates[i] == target) || (i == startIndex)
            if (sum + candidates[i] == target) {
                result.add(candidates[i]);
                ans.add(new LinkedList<>(result));
                result.remove(result.size() - 1);
                return;
            }

            if(sum + candidates[i] > target) {
                break;
            }

            result.add(candidates[i]);
            sum += candidates[i];
            startIndex = i + 1;
            backtracking(candidates, target, startIndex, sum, result, ans);
            //后三句是回溯函数的核心
            result.remove(result.size() - 1);
            sum -= candidates[i];
            startIndex = i - 1;
        }
    }
}
