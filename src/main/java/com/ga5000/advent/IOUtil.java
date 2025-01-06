package com.ga5000.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtil {
    public static List<String> readInputDaysOneAndTwo(File file) {
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

    public static List<List<String>> dayThreePartOne(File file){
        List<List<String>> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    lines.add(List.of(matcher.group(1), matcher.group(2)));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lines;
    }

    public static List<List<String>> dayThreePartTwo(File file) {
        List<List<String>> lines = new ArrayList<>();
        Pattern regex = Pattern.compile("(?<multiply>mul\\((\\d+),(\\d+)\\))|(?<doIt>do\\(\\))|(?<dont>don't\\(\\))");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean enabled = true;

            while ((line = br.readLine()) != null) {
                Matcher matcher = regex.matcher(line);

                while (matcher.find()) {
                    if (matcher.group("doIt") != null) {
                        enabled = true;
                    } else if (matcher.group("dont") != null) {
                        enabled = false;
                    } else if (matcher.group("multiply") != null && enabled) {
                        String firstNumber = matcher.group(2);
                        String secondNumber = matcher.group(3);
                        lines.add(List.of(firstNumber, secondNumber));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return lines;
    }

}
