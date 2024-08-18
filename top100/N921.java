package algorithmbasic.leetcode.top100;

import java.util.Stack;

public class N921 {
    public int minAddToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }else {
                if(!stack.isEmpty()) {
                    stack.pop();
                }else {
                    res++;
                }
            }
        }
        return res + stack.size();
    }
}

/*
0：题目描述：
    给你输入一个字符串 s，你可以在其中的任意位置插入左括号 ( 或者右括号 )，
    请问你最少需要几次插入才能使得 s 变成一个有效的括号串？
    比如说输入 s = "())("，算法应该返回 2，因为我们至少需要插入两次把 s 变成 "(())()"，
    这样每个左括号都有一个右括号匹配，s 是一个有效的括号串。

1：思路：
    直接把'('压入栈中，然后遇到')'直接从栈中取，栈中剩几个 + 遍历中有几个')'没有配对的 => 结果


*/
