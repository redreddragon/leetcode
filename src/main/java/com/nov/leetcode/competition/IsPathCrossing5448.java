package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : longwenhe
 * @date : 2020/6/28 10:34
 * @description :
 */
public class IsPathCrossing5448 {
    public boolean isPathCrossing(String path) {
        int len = path.length();
        int originY = len;
        int originX = len;
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        visited.put(originX, new HashSet<>());
        visited.get(originX).add(originY);
        for (Character c : path.toCharArray()) {
            if (c.equals('S')) {
                originY--;
            } else if (c.equals('E')) {
                originX++;
            } else if (c.equals('N')) {
                originY++;
            } else {
                originX--;
            }
            if (!visited.containsKey(originX)) {
                visited.put(originX, new HashSet<>());
            }
            if (visited.get(originX).contains(originY)) {
                return true;
            } else {
                visited.get(originX).add(originY);
            }
        }
        return false;
    }
}
