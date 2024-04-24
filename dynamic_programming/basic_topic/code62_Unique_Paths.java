package algorithmbasic.leetcode.dynamic_programming.basic_topic;

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */


/*
1：确定dp数组的含义以及dp数组下标的含义
    dp[i][j] 到达[i][j]位置的路径数是dp[i][j]
2：确定递推公式
    dp[i][j] = dp[i-1][j] + dp[i][j-1]
3：dp数组如何初始化
    dp[0][0] = 0
    dp[0][j] = 1
    dp[i][0] = 1
4：确定遍历顺序
    从左往右遍历
    从上往下遍历
5：举例推导dp数组
dp[3][1] = dp[2][1] + dp[3][0]
dp[2][1] = dp[1][0] + dp[0][1]
*/
public class code62_Unique_Paths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];

        dp[0][0] = 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
