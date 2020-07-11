package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/5/31 11:18
 * @description
 */
public class MinReorder5426 {
    int count = 0;

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> inMap = new HashMap<>();
        Map<Integer, List<Integer>> outMap = new HashMap<>();
        for (int[] c : connections) {
            if (!inMap.containsKey(c[1])) {
                inMap.put(c[1], new ArrayList<>());
            }
            if (!outMap.containsKey(c[0])) {
                outMap.put(c[0], new ArrayList<>());
            }
            inMap.get(c[1]).add(c[0]);
            outMap.get(c[0]).add(c[1]);
        }
        boolean[] visited = new boolean[n];
        setPath(0, inMap, outMap, visited);
        return count;
    }

    private void setPath(int node, Map<Integer, List<Integer>> inMap, Map<Integer, List<Integer>> outMap, boolean[] visited) {
        visited[node] = true;
        if (inMap.containsKey(node)) {
            for (Integer num : inMap.get(node)) {
                if (!visited[num]) {
                    setPath(num, inMap, outMap, visited);
                }
            }
        }
        if (outMap.containsKey(node)) {
            for (Integer num : outMap.get(node)) {
                if (!visited[num]) {
                    count++;
                    setPath(num, inMap, outMap, visited);
                }
            }
        }
    }

}
