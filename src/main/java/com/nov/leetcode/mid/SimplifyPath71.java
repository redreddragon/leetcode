package com.nov.leetcode.mid;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/3/29 21:05
 * @description
 */
public class SimplifyPath71 {
    // 存在"/.../a"这样的脏测试用例无法通过
    public String simplifyPath01(String path) {
        char[] pathChar = path.toCharArray();
        Stack<Character> result = new Stack<>();
        for (int i = 0; i < pathChar.length; i++) {
            if (pathChar[i] == '/') {
                if (result.isEmpty() || (result.peek() != '/' && result.peek() != '.')) {
                    result.push(pathChar[i]);
                } else if (result.peek() == '.') {
                    result.pop();
                }
            } else if (pathChar[i] == '.') {
                if (result.peek() == '/') {
                    result.push(pathChar[i]);
                } else if (result.peek() == '.') {
                    result.pop();
                    if (result.size() != 1) {
                        result.pop();
                        while (result.peek() != '/') {
                            result.pop();
                        }
                        if (result.size() != 1) {
                            result.pop();
                        }
                    }
                }
            } else {
                result.push(pathChar[i]);
            }
        }
        if (result.peek() == '.' && result.size() != 1) {
            result.pop();
        }
        if (result.peek() == '/' && result.size() != 1) {
            result.pop();
        }
        char[] resultChar = new char[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultChar[i] = result.get(i);
        }
        return new String(resultChar);
    }

    public String simplifyPath(String path) {
        char[] chars = path.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') {
                count++;
            }
        }
        String[] pathStr = new String[count + 1];
        int index = 0;
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') {
                pathStr[size] = new String(chars, index, i - index);
                if (!pathStr[size].isEmpty()) {
                    size++;
                }
                index = i + 1;
            }
        }
        if (chars[chars.length - 1] != '/') {
            pathStr[size] = new String(chars, index, chars.length - index);
            size++;
        }

//        String[] pathStr = path.split("/");
        Stack<String> stackStr = new Stack<>();
        for (int i = 0; i < size; i++) {
            if (!pathStr[i].isEmpty() && !pathStr[i].equals("..") && !pathStr[i].equals(".")) {
                stackStr.push(pathStr[i]);
            } else if (!stackStr.isEmpty() && pathStr[i].equals("..")) {
                stackStr.pop();
            }
        }
        if (stackStr.isEmpty()) {
            return "/";
        }
        StringBuffer result = new StringBuffer();
        for (String str : stackStr) {
            result.append("/");
            result.append(str);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String path = "/a//b////c/d//././/..";
        SimplifyPath71 solution = new SimplifyPath71();
        System.out.println(path);
        System.out.println(solution.simplifyPath(path));
    }
}
