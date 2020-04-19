package com.nov.leetcode.competition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author longwenhe
 * @date 2020/4/18 16:01
 * @description
 */
public class GetTriggerTime {
    // 复杂度超过要求，为O(n²)
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] sum = new int[3];
        int[] time = new int[requirements.length];
        boolean[] visited = new boolean[requirements.length];
        for (int i = 0; i < increase.length; i++) {
            checkLimit(requirements, sum, time, visited, i);
            sum[0] += increase[i][0];
            sum[1] += increase[i][1];
            sum[2] += increase[i][2];
        }
        checkLimit(requirements, sum, time, visited, increase.length);
        return time;
    }

    private void checkLimit(int[][] requirements, int[] sum, int[] time, boolean[] visited, int index) {
        for (int i = 0; i < requirements.length; i++) {
            if (visited[i]) {
                continue;
            } else if (compareLimit(sum, requirements[i])) {
                time[i] = index;
                visited[i] = true;
            } else {
                time[i] = -1;
            }
        }
    }

    private boolean compareLimit(int[] sum, int[] require) {
        if (sum[0] >= require[0] && sum[1] >= require[1] && sum[2] >= require[2]) {
            return true;
        } else {
            return false;
        }
    }

    // 复杂度最坏情况下O(nlogn)，最好情况下O(n)
    public int[] getTriggerTime02(int[][] inc, int[][] req) {
        int m = req.length;
        Req[] reqs = new Req[m];
        for (int i = 0; i < m; i++) {
            reqs[i] = new Req();
            reqs[i].vals = req[i];
        }

        // 没想到优先级队列还可以形成数组
        PriorityQueue<Req>[] pq = new PriorityQueue[3];
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            pq[i] = new PriorityQueue<Req>(m, Comparator.comparingInt(a -> a.vals[finalI]));
            pq[i].addAll(Arrays.asList(reqs));
        }

        int time = 0;
        int[] state = new int[3];
        while (time <= inc.length) {
            for (int i = 0; i < 3; i++) {
                while (!pq[i].isEmpty() && pq[i].peek().vals[i] <= state[i]) {
                    Req r = pq[i].remove();
                    r.used++;
                    if (r.used == 3) {
                        r.ans = time;
                    }
                }
            }

            if (time == inc.length) {
                break;
            }

            for (int i = 0; i < 3; i++) {
                state[i] += inc[time][i];
            }
            time++;
        }

        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = reqs[i].ans;
        }
        return ans;
    }

    // 类包装的方式还不熟悉
    class Req {
        int[] vals;
        int used;
        int ans = -1;
    }

    public static void main(String[] args) {
        int[][] increase = {{2, 8, 4}, {2, 5, 0}, {10, 9, 8}};
        int[][] requirements = {{2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}};
//        int[][] increase = {{1, 1, 1}};
//        int[][] requirements = {{0, 0, 0}};
        GetTriggerTime solution = new GetTriggerTime();
        int[] result = solution.getTriggerTime(increase, requirements);
        int[] result02 = solution.getTriggerTime02(increase, requirements);
        System.out.println(result);
    }
}


