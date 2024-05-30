package algorithmbasic.leetcode.top100;

public class N5 {
    public String longestPalindrome(String str) {
        // 从中间往外扩
        int N = str.length();
        String maxString = str.substring(0, 1); //一个字母的时候

        for (int i = 0; i < N - 1; i++) {
            // 以str[i]为中心往两边扩 有两种情况 奇数 / 偶数
            String ans1 = palindrome(str, i, i);
            String ans2 = palindrome(str, i, i + 1);

            String maxt = ans1.length() > ans2.length() ? ans1 : ans2;
            maxString = maxString.length() >= maxt.length() ? maxString : maxt;
        }
        return maxString;
    }

    private String palindrome(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }
}
