package com.nov.leetcode.competition;

/**
 * @author longwenhe
 * @date 2020/8/30 11:47
 * @description
 */
public class ContainsPattern5499 {
    public boolean containsPattern(int[] arr, int m, int k) {
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            for (int j = i; j < arr.length; j += m) {
                int tmp = j;
                int last = j + (m << 1);
                if (last > arr.length) {
                    continue;
                }
                int n = j + m;
                for (; n < last; n++) {
                    if (arr[tmp] != arr[n]) {
                        break;
                    }
                    tmp++;
                }
                if (n == last) {
                    count++;
                } else {
                    break;
                }
            }
            max = Math.max(max, count);
        }
        return max >= k;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 1, 3};
        int m = 2;
        int k = 3;
        ContainsPattern5499 solution = new ContainsPattern5499();
        System.out.println(solution.containsPattern(arr, m, k));
    }
}
