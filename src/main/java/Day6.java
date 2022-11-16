package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {
  public static void main(String[] args) throws IOException {
    // this array is a representation of the numbers of fish at each stage of reproduction for any
    // particular day
    // e.g. fishes[0] will be the number of total fishes that are day 0 of the reproduction process
    double[] fishes = new double[9];
    // int days = 80;
    int days = 256;
    //        BufferedReader reader = new BufferedReader(new
    // FileReader("src/main/resources/day6example.txt"));
    BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day6.txt"));
    String line = reader.readLine();
    double[] x = (Arrays.stream(line.split(",")).mapToDouble(Integer::parseInt).toArray());
    for (int i = 0; i < x.length; i++) fishes[(int) x[i]]++;
    reader.close();
    // I want to iterate over the number of days here, and each day simulate a reproduction cycle
    // by moving each number to the next cycle, and duplicating day0 fish to day 8 and adding the
    // dupe to day6 with day7 fish
    for (int i = 0; i < days; i++) {
      double[] temp = new double[9];
      temp[8] = fishes[0];
      temp[7] = fishes[8];
      temp[6] = fishes[7] + fishes[0];
      temp[5] = fishes[6];
      temp[4] = fishes[5];
      temp[3] = fishes[4];
      temp[2] = fishes[3];
      temp[1] = fishes[2];
      temp[0] = fishes[1];
      fishes = temp;
    }
    double countOfFishes = 0;
    for (double y : fishes) {
      countOfFishes += y;
    }
    System.out.println(new BigDecimal(countOfFishes).toPlainString());
  }
}
