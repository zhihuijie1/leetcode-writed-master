package algorithmbasic.leetcode.top100;

public class N25 {
    static class ListNode {
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

    // k个节点一组反转链表，返回反转后的头节点
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode end = head;
        for(int i = 0; i < k; i++) {
            if(end == null) {
                return head;
            }
            end = end.next;
        }
        // 范围：[ first, end )
        ListNode newHead = reverseGroup(first, end);
        first.next = reverseKGroup(end, k);
        return newHead;
    }

    private ListNode reverseGroup(ListNode first, ListNode end) {
        if(first == null || first.next == null) {
            return first;
        }
        ListNode cur = first;
        ListNode pre = null;
        ListNode nxt = first;
        while(cur != end) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
