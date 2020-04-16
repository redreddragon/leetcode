package com.nov.leetcode.hard;

import com.nov.leetcode.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author longwenhe
 * @date 2020/4/16 20:23
 * @description
 */
public class MergeKLists23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        while (lists.length > 1) {
            int len = ((lists.length - 1) >> 1) + 1;
            ListNode[] tmpLists = new ListNode[len];
            for (int i = 0, j = 0; i < lists.length && j < len; ) {
                if (i < lists.length - 1) {
                    tmpLists[j] = binaryMerge(lists[i], lists[i + 1]);
                    i = i + 2;
                } else {
                    tmpLists[j] = lists[i];
                    i++;
                }
                j++;
            }
            lists = tmpLists;
        }
        return lists[0];
    }

    private ListNode binaryMerge(ListNode firstList, ListNode secondList) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (firstList != null || secondList != null) {
            if (firstList == null) {
                cur.next = secondList;
                secondList = secondList.next;
            } else if (secondList == null) {
                cur.next = firstList;
                firstList = firstList.next;
            } else {
                if (firstList.val < secondList.val) {
                    cur.next = firstList;
                    firstList = firstList.next;
                } else {
                    cur.next = secondList;
                    secondList = secondList.next;
                }
            }
            cur = cur.next;
        }
        return head.next;
    }

    // 优先级队列的使用，应考虑自己会构造基本的优先级队列
    public ListNode mergeKLists02(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val > o2.val) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!priorityQueue.isEmpty()) {
            head.next = priorityQueue.poll();
            head = head.next;
            if (head.next != null) {
                priorityQueue.add(head.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        lists[0] = node1;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;
        lists[1] = node4;
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;
        lists[2] = node7;
        MergeKLists23 solution = new MergeKLists23();
        ListNode result = solution.mergeKLists02(lists);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
