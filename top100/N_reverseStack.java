package algorithmbasic.leetcode.top100;

import java.util.Stack;

public class N_reverseStack {
    public static void reverseStack(Stack<Integer> stack1, Stack<Integer> stack2) {
        process(stack1, stack2);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    private static void process(Stack<Integer> stack1, Stack<Integer> stack2) {
        if(stack1.isEmpty()) {
            return;
        }
        int peek = stack1.pop();
        process(stack1, stack2);
        stack2.push(peek);
    }

    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        for(int i = 5; i >= 1; i--) {
            stack1.push(i);
        }
        reverseStack(stack1, new Stack<>());
        while(!stack1.isEmpty()) {
            System.out.print(stack1.pop() + " ");
        }
    }
}
