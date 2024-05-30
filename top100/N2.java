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
