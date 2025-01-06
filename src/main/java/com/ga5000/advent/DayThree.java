package com.ga5000.advent;

import java.io.File;
import java.util.List;

public class DayThree {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/ga5000/advent/inputs/dayThree");
        List<List<String>> inputPartOne = IOUtil.dayThreePartOne(file);
        List<List<String>> inputPartTwo = IOUtil.dayThreePartTwo(file);
        solution(inputPartTwo);
    }

    private static void solution(List<List<String>> data){
        int result = 0;
        for(List<String> d : data){
            result += mul(Integer.parseInt(d.get(0)), Integer.parseInt(d.get(1)));
        }
        System.out.println(result);
    }

    private static int mul(int x, int y){
        return x * y;
    }

}
