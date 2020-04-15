package com.nov.leetcode.easy;

import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/16 0:56
 * @description
 */
/*
使用栈实现队列的下列操作：

        push(x) -- 将一个元素放入队列的尾部。
        pop() -- 从队列首部移除元素。
        peek() -- 返回队列首部的元素。
        empty() -- 返回队列是否为空。

        说明:

        你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
        你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
        假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
*/
public class MyQueue {
    Stack<Integer> inStack;
    Stack<Integer> outStack;
    int front; // 用于记录inStack可能的队首元素

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (inStack.isEmpty()) {
            front = x;
        }
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        // 每n次入队，才会有一次出队，因此摊还分析复杂度为O(1)
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // 出栈有元素，则队首必在出栈，否则在入栈的栈底，已用front记录
        if (!outStack.isEmpty()) {
            return outStack.peek();
        }
        return front;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }
}
