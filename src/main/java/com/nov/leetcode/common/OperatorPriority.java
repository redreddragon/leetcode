package com.nov.leetcode.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longwenhe
 * @date 2020/4/15 22:42
 * @description
 */
public class OperatorPriority {
    private Map<Character, Map<Character, Character>> priorityMap;

    /*
        运算符优先级比较 '('     ')'     '+'     '-'    '\0'  当前运算符
            栈     '('    '>'     '='    '>'      '>'    ' '
            顶     ')'    ' '     ' '    ' '     ' '     ' '
            运     '+'    '>'     '<'     '<'     '<'    '<'
            算     '-'    '>'     '<'     '<'     '<'    '<'
            符     '\0'   '>'     ' '     '>'     '>'    '='
        */
    public OperatorPriority() {
        this.priorityMap = new HashMap<>();
        Map<Character, Character> leftBracket = new HashMap<>();
        leftBracket.put('(', '>');
        leftBracket.put(')', ' ');
        leftBracket.put('+', '>');
        leftBracket.put('-', '>');
        leftBracket.put('\0', '>');
        priorityMap.put('(', leftBracket);
        Map<Character, Character> rightBracket = new HashMap<>();
        rightBracket.put('(', '=');
        rightBracket.put(')', ' ');
        rightBracket.put('+', '<');
        rightBracket.put('-', '<');
        rightBracket.put('\0', ' ');
        priorityMap.put(')', rightBracket);
        Map<Character, Character> plusMap = new HashMap<>();
        plusMap.put('(', '>');
        plusMap.put(')', ' ');
        plusMap.put('+', '<');
        plusMap.put('-', '<');
        plusMap.put('\0', '>');
        priorityMap.put('+', plusMap);
        Map<Character, Character> minusMap = new HashMap<>();
        minusMap.put('(', '>');
        minusMap.put(')', ' ');
        minusMap.put('+', '<');
        minusMap.put('-', '<');
        minusMap.put('\0', '>');
        priorityMap.put('-', minusMap);
        Map<Character, Character> guardMap = new HashMap<>();
        guardMap.put('(', ' ');
        guardMap.put(')', ' ');
        guardMap.put('+', '<');
        guardMap.put('-', '<');
        guardMap.put('\0', '=');
        priorityMap.put('\0', minusMap);
    }

    public Character getPriority(Character cur, Character stackPeek) {
        return this.priorityMap.get(cur).get(stackPeek);
    }

    public boolean isDigit(Character c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }
}
