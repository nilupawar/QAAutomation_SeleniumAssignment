package com.assignment.lib.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {
    public static <T> boolean isSorted(List<T> items, SortType sortType) {
        List<T> sorted = new ArrayList<T>(items);
        if (sortType == SortType.DESC) {
            sorted.sort(Collections.reverseOrder());
        }
        if (sortType == SortType.ASC) {
            sorted = sorted.stream().sorted().collect(Collectors.toList());
        }
        return sorted.equals(items);
    }
}
