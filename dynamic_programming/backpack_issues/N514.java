public class N514 {
    //求的是组合数 -- 先遍历物品后遍历背包，每一个物品放入的顺序是一定的  比如先放物品0后放物品1
    public int change(int amount, int[] coins) {
        int N = coins.length;
        //定义一个dp数组
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 1;
        //开始遍历 - 从前往后
        //先遍历物品
        for(int i = 0; i < N; i++) {
            //后遍历背包
            for(int j = 1; j <= amount; j++) {
                if(j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    //求的是排列数 -- 先遍历背包后遍历物品，每一个物品放入的顺序是不一定的  比如当背包容量是1时放入物品0和物品1 当背包容量变成2时，此时又可以放入物品0 此时背包物品顺序是 物品010
    public int change2(int amount, int[] coins) {
        int N = coins.length;
        //定义一个dp数组
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 1;
        //开始遍历 - 从前往后
        //先遍历物品
        for(int j = 1; j <= amount; j++) {
            //后遍历背包
            for(int i = 0; i < N; i++) {
                if(j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    //二维数组解法
    public int change3(int amount, int[] coins) {
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // dp[i][0] = 1,  dp[0][j] = 0
        for(int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        //
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= amount; j++) {
                // 当前物品一个不选
                // dp[i - 1][j]
                // 选一个当前物品
                // dp[i - 1][j - coins[i]]
                if(j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]; // 注意---------------
                }else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
        return dp[N][amount];
    }
}

/**
    完全背包问题
    1:dp数组以及数组下标的含义：
        当背包容量是j时，恰好可以组成容量j的方法数有dp[j]个

    2：dp数组的递推公式：
        dp[j] = dp[j] + dp[j - coins[i]]
 */
