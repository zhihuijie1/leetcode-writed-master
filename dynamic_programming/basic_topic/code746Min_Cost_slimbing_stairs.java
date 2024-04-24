package algorithmbasic.leetcode.dynamic_programming.basic_topic;
/*
You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
Once you pay the cost, you can either climb one or two steps.
You can either start from the step with index 0, or the step with index 1.
Return the minimum cost to reach the top of the floor.
 */

/*
1：确定dp数组的含义以及dp数组下标的含义
    dp[i]: 到达第i个台阶最小的总花费是dp[i]
2：确定递推公式
    dp[i] = Math.min(dp[i-1] + cost[i-1] , dp[i-2] + cost[i-2])
3：dp数组如何初始化
    dp[0] = 0
    dp[1] = 0
4：确定遍历顺序
    根据递推公式可知要从前往后进行遍历
5：举例推导dp数组
    dp[5] = Math.min(dp[4] + cost[4] ,dp[3] + cost[3])
    dp[4] = Math.min(dp[3] + cost[3] ,dp[2] + cost[2] )
    dp[3] = Math.min(dp[2] + cost[2] ,dp[1] + cost[1] )
    dp[2] = Math.min(dp[1] + cost[1] ,dp[0] + cost[0] )
 */
public class code746Min_Cost_slimbing_stairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null) {
            return 0;
        }
        int N = cost.length;
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 0;
        }
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[N];
    }
}
