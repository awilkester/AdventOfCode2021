package main.java.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException {
        List<Integer[][]> result = getBingoBoards("src/main/resources/day4.txt");
        System.out.println(result.size());
    }

    public static List<Integer[][]> getBingoBoards(String filename) throws IOException {
        List<Integer[][]> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int currentLine = 0;
        while((line = reader.readLine()) != null){
            if (currentLine++ < 2) {
                continue;
            }
            if(line != ""){
                int linesToRead = 5;
                Integer[][] bingo = new Integer[5][5];
                for(int j = 0; j < linesToRead; j++) {
                    bingo[j] = Arrays.stream(line.trim().replaceAll("  ", " ").split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
                    line = reader.readLine();
                }
                result.add(bingo);
            }
        }
        reader.close();
        return result;
    }

    public static List<Integer> bingoNumbers(String filename) throws IOException {
        List<Integer> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        for(String x : reader.readLine().split(",")){
            result.add(Integer.parseInt(x));
        }
        reader.close();
        return result;
    }
}
