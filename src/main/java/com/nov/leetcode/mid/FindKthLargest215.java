package com.nov.leetcode.mid;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author longwenhe
 * @date 2020/4/18 0:36
 * @description
 */
public class FindKthLargest215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (Integer num : nums) {
            priorityQueue.add(num);
        }
        while (k > 1) {
            priorityQueue.poll();
            k--;
        }
        return priorityQueue.poll();
    }

    public int findKthLargest01(int[] nums, int k) {
        return findKth(nums, 0, nums.length - 1, k);
    }

    public int findKth(int[] nums, int start, int end, int k) {
        int pivot = getPivot(nums, start, end);
        if (pivot == k - 1) {
            return nums[pivot];
        } else if (pivot > k - 1) {
            return findKth(nums, start, pivot - 1, k);
        } else {
            return findKth(nums, pivot + 1, end, k);
        }

    }

    private int getPivot(int[] nums, int start, int end) {
        Random r = new Random();
        int index = r.nextInt(end - start + 1) + start;
        swap(nums, index, start);
        int pivotNum = nums[start];
        int i = start, j = end;
        while (j > i) {
            while (j > i && nums[j] <= pivotNum) {
                j--;
            }
            nums[i] = nums[j];
            while (j > i && nums[i] >= pivotNum) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivotNum;
        return i;
    }

    private int getPivot02(int[] nums, int start, int end) {
        Random r = new Random();
        int index = r.nextInt(end - start + 1) + start;
        swap(nums, index, end);
        int pivotNum = nums[end];
        int i = start;
        for (int j = start; j <= end; j++) {
            if (nums[j] > pivotNum) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(2));
        }
    }

}
