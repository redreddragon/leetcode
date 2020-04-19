package com.nov.leetcode.hard;

import java.util.*;

/**
 * @author longwenhe
 * @date 2020/4/18 21:14
 * @description
 */
public class GetSkyline218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Val[] val = new Val[buildings.length * 2];
        for (int i = 0, j = 0; i < val.length; j++) {
            val[i] = new Val(buildings[j][0], buildings[j][2], true);
            val[i + 1] = new Val(buildings[j][1], buildings[j][2], false);
            i++;
            i++;
        }
        Arrays.sort(val, Comparator.comparingInt(Val::getVal));
        PriorityQueue<Integer> heightQueue = new PriorityQueue<>(Comparator.reverseOrder());
        // TODO 优化：可以考虑右边界也使用优先级队列，小顶堆
        heightQueue.add(0);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < val.length; ) {
            if (i < val.length - 1 && val[i].getVal() == val[i + 1].getVal()) {
                if (val[i].getHeight() != val[i + 1].getHeight() && !val[i].isLeft()) {
                    heightQueue.remove(val[i].getHeight());
                } else if (val[i].getHeight() != val[i + 1].getHeight() && val[i].isLeft() && val[i + 1].isLeft()) {
                    if (val[i + 1].getHeight() < val[i].getHeight()) {
                        Val tmp = val[i + 1];
                        val[i + 1] = val[i];
                        val[i] = tmp;
                    }
                    heightQueue.add(val[i].getHeight());
                } else if (val[i].getHeight() == val[i + 1].getHeight()) {
                    i++;
                }
                i++;
                continue;
            }
            Val point = val[i];
            if (point.isLeft()) {
                if (point.getHeight() > heightQueue.peek()) {
                    List<Integer> toAddPoint = new ArrayList<>();
                    toAddPoint.add(point.getVal());
                    toAddPoint.add(point.getHeight());
                    result.add(toAddPoint);
                }
                heightQueue.add(point.getHeight());
            } else {
                heightQueue.remove(point.getHeight());
                if (point.getHeight() > heightQueue.peek()) {
                    List<Integer> toAddPoint = new ArrayList<>();
                    toAddPoint.add(point.getVal());
                    toAddPoint.add(heightQueue.peek());
                    result.add(toAddPoint);
                }
            }
            i++;
        }
        return result;
    }

    class Val {
        private int val;
        private int height;
        private boolean isLeft;

        public int getVal() {
            return val;
        }

        public int getHeight() {
            return height;
        }

        public boolean isLeft() {
            return isLeft;
        }

        public Val(int val, int height, boolean isLeft) {
            this.val = val;
            this.height = height;
            this.isLeft = isLeft;
        }
    }

    public static void main(String[] args) {
//        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] buildings = {{2, 9, 10}, {9, 12, 15}};
        int[][] buildings = {{2, 4, 70}, {3, 8, 30}, {6, 100, 41}, {7, 15, 70}, {10, 30, 102}, {15, 25, 76}, {60, 80, 91}, {70, 90, 72}, {85, 120, 59}};
        GetSkyline218 solution = new GetSkyline218();
        System.out.println(solution.getSkyline(buildings));
    }
}
