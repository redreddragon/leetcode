package com.nov.niuke.interview;

/**
 * @author longwenhe
 * @date 2020/7/19 22:44
 * @description
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int R = input.nextInt();
        int C = input.nextInt();
        int[][] value = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                value[i][j] = input.nextInt();
            }
        }
        Stack<Context> stack = new Stack<>();
        int r = 0;
        int c = 0;
        int ans = Math.min(value[0][0], value[R - 1][C - 1]);
        boolean[][] visited = new boolean[R][C];
        Context initContext = new Context(r, c, visited, ans);
        stack.push(initContext);
        Stack<Integer> result = new Stack<>();
        while (!stack.isEmpty()) {
            Context nextContext = stack.pop();
            r = nextContext.r;
            c = nextContext.c;
            ans = nextContext.ans;
            visited = nextContext.visited;
            if (r == R - 1 && c == C - 1) {
                result.push(ans);
            } else {
                chooseNext(value, visited, r, c, R, C, ans, stack);
            }
        }
        int answer = 0;
        for (Integer integer : result) {
            if (integer > answer) {
                answer = integer;
            }
        }
        System.out.println(answer);
    }

    public static void chooseNext(int[][] value, boolean[][] visited, int r, int c, int R, int C, int ans, Stack<Context> stack) {
        visited[r][c] = true;
        if ((r == R - 1 && c == C - 2) || (r == R - 2 && c == C - 1)) {
            Context tmp = new Context(R - 1, C - 1, visited, ans);
            stack.push(tmp);
            return;
        }
        // 0:left 1:right 2:up 3:down
        int[] record = new int[4];
        if (c > 0 && !visited[r][c - 1]) {
            record[0] = value[r][c - 1];
        }
        if (c < C - 1 && !visited[r][c + 1]) {
            record[1] = value[r][c + 1];
        }
        if (r > 0 && !visited[r - 1][c]) {
            record[2] = value[r - 1][c];
        }
        if (r < R - 1 && !visited[r + 1][c]) {
            record[3] = value[r + 1][c];
        }
        int tmpIndex = 0;
        boolean add = false;
        for (int i = 0; i < record.length; i++) {
            if (record[i] >= ans && record[i] != 0) {
                int index[] = getRes(i, r, c);
                ans = Math.min(value[index[0]][index[1]], ans);
                Context tmp = new Context(index[0], index[1], visited, ans);
                stack.push(tmp);
                add = true;
            }
            if (record[i] > record[tmpIndex]) {
                tmpIndex = i;
            }
        }
        if (!add && record[tmpIndex] != 0) {
            int index[] = getRes(tmpIndex, r, c);
            ans = Math.min(value[index[0]][index[1]], ans);
            Context tmp = new Context(index[0], index[1], visited, ans);
            stack.push(tmp);
        }
    }

    public static int[] getRes(int index, int r, int c) {
        if (index == 0) {
            c--;
        } else if (index == 1) {
            c++;
        } else if (index == 2) {
            r--;
        } else {
            r++;
        }
        int[] res = new int[2];
        res[0] = r;
        res[1] = c;
        return res;
    }

    static class Context {
        int r;
        int c;
        boolean[][] visited;
        int ans;

        public Context(int r, int c, boolean[][] visited, int ans) {
            int a = visited.length;
            int b = visited[0].length;
            this.r = r;
            this.c = c;
            this.visited = new boolean[a][b];
            this.ans = ans;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    this.visited[i][j] = visited[i][j];
                }
            }
        }
    }
}

