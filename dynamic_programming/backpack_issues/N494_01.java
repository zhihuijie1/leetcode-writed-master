package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class N494_01 {
    public int findTargetSumWays(int[] nums, int target) {
        int N = nums.length;
        //定义dp数组
        //ps：因为涉及到 + - 之和，所以必定会超出常规数组的范围，并且有j为负数的情况，所以采用偏移数组
        //计算数组开辟范围
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果目标超出最大可能范围，则无解
        if(target > sum || target < -sum) return 0;
        int[][] dp = new int[N + 1][2 * sum + 1];
        //dp数组初始化
        dp[0][sum] = 1;
        //开始遍历
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= 2 * sum; j++) {
                //+
                dp[i][j] = j - nums[i - 1] >= 0 ? dp[i - 1][j - nums[i - 1]] : 0;
                //-
                dp[i][j] += j + nums[i - 1] <= 2 * sum ? dp[i - 1][j + nums[i - 1]] : 0;
            }
        }
        return dp[N][target + sum];
    }
}
/**
 1: dp数组以及数组下标的含义：
    前i个数字达到目标值j的总个数有dp[i][j]个

 2：递推公式：
    当前第i个数要 '-'号 ：dp[i][j] = dp[i - 1][j + nums[i - 1]];
    当前第i个数要 '+'号 ：dp[i][j] = dp[i - 1][j - nums[i - 1]];
    结果：dp[i][j] = dp[i - 1][j + nums[i - 1]] + dp[i - 1][j - nums[i - 1]]

 3: 初始化：

    dp[0][j] = 0

 4：遍历顺序：
    根据递推关系，可知：依赖左上方 与 右上方
    从上往下 从左往右

 */
