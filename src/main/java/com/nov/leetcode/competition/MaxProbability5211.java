package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author longwenhe
 * @date 2020/7/12 11:21
 * @description
 */
public class MaxProbability5211 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> helper = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!helper.containsKey(edges[i][0])) {
                helper.put(edges[i][0], new HashMap<>());
            }
            if (!helper.containsKey(edges[i][1])) {
                helper.put(edges[i][1], new HashMap<>());
            }
            helper.get(edges[i][0]).put(edges[i][1], succProb[i]);
            helper.get(edges[i][1]).put(edges[i][0], succProb[i]);
        }
        if(!helper.containsKey(start)){
            return 0;
        }
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        queue.add(new double[]{start, 1});
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            double[] next = queue.remove();
            if (next[0] == end) {
                return next[1];
            } else if (!visited[(int) next[0]]) {
                visited[(int) next[0]] = true;
                Map<Integer, Double> neighbor = helper.get((int)next[0]);
                for (Integer index : neighbor.keySet()) {
                    queue.add(new double[]{index, next[1] * neighbor.get(index)});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int n = 500;
        int[][] edges = {{193,229}, {133,212}, {224,465}};
        double[] succProb = {0.91,0.78,0.64};
        int start = 4;
        int end = 364;
        MaxProbability5211 solution = new MaxProbability5211();
        System.out.println(solution.maxProbability(n, edges, succProb, start, end));
        System.out.println(solution.maxProbability1(n, edges, succProb, start, end));
    }

    public double maxProbability1(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList<double[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new double[]{edges[i][1], succProb[i]});
            graph[edges[i][1]].add(new double[]{edges[i][0], succProb[i]});
        }
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        boolean[] visited = new boolean[n];
        queue.add(new double[]{start, 1});
        while (!queue.isEmpty()) {
            double[] head = queue.remove();
            if (head[0] == end) {
                return head[1];
            } else if (!visited[(int) head[0]]) {
                visited[(int) head[0]] = true;
                for (double[] next : graph[(int) head[0]]) {
                    queue.add(new double[]{next[0], head[1] * next[1]});
                }
            }
        }
        return 0;
    }
}
