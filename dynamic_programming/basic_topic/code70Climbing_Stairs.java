package algorithmbasic.leetcode.dynamic_programming.basic_topic;
/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */


//每一步的状态都与前面的状态有关，所以是动态规划问题


/*
dp五部曲：这五步都搞清楚了，才能说把动态规划真的掌握了
> 1：确定dp数组的含义以及dp数组下标的含义
     dp[i] 来到第i个台阶，一共有dp[i]种方法。
> 2：确定递推公式
>    dp[i] = dp[i - 1] + dp[i - 2]
> 3：dp数组如何初始化
>    dp[0] = 0  dp[1] = 1 dp[2] = 2
> 4：确定遍历顺序
>    根据递推公式可知，从前往后遍历
> 5：举例推导dp数组
     dp[4] = dp[2] + dp[3]
     dp[3] = dp[2] + dp[1]
 */
public class code70Climbing_Stairs {
    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
