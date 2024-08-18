package algorithmbasic.leetcode.top100;

import java.util.Stack;

public class N32 {
    public int longestValidParentheses(String s) {
        int N = s.length();
        int[] dp = new int[N];
        //dp[0] = 0;
        Stack<Integer> stack = new Stack<>();
        int MAX = 0;
        for(int i = 0; i < N; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
                dp[i] = 0;
            }else {
                if(!stack.isEmpty()) {
                    int left = stack.pop();
                    dp[i] = left - 1 >= 0 ? dp[left - 1] + i - left + 1 : i - left + 1; //小窍门   （（）（）） -> i - left + 1
                }else {
                    dp[i] = 0;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            MAX = Math.max(MAX, dp[i]);
        }
        return MAX;
    }
}

/*
    0：给定一个仅包含字符 '(' 和 ')' 的字符串，返回正确匹配的子串的长度   ")()())" -> 4

    1：思路：将'('直接丢到栈里面，当遇到')'，直接从栈顶取，这就是恰好匹配的一对，假设从栈顶取出的'('的下标是m
            由于要求的是可以匹配的最长子串，所以还需要知道以 m-1截止的可以正确匹配的子串长度l
            结果就是：l + (i - m + 1)
                - 解释一下为什么加的是 i - m + 1 ： 当前')'位置的下标 减 匹配'('位置的下标, 因为两个正好匹配的时候 不一定是挨在一起的 比如 (())
                - 之所以有信息 i - m + 1， 是因为中间相隔的一定匹配 因为相隔的那些都会被弹出消失，这才有了当前这一对的匹配。

            所以定义一个dp数组，dp[k] -> 以下标k结尾的可以正确匹配的子串长度是dp[k]


    2: dp数组的递推公式：
            如果当前下标k位置是"(" -> dp[k] = 0
            如果当前下标k位置是")" -> 栈顶元素是'('，栈顶的下标是n  dp[k] = dp[n-1] + k - n + 1



    3:dp数组的初始化：
            dp[0] = 0;


 */