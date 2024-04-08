package algorithmbasic.leetcode.coding2;

import java.util.Scanner;

/**
 * 这天，一只蜗牛来到了二维坐标系的原点。 在 x 轴上长有 n 根竹竿。它们平行于 y 轴，底部纵坐标为 0 ，横坐标分别 为 x 1 , x 2 , ..., x n 。
 * 竹竿的高度均为无限高，宽度可忽略。蜗牛想要从原点走到第 n 个竹竿的底部也就是坐标 ( x n , 0) 。它只能在 x 轴上或者竹竿上爬行，在 x 轴
 * 上爬行速度为 1 单位每秒；由于受到引力影响，蜗牛在竹竿上向上和向下爬行 的速度分别为 0 . 7 单位每秒和 1 . 3 单位每秒。
 * 为了快速到达目的地，它施展了魔法，在第 i 和 i + 1 根竹竿之间建立了传 送门（0 < i < n ），
 * 如果蜗牛位于第 i 根竹竿的高度为 a i 的位置 ( x i , a i ) ，就可以 瞬间到达第 i + 1 根竹竿的高度为 b i +1 的位置 ( x i +1 , b i +1 )，
 * 请计算蜗牛最少需要多少秒才能到达目的地。
 */

//3
//1 10 11
//1 1
//2 1

//对于 100 % 的数据，保证 n ≤ 10⁵ ，a i , b i ≤ 10⁴ ，x i ≤ 10⁹ 。
public class E_snail_pace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n + 1];
        int[] a = new int[n]; //传送带的起始高度
        int[] b = new int[n]; //传送带的到达高度
        double[][] dp = new double[n + 1][2]; //答案存放在dp[n][0];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
        }
        System.out.println("信息录入完毕");
        //信息录入完毕

        dp[1][0] = arr[1] / 1.0;
        dp[1][1] = dp[1][0] + a[1] / 0.7;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + arr[i] - arr[i - 1], dp[i - 1][1] + b[i - 1] / 1.3); //从起点走到第i个杆子底部的时间
            dp[i][1] = Math.min(dp[i][0] + a[i] / 0.7, b[i - 1] > a[i] ? dp[i - 1][1] + (b[i - 1] - a[i]) / 1.3 : dp[i - 1][1] + (a[i] - b[i - 1]) / 0.7); //从起点走到第i个杆子传送带的时间
        }
        //dp[n][0]
        System.out.println(dp[n][0]);
    }
}