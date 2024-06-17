package algorithmbasic.leetcode.top100;

import java.util.Stack;

public class N20 {
    public static boolean isValid(String s) {
        Stack<Character> validStack = new Stack<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '{' || str[i] == '[' || str[i] == '(') {
                validStack.push(str[i]);
            } else {
                if (validStack.isEmpty()) {
                    return false;
                } else if (validStack.peek() == '{' && str[i] == '}'
                        || validStack.peek() == '(' && str[i] == ')'
                        || validStack.peek() == '[' && str[i] == ']') {
                    validStack.pop();
                } else {
                    return false;
                }
            }
        }
        return validStack.isEmpty();
    }
}
