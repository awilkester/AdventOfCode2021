package main.java.Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {
        String filename = "src/main/resources/day4.txt";
        List<BingoBoard> boards = getBingoBoards(filename);
        runBingoXTimes(boards, bingoNumbers(filename), 84);
//        printBoard(winner);
//        System.out.println(winner.getTotal());

    }

    public static BingoBoard runBingoWithNumbers(List<BingoBoard> boards, List<Integer> numbers){
        BingoBoard result = new BingoBoard();
        int numOfCalls = 0;
        for(Integer i : numbers){
            printBoards(boards);
            for(BingoBoard b : boards){
                b.callNumber(i);
                if(b.isBingo()){
                    result = b;
                    result.setTotal(b.getUncalledTotal() * i);
//                    printBoards(boards);
                    System.out.println(numOfCalls);
                    break;
                }
            }
            numOfCalls++;
            if(result.isBingo()){
                break;
            }
        }
        return result;
    }

    public static void runBingoXTimes(List<BingoBoard> boards, List<Integer> numbers, int number){
        for(int i = 0; i < number; i++){
            System.out.println(numbers.get(i));
            for(BingoBoard b : boards){
                b.callNumber(numbers.get(i));
            }
        }
        printBoards(boards);
        System.out.println(numbers.get(number));
    }


    public static void printBoard(BingoBoard board) {
        for (BingoSquare[] b : board.getBoard()) {
            StringBuilder row = new StringBuilder();
            for (BingoSquare c : b) {
                if (c.isCalled()) {
                    if (c.getValue() < 10) {
                        row.append(ANSI_RED + "  " + c.getValue() + " " + ANSI_RESET);
                    } else {
                        row.append(ANSI_RED + " " + c.getValue() + " " + ANSI_RESET);
                    }
                } else {
                    if (c.getValue() < 10) {
                        row.append("  " + c.getValue() + " ");
                    } else {
                        row.append(" " + c.getValue() + " ");
                    }
                }
            }
            System.out.println(row);
        }
    }

    public static void printBoards(List<BingoBoard> boards){
        for(BingoBoard a : boards){
            printBoard(a);
            System.out.println();
        }
    }

    public static List<BingoBoard> getBingoBoards(String filename) throws IOException {
        List<BingoBoard> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int currentLine = 0;
        while((line = reader.readLine()) != null){
            if (currentLine++ < 2) {
                continue;
            }
            int linesToRead = 5;
            BingoSquare[][] bingo = new BingoSquare[5][5];
            for(int j = 0; j < linesToRead; j++) {
                BingoSquare[] x = new BingoSquare[5];
                Integer[] y = Arrays.stream(line.trim().replaceAll(" {2}", " ").split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
                for(int i = 0; i < y.length; i++){
                    x[i] = new BingoSquare(y[i]);
                }
                bingo[j] = x;
                line = reader.readLine();
            }
            result.add(new BingoBoard(bingo));
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
