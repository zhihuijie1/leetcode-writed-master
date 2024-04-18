package algorithmbasic.leetcode.coding3;

// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/

//看到子串，子数组，就联想到 -- 窗口，前缀和，（以什么开头，以什么结尾 -->目的是 动态规划）

import java.util.HashMap;

//思路： a b c a c b d a c c
//      想法 -- 以当前位置结尾 前面的最长不重复子串长度 -- 动态规划的方法，合理利用前面已经计算过的位置
//         a k c a b d
//假设当前位置是d位置，其当前位置结尾的最长不重复子串的长度取决于两个因素：1：前面的与i位置相同字符出现的位置m   2: i-1位置的最大不重复子串长度 n
// m < i-1-n 时 --> i位置最长不重复子串长度是 n+1
// m > i-1-n 时 --> i位置最长不重复子串长度是 i - m
public class Code01_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int ans = 1;// 收集结果
        HashMap<Character, Integer> map = new HashMap<>();// 字符对应的位置下标
        int pre = 0;//前一个位置最大不重复子串的长度

        map.put(str[0], 0);
        pre = 1;
        for (int i = 1; i < str.length; i++) {
            //前面的与i位置相同字符出现的位置m
            int beforeIIndex = map.get(str[i]) == null ? -1 : map.get(str[i]);
            int p1 = i - beforeIIndex;
            //i-1位置的最大不重复子串长度 n
            int p2 = pre + 1;
            int max = Math.min(p1, p2);
            ans = Math.max(max, ans);
            pre = max;
            map.put(str[i], i);
        }
        return ans;
    }
}
