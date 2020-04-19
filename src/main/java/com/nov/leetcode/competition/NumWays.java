package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/18 15:29
 * @description
 */
public class NumWays {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> relationMap = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            if (relationMap.containsKey(relation[i][0])) {
                relationMap.get(relation[i][0]).add(relation[i][1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(relation[i][1]);
                relationMap.put(relation[i][0], list);
            }
        }
        return getWays(n, relationMap, k, 0);
    }

    public int getWays(int n, Map<Integer, List<Integer>> relationMap, int k, int start) {
        if (start == n - 1 && k == 0) {
            return 1;
        } else if (k < 0) {
            return 0;
        }
        int sum = 0;
        List<Integer> nextStep = relationMap.get(start);
        if (nextStep == null || nextStep.isEmpty()) {
            return 0;
        }
        for (Integer num : nextStep) {
            start = num;
            sum += getWays(n, relationMap, k - 1, start);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
//        int[][] relation = {{0, 2}, {2, 1}};
        NumWays solution = new NumWays();
        int n = 5;
        int k = 3;
        System.out.println(solution.numWays(n, relation, k));
    }
}
