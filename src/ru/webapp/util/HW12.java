package ru.webapp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HW12 {
    public static void main(String[] args) {
        int[] values = {9, 9, 8, 7, 1, 1, 1, 2, 4, 6};
        System.out.println(minValue(values));

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(2);
        integers.add(2);
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {

        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> a * 10 + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(i -> i).sum();
        System.out.println("Sum = " + sum);
        integers.removeIf(i -> (sum % 2 == i % 2));
        return integers;
    }
}

