package algorithmbasic.leetcode.dynamic_programming.basic_topic;
/*
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
Return the maximum product you can get.
 */

/*
1：确定dp数组的含义以及dp数组下标的含义
    dp[i]：分拆数字i，可以得到的最大乘积为dp[i]
2：确定递推公式
    j从1开始遍历到i-2位置，因为dp[0]与dp[1]没有意义    dp[i] = Math.max(j * (i-j) , j * dp[i-j])
    j * (i-j) : 两个数相乘
    j * dp[i-j] ： 三个数及三个数以上的数相乘
3：dp数组如何初始化
    dp[2] = 1
4：确定遍历顺序
    从前往后遍历
5：举例推导dp数组
dp[8] = Math.max(1 * 7 , 1 * dp[7])  Math.max(2 * 6 , 2 * dp[6]) ...............
 */
public class code343_Integer_Break {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) { // dp[0] 与 dp[1] 没有意义
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];

    }
}
