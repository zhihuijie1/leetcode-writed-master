package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N279 {
    public int numSquares(int n) {
        //定义一个dp数组
        int[] dp = new int[n + 1];
        //初始化
        for(int j = 0; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        //开始遍历 - 考虑的是组合数 所以先遍历物品后遍历背包
        for(int i = 1; i * i <= n; i++) {
            for(int j = i * i; j <= n; j++) {
                //记录当前的完全平方数
                int k = i * i;
                if(j - k >= 0 && dp[j - k] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - k] + 1);
                }
            }
        }
        return dp[n];
    }
}

/**
 0 题目描述：
    给一个整数n，求累加和为n的最少完全平方数的个数。

 1：dp数组以及数组下标的含义：
    背包容量为j时，累加和为j的最少完全平方数的个数是dp[j]

 2: 递推公式：
    在一个完全平方数的数组中
    - 要不要当前位置的数：dp[j]
    - 要当前位置的数：dp[j - square[i]] + 1
    最终的结果：Math.min(dp[j], dp[j - square[i]] + 1)

 3: dp数组的初始化：
    dp[0] = 0

 4: dp数组的遍历顺序：
    考虑的是组合数 所以先遍历物品后遍历背包
 */
