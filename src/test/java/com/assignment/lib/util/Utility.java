package com.assignment.lib.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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

    public static void main(String[] args) throws ParseException {
        String cur = "[£$€]";
        String cur1 = "$10000";

        System.out.println(cur1.replaceAll(cur,""));
//        System.out.println(NumberFormat.getNumberInstance(Locale.ENGLISH).parse("10.000").doubleValue());
    }
}
