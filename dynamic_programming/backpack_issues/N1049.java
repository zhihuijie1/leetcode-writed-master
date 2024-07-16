public class N1049 {
    public int lastStoneWeightII(int[] stones) {
        public int lastStoneWeightII(int[] stones) {
            int weightSum = 0;
            for(int i = 0; i < stones.length; i++) {
                weightSum += stones[i];
            }
            int target = weightSum / 2;
            boolean[][] dp = new boolean[stones.length + 1][target + 1];
            //初始化
            for(int i = 0; i < stones.length + 1; i++) {
                dp[i][0] = true;
            }

            for(int i = 1; i < target + 1; i++) {
                dp[0][i] = false;
            }

            //递推公式
            for(int i = 1; i < stones.length + 1; i++) {
                for(int j = 1; j < target + 1; j++) {
                    dp[i][j] = dp[i-1][j];
                    if(j >= stones[i - 1]) {
                        // 或”运算的使用就在这里发挥作用：如果任意一种情况为真（不使用第 i 个石头或使用第 i 个石头），
                        // 那么 dp[i][j] 就为真。
                        dp[i][j] = dp[i][j] || dp[i - 1][j - stones[i - 1]];
                    }
                }
            }

            for(int j = target; j >= 0; j--) {
                if(dp[stones.length][j]) { // 所有的石头重量有没有总和是j
                    return weightSum - j -j;
                }
            }

            return 0;

        }
    }
}
/**
 * 1: 定dp数组以及下标的含义
 *     前i块石头的重量总和能否达到 j，如果能则dp[i][j]= true; 不能则dp[i][j] = false
 *
 * 2: 确定递推公式
 *     不要第i块石头 ：dp[i][j] = dp[i-1][j]
 *     要第i块石头 ： dp[i][j] = dp[i - 1][j - stones[i - 1]] 前提条件是: j >= stones[i - 1]
 *
 * 3: dp数组如何初始化
 *     根据地推公式 可知依赖 左侧 以及 左上侧
 *     dp[i][0] : true 不选呗
 *     dp[0][j] : false
 *
 * 4: 确定遍历顺序
 *     根据递推公式 ：从上往下
 */