public class N188 {
    public int maxProfit(int k, int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][2*k+1];
        for(int h = 1; h <= k; h++) {
            dp[0][2*h - 1] = 0;
            dp[0][2*h] = -prices[0];
        }
        dp[0][0] = 0;
        for(int i = 1; i < N; i++) {
            for(int j = 1; j <= k; j++) {
                dp[i][2*j-1] = Math.max(dp[i-1][2*j-1] , dp[i-1][2*j] + prices[i]);
                int pp = 2*(j-1)-1 >= 0 ? 2*(j-1)-1 : 0;
                dp[i][2*j] = Math.max(dp[i-1][2*j], dp[i-1][pp] - prices[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        for(int j = 1; j <= k; j++) {
            res = Math.max(res, Math.max(dp[N-1][2*j-1],dp[N-1][2*j]));
        }
        return Math.max(res, dp[N-1][0]);
    }
}

/**
 0:你最多可以完成 k 笔交易。注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 1:dp数组以及数组下标的含义：
    dp[i][2*k-1]: 在第i天第k次不持有股票的最大利润
    dp[i][2*k]：在第i天第k次持有股票的最大利润
     1   |  2  |  3  |  4
    1 2    3 4   5 6   7 8

 2:dp数组的递推公式：
    dp[i][2*k-1] ：1： 在前一天就已经第k次不持有股票了 dp[i][2*k-1] = dp[i-1][2*k-1]
                   2： 前一天是第k次持有股票，今天把股票卖了 dp[i][2*k-1] = dp[i-1][2*k] + prices[i]
                   result：dp[i][2*k-1] = Math.max(dp[i-1][2*k-1] , dp[i-1][2*k] + prices[i]);

    dp[i][2*k] : 1: 前一天就已经第k次持有股票了 dp[i][2*k] = dp[i-1][2*k]
                 2: 前一天是第k-1次不持有股票，今天是第k次持有股票 dp[i][2*k] = dp[i-1][2*(k-1)-1] - prices[i]
                 result： dp[i][2*k] = Math.max(dp[i-1][2*k], dp[i-1][2*(k-1)-1] - prices[i]);

 3:dp数组的初始化：
    dp[0][0] = -prices[0]
    dp[0][2*k-1] = 0
    dp[0][2*k] = -prices[0]

 4:dp数组的遍历顺序：
    从上往下，从左往右

 */