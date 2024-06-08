package algorithmbasic.leetcode.top100;

import java.util.LinkedList;
import java.util.List;

public class N131 {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new LinkedList<>();
        List<String> result = new LinkedList<>();
        int startIndex = 0;
        backTracking(ans, result, startIndex, s);
        return ans;
    }

    private void backTracking(List<List<String>> ans, List<String> result, int startIndex, String s) {
        //纵向结尾 处理结果
        if(startIndex == s.length()) {
            ans.add(new LinkedList<>(result));
            return;
        }


        //横向单层（单节点）处理步骤
        while(startIndex < s.length()) {
            for(int i = startIndex; i < s.length(); i++) {
                if(isPalindrome(s.substring(startIndex, i + 1))) {
                    String str = s.substring(startIndex, i + 1);
                    result.add(str);
                    backTracking(ans, result, i+1, s);
                    result.remove(result.size() - 1);
                }
            }
            return;
        }

    }

    private boolean isPalindrome(String str) {
        for(int start = 0, end = str.length() - 1; start <= end; start++ , end--) {
            if(str.charAt(start) != str.charAt(end)) {
                return false;
            }
        }
        return true;
    }
}
