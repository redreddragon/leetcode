package com.nov.leetcode.hard;

import com.nov.leetcode.common.ListNode;

/**
 * @author longwenhe
 * @date 2020/4/17 1:10
 * @description
 */
public class ReverseKGroup25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nextKNode = head;
        int tmpVal = k;
        while (nextKNode != null && tmpVal > 0) {
            nextKNode = nextKNode.next;
            tmpVal--;
        }
        if (tmpVal > 0) {
            return head;
        }
        tmpVal = k;
        ListNode cur = head;
        ListNode pre = cur.next;
        while (tmpVal > 1) {
            ListNode tmp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = tmp;
            tmpVal--;
        }
        // 头部已经变成尾部，也可以考虑不用递归，相当于再加一个外层循环
        head.next = reverseKGroup(nextKNode, k);
        return cur;
    }
}
