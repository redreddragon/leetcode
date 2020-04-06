package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longwenhe
 * @date 2020/4/5 10:43
 * @description
 */
public class NumSteps {
    public int numSteps(String s) {
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            list.add(chars[i]);
        }
        // 双端队列是否会更好？
        int count = 0;
        while (list.size() > 1) {
            if (list.get(0) == '1') {
                add(list);
            } else {
                list.remove(0);
            }
            count++;
        }
        return count;

    }

    private void add(List<Character> list) {
        int step = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == '1') {
                list.set(i, '0');
                step = 1;
            } else {
                list.set(i, '1');
                step = 0;
                break;
            }
        }
        if (step == 1) {
            list.add('1');
        }

    }

    public static void main(String[] args) {
        String s = "1101";
        NumSteps solution = new NumSteps();
        System.out.println(solution.numSteps(s));
    }
}
