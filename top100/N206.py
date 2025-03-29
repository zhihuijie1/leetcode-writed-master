
# Definition for singly-linked list.
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return
        curN = head
        nextN = head.next
        while nextN is not None:
            nnextN = nextN.next
            nextN.next = curN
            if curN == head:
                curN.next = None
            curN = nextN
            nextN = nnextN
        return curN

