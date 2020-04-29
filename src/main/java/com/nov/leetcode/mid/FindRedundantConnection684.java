package com.nov.leetcode.mid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : longwenhe
 * @date : 2020/4/29 11:35
 * @description :
 */
/*
在本问题中, 树指的是一个连通且无环的无向图。

        输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

        结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

        返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

        输入: [[1,2], [1,3], [2,3]]
        输出: [2,3]
        解释: 给定的无向图为:
        1
        / \
        2 - 3

        输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
        输出: [1,4]
        解释: 给定的无向图为:
        5 - 1 - 2
            |   |
            4 - 3

        输入的二维数组大小在 3 到 1000。
        二维数组中的整数在1到N之间，其中N是输入数组的大小。
        更新(2017-09-26):
        我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
*/
public class FindRedundantConnection684 {
    // TODO 并不是从度的角度考虑，而是连通域的角度，或者说最小支撑树
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Integer> degreeMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Integer v1 = edges[i][0];
            Integer v2 = edges[i][1];
            if (!degreeMap.containsKey(v1)) {
                degreeMap.put(v1, 1);
            } else {
                Integer count = degreeMap.get(v1);
                count++;
                degreeMap.put(v1, count);
            }
            if (!degreeMap.containsKey(v2)) {
                degreeMap.put(v2, 1);
            } else {
                Integer count = degreeMap.get(v2);
                count++;
                degreeMap.put(v2, count);
            }
        }
        int[] result = new int[2];
        for (int[] edge : edges) {
            if (degreeMap.get(edge[0]) != 1 && degreeMap.get(edge[1]) != 1) {
                result[0] = edge[0];
                result[1] = edge[1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
//        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[][] edges = {{9, 10}, {5, 8}, {2, 6}, {1, 5}, {3, 8}, {4, 9}, {8, 10}, {4, 10}, {6, 8}, {7, 9}};
        FindRedundantConnection684 solution = new FindRedundantConnection684();
        int[] result = solution.findRedundantConnection(edges);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
