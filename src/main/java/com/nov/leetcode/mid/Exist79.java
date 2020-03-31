package com.nov.leetcode.mid;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/3/31 10:59
 * @description
 */
public class Exist79 {

    public boolean exist(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] chars = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0] && dfs(board, visited, chars, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    // TODO 使用偏移数量组将四种情况整合为一个循环
    private boolean dfs(char[][] board, boolean[][] visited, char[] chars, int row, int col, int index) {
        if (index == chars.length - 1) {
            return true;
        }
        visited[row][col] = true;
        boolean success = false;
        // upper search
        if (row > 0 && !visited[row - 1][col] && board[row - 1][col] == chars[index + 1]) {
            success = dfs(board, visited, chars, row - 1, col, index + 1);
        }
        if (success) {
            return true;
        }
        // right search
        if (col < board[row].length - 1 && !visited[row][col + 1] && board[row][col + 1] == chars[index + 1]) {
            success = dfs(board, visited, chars, row, col + 1, index + 1);
        }
        if (success) {
            return true;
        }
        // down search
        if (row < board.length - 1 && !visited[row + 1][col] && board[row + 1][col] == chars[index + 1]) {
            success = dfs(board, visited, chars, row + 1, col, index + 1);
        }
        if (success) {
            return true;
        }
        // left search
        if (col > 0 && !visited[row][col - 1] && board[row][col - 1] == chars[index + 1]) {
            success = dfs(board, visited, chars, row, col - 1, index + 1);
        }
        if (success) {
            return true;
        }
        visited[row][col] = false;
        return false;
    }

    // record path
    public boolean exist(char[][] board, String word, Stack<int[]> path) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] chars = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == chars[0] && dfs(board, visited, chars, i, j, 0, path)) {
                    System.out.println("----------------------");
                    for (int m = 0; m < visited.length; m++) {
                        for (int n = 0; n < visited[m].length; n++) {
                            if (visited[m][n]) {
                                System.out.print("* ");
                            } else {
                                System.out.print("- ");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println("----------------------");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, char[] chars, int row, int col, int index, Stack<int[]> path) {
        int[] tmp = new int[2];
        tmp[0] = row;
        tmp[1] = col;
        path.push(tmp);
        visited[row][col] = true;
        if (index == chars.length - 1) {
            return true;
        }
        boolean success = false;
        // upper search
        if (row > 0 && !visited[row - 1][col] && board[row - 1][col] == chars[index + 1]) {
            success = dfs(board, visited, chars, row - 1, col, index + 1, path);
        }
        if (success) {
            return true;
        }
        // right search
        if (col < board[row].length - 1 && !visited[row][col + 1] && board[row][col + 1] == chars[index + 1]) {
            success = dfs(board, visited, chars, row, col + 1, index + 1, path);
        }
        if (success) {
            return true;
        }
        // down search
        if (row < board.length - 1 && !visited[row + 1][col] && board[row + 1][col] == chars[index + 1]) {
            success = dfs(board, visited, chars, row + 1, col, index + 1, path);
        }
        if (success) {
            return true;
        }
        // left search
        if (col > 0 && !visited[row][col - 1] && board[row][col - 1] == chars[index + 1]) {
            success = dfs(board, visited, chars, row, col - 1, index + 1, path);
        }
        if (success) {
            return true;
        }
        visited[row][col] = false;
        path.pop();
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
//        String word = "ABC";
        Exist79 solution = new Exist79();
        Stack<int[]> path = new Stack<>();
        solution.exist(board, word, path);
        System.out.println(solution.exist(board, word));
        for (int i = 0; i < path.size(); i++) {
            int[] tmpPath = path.get(i);
            if (i < path.size() - 1) {
                System.out.print("[" + tmpPath[0] + "," + tmpPath[1] + "]" + "→");
            } else {
                System.out.print("[" + tmpPath[0] + "," + tmpPath[1] + "]");
            }
        }
        System.out.println();
    }
}
