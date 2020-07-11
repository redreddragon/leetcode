package com.nov.leetcode.competition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author longwenhe
 * @date 2020/5/17 11:03
 * @description
 */
public class PeopleIndexes5414 {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        boolean[] visited = new boolean[favoriteCompanies.size()];
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            if (visited[i]) {
                continue;
            }
            for (int j = i + 1; j < favoriteCompanies.size(); j++) {
                if (visited[j]) {
                    continue;
                }
                int index = compareList(favoriteCompanies.get(i), favoriteCompanies.get(j));
                if (index == 0) {
                    visited[i] = true;
                } else if (index == 1) {
                    visited[j] = true;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private int compareList(List<String> list1, List<String> list2) {
        Set<String> stringSet = new HashSet<>();
        if (list1.size() == list2.size()) {
            return -1;
        } else if (list1.size() > list2.size()) {
            stringSet.addAll(list1);
            for (String str : list2) {
                if (!stringSet.contains(str)) {
                    return -1;
                }
            }
            return 1;
        } else {
            stringSet.addAll(list2);
            for (String str : list1) {
                if (!stringSet.contains(str)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
