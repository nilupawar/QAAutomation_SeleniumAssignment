package com.assignment.lib.util;

import com.assignment.lib.config.TestConfig;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public static String getScreenshotFileName() {
        return "Screenshot_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".PNG";
    }

    public static int parseInt(String intString) {
        return Integer.parseInt(intString);
    }

    public static int parseIntTestConfig(String intString) {
        return parseInt(TestConfig.getConfig(intString));
    }

    public static void main(String[] args) throws ParseException {
        String string = "2021-07-28 19:46";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date date = format.parse(string);
        System.out.println(date);
    }
}
