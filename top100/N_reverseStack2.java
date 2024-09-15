package algorithmbasic.leetcode.top100;

import java.util.LinkedList;
import java.util.Stack;

//给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
public class N_reverseStack2 {
    public static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int lowValue = findLowValue(stack);
        reverse(stack);
        stack.push(lowValue);
    }

    public static int findLowValue(Stack<Integer> stack) {
        int value = stack.pop(); //2 1
        if(stack.isEmpty()) {
            return value; //1
        }
        int lowValue = findLowValue(stack); //1 1
        stack.push(value); //2
        return lowValue;
    }
}
