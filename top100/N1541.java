package algorithmbasic.leetcode.top100;

import java.util.Stack;

public class N1541 {
    public int minInsertions(String s) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }else {
                if(!stack.isEmpty()) {
                    if(i + 1 < s.length() && s.charAt(i + 1) == ')') {
                        i++;
                    }else {
                        count++;
                    }
                    stack.pop();
                }else {
                    if(i + 1 < s.length() && s.charAt(i + 1) == ')') {
                        count++;
                        i++;
                    }else {
                        count += 2;
                    }
                }
            }
        }
        return count + 2 * stack.size();
    }
}

/*
    0:题目描述：
        给定一个仅包含字符 '(' 和 ')' 的括号字符串 s。一个括号字符串是平衡的如果：
        任何左括号 '(' 必须有两个连续的右括号 '))' 对应。
        左括号 '(' 必须在对应的两个连续右括号 '))' 之前。
        换句话说，我们将 '(' 视为开括号，将 '))' 视为闭括号。
        例如，"())"、"())(())))" 和 "(())())))" 是平衡的，")()"、"()))" 和 "(()))" 是不平衡的。
        你可以在字符串的任何位置插入字符 '(' 和 ')' 来平衡它（如果需要的话）。
        返回使 s 平衡所需的最小插入次数。

    1:思路："))())(" -> 3  '() ()) )' -> 3   (())) -> 1  () ()) ()
        直接把'('扔进栈中，当拿到')'时判断一下后面是不是也是')'，后面也是的化，直接弹出栈，然后指针来到后面的后一个位置
        如果后面不是')'则需要插入一个')' count++
        如果当前是')'而此时栈是空的，则需要一个'('，于此同时判断一下，后面是不是')'是的化就加一个‘(’就可以，如果不是的化，还需要再加一个')'

 */
