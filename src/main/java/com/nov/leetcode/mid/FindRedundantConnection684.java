package com.nov.leetcode.mid;

import java.util.*;

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
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> adTable = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Integer v1 = edges[i][0];
            Integer v2 = edges[i][1];
            if (!adTable.containsKey(v1)) {
                adTable.put(v1, new HashSet<>());
            }
            if (!adTable.containsKey(v2)) {
                adTable.put(v2, new HashSet<>());
            }
            adTable.get(v1).add(v2);
            adTable.get(v2).add(v1);
        }
        int[] result = new int[2];
        Queue<Integer> queue = new LinkedList<>();
        int[] degree = new int[adTable.size() + 1];
        int j = 1;
        for (Integer v : adTable.keySet()) {
            degree[j] = adTable.get(v).size();
            if (degree[j] == 1) {
                queue.add(v);
            }
            j++;
        }
        boolean[] visited = new boolean[adTable.size() + 1];
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            visited[node] = true;
            for (Integer next : adTable.get(node)) {
                if (!visited[next]) {
                    degree[next]--;
                    if (degree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        // 最后剩下未访问的节点，必然构成一个环路
        for (int i = edges.length - 1; i >= 0; i--) {
            if (!visited[edges[i][0]] && !visited[edges[i][1]]) {
                result[0] = edges[i][0];
                result[1] = edges[i][1];
                break;
            }
        }
        return result;
    }

    public int[] findRedundantConnection02(int[][] edges) {
        Map<Integer, Set<Integer>> adTable = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Integer v1 = edges[i][0];
            Integer v2 = edges[i][1];
            if (!adTable.containsKey(v1)) {
                adTable.put(v1, new HashSet<>());
            }
            if (!adTable.containsKey(v2)) {
                adTable.put(v2, new HashSet<>());
            }
            adTable.get(v1).add(v2);
            adTable.get(v2).add(v1);
        }
        int[] result = new int[2];
        for (int i = edges.length - 1; i >= 0; i--) {
            Integer v1 = edges[i][0];
            Integer v2 = edges[i][1];
            // 去掉当前边，如果还存在通路，则证明有环路，该边为冗余边
            adTable.get(v1).remove(v2);
            adTable.get(v2).remove(v1);
            if (dfs(adTable, new boolean[adTable.size() + 1], v1, v2)) {
                result[0] = v1;
                result[1] = v2;
                break;
            }
            adTable.get(v1).add(v2);
            adTable.get(v2).add(v1);
        }
        return result;
    }

    private boolean dfs(Map<Integer, Set<Integer>> adTable, boolean[] visited, Integer start, Integer end) {
        if (start.equals(end)) {
            return true;
        }
        visited[start] = true;
        for (Integer v : adTable.get(start)) {
            if (!visited[v] && dfs(adTable, visited, v, end)) {
                return true;
            }
        }
        visited[start] = false;
        return false;
    }

    // TODO 并查集
    public int[] findRedundantConnection03(int[][] edges) {
        Map<Integer, Set<Integer>> adTable = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Integer v1 = edges[i][0];
            Integer v2 = edges[i][1];
            if (!adTable.containsKey(v1)) {
                adTable.put(v1, new HashSet<>());
            }
            if (!adTable.containsKey(v2)) {
                adTable.put(v2, new HashSet<>());
            }
            adTable.get(v1).add(v2);
            adTable.get(v2).add(v1);
        }
        int[] result = new int[2];
        return result;
    }

    public static void main(String[] args) {
//        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
//        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
//        int[][] edges = {{9, 10}, {5, 8}, {2, 6}, {1, 5}, {3, 8}, {4, 9}, {8, 10}, {4, 10}, {6, 8}, {7, 9}};
        int[][] edges = {{142, 145}, {70, 73}, {45, 80}, {89, 126}, {126, 134}, {46, 132}, {63, 174}, {81, 151}, {154, 184}, {37, 182}, {155, 166}, {36, 155}, {28, 119}, {35, 135}, {5, 77}, {174, 200}, {1, 29}, {167, 191}, {140, 143}, {125, 171}, {25, 172}, {67, 169}, {138, 143}, {11, 86}, {106, 199}, {65, 75}, {73, 162}, {4, 121}, {95, 180}, {39, 137}, {14, 117}, {76, 170}, {68, 81}, {84, 167}, {31, 65}, {116, 136}, {115, 186}, {78, 98}, {111, 159}, {22, 88}, {160, 180}, {43, 134}, {107, 194}, {98, 115}, {134, 168}, {23, 41}, {25, 150}, {61, 113}, {34, 177}, {86, 144}, {41, 148}, {115, 181}, {91, 158}, {6, 121}, {12, 69}, {99, 141}, {39, 192}, {84, 176}, {99, 116}, {15, 79}, {10, 168}, {102, 165}, {134, 170}, {41, 109}, {64, 193}, {14, 125}, {60, 159}, {55, 164}, {18, 195}, {132, 157}, {40, 170}, {72, 103}, {17, 104}, {25, 101}, {26, 139}, {107, 178}, {56, 104}, {29, 85}, {72, 106}, {5, 59}, {21, 82}, {29, 78}, {133, 174}, {13, 71}, {19, 70}, {159, 173}, {114, 180}, {188, 189}, {105, 177}, {81, 89}, {6, 178}, {57, 129}, {64, 138}, {63, 86}, {44, 48}, {48, 56}, {131, 147}, {92, 192}, {67, 190}, {118, 138}, {35, 49}, {96, 156}, {55, 77}, {142, 152}, {102, 189}, {143, 162}, {44, 90}, {8, 32}, {34, 137}, {16, 194}, {12, 181}, {53, 145}, {137, 149}, {103, 180}, {67, 197}, {82, 170}, {122, 173}, {15, 28}, {9, 57}, {99, 179}, {76, 187}, {65, 96}, {102, 118}, {20, 150}, {62, 138}, {38, 185}, {9, 139}, {50, 168}, {18, 30}, {24, 174}, {9, 120}, {3, 82}, {123, 194}, {54, 72}, {71, 179}, {2, 88}, {182, 195}, {27, 175}, {122, 127}, {20, 104}, {35, 102}, {99, 180}, {167, 196}, {175, 182}, {93, 121}, {83, 146}, {12, 44}, {95, 96}, {7, 18}, {26, 45}, {107, 185}, {86, 88}, {25, 199}, {61, 63}, {80, 135}, {45, 94}, {128, 142}, {58, 174}, {138, 198}, {51, 149}, {152, 182}, {23, 33}, {18, 67}, {23, 121}, {66, 143}, {40, 194}, {5, 144}, {42, 192}, {116, 154}, {8, 166}, {147, 175}, {41, 163}, {110, 165}, {50, 135}, {112, 155}, {60, 183}, {33, 53}, {73, 171}, {47, 100}, {74, 169}, {70, 189}, {51, 131}, {87, 143}, {126, 158}, {78, 174}, {52, 76}, {129, 146}, {46, 84}, {96, 143}, {108, 175}, {97, 152}, {66, 153}, {100, 137}, {161, 174}, {79, 194}, {8, 181}, {55, 159}, {124, 144}, {130, 185}, {73, 132}};
        FindRedundantConnection684 solution = new FindRedundantConnection684();
        int[] result = solution.findRedundantConnection02(edges);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
