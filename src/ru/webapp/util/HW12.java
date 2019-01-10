package ru.webapp.util;

import java.util.*;
import java.util.function.Consumer;

public class HW12 {
    public static void main(String[] args) {
        int[] values = {1,4,6,8,9,3,5,6,8,9,1,2,3};
        minValue(values);
    }

    private static int minValue(int[] values) {
        List<Integer> uniqueValues = new ArrayList<>();
        Arrays.stream(values).distinct().forEach(uniqueValues::add);
        Collections.sort(uniqueValues);
        int start = uniqueValues.get(0);
        uniqueValues.stream().map(value -> value)

        for (Integer integer : uniqueValues) {
            System.out.println(integer);
        }
        return 4;
    }
}
}
