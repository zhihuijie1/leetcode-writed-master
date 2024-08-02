package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class CompletedBackpack {
    public int completedbackpack(int[] weight, int[] value,int backsize) {
        int N = weight.length;
        //定义dp数组
        int[] dp = new int[N + 1];
        //初始化
        dp[0] = 0;
        //开始遍历 - 从前往后遍历
        //先遍历物品
        for (int i = 0; i < N; i++) {
            //后遍历背包
            for (int j = 1; j <= backsize; j++) {
                if(j - weight[i] >= 0) {
                    dp[j] = Math.max( dp[j], dp[ j - weight[i] ] + value[i] );
                }
            }
        }
        return dp[backsize];
    }
}


/**
 *  题目描述：
 *          有N件物品和一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。
 *          每件物品都有无限个（也就是可以放入背包多次），求解将哪些物品装入背包里物品价值总和最大。
 *
 *  1：dp数组以及数组下标的含义：
 *          二维：
 *          在前i个物品中任意选择（可以多次被选择），放入剩余背包中，此时背包的容量为j，此时背包中物品最大的价值是dp[i][j]
 *          一维：
 *          背包容量为j时，此时背包中所放物品的最大价值是dp[j]
 *
 *  2：dp数组的递推公式：
 *          一维：
 *          dp[j] = Math.max( dp[j], dp[ j - weight[i] ] + value[i] )
 *          二维：
 *          dp[i][j] = Math.max( dp[i-1][j] , dp[i-1][j - weight[i]] + value[i] )
 *
 *  4: dp数组的遍历顺序：
 *          一维：从前往后遍历
 *          一维递推公式 -> dp[j] = Math.max( dp[j], dp[ j - weight[i] ] + value[i] )
 *
 *                重量  价值
 *          物品0	1	15
 *          物品1	3	20
 *          物品2	4	30
 *
 *          i = 0 : j = 0 -> dp[0] = 0  <只可以选择物品0>
 *                  j = 1 -> dp[1] = Math.max( dp[1], dp[ 1 - 1 ] + 15 ) = 15  放1个物品0
 *                  j = 2 -> dp[j] = Math.max( dp[2], dp[ 2 - 1 ] + 15 ) = 30  放2个物品0
 *                  j = 3 -> dp[j] = Math.max( dp[3], dp[ 3 - 1 ] + 15 ) = 45  放3个物品0
 *                  j = 4 -> dp[j] = Math.max( dp[4], dp[ 4 - 1 ] + 15 ) = 60  放4个物品0
 *
 *          i = 1 : j = 0 -> dp[0] = 0   <可以选择物品0和物品1>
 *                 j = 1 -> dp[1] = Math.max( dp[1], dp[ 1 - 3 ] + 20 ) = 15  放不下物品1 但是可以放1个物品0
 *                 j = 2 -> dp[j] = Math.max( dp[2], dp[ 2 - 3 ] + 20 ) = 30  放不下物品1 但是可以放2个物品0
 *                 j = 3 -> dp[j] = Math.max( dp[3], dp[ 3 - 3 ] + 20 ) = 45  可以放1个物品1 或者 放3个物品0 取最大的话还是放3个物品0
 *                 j = 4 -> dp[j] = Math.max( dp[4], dp[ 4 - 3 ] + 20 ) = 60  可以放1个物品0和一个物品1 或者 放4个物品0 取最大的话还是放4个物品0
 *
 *          i = 2 : j = 0 -> dp[0] = 0   <可以选择物品0和物品1和物品2>
 *                 j = 1 -> dp[1] = Math.max( dp[1], dp[ 1 - 4 ] + 30 ) = 15  放不下物品2 但是可以放1个物品0
 *                 j = 2 -> dp[j] = Math.max( dp[2], dp[ 2 - 4 ] + 30 ) = 30  放不下物品2 但是可以放2个物品0
 *                 j = 3 -> dp[j] = Math.max( dp[3], dp[ 3 - 4 ] + 30 ) = 45  放不下物品2 但是可以放3个物品0
 *                 j = 4 -> dp[j] = Math.max( dp[4], dp[ 4 - 4 ] + 30 ) = 60  可以放4个物品0 或者 1个物品4 取最大的话还是放4个物品0
 *
 *          dp[j] 是依赖于当前层前面的最佳值，因为从前往后遍历的时候将前面的值正好更新了
 *
 *
 *          从后往前遍历：
 *              i = 0 : j = 0 -> dp[0] = 0  <只可以选择物品0>
// *                      j = 4 -> dp[j] = Math.max( dp[4], dp[ 4 - 1 ] + 15 ) = 15  放一个物品0  因为只有物品0可以选择
 *                      j = 3 -> dp[j] = Math.max( dp[3], dp[ 3 - 1 ] + 15 ) = 15  放一个物品0  因为只有物品0可以选择
 *                      j = 2 -> dp[j] = Math.max( dp[2], dp[ 2 - 1 ] + 15 ) = 15  放一个物品0  因为只有物品0可以选择
 *                      j = 1 -> dp[1] = Math.max( dp[1], dp[ 1 - 1 ] + 15 ) = 15  放一个物品0  因为只有物品0可以选择
 *
 *              i = 1 : j = 0 -> dp[0] = 0  <可以选择物品0和物品1>
 *  *                      j = 4 -> dp[j] = Math.max( dp[4], dp[ 4 - 3 ] + 20 ) = 35  1个物品0 / 一个物品0+一个物品1
 *  *                      j = 3 -> dp[j] = Math.max( dp[3], dp[ 3 - 3 ] + 20 ) = 20  1个物品0 / 一个物品1
 *  *                      j = 2 -> dp[j] = Math.max( dp[2], dp[ 2 - 3 ] + 20 ) = 15  放一个物品0
 *  *                      j = 1 -> dp[1] = Math.max( dp[1], dp[ 1 - 3 ] + 20 ) = 15  放一个物品0
 *
 *           dp[j]依赖于上一层的结果，因为是从后往前遍历所以当前层前面的值未更新，只能依赖于上一层更新的值
 *
 */
