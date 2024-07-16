package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class backpack01_roll {
    public int testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int N = weight.length;
        //定义dp数组
        int[] dp = new int[bagSize + 1];
        //初始化
        dp[0] = 0;
        //遍历顺序
        //外层循环先遍历物品
        for (int i = 0; i < N; i++) {
            //内层循环遍历背包大小
            for (int j = bagSize; j > weight[i] ; j--) {
                dp[j] = Math.max(dp[j] , dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagSize];
    }
}

/**
 * 1: 确定dp数组以及数组下标的含义
 *      背包容量为j时 背包最大的价值是dp[j]
 *
 * 2： 递推公式
 *      不要i位置的物品 ： dp[j] = dp[j]
 *      要i位置的物品 ： dp[j] = dp[j - weight[i]] + value[i]
 *      最终的结果 ：Math.max(dp[j], dp[j - weight[i]] + value[i])
 *
 * 3： 初始化
 *      dp[0] = 0
 *
 * 4： 遍历顺序
 *      for(int i = 0; i < weight.length; i++) {
 *          for(int j = bagSize; j >= weight[i]; j--) {
 *              dp[j] = Math.max( dp[j] , dp[j - weight[i] + value[i]);
 *          }
 *      }
 *      背包的容量j要从后往前遍历原因是：
            正序遍历的问题
                初始化：dp = [0, 0, 0, 0]（容量从0到3的最大价值均为0）
                正序遍历：
                当容量 j=1：dp[1] 可以选择装入这个物品，所以 dp[1] 更新为 15。
                当容量 j=2：你再次考虑这个物品，看 dp[2 - 1] + 15，即 dp[1] + 15 = 30，所以 dp[2] 也更新为 30。
                当容量 j=3：同样的方法，dp[3] 更新为 dp[2] + 15 = 45。
                现在，dp 数组变成了 [0, 15, 30, 45]。这里的问题是，正序遍历使得同一件物品被多次计算，使得 dp 数组反映的是这件物品被重复添加多次的情况，而不是实际应有的单次添加。

            倒序遍历的正确操作
                初始化：dp = [0, 0, 0, 0]（容量从0到3的最大价值均为0）
                倒序遍历：
                当容量 j=3：你考虑这件物品，dp[3] 可以选择装入这个物品，所以 dp[3] 更新为 dp[3 - 1] + 15 = dp[2] + 15 = 15。
                当容量 j=2：同样的方法，dp[2] 更新为 dp[2 - 1] + 15 = dp[1] + 15 = 15。
                当容量 j=1：dp[1] 更新为 dp[1 - 1] + 15 = dp[0] + 15 = 15。
                倒序遍历后，dp 数组变成了 [0, 15, 15, 15]。这反映了在任何容量下，最多只能装入这件物品一次，这是 0-1 背包问题的本质：每个物品只能使用一次。
 *          同样的，二维dp数组，之所以可以从前往后，是因为，依赖的上一层的数据，依赖的数据不在本层，所以没有污染问题
 *
 * 5： 举例推导
 */

/**
 * 其实可以发现如果把dp[i - 1]那一层拷贝到dp[i]上，表达式完全可以是：dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
 * 与其把dp[i - 1]这一层拷贝到dp[i]上，不如只用一个一维数组了，只用dp[j]（一维数组，也可以理解是一个滚动数组）。
 * dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
 */
