public class N213 {
    public int rob(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        return Math.max(getMaxValue(nums, 0, nums.length - 2), getMaxValue(nums, 1, nums.length-1));
    }

    private static int getMaxValue(int[] nums, int start, int end) { //左闭右闭
        int[] dp = new int[end - start + 2]; //从start位置开始到当前位置的前一个最大的金币总数是多少
        dp[0] = 0;
        dp[1] = nums[start];
        for(int i = start + 1; i <= end; i++) { //遍历物品
            int dpindex = i - start + 1; //物品位置与当前dp位置的映射关系
            dp[dpindex] = Math.max(dp[dpindex - 1], dp[dpindex-2] + nums[i]);
        }
        return dp[end - start + 1];
    }
}

// dp[i]表示在从start到start + i - 1的房子范围内，可以获得的最大金额。
// satrt = 0 -> start + i - 1  ->  0 + 4 - 1 = 3 -> dp[3]就是0，1，2中最大的金额
// satrt = 1 -> start + i - 1  ->  1 + 4 - 1 = 4 -> dp[4]就是1，2，3中最大的金额
