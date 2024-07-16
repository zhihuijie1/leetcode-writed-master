package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class backpack01 {
    public int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int N = weight.length;
        //定义dp数组
        int[][] dp = new int[N + 1][bagSize + 1];
        //初始化
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }
        //从上往下遍历
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= bagSize; j++) {
                //不要第i个物品
                dp[i][j] = dp[i - 1][j];
                //要第i个物品
                if (j - weight[i - 1] > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[N][bagSize];
    }
}

/**
 * 1: dp数组与数组下标的含义
 *      在前i个物品中任意选取，放入剩余容量为j的背包中，此时最大的背包最大的价值是dp[i][j]
 *
 * 2：递推公式
 *      不要第i个物品 ： dp[i][j] = dp[i-1][j]
 *      要第i个物品 : dp[i][j] = dp[i - 1][j - weight[i - 1]] + value[i - 1]
 *      最终结果：dp[i][j] = Math.max(dp[i-1][j] ， dp[i - 1][j - weight[i - 1]] + value[i - 1])
 *
 * 3：初始化
 *      根据递推公式可知： 以来的是正上方 和 左上方
 *      dp[0][j] = 0  前0个物品挑选任意个使重量总和达到j
 *      dp[i][0] = 0
 *
 *      for(int i = 0; i <= weight.length; i++) {
 *          dp[i][0] = 0;
 *          dp[0][i] = 0;
 *      }
 *
 * 4：遍历顺序
 *         先遍历物品后遍历背包
 * 从上往下遍历
 *
 * 5：距离推导dp数组
 */