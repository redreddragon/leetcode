package com.nov.leetcode.mid;

import java.util.*;

/**
 * @author : longwenhe
 * @date : 2020/4/28 13:41
 * @description :
 */
/*
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式

该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。

你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

0
|
1
/ \
2   3

输出: [1]

输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

输出: [3, 4]

 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
*/
public class FindMinHeightTrees310 {
    // 复杂度O(n²)，最后的大数不能通过
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> adList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adList.get(edges[i][0]).add(edges[i][1]);
            adList.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = bfs(adList, i, n);
            if (minHeight == tmp) {
                result.add(i);
            } else if (minHeight > tmp) {
                result.clear();
                result.add(i);
                minHeight = tmp;
            }
        }
        return result;
    }

    private int bfs(List<List<Integer>> adList, int v, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        int pre = 1;
        int next = 0;
        int height = 0;
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            pre--;
            visited[node] = true;
            for (Integer vertex : adList.get(node)) {
                if (!visited[vertex]) {
                    queue.add(vertex);
                    next++;
                }
            }
            if (pre == 0) {
                height++;
                pre = next;
                next = 0;
            }
        }
        return height - 1;
    }

    // 部分剪纸，但复杂度最坏情况下依然为O(n²)，能通过所有测试用例，但只超过5%的用户
    public List<Integer> findMinHeightTrees02(int n, int[][] edges) {
        List<List<Integer>> adList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adList.get(edges[i][0]).add(edges[i][1]);
            adList.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> result = new ArrayList<>();
        int currentHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = dfs(adList, new boolean[n], new Stack<>(), i, currentHeight);
            if (tmp != -1 && h == currentHeight) {
                result.add(i);
            } else if (tmp != -1 && currentHeight > h) {
                result.clear();
                result.add(i);
                currentHeight = h;
            }
            h = 0;
        }
        return result;
    }

    // h记录每一次深搜的最大深度
    int h = 0;

    private int dfs(List<List<Integer>> adList, boolean[] visited, Stack<Integer> result, int v, int currentHeight) {
        // 因为是树结构的图，所以一个从任意节点出发必然能遍历所有的节点，故visited只设置两种状态
        visited[v] = true;
        result.push(v);
        for (Integer node : adList.get(v)) {
            if (!visited[node]) {
                int tmp = dfs(adList, visited, result, node, currentHeight);
                // 如果某次深搜大于记录的最小当前高度，则该起始点必然不符合要求，没必要再继续其它深搜
                if (tmp == -1) {
                    return -1;
                }
                h = Math.max(h, tmp);
            }
        }
        int size = result.size();
        result.pop();
        return size - 1 > currentHeight ? -1 : size - 1;
    }

    public static void main(String[] args) {
//        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
//        int n = 4;
//        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
//        int n = 6;
        int[][] edges = {{0,1},{0,2},{0,3},{3,4}};
        int n = 5;
        FindMinHeightTrees310 solution = new FindMinHeightTrees310();
        System.out.println(solution.findMinHeightTrees(n, edges));
        System.out.println(solution.findMinHeightTrees02(n, edges));
    }
}
