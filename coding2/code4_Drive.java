package algorithmbasic.leetcode.coding2;

/*
现有司机N*2人，调度中心会将所有司机平分给A、B两个区域第i个司机去A可得收入为income[i][0],
第i个司机去B可得收入为income[i][1],
返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
*/

/*
  思路：采用动态规划来解决，
*/
public class code4_Drive {
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) { //income.length & 1) == 0 奇数个数
            return 0;
        }
        int N = income.length;// 司机总人数-偶数
        int M = N >> 1;//A B两地人数平分
        return process(income, 0, M);
    }


    //return --> 返回index位置(包括index位置)以后的最高收入
    //index --> 指向income数组的当前位置
    //aRest --> A区域还差几人
    public static int process(int[][] income, int index, int aRest) {
        if (index == income.length) {
            return 0;
        }
        //index还可以继续向下走
        //A区域人已经满了
        if (aRest == 0) {
            return income[index][1] + process(income, index + 1, aRest);
        }
        //B区域人已经满了
        if (income.length - index == aRest) {
            return income[index][0] + process(income, index + 1, aRest - 1);
        }
        //AB区域人都没有满
        int p1 = income[index][1] + process(income, index + 1, aRest);
        int p2 = income[index][0] + process(income, index + 1, aRest - 1);
        return Math.max(p1, p2);
    }


    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) { //income.length & 1) == 0 奇数个数
            return 0;
        }
        int N = income.length;// 司机总人数-偶数
        int M = N >> 1;//A B两地人数平分
        int[][] dp = new int[N + 1][M + 1]; //结果存放在dp[0][M]
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (j == 0) {
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else if (N - i == j) {
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(income[i][1] + dp[i + 1][j], income[i][0] + dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][M];
    }
}
