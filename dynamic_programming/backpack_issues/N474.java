package algorithmbasic.leetcode.dynamic_programming.backpack_issues;

public class N474 {
    public int findMaxForm(String[] strs, int m, int n) {
        //定义dp数组
        int[][] dp = new int[m + 1][n + 1];
        //开始遍历
        for(String str : strs) {
            //先统计01的数量
            int zeroNum = 0;
            int oneNum = 0;
            char[] ch = str.toCharArray();
            for(char i : ch){
                if(i == '0') {
                    zeroNum++;
                }else{
                    oneNum++;
                }
            }
            //内层遍历背包容量
            for(int i = m ; i >= 0; i--) {
                for(int j = n; j >= 0; j--) {
                    if(i >= zeroNum && j >= oneNum) {
                        dp[i][j] = Math.max(dp[i][j] , dp[i - zeroNum][j - oneNum] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }
}

/**
 题目描述：
 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 最多有 5 个 0 和 3 个 1
 最大子集是 {"10","0001","1","0"} ，因此答案是 4

 1：dp数组与数组下标的含义：
    01背包：dp[i][j] : 在前i个物品中任意选取，放入剩余容量为j的背包中，此时最大的背包最大的价值是dp[i][j]
    现在背包有两个判断维度分别是m和n
    dp[i][j] : strs字符串最多有i个0和j个1的条件下，最多有dp[i][j]大小的子集

 2：递推公式：
    当前位置如果满足zeroNum <= i , oneNum <= j ，则dp[i][j] = dp[i - zeroNum][j - oneNum] + 1
    如果不满足的话： dp[i][j] = dp[i][j]
    最终结果：dp[i][j] = Math.max( dp[i][j], dp[i - zeroNum][j - oneNum] + 1)

 3: dp数组初始化：
    根据递推公式可知，依赖的顺序是：左上方
    dp[0][i]:
    dp[i][0]:
    没必要初始化，因为不是特殊情况

 4：遍历公式：
    外层for循环遍历物品，内层for循环遍历背包容量且从后向前遍历
    //外层先遍历物品
    for(String str : strs) {
        //先统计01的数量
        int zeroNum = 0;
        int oneNum = 0;
        char[] ch = str.tochararray();
        for(char i : ch){
            if(i == '0') {
                zeroNum++;
            }esle {
                oneNum++;
            }
        }
        //内层遍历背包容量
        for(int i = m ; i >= 0; i--) {
            for(int j = n; j >= 0; j--) {
                if(i >= zeroNum && j >= oneNum) {
                    dp[i][j] = Math.max(dp[i][j] , dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
    }


 */

