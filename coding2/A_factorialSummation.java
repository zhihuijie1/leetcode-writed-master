package algorithmbasic.leetcode.coding2;

/**
 * 【问题描述】
 * 令 S = 1! + 2! + 3! + ... + 202320232023! ，求 S 的末尾 9 位数字。
 * 提示：答案首位不为 0 。
 */
public class A_factorialSummation {
    public static void main(String[] args) {
        long pre = 1;
        long sum = 0;
        String s = "202320232023";
        long n = Long.parseLong(s);
        for (long i = 1; i <= n; i++) {
            if(i <= 40) {
                pre *= i;
                sum += pre % 1000000000;
                sum %= 1000000000;
                System.out.println(sum);
            }else {
                System.out.println(sum);
            }
        }
    }
}
