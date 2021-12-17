package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day9 {

    public static void main(String[] args) {
        int[][] input = getHeightMap();

        //System.out.println(Arrays.deepToString(input));
        List<Integer> basins = calcBasins(input);

        basins.sort(Collections.reverseOrder());
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2));
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


    // return all basin sizes
    private static List<Integer> calcBasins(int[][] input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if((i-1 >=0 && input[i-1][j] <= input[i][j])
                        || (i+1 < input.length && input[i+1][j] <= input[i][j])
                        || (j-1 >=0 && input[i][j-1] <= input[i][j])
                        || (j+1 < input[i].length && input[i][j+1] <= input[i][j])) {
                    continue;
                }

                result.add(calcBasinSize(input, i, j));
            }
        }
        return result;
    }

    private static int calcBasinSize(int[][] input, int i, int j) {
        int size = 0;
        int[][] visited = new int[input.length][input[0].length];
        return calcBasinSize(input, i, j, visited);
    }

    private static int calcBasinSize(int[][] input, int i, int j, int[][] visited) {
        if(i < 0 || i >= input.length || j < 0 || j >= input[i].length || visited[i][j] == 1 || input[i][j] == 9) {
            return 0;
        }

        int size = 0;

        visited[i][j] = 1;
        size++;
        size += calcBasinSize(input, i-1, j, visited);
        size += calcBasinSize(input, i+1, j, visited);
        size += calcBasinSize(input, i, j-1, visited);
        size += calcBasinSize(input, i, j+1, visited);

        return size;
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
