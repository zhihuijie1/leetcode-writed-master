package algorithmbasic.leetcode.top100;

public class N21 {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //双指针方法
        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode cur = new ListNode(0);
        ListNode res = cur;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }

        while (l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }

        while (l2 != null) {
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return res.next;
    }
}
