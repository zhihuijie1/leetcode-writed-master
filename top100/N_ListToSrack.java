package algorithmbasic.leetcode.top100;

// 使用单链表实现栈的功能
public class N_ListToSrack {
    class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    Node head;
    int usedSize;

    public N_ListToSrack() {
        this.head = null;
        usedSize = 0;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        usedSize++;
    }

    public  int pop() {
        if(isEmpty()) {
            System.out.println("栈是空的");
        }
        int value = head.val;
        head = head.next;
        usedSize--;
        return value;
    }

    public int peek() {
        if(isEmpty()) {
            System.out.println("栈是空的");
        }
        return head.val;
    }

    public boolean isEmpty() {
        return usedSize == 0;
    }
}
