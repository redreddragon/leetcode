package com.nov.leetcode.competition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/5 11:06
 * @description
 */
public class LongestDiverseString {
    public String longestDiverseString(int a, int b, int c) {
        StringBuffer result = new StringBuffer();
        // 准备工作环境
        int[] nums = {a, b, c};
        Map<Character, Integer> tokenNumMap = new HashMap<>();
        tokenNumMap.put('a', 0);
        tokenNumMap.put('b', 0);
        tokenNumMap.put('c', 0);
        Map<Integer, Character> tokenMap = new HashMap<>();
        tokenMap.put(0, 'a');
        tokenMap.put(1, 'b');
        tokenMap.put(2, 'c');
        Map<Character, Integer> tokenIndex = new HashMap<>();
        tokenIndex.put('a', 0);
        tokenIndex.put('b', 1);
        tokenIndex.put('c', 2);
        // 找到最大值，作为初始化令牌获得者
        Character token = tokenMap.get(getMaxIndex(nums, -1));
        token = transferToken(nums, tokenNumMap, tokenIndex, tokenMap, token);
        while (token != null) {
            result.append(token);
            token = transferToken(nums, tokenNumMap, tokenIndex, tokenMap, token);
        }
        return new String(result);
    }

    private Character transferToken(int[] nums, Map<Character, Integer> tokenNumMap, Map<Character, Integer> tokenIndex, Map<Integer, Character> tokenMap, Character token) {
        // 发生令牌转移有两种情况，一种是本轮次数耗尽，另一种是有更高的优先级抢占
        int index = getMaxIndex(nums, -1);
        int count;
        if (index != tokenIndex.get(token)) {
            // 更高的优先级抢占，需清理被抢占的资源的使用次数，此时index即更高优先级资源的索引
            count = 0;
            tokenNumMap.put(token, count);
        } else if (tokenNumMap.get(token) >= 2) {
            // 次数耗尽，此时显然应该在当前token的index之外的剩下元素中寻找最大值，更新获得令牌的index，同样需清理被抢占的资源的使用次数
            index = getMaxIndex(nums, tokenIndex.get(token));
            count = 0;
            tokenNumMap.put(token, count);
        } else {
            // 令牌未转移，继续保持
        }
        if (nums[index] == 0) {
            // 此时表示获得令牌的资源，数目已经耗尽，可以退出循环
            return null;
        }
        // 处理获得令牌的资源
        token = tokenMap.get(index);
        nums[index]--;
        count = tokenNumMap.get(token);
        count++;
        tokenNumMap.put(token, count);
        return token;
    }

    private int getMaxIndex(int[] nums, int exclude) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i != exclude && nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int a = 2, b = 4, c = 1;
        LongestDiverseString solution = new LongestDiverseString();
        System.out.println(solution.longestDiverseString(a, b, c));
    }
}
