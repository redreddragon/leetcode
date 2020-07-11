package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longwenhe
 * @date 2020/5/30 23:41
 * @description
 */
public class CheckIfPrerequisite5410 {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] query = new boolean[n][n];
        for (int[] p : prerequisites) {
            query[p[0]][p[1]] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (query[k][i] && query[i][j]) {
                        query[k][j] = true;
                    }
                }
            }
        }
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            result.add(query[queries[i][0]][queries[i][1]]);
        }
        return result;
    }

    private boolean checkExist(Integer first, Integer next, Map<Integer, Set<Integer>> adMap) {
        if (!adMap.containsKey(first)) {
            return false;
        }
        if (adMap.get(first).contains(next)) {
            return true;
        }


        for (Integer num : adMap.get(first)) {
            if (checkExist(num, next, adMap)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] prerequisites = {
                {1, 0}
        };
        int[][] queries = {
                {1, 0},
                {0, 1}
        };
        CheckIfPrerequisite5410 solution = new CheckIfPrerequisite5410();
        System.out.println(solution.checkIfPrerequisite(n, prerequisites, queries));
    }
}
