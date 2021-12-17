package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day9 {

    public static void main(String[] args) {
        int[][] input = getHeightMap();

        //System.out.println(Arrays.deepToString(input));

        System.out.println(calcRisk(input));
    }

    private static int calcRisk(int[][] input) {
        int risk = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if((i-1 >=0 && input[i-1][j] <= input[i][j])
                        || (i+1 < input.length && input[i+1][j] <= input[i][j])
                        || (j-1 >=0 && input[i][j-1] <= input[i][j])
                        || (j+1 < input[i].length && input[i][j+1] <= input[i][j])) {
                    continue;
                }

                risk += input[i][j]+1;
            }
        }
        return risk;
    }

    private static int[][] getHeightMap() {

        try {
            //BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day9_example.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day9_input.txt"));

            String line;
            List<int[]> allLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                int[] intLine = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    intLine[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
                }
                allLines.add(intLine);
            }

            return allLines.toArray(new int[0][0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new int[0][0];
    }
}
