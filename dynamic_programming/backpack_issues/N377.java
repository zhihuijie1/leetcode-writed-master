package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N377 {
    public int combinationSum4(int[] nums, int target) {
        int N = nums.length;
        //定义dp数组
        int[] dp = new int[target + 1];
        //初始化dp数组
        dp[0] = 1;
        //开始遍历
        //完全背包的排列问题 先遍历背包后遍历物品
        for(int j = 1 ; j <= target; j++) {
            for(int i = 0; i < N; i++) {
                if(j - nums[i] >= 0) {
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }
}

/**
 完全背包问题，因为一个数字可以被多次的选中

 1：dp数组与数组下标的含义：
        背包容量为j时，组合数为dp[j]

 2: dp数组的递推公式：
        来到当前的i位置
            - 不要当前i位置的数：dp[j]
            - 要当前i位置的数：dp[j - nums[i]]
            dp[j] = dp[j] + dp[j - nums[i]]

 3: dp数组的初始化：
        dp[0] = 1;

 4: 遍历顺序：
        从前往后，因为是一个完全背包问题，每一种可以都次选择
        得到的集合是排列，说明需要考虑元素之间的顺序。
        本题要求的是排列，那么这个for循环嵌套的顺序可以有说法了。
        如果求组合数就是外层for循环遍历物品，内层for遍历背包。
        如果求排列数就是外层for遍历背包，内层for循环遍历物品。
        如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，举一个例子：计算dp[4]的时候，结果集只有 {1,3} 这样的集合，
        不会有{3,1}这 样的集合，因为nums遍历放在外层，3只能出现在1后面！
        所以本题遍历顺序最终遍历顺序：target（背包）放在外循环，将nums（物品）放在内循环，内循环从前到后遍历。

 */
