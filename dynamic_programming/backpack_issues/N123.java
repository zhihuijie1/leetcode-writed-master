package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N123 {
    public static int maxProfit(int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][5];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        dp[0][4] = -prices[0];
        for(int i = 1; i < N; i++) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] + prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] - prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][4] + prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][1] - prices[i]);
        }
        return Math.max( Math.max(dp[N-1][1] , dp[N-1][2]) , Math.max(dp[N-1][3] , dp[N-1][4]) );
    }
}

/**
 0:你至多可以完成两笔交易, 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 可以做0次，1次，2次交易

 1:dp数组以及数组下标的含义：
    dp[i][0]: 表示在第i天不进行任何操作的最大利润
    dp[i][1]:表示在第i天第一次不手持股票的最大利润
    dp[i][2]:表示在第i天第一次手持股票的最大利润
    dp[i][3]:在第i天第二次不手持股票的最大利润
    dp[i][4]:在第i天第二次手持股票的最大利润
    result：Math.max(dp[i][0],dp[i][1],dp[i][2],dp[i][3],dp[i][4]);

 2:dp数组的递推公式：
    dp[i][0]: 0
    dp[i][1] - 1：前一天就不手持股票了：dp[i][1] = dp[i-1][1]
             - 2：今天把股票卖了:dp[i][1] = dp[i-1][2] + prices[i]
             - dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] + prices[i]);

    dp[i][2] - 1：前一天就已经手持股票了：dp[i][2] = dp[i-1][2]
             - 2：之前没有手持股票，现在才买股票：dp[i][2] = dp[i-1][1] - prices[i]
             - dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] - prices[i]);

    dp[i][3] - 1：前一天就已经第二次不手持了：dp[i][3] = dp[i-1][3]
             - 2：前一天第二次手持，现在今天要卖了股票：dp[i][3] = dp[i-1][4] + prices[i]
             - dp[i][3] = Math.max(dp[i-1][3], dp[i-1][4] + prices[i]);

    dp[i][4] - 1：前一天就已经第二次手持股票了：dp[i][4] = dp[i-1][4]
             - 2: 前一天第一次不持有股票，今天开始买股票：dp[i][4] = dp[i-1][1] - prices[i]
             - dp[i][4] = Math.max(dp[i-1][4], dp[i-1][1] - pricesp[i]);

 3:dp数组初始化：
    dp[0][0] = 0
    dp[0][1] = 0
    dp[0][2] = -prices[0]
    dp[0][3] = 0
    dp[0][4] = -prices[0]

 4:dp数组的遍历顺序：
    从上往下，从左往右
 */