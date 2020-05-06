package com.nov.leetcode.mid;

import java.util.*;

/**
 * @author longwenhe
 * @date 2020/4/28 23:26
 * @description
 */
public class FindItinerary332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adTable = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (adTable.containsKey(from)) {
                adTable.get(from).add(to);
            } else {
                PriorityQueue<String> priorityQueue = new PriorityQueue<>();
                priorityQueue.add(to);
                adTable.put(from, priorityQueue);
            }
        }
        List<String> result = new LinkedList<>();
        dfs(adTable, result, "JFK");
        return result;
    }

    // 头插法 + 深搜解决“死路”问题，本质上是一次遍历获取有向图的欧拉路径
    private void dfs(Map<String, PriorityQueue<String>> adTable, List<String> result, String source) {
        while (adTable.containsKey(source) && !adTable.get(source).isEmpty()) {
            String destination = adTable.get(source).poll();
            dfs(adTable, result, destination);
        }
        result.add(0, source);
    }

    public List<String> findItinerary02(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adTable = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (adTable.containsKey(from)) {
                adTable.get(from).add(to);
            } else {
                PriorityQueue<String> priorityQueue = new PriorityQueue<>();
                priorityQueue.add(to);
                adTable.put(from, priorityQueue);
            }
        }
        List<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (adTable.containsKey(stack.peek()) && !adTable.get(stack.peek()).isEmpty()) {
                stack.add(adTable.get(stack.peek()).poll());
            }
            result.add(0, stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] strings = {{"EZE", "TIA"}, {"EZE", "HBA"}, {"AXA", "TIA"}, {"JFK", "AXA"}, {"ANU", "JFK"}, {"ADL", "ANU"}, {"TIA", "AUA"}, {"ANU", "AUA"}, {"ADL", "EZE"}, {"ADL", "EZE"}, {"EZE", "ADL"}, {"AXA", "EZE"}, {"AUA", "AXA"}, {"JFK", "AXA"}, {"AXA", "AUA"}, {"AUA", "ADL"}, {"ANU", "EZE"}, {"TIA", "ADL"}, {"EZE", "ANU"}, {"AUA", "ANU"}};
        List<List<String>> tickets = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            List<String> ticket = new ArrayList<>();
            ticket.add(strings[i][0]);
            ticket.add(strings[i][1]);
            tickets.add(ticket);
        }
        FindItinerary332 solution = new FindItinerary332();
        System.out.println(solution.findItinerary(tickets));
        System.out.println(solution.findItinerary02(tickets));
    }
}
