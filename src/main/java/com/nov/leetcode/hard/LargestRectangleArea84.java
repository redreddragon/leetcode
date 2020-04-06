package com.nov.leetcode.hard;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/4 15:27
 * @description
 */
public class LargestRectangleArea84 {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0];
        }
        int maxSquare = 0;
        Stack<Integer> boundary = new Stack<>();
        boundary.push(-1);
        int right = 0;
        for (; right < height.length; right++) {
            if (boundary.size() == 1 || height[right] >= height[boundary.peek()]) {
                boundary.push(right);
            } else {
                while (boundary.size() > 1 && height[boundary.peek()] > height[right]) {
                    int index = boundary.pop();
                    int left = boundary.peek();
                    maxSquare = Math.max(maxSquare, height[index] * (right - left - 1));
                }
                boundary.push(right);
            }
        }
        while (boundary.size() > 1) {
            int index = boundary.pop();
            int left = boundary.peek();
            maxSquare = Math.max(maxSquare, height[index] * (right - left - 1));
        }
        return maxSquare;
    }

    // 哨兵写法，注意宽度寻找应该以目标元素的前一个为准，而不是本身（避免逆序的例子）
    public int largestRectangleArea02(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0];
        }
        int len = height.length;
        int[] tmpHeight = new int[len + 2];
        tmpHeight[0] = -1;
        tmpHeight[len + 1] = -1;
        for (int i = 0; i < len; i++) {
            tmpHeight[i + 1] = height[i];
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int j = 0; j < tmpHeight.length; j++) {
            while (!stack.isEmpty() && tmpHeight[j] < tmpHeight[stack.peek()]) {
                int curHeight = tmpHeight[stack.pop()];
                while (curHeight == tmpHeight[stack.peek()]) {
                    stack.pop();
                }
                int width = j - stack.peek() - 1;
                max = Math.max(max, curHeight * width);
            }
            stack.push(j);
        }
        return max;
    }

    // 分而治之(线段树优化？)
    private int largestRectangleArea(int[] height, int start, int end) {
        if (start > end) {
            return 0;
        }
        boolean sorted = true;
        int minHeight = height[start];
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (height[i - 1] > height[i]) {
                sorted = false;
            }
            if (minHeight > height[i]) {
                minHeight = height[i];
                minIndex = i;
            }
        }
        int result = 0;
        if (sorted) {
            for (int i = start; i <= end; i++) {
                result = Math.max(result, height[i] * (end - i + 1));
            }
        } else {
            int leftMax = largestRectangleArea(height, start, minIndex - 1);
            int rightMax = largestRectangleArea(height, minIndex + 1, end);
            result = Math.max(minHeight * (end - start + 1), Math.max(leftMax, rightMax));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3};
        LargestRectangleArea84 solution = new LargestRectangleArea84();
        System.out.println(solution.largestRectangleArea02(height));
        System.out.println(solution.largestRectangleArea(height, 0, height.length - 1));
    }
}
