package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class code416_PartitionEqualSubsetSum_F {
    public boolean canPartition(int[] nums) {
        int N = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[][] dp = new boolean[N + 1][sum + 1];

        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[N][sum];
    }
}

// dp[i][j] 前i个元素可不可以将容量j恰好填满