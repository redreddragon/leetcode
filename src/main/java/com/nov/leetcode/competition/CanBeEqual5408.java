package com.nov.leetcode.competition;

import java.util.Arrays;

/**
 * @author longwenhe
 * @date 2020/5/30 23:19
 * @description
 */
public class CanBeEqual5408 {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.toString(target).equals(Arrays.toString(arr));
    }
}
