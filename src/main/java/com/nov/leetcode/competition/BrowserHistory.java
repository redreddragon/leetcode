package com.nov.leetcode.competition;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/6/7 10:54
 * @description
 */
public class BrowserHistory {
    Stack<String> backStack = new Stack<>();
    Stack<String> forwardStack = new Stack<>();
    String curUrl;

    public BrowserHistory(String homepage) {
        this.curUrl = homepage;
    }

    public void visit(String url) {
        if (!forwardStack.isEmpty()) {
            forwardStack.clear();
        }
        backStack.push(curUrl);
        curUrl = url;
    }

    public String back(int steps) {
        int min = Math.min(steps, backStack.size());
        while (min > 0) {
            forwardStack.push(curUrl);
            curUrl = backStack.pop();
            min--;
        }
        return curUrl;
    }

    public String forward(int steps) {
        int min = Math.min(steps, forwardStack.size());
        while (min > 0) {
            backStack.push(curUrl);
            curUrl = forwardStack.pop();
            min--;
        }
        return curUrl;
    }
}
