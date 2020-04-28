package com.nov.leetcode.mid;

import java.util.*;

/**
 * @author longwenhe
 * @date 2020/4/27 22:04
 * @description
 */
public class CloneGraph133 {
    // BFS，queue用来决定遍历顺序，map用来去重、退出循环
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(node);

        Node root = new Node(node.val, new ArrayList<>());
        Map<Integer, Node> nodeMap = new HashMap<>();
        nodeMap.put(node.val, root);

        while (!nodeQueue.isEmpty()) {
            Node oldNode = nodeQueue.poll();
            for (Node tmp : oldNode.neighbors) {
                if (!nodeMap.containsKey(tmp.val)) {
                    nodeMap.put(tmp.val, new Node(tmp.val, new ArrayList<>()));
                    nodeQueue.add(tmp);
                }
                nodeMap.get(oldNode.val).neighbors.add(nodeMap.get(tmp.val));
            }
        }
        return root;
    }

    // DFS，同样的，使用map来去重和标记已访问的元素，对于一般的深搜、广搜而言，在Node类中引入boolean visited就能标记已访问的元素
    public Node cloneGraph02(Node node) {
        return dfs(node, new HashMap<>());
    }

    private Node dfs(Node node, Map<Node, Node> nodeMap) {
        if (node == null) {
            return null;
        }
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }
        Node root = new Node(node.val, new ArrayList<>());
        nodeMap.put(node, root);
        for (Node tmp : node.neighbors) {
            root.neighbors.add(dfs(tmp, nodeMap));
        }
        return root;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}


