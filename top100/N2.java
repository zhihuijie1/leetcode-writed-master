package algorithmbasic.leetcode.top100;

public class N2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
    这道题主要考察 链表双指针技巧 和加法运算过程中对进位的处理。注意这个 carry 变量的处理，
    在我们手动模拟加法过程的时候会经常用到。代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，也就是 dummy 节点。
    你可以试试，如果不使用 dummy 虚拟节点，代码会稍显复杂，而有了 dummy 节点这个占位符，可以避免处理初始的空指针情况，降低代码的复杂性。
     */


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // p1 p2 分别指着两个链表的头节点
        ListNode p1 = l1;
        ListNode p2 = l2;
        //创建一个虚拟头节点
        ListNode dummy = new ListNode(-1);
        //p指向虚拟头节点，p用来创建新链表连接用
        ListNode p = dummy;
        //进位寄存在carry中
        int carry = 0;
        while (p1 != null || p2 != null || carry > 0) {
            int value = carry;
            if (p1 != null) {
                value += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                value += p2.val;
                p2 = p2.next;
            }
            int m = value % 10;
            int n = value / 10;
            carry = n;
            p.next = new ListNode(m);
            p = p.next;
        }
        return dummy.next;
    }
}
