package algorithmbasic.leetcode.dynamic_programming.basic_topic;
/*
Given an integer n,
return the number of structurally unique BST's (binary search trees)
which has exactly n nodes of unique values from 1 to n.
 */

/*
1:确定dp数组的含义，下标的含义
    dp[i] : i个节点数量一共有dp[i]种组合情况
2:递推公式
    dp[i] = dp[左子树节点个数] * dp[右子树节点个数]
    以j为头节点时，左子树节点个数是：j - 1  右子树节点个数是 i - j
    即：dp[i] = dp[j - 1] * dp[i - j]
3:dp数组如何初始化
    dp[0] = 1
    dp[1] = 1
    dp[2] = 2
4:遍历顺序
    从前往后遍历
5:举例推导dp数组
    dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
 */
public class code96Unique_Binary_Search_Trees {
    public int numTrees(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            for(int j = 1; j <= i ; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

}
