package algorithmbasic.leetcode.top100;

public class N14 {
    public String longestCommonPrefix(String[] strs) {
        int N = strs.length;
        int M = strs[0].length();

        for(int col = 0 ; col < M ; col++) {
            for(int row = 1 ; row < N ; row++) {
                String curStr = strs[row];
                String preStr = strs[row - 1];
                if(col >= curStr.length() || col >= preStr.length()
                        ||  curStr.charAt(col) != preStr.charAt(col)) {
                    return strs[0].substring(0 , col);
                }
            }
        }
        return strs[0];
    }
}
