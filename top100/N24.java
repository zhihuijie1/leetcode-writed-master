package algorithmbasic.leetcode.top100;

public class N24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        ListNode skip = second.next;
        second.next = first;
        first.next = swapPairs(skip);
        return second;
    }
}
// 首先要明白递归是用来干嘛的
// 注意纵向截至条件
