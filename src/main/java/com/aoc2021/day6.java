package main.java.com.aoc2021;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println(calcLanternFish(input, 80));

    }

    public static int calcLanternFish(int[] input, int days){
        if(days == 0){
            return input.length;
        }

        int zeroCount = 0;
        List<Integer> result = new ArrayList<>();

        for (int t : input) {
            if (t == 0) {
                zeroCount++;
                result.add(6);
            } else {
                result.add(t - 1);
            }
        }

        for(int i=0; i<zeroCount; i++){
            result.add(8);
        }

        //System.out.println(result);

        return calcLanternFish(result.stream().mapToInt(i->i).toArray(), days-1);
    }
}
