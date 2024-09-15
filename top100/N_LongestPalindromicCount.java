package algorithmbasic.leetcode.top100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class N_LongestPalindromicCount {

    public static String[] longestPalindrome(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        int N = s.length();
        String maxString = s.substring(0, 1);
        List<String> result = new LinkedList<>();

        for(int i = 0; i < N - 1; i++) {
            //奇数
            String str1 = palindrome(s, i,i);
            //偶数
            String str2 = palindrome(s, i, i+1);
            String maxT = str1.length() > str2.length() ? str1 : str2;
            map.put(maxT, maxT.length());
            maxString = maxString.length() >= maxT.length() ? maxString : maxT;
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxString.length()) {
                result.add(entry.getKey());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    //判断当前为中心的数，最大回文字符串是多少
    private static String palindrome(String s, int L, int R) {
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return s.substring(L+1 , R);
    }

    public static void main(String[] args) {
        String s = "babad";
        String[] str = longestPalindrome(s);
        for(String k : str) {
            System.out.println(k);
        }
    }
}

/**
 * abcbaaba
 * abcba
 *
 * 1:找到各个回文字符串，并计算其长度
 *
 * 2：收集最长的回文字符串
 */
