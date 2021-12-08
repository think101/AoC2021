package main.java.com.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day4 {
    public static void main(String[] args) {
        int[] draws = {59,91,13,82,8,32,74,96,55,51,19,47,46,44,5,21,95,71,48,60,68,81,80,14,
                23,28,26,78,12,22,49,1,83,88,39,53,84,37,93,24,42,7,56,20,92,90,25,36,34,52,
                27,50,85,75,89,63,33,4,66,17,98,57,3,9,54,0,94,29,79,61,45,86,16,30,77,76,6,38,
                70,62,72,43,69,35,18,97,73,41,40,64,67,31,58,11,15,87,65,2,10,99};

        //int[] draws = {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1};

        List<int[][]> boards = getBoards( "src/main/resources/day4_input.txt");
        //List<int[][]> boards = getBoards( "src/main/resources/day4_example.txt");
        List<int[][]> boardsStats = initMarkStatus(boards);
        boolean[] winBoards = new boolean[boards.size()];

        for(int draw : draws) {
            Integer score = markBoards(boards, boardsStats, draw, winBoards);

            if(score != null) {
                System.out.println(score);
                //break;
            }
        }
    }

    private static List<int[][]> initMarkStatus(List<int[][]> boards) {
        List<int[][]> result = new ArrayList<>();

        for (int[][] board : boards) {
            int[][] boardStats = new int[board.length][board[0].length];
            result.add(boardStats);

            for (int[] boardStat : boardStats) {
                Arrays.fill(boardStat, 0);
            }
        }

        return result;
    }

    private static List<int[][]> getBoards(String fileName) {
        List<int[][]> boards = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            List<int[]> board = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                if(line.isEmpty()) {
                    if(board.size() > 0) {
                        boards.add(board.toArray(new int[0][]));
                        board = new ArrayList<>();
                    }

                    continue;
                }

                String[] row = line.trim().split("\\s+");

                int[] rowInt = new int[row.length];
                for(int i = 0; i < row.length; i++) {
                    rowInt[i] = Integer.parseInt(row[i]);
                }

                board.add(rowInt);
            }

            // Add last board
            if(board.size() > 0) {
                boards.add(board.toArray(new int[0][]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return boards;
    }

    private static Integer markBoards(List<int[][]> boards, List<int[][]> boardsStats, int draw, boolean[] winBoards) {
        Integer score = null;

        for(int i = 0; i < boards.size(); i++) {
            if(winBoards[i]) {
                continue;
            }

            score = markBoard(boards.get(i), boardsStats.get(i), draw);

            if(score != null) {
                winBoards[i] = true;
            }
        }

        return score;
    }

    private static Integer markBoard(int[][] board, int[][] boardStat, int draw) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == draw) {
                    boardStat[i][j] = 1;
                }
            }
        }

        // check whether bingo at each row
        for (int[] ints : boardStat) {
            boolean bingo = true;
            for (int anInt : ints) {
                if (anInt == 0) {
                    bingo = false;
                    break;
                }
            }

            if (bingo) {
                return calculateScore(board, boardStat, draw);
            }
        }

        // check whether bingo at each column
        for(int i = 0; i < boardStat[0].length; i++) {
            boolean bingo = true;
            for (int[] ints : boardStat) {
                if (ints[i] == 0) {
                    bingo = false;
                    break;
                }
            }

            if(bingo) {
                return calculateScore(board, boardStat, draw);
            }
        }

        return null;
    }

    private static Integer calculateScore(int[][] board, int[][] boardStat, int draw) {
        System.out.println("bingo at " + draw);
        int sum = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(boardStat[i][j] == 0) {
                    sum += board[i][j];
                }
            }
        }

        return sum * draw;
    }
}