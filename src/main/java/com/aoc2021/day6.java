package main.java.com.aoc2021;

import java.util.ArrayList;
import java.util.List;

public class day6 {

    public static void main(String[] args) {

        int[] input = new int[]{3,4,3,1,2};
//        int[] input = new int[]{2,1,1,1,1,1,1,5,1,1,1,1,5,1,1,3,5,1,1,3,1,1,3,1,4,4,4,5,1,1,1,3,1,3,1,1,2,
//                2,1,1,1,5,1,1,1,5,2,5,1,1,2,1,3,3,5,1,1,4,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,1,5,1,2,1,1,1,1,
//                5,1,1,1,1,1,5,1,1,1,4,5,1,1,3,4,1,1,1,3,5,1,1,1,2,1,1,4,1,4,1,2,1,1,2,1,5,1,1,1,5,1,2,2,1,
//                1,1,5,1,2,3,1,1,1,5,3,2,1,1,3,1,1,3,1,3,1,1,1,5,1,1,1,1,1,1,1,3,1,1,1,1,3,1,1,4,1,1,3,2,1,
//                2,1,1,2,2,1,2,1,1,1,4,1,2,4,1,1,4,4,1,1,1,1,1,4,1,1,1,2,1,1,2,1,5,1,1,1,1,1,5,1,3,1,1,2,3,
//                4,4,1,1,1,3,2,4,4,1,1,3,5,1,1,1,1,4,1,1,1,1,1,5,3,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
//                1,1,5,1,1,1,1,1,1,1,1,5,1,4,4,1,1,1,1,1,1,1,1,3,1,3,1,4,1,1,2,2,2,1,1,2,1,1};

        System.out.println(calcLanternFish(input, 256));

    }

    public static int calcLanternFish(int[] input, int days){
        List<Integer> list = new ArrayList<>();
        for (int k : input) {
            list.add(k);
        }

        for(int i = 0; i < days; i++){
            int zeroCount = 0;
            for(int j = 0; j < list.size(); j++){
                int t = list.get(j);

                if(t == 0){
                    zeroCount++;
                    list.set(j, 6);
                }
                else {
                    list.set(j, t - 1);
                }
            }

            for (int j = 0; j < zeroCount; j++) {
                list.add(8);
            }

            //System.out.println(list);
        }

        return list.size();
    }
}
