import java.util.Scanner;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 输入描述：输入共一行，包含两个正整数，分别表示n, m
 * 输出描述：输出一个整数，表示爬到楼顶的方法数。
 * 输入示例：3 2
 * 输出示例：3
 * 提示：
 * 当 m = 2，n = 3 时，n = 3 这表示一共有三个台阶，m = 2 代表你每次可以爬一个台阶或者两个台阶。
 * 此时你有三种方法可以爬到楼顶。
 * 1 阶 + 1 阶 + 1 阶段
 * 1 阶 + 2 阶
 * 2 阶 + 1 阶
 */
public class N70 {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int m, n;
        while (sc.hasNextInt()) {
            // 从键盘输入参数，中间用空格隔开
            n = sc.nextInt();
            m = sc.nextInt();

            int[] dp = new int[n];
            dp[0] = 1;
            for(int j = 1; j <= n; j++) {
                for(int i = 1; i <= m; i++) {
                    if(j - i >= 0) {
                        dp[j] = dp[j] + dp[j - i];
                    }
                }
            }

            System.out.println(dp[n]);
        }
    }
}


/**
 * 确定dp数组以及下标的含义
 * dp[i]：爬到有i个台阶的楼顶，有dp[i]种方法。
 *
 * 确定递推公式
 * 在动态规划：494.目标和 (opens new window)、 动态规划：518.零钱兑换II (opens new window)、动态规划：377. 组合总和 Ⅳ (opens new window)中我们都讲过了，求装满背包有几种方法，递推公式一般都是dp[i] += dp[i - nums[j]];
 *
 * 本题呢，dp[i]有几种来源，dp[i - 1]，dp[i - 2]，dp[i - 3] 等等，即：dp[i - j]
 *
 * 那么递推公式为：dp[i] += dp[i - j]
 *
 * dp数组如何初始化
 * 既然递归公式是 dp[i] += dp[i - j]，那么dp[0] 一定为1，dp[0]是递归中一切数值的基础所在，如果dp[0]是0的话，其他数值都是0了。
 *
 * 下标非0的dp[i]初始化为0，因为dp[i]是靠dp[i-j]累计上来的，dp[i]本身为0这样才不会影响结果
 *
 * 确定遍历顺序
 * 这是背包里求排列问题，即：1、2 步 和 2、1 步都是上三个台阶，但是这两种方法不一样！
 *
 * 所以需将target放在外循环，将nums放在内循环。
 *
 * 每一步可以走多次，这是完全背包，内循环需要从前向后遍历。
 */
