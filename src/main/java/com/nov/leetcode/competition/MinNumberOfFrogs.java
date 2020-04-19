package com.nov.leetcode.competition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author longwenhe
 * @date 2020/4/19 11:33
 * @description
 */
public class MinNumberOfFrogs {
    public int minNumberOfFrogs(String croakOfFrogs) {
        Queue<List<Character>> queue1 = new ArrayDeque<>();
        Queue<List<Character>> queue2 = new ArrayDeque<>();
        Queue<List<Character>> queue3 = new ArrayDeque<>();
        Queue<List<Character>> queue4 = new ArrayDeque<>();
        Queue<List<Character>> queue5 = new ArrayDeque<>();
        char[] chars = croakOfFrogs.toCharArray();
        List<Character> first = new ArrayList<>();
        if (chars[0] == 'c') {
            first.add(chars[0]);
        } else {
            return -1;
        }
        queue1.add(first);
        int min = Integer.MIN_VALUE;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == 'c') {
                List<Character> tmp = new ArrayList<>();
                tmp.add('c');
                queue1.add(tmp);
            } else if (chars[i] == 'r') {
                if (queue1.isEmpty()) {
                    return -1;
                } else {
                    List<Character> tmp = queue1.poll();
                    tmp.add('r');
                    queue2.add(tmp);
                }
            } else if (chars[i] == 'o') {
                if (queue2.isEmpty()) {
                    return -1;
                } else {
                    List<Character> tmp = queue2.poll();
                    tmp.add('o');
                    queue3.add(tmp);
                }
            } else if (chars[i] == 'a') {
                if (queue3.isEmpty()) {
                    return -1;
                } else {
                    List<Character> tmp = queue3.poll();
                    tmp.add('a');
                    queue4.add(tmp);
                }
            } else if (chars[i] == 'k') {
                if (queue4.isEmpty()) {
                    return -1;
                } else {
                    List<Character> tmp = queue4.poll();
                    tmp.add('k');
                    queue5.add(tmp);
                    int sum = queue1.size() + queue2.size() + queue3.size() + queue4.size() + queue5.size();
                    min = Math.max(min, sum);
                    queue5.poll();
                }
            }
        }
        if (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty() || !queue4.isEmpty() || !queue5.isEmpty()) {
            return -1;
        }
        return min;
    }
}
