package algorithmbasic.leetcode.top100;

import java.util.LinkedList;
import java.util.List;

public class N17 {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new LinkedList<>();// 收集最终的结果
        if(digits.length() == 0) {
            return ans;
        }
        // 建立一个数字与字符串的一一关系映射
        String[] nubLetterMap = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        StringBuffer result = new StringBuffer();// 间断性结果
        int index = 0;// 指向digits的位置
        backtracking(nubLetterMap, ans, result, digits, index);
        return ans;
    }

    private void backtracking(String[] nubLetterMap, List<String> ans, StringBuffer result,String digits, int index) {
        //纵向末尾收集结果
        if(index == digits.length()) {
            ans.add(result.toString());
            return;
        }
        //横向单节点处理
        String digit = nubLetterMap[digits.charAt(index) - '0'];
        for(int i = 0; i < digit.length(); i++) {
            result.append(digit.charAt(i));
            backtracking(nubLetterMap, ans, result, digits, index+1);
            //回溯的核心 回去的时候要删除最后的字母 来放新的字母
            result.deleteCharAt(result.length() - 1);
        }
        return;
    }
}
