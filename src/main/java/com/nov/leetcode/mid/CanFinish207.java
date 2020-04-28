package com.nov.leetcode.mid;

import java.util.*;

/**
 * @author : longwenhe
 * @date : 2020/4/28 9:35
 * @description :
 */
/*
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

        在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

        给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
*/
public class CanFinish207 {

    // BFS，构建邻接表，使用入度为0的点作为起始遍历点，并修改出度点的入度，从而获得拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            adList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (inDegree[node] == 0) {
                numCourses--;
                for (Integer v : adList.get(node)) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.add(v);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    // DFS，构建邻接表的同时需要使用map来装入需要遍历的所有顶点，并给出标记，来区分是同一次深搜还是不同次，从而判定是否有环路
    public boolean canFinish02(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adList.add(new ArrayList<>());
        }
        // -1表示已被其他次搜索访问过，0表示未访问，1表示被当前搜索访问过
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            visited.put(prerequisites[i][0], 0);
            visited.put(prerequisites[i][1], 0);
            adList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (Integer v : visited.keySet()) {
            if (visited.get(v) == 0 && !dfs(adList, visited, v)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adList, Map<Integer, Integer> lookup, int v) {
        lookup.put(v, 1);
        for (Integer node : adList.get(v)) {
            if (lookup.get(node) == 1) {
                return false;
            } else if (lookup.get(node) == 0) {
                if (!dfs(adList, lookup, node)) {
                    return false;
                }
            }
        }
        lookup.put(v, -1);
        return true;
    }
}
