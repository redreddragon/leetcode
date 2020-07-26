package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/7/19 11:11
 * @description
 */
public class CountSubTrees5465 {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Node>> helper = new HashMap<>();
        for (int[] edge : edges) {
            if (!helper.containsKey(edge[0])) {
                helper.put(edge[0], new ArrayList<>());
            }
            if (!helper.containsKey(edge[1])) {
                helper.put(edge[1], new ArrayList<>());
            }
            Node node1 = new Node(edge[1], labels.charAt(edge[1]));
            helper.get(edge[0]).add(node1);
            Node node2 = new Node(edge[0], labels.charAt(edge[0]));
            helper.get(edge[1]).add(node2);
        }
        Node[] result = new Node[n];
        dfs(helper, 0, labels, result, new boolean[n]);

        int[] ans = new int[n];
        for (Node tmpNode : result) {
            if (tmpNode == null) {
                continue;
            }
            ans[tmpNode.index] = tmpNode.countMap.get(tmpNode.value);
        }
        return ans;
    }

    public void dfs(Map<Integer, List<Node>> helper, int num, String labels, Node[] result, boolean[] visited) {
        if (helper.get(num).size() == 1 && visited[helper.get(num).get(0).index]) {
            Node leafNode = new Node(num, labels.charAt(num));
            leafNode.countMap.put(leafNode.value, 1);
            result[num] = leafNode;
            return;
        }
        List<Node> children = helper.get(num);
        Node midNode = new Node(num, labels.charAt(num));
        midNode.countMap.put(midNode.value, 1);
        visited[num] = true;
        for (Node child : children) {
            if (!visited[child.index]) {
                dfs(helper, child.index, labels, result, visited);
            }
        }
        for (Node child : children) {
            if (result[child.index] == null) {
                continue;
            }
            Map<Character, Integer> tmpMap = result[child.index].countMap;
            Map<Character, Integer> midNodeMap = midNode.countMap;
            for (Character tmpChar : tmpMap.keySet()) {
                if (midNodeMap.containsKey(tmpChar)) {
                    int tmpCount = midNodeMap.get(tmpChar) + tmpMap.get(tmpChar);
                    midNodeMap.put(tmpChar, tmpCount);
                } else {
                    midNodeMap.put(tmpChar, tmpMap.get(tmpChar));
                }
            }
        }
        result[num] = midNode;
    }


    class Node {
        int index;
        char value;
        Map<Character, Integer> countMap;

        public Node(int index, char value) {
            this.index = index;
            this.value = value;
            this.countMap = new HashMap<>();
        }
    }

    public static void main(String[] args) {
/*        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        String labels = "abaedcd";*/
        int n = 4;
        int[][] edges = {{0, 3}, {0, 2}, {1, 2}};
        String labels = "aeed";
        CountSubTrees5465 solution = new CountSubTrees5465();
        int[] result = solution.countSubTrees(n, edges, labels);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
