package com.nov.leetcode.competition;

import java.util.LinkedList;

/**
 * @author longwenhe
 * @date 2020/4/12 11:01
 * @description
 */
public class ProcessQueries {
    public int[] processQueries(int[] queries, int m) {
        LinkedList<Integer> numList = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            numList.addLast(i);
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            Integer tmp = null;
            for (Integer num : numList) {
                if (num == queries[i]) {
                    tmp = num;
                    break;
                }
                count++;
            }
            numList.remove(tmp);
            numList.addFirst(tmp);
            result[i] = count;
        }
        return result;
    }
}
