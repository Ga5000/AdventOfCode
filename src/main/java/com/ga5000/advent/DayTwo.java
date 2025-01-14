package com.ga5000.advent;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DayTwo {

    public static void main(String[] args) {
        File file = new File("src/main/java/com/ga5000/advent/inputs/dayTwo");
        List<String> input = IOUtil.readInputDaysOneAndTwo(file);
        solution(input);
    }

    private static List<LinkedList<Integer>> parseData(List<String> data) {
        List<LinkedList<Integer>> result = new LinkedList<>();

        for (String d : data) {
            if (!d.isEmpty()) {
                LinkedList<Integer> p = new LinkedList<>();
                for (String s : d.split("\\s+")) {
                    p.add(Integer.parseInt(s.trim()));
                }
                result.add(p);
            }
        }
        return result;
    }

    private static void solution(List<String> data) {
        int safeCount = 0;
        List<LinkedList<Integer>> parsedData = parseData(data);

        for (LinkedList<Integer> row : parsedData) {
            if (isSafe(row) || canBeMadeSafe(row)) {
                safeCount++;
            }
        }

        System.out.println("Safe count: " + safeCount);
    }

    private static boolean isSafe(LinkedList<Integer> row) {
        boolean isIncreasing = false;
        boolean isDecreasing = false;

        for (int i = 0; i < row.size() - 1; i++) {
            Integer current = row.get(i);
            Integer next = row.get(i + 1);
            int diff = Math.abs(current - next);

            if (diff < 1 || diff > 3) {
                return false;
            }

            if (next > current) {
                if (isDecreasing) return false;
                isIncreasing = true;
            } else if (next < current) {
                if (isIncreasing) return false;
                isDecreasing = true;
            }
        }
        return true;
    }

    private static boolean canBeMadeSafe(LinkedList<Integer> row) {
        for (int i = 0; i < row.size(); i++) {
            LinkedList<Integer> modifiedRow = new LinkedList<>(row);
            modifiedRow.remove(i);
            if (isSafe(modifiedRow)) {
                return true;
            }
        }
        return false;
    }
}
