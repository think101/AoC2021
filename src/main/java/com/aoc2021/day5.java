package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day5 {

    public static void main(String[] args) {

    }

    private static int[][] getBoard(String fileName) {

        List<Line> lines = new ArrayList<>();

        // get max number from input file
        int max = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            List<int[]> board = new ArrayList<>();
            while((line = reader.readLine()) != null) {

                String[] points = line.trim().split("->");
                String[] start = points[0].trim().split(",");
                String[] end = points[1].trim().split(",");

                int startX = Integer.parseInt(start[0].trim());
                int startY = Integer.parseInt(start[1].trim());

                int endX = Integer.parseInt(end[0].trim());
                int endY = Integer.parseInt(end[1].trim());

                Integer[] nums = {startX, startY, endX, endY};
                int maxNum = Collections.max(Arrays.asList(nums));
                if(Collections.max(Arrays.asList(nums)) > max) {
                    max = Collections.max(Arrays.asList(nums));
                }

                Point startPoint = new Point(startX, startY);
                Point endPoint = new Point(endX, endY);


            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    static class Point {
        int x;
        int y;

        public Point(int startX, int startY) {
            this.x = startX;
            this.y = startY;
        }
    }

    static class Line {
        Point start;
        Point end;
    }
}
