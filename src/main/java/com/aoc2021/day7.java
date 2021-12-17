package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day7 {

    public static void main(String[] args) {
        //int[] input = new int[]{16,1,2,0,4,2,7,1,2,14};
        int[] input = getInput();

        if(input != null) {
            System.out.println(calcMinDistance(input));
        }
    }

    private static int calcMinDistance(int[] input) {
        int result = Integer.MAX_VALUE;

        int maxPosition = Arrays.stream(input).max().getAsInt();

        for (int k = 0; k <= maxPosition; k++) {
            int distance = 0;
            for (int i : input) {
                int t = Math.abs(k - i);
                for (int l = 1; l <= t; l++) {
                    distance += l;
                }
            }

            if (distance < result) {
                result = distance;
            }
        }

        return result;
    }

    private static int[] getInput() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day7_input.txt"));

            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                lines.addAll(Arrays.asList(split));
            }

            return lines.stream().mapToInt(Integer::parseInt).toArray();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}



// hint 1: need to check all positions, even positions that are not used
