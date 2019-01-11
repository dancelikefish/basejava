package ru.webapp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HW12 {
    public static void main(String[] args) {
        int[] values = {9, 8, 7, 1, 1, 1, 2};
        System.out.println(minValue(values));

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(2);
        integers.add(1);
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        List<Integer> uniqueValues = new ArrayList<>();
        Arrays.stream(values).distinct().sorted().forEach(uniqueValues::add);
        final int[] start = {uniqueValues.get(0)};
        IntStream.range(1, uniqueValues.size()).forEach(i -> start[0] = start[0] * 10 + uniqueValues.get(i));
        return start[0];
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum;
        sum = integers.stream().mapToInt(i -> i).sum();
        System.out.println("Sum = " + sum);
        if (sum % 2 != 0) {
            integers.removeIf(i -> i % 2 != 0);
        } else
            integers.removeIf(i -> i % 2 == 0);
        return integers;
    }
}

