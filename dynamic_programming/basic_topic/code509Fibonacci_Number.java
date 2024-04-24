package algorithmbasic.leetcode.dynamic_programming.basic_topic;


/*
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
 */

/*
dp五部曲：
1 确定dp数组的含义，以及dp数组下标的含义
    dp[i]--当前该位置的斐波数
2 确定递推公式
    dp[i] = dp[i-1] + dp[i-2]
3 dp数组如何初始化
    dp[0] = 0 , dp[1] = 1
4 确定遍历顺序
    从第2个下标开始往后遍历,根据递推公式可以得知，后面的数依赖前面的数，所以从前往后遍历
5 举例推导dp数组
    dp[4] = dp[3] + dp[2]
    dp[3] = dp[2] + dp[1]
    dp[2] = dp[1] + dp[0]
 */
public class code509Fibonacci_Number {
    public int fib(int n) {
        if(n <= 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}