package com.nov.leetcode.mid;

import com.nov.leetcode.utils.ArrayToList;

import java.util.*;

/**
 * @author : longwenhe
 * @date : 2020/4/29 9:52
 * @description :
 */
/*
给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

        示例 :
        给定 a / b = 2.0, b / c = 3.0
        问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
        返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

        输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

        基于上述例子，输入如下：

        equations(方程式) = [ ["a", "b"], ["b", "c"] ],
        values(方程式结果) = [2.0, 3.0],
        queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
        输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
*/
public class CalcEquation399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> adTable = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String source = equations.get(i).get(0);
            String destination = equations.get(i).get(1);
            Double val = values[i];
            Double reverseVal = 1.0 / values[i];
            if (!adTable.containsKey(source)) {
                adTable.put(source, new HashMap<>());
            }
            if (!adTable.containsKey(destination)) {
                adTable.put(destination, new HashMap<>());
            }
            adTable.get(source).put(destination, val);
            adTable.get(destination).put(source, reverseVal);
        }
        double[] result = new double[queries.size()];
        Arrays.fill(result, 1.0);
        int i = 0;
        for (List<String> query : queries) {
            String start = query.get(0);
            String end = query.get(1);
            Stack<Double> nums = new Stack<>();
            if (dfs(adTable, nums, new HashSet<>(), start, end)) {
                while (!nums.isEmpty()) {
                    result[i] *= nums.pop();
                }
            } else {
                result[i] = -1.0;
            }
            i++;
        }
        return result;
    }

    private boolean dfs(Map<String, Map<String, Double>> adTable, Stack<Double> stack, Set<String> visited, String start, String end) {
        if (!adTable.containsKey(start) || !adTable.containsKey(end)) {
            return false;
        } else if (start.equals(end)) {
            return true;
        }
        Map<String, Double> neighbors = adTable.get(start);
        visited.add(start);
        for (String v : neighbors.keySet()) {
            stack.push(neighbors.get(v));
            if (!visited.contains(v) && dfs(adTable, stack, visited, v, end)) {
                return true;
            }
            stack.pop();
        }
        visited.remove(start);
        return false;
    }

    public static void main(String[] args) {
        String[][] equations1 = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries1 = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        List<List<String>> equations = new ArrayList<>();
        ArrayToList.TwoDimArrayToList(equations1, equations);
        List<List<String>> queries = new ArrayList<>();
        ArrayToList.TwoDimArrayToList(queries1, queries);
        CalcEquation399 solution = new CalcEquation399();
        double[] result = solution.calcEquation(equations, values, queries);
        for (Double num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
