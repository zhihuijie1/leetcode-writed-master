package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
import java.util.HashSet;
import java.util.List;

public class N139 {
    public static boolean wordBreak(String s, List<String> wordDict) {
        int N = s.length();
        int M = wordDict.size();
        HashSet<String> set = new HashSet<>(wordDict);
        //定义dp数组：
        boolean[] dp = new boolean[N + 1];
        //dp数组初始化：
        dp[0] = true;
        //开始遍历数组 - 先遍历背包后遍历物品
        for(int j = 1; j <= N; j++) {
            for(int i = 0; i < M; i++) {
                if(wordDict.get(i).length() <= j) {
                    dp[j] = dp[j] || (dp[j - wordDict.get(i).length()]
                            && set.contains(s.substring(j - wordDict.get(i).length(), j)));
                }
            }
        }
        return dp[N];
    }
}

/**
 0: 完全背包问题：
        因为每一个单词可以被重复的使用

 1: dp数组以及数组下标的含义：
        背包：字符串
        物品：单词数组
        字符串的长度如果是j的话，如果可以被拆分成单词数组中的单词，则dp[j]为true

 2: dp数组的递推公式：
        来到第i个单词，
        - 不要第i个单词：dp[j]
        - 要第i个单词：dp[j - i] && i~j的单词与第i个单词是否一致
        结果：dp[j] = dp[j] || dp[j - i] && i~j的单词与第i个单词是否一致

 3：初始化：
        dp[0] = true， 如果是false的话后面的都是false

 4:遍历顺序:
        排列问题 - 先遍历背包后遍历物品
 */
