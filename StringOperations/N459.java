package algorithmbasic.leetcode.StringOperations;

public class N459 {
    public boolean repeatedSubstringPattern(String s) {
        int N = s.length();
        int[] nextarray = next(s);
        if (nextarray[N] != 0 && N % (N - nextarray[N]) == 0) {
            return true;
        }
        return false;

    }

    // make next array
    private static int[] next(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] next = new int[N + 1];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int index = 2;
        while (index <= N) {
            if (str[index - 1] == str[cn]) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                index++;
            }
        }
        return next;
    }
}
/**
 * abcd abcd abcd
 * abcde abcde
 * abcde abcde abcde abcde
 * kmp的next数组方法
 *
 * abc abd abc
 * abf abc abc
 * abc abc abd abc abc
 *
 * abc abc abc abc
 * | |
 */