public class N121 {
    public int maxProfit(int[] prices) {
        int N = prices.length;
        int[][] dp = new int[N][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i-1][0] , dp[i][0] = 0 - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1] , dp[i-1][0] + prices[i]);
        }
        return dp[N - 1][1];
    }
}
/**
 1:dp数组以及数组下标的含义：
    dp[i][0] -> 第i天在我手持股票的状态下，所剩下的最大现金额
    dp[i][1] -> 第i天在我不手持股票的状态下，手里剩下的最大现金额

 2:dp数组的递推公式：
    dp[i][0] -> 1:前一天就已经手持股票了：dp[i][0] = dp[i-1][0]
                2：前一天没有手持股票，今天刚买的股票：dp[i][0] = dp[i-1][1] - prices[i]-> error    -prices[i]-> right
                结果：Math.max(dp[i-1][0] , dp[i][0] = dp[i-1][1] - prices[i])

    dp[i][1] -> 1:前一天我就不手持股票了：dp[i][1] = dp[i-1][1]
             2:前一天我还手持股票，今天把股票卖了：dp[i][1] = dp[i-1][0] + prices[i]
             结果：Math.max(dp[i-1][1] , dp[i-1][0] + prices[i])

 3:dp数组初始化：
    根据递推公式可知：依赖上一层以及右上层
    dp[0][0] = -prices[0] 第0天持有股票
    dp[0][1] = 0 第0天不持有股票

 4遍历顺序：
    从上往下，从前往后

 */
