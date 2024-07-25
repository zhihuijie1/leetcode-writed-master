public class N198 {
    public static int rob(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2; i <= N; i++) {
            int n = 0;
            int y = 0;
            int curIndex = i - 1;
            //不要当前位置的数
            n = curIndex >= 0 ? dp[curIndex] : 0; // 1
            //要当前位置的数
            y = curIndex - 1 >= 0 ? dp[curIndex - 1] + nums[curIndex] : 0; //2
            if(y > n) {
                dp[i] = y;
            }else {
                dp[i] = n;
            }
        }

        for(int i = 0; i <= N ; i++) {
            System.out.print(dp[i] + " ");
        }
        return dp[N];
    }
}

/**
 0: 01背包问题：

 1：dp数组以及数组下标的含义：
    在前i个物品中任选，所装的最大金额是dp[i]

 2: 递推公式：
    要当i位置的数：dp[i - 2] + nums[i]   i++
    不要当前i位置的数：dp[i - 1]

 3: 初始化：
    dp[0] = 0

 4: 遍历顺序：
     从前往后
 */
