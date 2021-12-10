package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day7example.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day7.txt"));
        String line = reader.readLine();
        int[] x = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(x);
        int mean, sum = 0, total = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        mean = (sum / x.length);
        int[] fuelEstimates = new int[100];
        int[] fuelTotalsPerEstimates = new int[100];
        System.out.println(mean);

        for(int z = 0; z < 100; z++) {
            fuelEstimates[z] = (mean + z - 50);
        }
        for(int i = 0; i < 100; i++){
            total = 0;
            for(int j: x){
                total += getSumOfRange(fuelEstimates[i], j);
            }
            fuelTotalsPerEstimates[i] = total;
        }
        for (int c : fuelEstimates){
            System.out.println(c);
        }
        Arrays.sort(fuelTotalsPerEstimates);
        System.out.println(fuelTotalsPerEstimates[0]);
    }
        //min, max
        //5, 16
        //for(int i = 1; i < 11; i++){
        //(6, 1, 1) min inc, range inc, sumtotal
        //(7, 2, 3)
        //(8, 3, 6)
        //(9, 4, 10)
        //(10, 5, 15)
        //(11, 6, 21)
        //(12, 7, 28)
        //(13, 8, 36)
        //(14, 9, 45)
        //(15, 10, 55)
        //(16, 11, 66)
        //this means I have to include the final value
    public static int getSumOfRange(int min, int max){
        int result = 0;
        for(int i = 1; i <= Math.abs(min - max); i++){
            result += i;
        }
        return result;
    }
}
