package com.nov.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/6 12:18
 * @description
 */
public class Generate118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                List<Integer> tmpResult = new ArrayList<>();
                tmpResult.add(1);
                result.add(tmpResult);
                continue;
            }
            if (i == 1) {
                List<Integer> tmpResult = new ArrayList<>();
                tmpResult.add(1);
                tmpResult.add(1);
                result.add(tmpResult);
                continue;
            }
            List<Integer> list = result.get(i - 1);
            // ArrayList<>() 初始化容量的机制探究
            List<Integer> tmpResult = new ArrayList<>();
            tmpResult.add(1);
            for (int j = 1; j < i; j++) {
                int val = list.get(j) + list.get(j - 1);
                tmpResult.add(val);
            }
            tmpResult.add(1);
            result.add(tmpResult);
        }
        return result;
    }

    public static void main(String[] args) {
        int numRows = 5;
        Generate118 solution = new Generate118();
        System.out.println(solution.generate(numRows));
    }
}
