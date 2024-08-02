package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N122 {
    public int maxProfit(int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i-1][0] , dp[i-1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1] , dp[i-1][0] + prices[i]);
        }
        return dp[N-1][1];
    }
}

/**
 0:注意你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    prices = [7,1,5,3,6,4]

 1:dp数组以及数组下标的含义：
    dp[i][0]: 第i天我手持股票状态下最大的利润总和为dp[i][0]
    dp[i][1]: 第i天我不手持股票状态下，最大利润总和为dp[i][1]
    最后返回的结果是：dp[i][1]

 2:dp数组的递推公式：
    dp[i][0] - 1：前1天我就已经手持了，dp[i][0] = dp[i-1][0]
             - 2: 前一天我没有手持股票，今天才购买股票，手持股票，dp[i][0] = dp[i-1][1] - prices[i]
             - result: dp[i][0] = Math.max(dp[i-1][0] , dp[i-1][1] - prices[i])

    dp[i][1] - 1:前一天我就已经不手持了，dp[i][1] = dp[i-1][1]
             - 2:前一天手持股票，今天把股票卖了，dp[i][1] = dp[i-1][0] + prices[i]
             - result: dp[i][1] = Math.max(dp[i-1][1] , dp[i-1][0] + prices[i]);

 3:dp数组的初始化：
    dp[0][0] = -prices[0]
    dp[0][1] = 0

 4:遍历顺序：
    从上往下，从左往右
 */
