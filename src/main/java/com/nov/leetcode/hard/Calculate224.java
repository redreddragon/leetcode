package com.nov.leetcode.hard;

import com.nov.leetcode.common.OperatorPriority;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author longwenhe
 * @date 2020/4/15 21:13
 * @description
 */
/*

实现一个基本的计算器来计算一个简单的字符串表达式的值。

        字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
        说明：

        你可以假设所给定的表达式都是有效的。
        请不要使用内置的库函数 eval。
*/
public class Calculate224 {
    // TODO 未考虑有负数、浮点数的情况，同样运算符目前只有 + - 两种
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        StringBuffer RPN = new StringBuffer();
        OperatorPriority operatorPriority = new OperatorPriority();
        Stack<Character> charStack = new Stack<>();
        // 哨兵入栈
        charStack.push('\0');
        Stack<Integer> numStack = new Stack<>();
        // 追加尾哨兵
        s = s + "\0";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            char c = chars[i];
            if (c == ' ') {
                i++;
                continue;
            } else if (operatorPriority.isDigit(c)) {
                int num = 0;
                int j = i;
                for (; j < chars.length; j++) {
                    if (operatorPriority.isDigit(chars[j])) {
                        num = num * 10 + (chars[j] - '0');
                        RPN.append(chars[j]);
                    } else {
                        break;
                    }
                }
                RPN.append(' ');
                i = j;
                numStack.push(num);
            } else {
                char priority = operatorPriority.getPriority(c, charStack.peek());
                switch (priority) {
                    case '=':
                        // 去掉双括号或哨兵
                        charStack.pop();
                        i++;
                        break;
                    case '>':
                        // 当前运算符优先级更高，延迟计算
                        charStack.push(c);
                        i++;
                        break;
                    case '<':
                        // 栈顶运算符优先级更高，可以弹出运算
                        char operator = charStack.pop();
                        RPN.append(operator);
                        RPN.append(' ');
                        int operand2 = numStack.pop();
                        int operand1 = numStack.pop();
                        int result = operator == '+' ? operand1 + operand2 : operand1 - operand2;
                        numStack.push(result);
                        break;
                    default:
                        // 异常情况返回-1
                        return -1;
                }
            }
        }
        System.out.println(RPN);
        // 表达式正常的情况，最终的栈只有一个元素，即为结果
        return numStack.pop();
    }

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        String s1 = "1 + 2";
        Calculate224 solution = new Calculate224();
        System.out.println(solution.calculate(s));
    }

    class MyQueue {
        Stack<Integer> inStack;
        Stack<Integer> outStack;
        int front;
        /** Initialize your data structure here. */
        public MyQueue() {
            this.inStack = new Stack<>();
            this.outStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if(inStack.isEmpty()){
                front = x;
            }
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(outStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(!outStack.isEmpty()){
                return outStack.peek();
            }
            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return outStack.isEmpty() && inStack.isEmpty();
        }
    }


}
