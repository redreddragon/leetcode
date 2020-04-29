package com.nov.leetcode.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : longwenhe
 * @date : 2020/4/29 11:20
 * @description :
 */
public class ArrayToList {

    // 测试用例数组转链表工具
    public static <T> void TwoDimArrayToList(T[][] source, List<List<T>> target) {
        for (int i = 0; i < source.length; i++) {
            List<T> tmp = new ArrayList<>();
            for (int j = 0; j < source[i].length; j++) {
                tmp.add(source[i][j]);
            }
            target.add(tmp);
        }
    }
}
