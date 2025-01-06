package com.ga5000.advent;
import java.io.File;
import java.util.*;


public class DayOne {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/ga5000/advent/inputs/dayOne");
        List<String> input = IOUtil.readInputDaysOneAndTwo(file);

        partOne(input);
        partTwo(input);
    }

    private static List<List<Long>> parseData(List<String> data){
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        for(String d : data){
            if(!"".equals(d)){
                List<Long> p = Arrays.stream(d.trim().split("\\s+")).map(Long::parseLong).toList();
                left.add(p.get(0));
                right.add(p.get(1));
            }
        }
        Collections.sort(left);
        Collections.sort(right);

        return List.of(left, right);
    }

    private static void partOne(List<String> data){
        List<List<Long>> parsedData = parseData(data);

        long result = 0;
        for(int i = 0; i < parsedData.get(0).size(); i++){
            long left = parsedData.get(0).get(i);
            long right = parsedData.get(1).get(i);
            long diff = Math.abs(left - right);

            result += diff;
        }

        System.out.println(result);
    }

    private static void partTwo(List<String> data){
        List<List<Long>> parsedData = parseData(data);
        long result = 0;
        int k = 0;
        int j = 0;

        while(j < parsedData.get(0).size()){
            for (Long l : parsedData.get(1)) {
                if (Objects.equals(parsedData.get(0).get(j), l)) {
                    k++;
                }
            }
            result += parsedData.get(0).get(j) * k;
            k = 0;
            j++;
        }

        System.out.println(result);
    }
}