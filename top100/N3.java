package algorithmbasic.leetcode.top100;

import java.util.HashMap;
import java.util.HashSet;

public class N3 {
    public int lengthOfLongestSubstring(String s) {
        //滑动窗口来解决，关键在于左边界
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int N  = s.length();
        char[] str = s.toCharArray();

        int left = 0;
        int right = 0;
        int maxLength = 0;

        while(right < N) {

            if(!charIndexMap.containsKey(str[right])) {
                charIndexMap.put(str[right], right);
            }else {
                left = Math.max(charIndexMap.get(str[right]) + 1, left);
                charIndexMap.put(str[right], right);
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;
    }
}
