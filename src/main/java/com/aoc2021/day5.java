package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day5 {

    // note for part 2 the example diagram and answer seems wrong

    public static void main(String[] args) {

        //int[][] board = getBoard( "src/main/resources/day5_example.txt");
        int[][] board = getBoard( "src/main/resources/day5_input.txt");

        int count = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt > 1) {
                    count++;
                }
            }
        }

        System.out.println("Part 2: " + count);

    }

    private static int[][] getBoard(String fileName) {

        List<Line> lines = new ArrayList<>();
        int[][] result;

        // get max number from input file
        int max = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] points = line.trim().split("->");
                String[] start = points[0].trim().split(",");
                String[] end = points[1].trim().split(",");

                int startX = Integer.parseInt(start[0].trim());
                int startY = Integer.parseInt(start[1].trim());

                int endX = Integer.parseInt(end[0].trim());
                int endY = Integer.parseInt(end[1].trim());

                Integer[] nums = {startX, startY, endX, endY};
                int maxNum = Collections.max(Arrays.asList(nums));
                if (maxNum > max) {
                    max = maxNum;
                }

                Point startPoint = new Point(startX, startY);
                Point endPoint = new Point(endX, endY);

                if (startPoint.x == endPoint.x || startPoint.y == endPoint.y
                        || Math.abs(startPoint.x - endPoint.x) == Math.abs(startPoint.y - endPoint.y)) {
                    lines.add(new Line(startPoint, endPoint));
                }
            }

            // construct board
            result = new int[max + 1][max + 1];

            for (Line l : lines) {
                if(l.start.x == l.end.x) {
                    int tMin = Math.min(l.start.y, l.end.y);
                    int tMax = Math.max(l.start.y, l.end.y);

                    for (int i = tMin; i <= tMax; i++) {
                        result[l.start.x][i] += 1;
                    }
                }
                else if (l.start.y == l.end.y) {
                    int tMin = Math.min(l.start.x, l.end.x);
                    int tMax = Math.max(l.start.x, l.end.x);

                    for (int i = tMin; i <= tMax; i++) {
                        result[i][l.start.y] += 1;
                    }
                }
                else {
                    if(l.start.x > l.end.x) {
                        if(l.start.y > l.end.y) {
                            for(int i = l.start.x, j = l.start.y; i >= l.end.x; i--, j--) {
                                result[i][j] += 1;
                            }
                        }
                        else {
                            for(int i = l.start.x, j = l.start.y; i >= l.end.x; i--, j++) {
                                result[i][j] += 1;
                            }
                        }
                    }
                    else {
                        if(l.start.y > l.end.y) {
                            for(int i = l.start.x, j = l.start.y; i <= l.end.x; i++, j--) {
                                result[i][j] += 1;
                            }
                        }
                        else {
                            for(int i = l.start.x, j = l.start.y; i <= l.end.x; i++, j++) {
                                result[i][j] += 1;
                            }
                        }
                    }
                }

                //System.out.println(Arrays.deepToString(result));
            }

        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error reading file");
        }

        return result;
    }

    private static class Point {
        int x;
        int y;

        public Point(int startX, int startY) {
            this.x = startX;
            this.y = startY;
        }
    }

    private static class Line {
        Point start;
        Point end;

        public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }
}


