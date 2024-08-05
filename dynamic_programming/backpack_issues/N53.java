package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class N53 {
    public int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int maxvalue = dp[0];
        for(int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i] , nums[i]);
            maxvalue = Math.max(maxvalue,dp[i]);
        }
        return maxvalue;
    }
}
/*
 * 0: 返回连续子数组的最大和
 *
 * 1：dp数组下标与dp数组的含义：
 *      dp[i] 以下标i结尾的最大连续子数组的最大和是dp[i]
 *
 * 2：dp数组的递推公式：
        注意一定要保证连续性，如果连续性断掉->结果存储nums[i]
 *      dp[i] = Math.max(dp[i-1] + nums[i] , nums[i]);
 *
 * 3：dp数组的初始化：
 *      dp[0] = nums[0];
 *
 * 4：dp数组的遍历顺序：
 *      从左往右
 *
 */

