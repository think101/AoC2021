package main.java.com.aoc2021;

import java.util.Arrays;
import java.util.stream.LongStream;

public class day6 {

    public static void main(String[] args) {

        //int[] input = new int[]{3,4,3,1,2};
        int[] input = new int[]{2,1,1,1,1,1,1,5,1,1,1,1,5,1,1,3,5,1,1,3,1,1,3,1,4,4,4,5,1,1,1,3,1,3,1,1,2,
                2,1,1,1,5,1,1,1,5,2,5,1,1,2,1,3,3,5,1,1,4,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,1,5,1,2,1,1,1,1,
                5,1,1,1,1,1,5,1,1,1,4,5,1,1,3,4,1,1,1,3,5,1,1,1,2,1,1,4,1,4,1,2,1,1,2,1,5,1,1,1,5,1,2,2,1,
                1,1,5,1,2,3,1,1,1,5,3,2,1,1,3,1,1,3,1,3,1,1,1,5,1,1,1,1,1,1,1,3,1,1,1,1,3,1,1,4,1,1,3,2,1,
                2,1,1,2,2,1,2,1,1,1,4,1,2,4,1,1,4,4,1,1,1,1,1,4,1,1,1,2,1,1,2,1,5,1,1,1,1,1,5,1,3,1,1,2,3,
                4,4,1,1,1,3,2,4,4,1,1,3,5,1,1,1,1,4,1,1,1,1,1,5,3,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,5,1,1,1,1,1,1,1,1,5,1,4,4,1,1,1,1,1,1,1,1,3,1,3,1,4,1,1,2,2,2,1,1,2,1,1};

        System.out.println(calcLanternFish(input, 256));

    }

    public static long calcLanternFish(int[] input, int days){
        long[] stats = new long[9];

        for (int k : input) {
            stats[k]++;
        }

        for(int i = 0; i < days; i++){
            long zeroCount = stats[0];

            for(int j = 1; j < stats.length; j++){
                stats[j-1] = stats[j];
            }

            stats[stats.length-1] = zeroCount;
            stats[6] = stats[6] + zeroCount;

            //System.out.println(Arrays.toString(stats));
        }

        return LongStream.of(stats).sum();
    }
}



// hint 1: To solve this puzzle, you only need a 9-element array.
// hint 2: use long instead of int for the array elements, otherwise int overflows cause wrong result.
// hint 3: use int list and model each lantern fish as an int, the example input 256 days result 26984457539 takes ~27*4 GB of memory.