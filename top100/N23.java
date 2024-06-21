public class N23 {
    //采用merge sort中的分而治之的思想来解决
    class ListNode {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return new ListNode();
        }
        ListNode result = merge(lists, 0, lists.length - 1);
        return result;
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        // 纵向结尾处理
        if(left == right) {
            return lists[left];
        }
        // 横向单节点处理
        int mid = (left + right) >> 1;
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);
        return pullTogeter(leftNode,rightNode);
    }

    private ListNode pullTogeter(ListNode list1, ListNode list2) {
        ListNode head1 = list1;
        ListNode head2 = list2;
        ListNode cur = new ListNode(0);
        ListNode head = cur;
        while(head1 != null && head2 != null) {
            if(head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            }else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        while(head1 != null) {
            cur.next = head1;
            head1 = head1.next;
            cur = cur.next;
        }
        while(head2 != null) {
            cur.next = head2;
            head2 = head2.next;
            cur = cur.next;
        }
        return head.next;
    }
}

// [1,4,5]    [1,3,4]
// -> [1 1 3 4 4 5]
// ListNode pullTogether(ListNode list1, ListNode list2);

// ListNode merge(ListNode[] lists, int left, int right )