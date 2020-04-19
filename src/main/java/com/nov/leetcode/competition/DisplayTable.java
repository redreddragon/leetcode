package com.nov.leetcode.competition;

import java.util.*;

/**
 * @author longwenhe
 * @date 2020/4/19 10:51
 * @description
 */
public class DisplayTable {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<String, Map<String, Integer>> tableMap = new HashMap<>();
        Set<String> foodSet = new TreeSet<String>((o1, o2) -> o1.compareTo(o2));
        Set<Integer> tableSet = new TreeSet<Integer>((o1, o2) -> o1.compareTo(o2));
        for (List<String> strList : orders) {
            String table = strList.get(1);
            String food = strList.get(2);
            foodSet.add(food);
            tableSet.add(Integer.valueOf(table));
            if (tableMap.containsKey(table)) {
                Map<String, Integer> numMap = tableMap.get(table);
                if (numMap.containsKey(food)) {
                    Integer foodNum = numMap.get(food);
                    foodNum++;
                    numMap.put(food, foodNum);
                } else {
                    numMap.put(food, 1);
                }
            } else {
                Map<String, Integer> numMap = new HashMap<>();
                numMap.put(food, 1);
                tableMap.put(table, numMap);
            }
        }
        List<List<String>> result = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add("Table");
        for (String foodName : foodSet) {
            name.add(foodName);
        }
        result.add(name);
        for (Integer table : tableSet) {
            List<String> tmpResult = new ArrayList<>();
            tmpResult.add(table.toString());
            Map<String, Integer> numMap = tableMap.get(table.toString());
            for (String food : foodSet) {
                if (numMap.containsKey(food)) {
                    tmpResult.add(numMap.get(food).toString());
                } else {
                    tmpResult.add("0");
                }
            }
            result.add(tmpResult);
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] strings = {
                {"David", "3", "Ceviche"},
                {"Corina", "10", "Beef Burrito"},
                {"David", "3", "Fried Chicken"},
                {"Carla", "5", "Water"},
                {"Carla", "5", "Ceviche"},
                {"Rous", "3", "Ceviche"}};
        List<List<String>> orders = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < strings[i].length; j++) {
                tmp.add(strings[i][j]);
            }
            orders.add(tmp);
        }
        DisplayTable solution = new DisplayTable();
        System.out.println(solution.displayTable(orders));
    }
}
