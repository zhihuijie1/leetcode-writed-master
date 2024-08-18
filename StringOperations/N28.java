package algorithmbasic.leetcode.StringOperations;

public class N28 {
    public int strStr(String haystack, String needle) {
        return kmp(haystack, needle);
    }

    private static int kmp(String str1, String str2) {
        if (str1.length() < str2.length() || str2.length() == 0 || str1.length() == 0) {
            return -1;
        }
        char[] st1 = str1.toCharArray();
        char[] st2 = str2.toCharArray();
        int[] next = makeNext(st2);
        int x = 0;
        int y = 0;
        while (x < st1.length && y < st2.length) {
            if (st1[x] == st2[y]) {
                x++;
                y++;
            } else if (y > 0) {
                y = next[y];
            } else {
                // y == 0
                x++;
            }
        }
        return y == st2.length ? x - y : -1;
    }

    private static int[] makeNext(char[] st2) {
        if (st2.length < 2) {
            return new int[] { -1 };
        }
        int[] next = new int[st2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0; // 前末尾处理
        int i = 2; // 后末尾处理
        while (i < st2.length) {
            if (st2[cn] == st2[i - 1]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                i++;
            }
        }
        return next;
    }
}
/**
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 * 示例 1: 输入: haystack = "hello", needle = "ll" 输出: 2
 * 示例 2: 输入: haystack = "aaaaa", needle = "bba" 输出: -1
 *
 * 1：kmp算法：在一个大的字符串中找到一个小字符串的位置
 */
