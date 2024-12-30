package com.ga5000.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DayTwo {

    public static void main(String[] args) {
        File file = new File("src/main/java/com/ga5000/advent/inputs/dayTwo");
        List<String> input = readInput(file);
        partOne(input);
    }

    private static List<String> readInput(File file) {
        return getStrings(file);
    }

    static List<String> getStrings(File file) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return lines;
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

    private static void partOne(List<String> data) {
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
