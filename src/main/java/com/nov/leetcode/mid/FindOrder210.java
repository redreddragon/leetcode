package com.nov.leetcode.mid;

import java.util.*;

/**
 * @author : longwenhe
 * @date : 2020/4/28 10:50
 * @description :
 */
/*
现在你总共有 n 门课需要选，记为 0 到 n-1。

        在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

        给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

        可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

        输入: 2, [[1,0]]
        输出: [0,1]
        解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

        输入: 4, [[1,0],[2,0],[3,1],[3,2]]
        输出: [0,1,2,3] or [0,2,1,3]
        解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
             因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

*/
public class FindOrder210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        Queue<Integer> resultQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            resultQueue.add(v);
            numCourses--;
            for (Integer node : adList.get(v)) {
                inDegree[node]--;
                if (inDegree[node] == 0) {
                    queue.add(node);
                }
            }
        }
        if (numCourses != 0) {
            resultQueue.clear();
        }
        int[] result = new int[resultQueue.size()];
        int i = 0;
        while (!resultQueue.isEmpty()) {
            result[i] = resultQueue.poll();
            i++;
        }
        return result;
    }

    // 一种极端的例子，就是所有的点都是孤立点，而没有任何边，即prerequisites.size() == 0
    // 因此对于顶点的遍历，需要以一种更“普遍”的方式，考虑到题目中给出的顶点是按编号0～n-1，因此可以跟随邻接表的初始化而构建
    // 与BFS不同的是，入度数组inDegree初始化默认所有元素为0，因此“潜在”的装入了所有的孤立点，故上述方式没有错误
    // 特别的，BFS使用队列装入结果，而DFS使用了栈，因为BFS首个元素必然是入度为0的元素，而DFS首个元素必然是出度为0的元素
    public int[] findOrder02(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adList = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            visited.put(i, 0);
            adList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Stack<Integer> stack = new Stack<>();
        for (Integer v : visited.keySet()) {
            if (visited.get(v) == 0 && !dfs(adList, visited, v, stack)) {
                stack.clear();
                break;
            }
        }
        int[] result = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i++;
        }
        return result;
    }

    private boolean dfs(List<List<Integer>> adList, Map<Integer, Integer> visited, int v, Stack<Integer> stack) {
        visited.put(v, 1);
        for (Integer node : adList.get(v)) {
            if (visited.get(node) == 1 || visited.get(node) == 0 && !dfs(adList, visited, node, stack)) {
                return false;
            }
        }
        visited.put(v, -1);
        stack.push(v);
        return true;
    }

}
