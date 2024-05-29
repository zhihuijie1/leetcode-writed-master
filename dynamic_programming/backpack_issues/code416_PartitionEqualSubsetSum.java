package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class code416_PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

    }
}

/*
1：确定dp数组的含义以及dp数组下标的含义
    dp[j] -> 来到当前j位置，此时背包的总重量是dp[j]

2：确定递推公式
    要: dp[j + 1] = dp[j] + weight[j]
    不要: dp[j + 1] = dp[j]

3：dp数组如何初始化
    dp[0] = 0

4：确定遍历顺序
    从左往右

5：举例推导dp数组

*/
