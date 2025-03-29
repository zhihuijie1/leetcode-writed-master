package algorithmbasic.leetcode.top100;

public class N_234 {
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


    public boolean isPalindrome(ListNode head) {
        ListNode L = head;
        ListNode R = head;
        //找到中点位置L
        while (R != null && R.next != null) {
            R = R.next.next;
            L = L.next;
        }

        //便于奇数长度时的一一比对，忽略中间节点的比对
        if (R != null) {
            L = L.next;
        }

        //L为中点位置
        //将以L为头的后面的节点反转,返回值为反转后的头节点
        ListNode right = reverse(L);
        ListNode left = head;

        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    //将以L为头的后面的节点反转,返回值为反转后的头节点
    //4 -> 5 -> 6 -> 7 -> 8
    //pre head next
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre; //注意返回的是pre
    }
}
/**
 0：题目描述：
 给定一个单链表的头节点，判断该链表是否为回文链表。如果是，返回 true，否则返回 false。
 示例 1：
 输入: head = [1,2,2,1]
 输出: true
 示例 2：
 输入: head = [1,2]
 输出: false

 奇数：
 1 2 3 4 |5| 4 3 2 1
 0 1 2 3 |4| 5 6 7 8
 L        R

 偶数：
 1 2 3 |4 5| 4 3 2 n
 0 1 2 |3 4| 5 6 7
 L        R


 1：思路：
 通过快慢指针找到找到链表的中点位置
 然后将后面的链表反转一下
 判断是否为回文链表

 */
