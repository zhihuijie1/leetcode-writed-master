package algorithmbasic.leetcode.top100;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class N1249 {
    public String minRemoveToMakeValid(String s) {
        int N = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }else if(s.charAt(i) == ')') {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                }else {
                    stack.push(i);
                }
            }
        }
        Set<Integer> set = new HashSet<>(stack);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}

/*
0：题目描述
    给定一个由 '('、')' 和小写英文字符组成的字符串 s。
    你的任务是移除最少数量的括号（'(' 或 ')'，可以在任何位置），使得最终的括号字符串是有效的，并返回任意一个有效的字符串。
    正式地讲，括号字符串是有效的当且仅当：
    它是一个空字符串，或只包含小写字母，或者
    它可以写作 AB（A 和 B 连接在一起），其中 A 和 B 都是有效字符串，或者
    它可以写作 (A)，其中 A 是一个有效字符串。

    示例 1：
    输入: s = "lee(t(c)o)de)"
    输出: "lee(t(c)o)de"
    解释: "lee(t(co)de)"，"lee(t(c)ode)" 也会被接受。

    示例 2：
    输入: s = "a)b(c)d"
    输出: "ab(c)d"

    示例 3：
    输入: s = "))(("
    输出: ""
    解释: 空字符串也是有效的。

    约束条件：
    1 <= s.length <= 105
    s[i] 不是 '(' 就是 ')'，或者是小写英文字符。

1：思路：
    将可以匹配的字符串给删掉
    栈里面全部都是无效的字符串
    遍历一遍将无效的字符串给跳过去

    )(()()
 */
