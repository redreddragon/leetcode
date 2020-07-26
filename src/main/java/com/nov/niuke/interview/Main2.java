package com.nov.niuke.interview;

import java.util.Scanner;

/**
 * @author longwenhe
 * @date 2020/7/20 1:25
 * @description
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] strings = s.split(",");
        int[] nums = new int[strings.length];
        int i = 0;
        for (String tmp : strings) {
            nums[i] = Integer.valueOf(tmp);
        }
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        int count = (int) (sum / 500.0 + 0.5);
        System.out.println(count);
    }

    public static int getNum(int[] nums) {
        return 0;

    }
}
