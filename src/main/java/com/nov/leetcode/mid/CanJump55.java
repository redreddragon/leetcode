package com.nov.leetcode.mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/3/26 13:54
 * @description
 */
public class CanJump55 {
    // 记录跳跃区间
    public boolean canJump(int[] nums,Stack<Integer> order) {
        // 从最后一个元素开始，找出相邻的“安全区域”
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return true;
        }
        // 初始化终点，为第一个安全区域
        int start = nums.length - 1, end = Integer.MAX_VALUE;
        order.push(start);
        while (start <= end) {
            int tmpStart = start, tmpEnd = start - 1;
            int i = start - 1;
            for (; i > -1; i--) {
                if (i + nums[i] >= start) {
                    // 出现连续的安全区时，找编号最小的元素
                    tmpStart = i;
                } else {
                    start = tmpStart;
                    end = tmpEnd;
                    if (start <= end) {
                        // 已经找到一个相邻的安全区，跳出循环继续找相邻安全区的相邻安全区
                        order.push(start);
                        break;
                    } else {
                        // 未找到，继续找
                        continue;
                    }
                }
            }
            if (i < 0) {
                start = tmpStart;
                order.push(start);
                end = tmpEnd;
                break;
            }
        }
        if (start <= end) {
            return true;
        } else {
            return false;
        }
    }
    // 从前至后贪心算法，且剪枝
    public boolean canJump2(int[] nums) {
        int maxDistance = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > maxDistance){
                return false;
            }
            maxDistance = Math.max(maxDistance,i + nums[i]);
            if(maxDistance > nums.length){
                return true;
            }
        }
        return true;
    }
    // 从后至前贪心算法
    public boolean canJump3(int[] nums) {
        int index = nums.length - 1;
        for(int i = nums.length - 2; i > -1; i--){
            if(i + nums[i] >= index){
                index = i;
            }
        }
        return index == 0;
    }
    public static void main(String[] args){
        int[] nums = {3,2,1,0,4};
        CanJump55 solution = new CanJump55();
        Stack<Integer> result = new Stack<>();
        if(solution.canJump(nums,result)){
            while (!result.isEmpty()){
                System.out.println(result.pop());
            }
        }else {
            System.out.println(false);
        }
        System.out.println("-------------------");
        System.out.println(solution.canJump3(nums));
    }

}
