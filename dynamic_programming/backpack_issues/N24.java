package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N24 {
    public class ListNode {
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

    public ListNode swapPairs(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode cur = new ListNode(0);
        ListNode H = cur;
        ListNode prev = head;

        while(prev != null && prev.next != null) {
            ListNode pn = prev.next;
            ListNode pnn = pn.next;
            pn.next = prev;
            prev.next = pnn;
            cur.next = pn;
            cur = prev;
            prev = pnn;
        }
        return H.next;
    }
}

// 0 -> 1 -> 2 -> 3 -> 4 -> 5
// c    p    pn   pnn
// H
// 0 -> 2 -> 1 -> 3 -> 4 -> 5
// c    pn   p    pnn
//           c     p   pn   pnn
// 0 -> 2 -> 1 -> 4 -> 3 -> 5
//           c    pn   p    pnn
//                     c     p