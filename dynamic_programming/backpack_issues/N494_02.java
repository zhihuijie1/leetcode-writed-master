package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class N494_02 {
    public static int findTargetSumWays(int[] nums, int target) {
        int N = nums.length;
        //定义dp数组
        //ps：因为涉及到 + - 之和，所以必定会超出常规数组的范围，并且有j为负数的情况，所以采用偏移数组
        //计算数组开辟范围
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果目标超出最大可能范围，则无解
        if(target > sum || target < -sum) return 0;
        int[][] dp = new int[N + 1][2 * sum + 1];
        //dp数组初始化
        dp[0][N] = 1;
        //开始遍历
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= 2 * sum; j++) {
                //+
                dp[i][j] = j - nums[i - 1] >= 0 ? dp[i - 1][j - nums[i - 1]] : dp[i][j];
                //-
                dp[i][j] += j + nums[i - 1] <= 2 * N ? dp[i - 1][j + nums[i - 1]] : dp[i][j];
            }
        }
        return dp[N][target + sum];
    }

    public static void main(String[] args) {
        int[] nums = {1};
        findTargetSumWays(nums,1);
    }
}
/**
 1：dp数组以及数组下标的含义：
    在前i个物品中，每个物品要么+要么-，将这些物品打包放入容量为j的背包中，正好放满的组合数为dp[i][j]

 2: dp数组的递推公式
    + 当前物品 ：dp[i][j] = dp[i - 1][j - nums[i - 1]]
    - 当前物品 ：dp[i][j] = dp[i - 1][j + nums[i - 1]]
    最终结果： dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j + nums[i - 1]]
    ps：因为涉及到 + - 之和，所以必定会超出常规数组的范围，并且有j为负数的情况，所以采用偏移数组

 3: dp数组初始化：
    dp[i][0] : 不一定
    dp[0][j] : 0
    dp[0][nums.length] : 1

 4: dp数组的遍历顺序：
    从上往下
    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= target; j++) {
            //+
            dp[i][j] = j - nums[i - 1] >= 0 ? dp[i - 1][j - nums[i - 1]] : dp[i][j];
            //-
            dp[i][j] += j + nums[i - 1] <= 2 * nums.length ? dp[i - 1][j + nums[i - 1]] : dp[i][j];
        }
    }

 */