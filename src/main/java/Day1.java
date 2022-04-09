package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

  public static void main(String[] args) throws IOException {
    Day1 x = new Day1();
    // Day 1 Part 1 solution:
    System.out.println(x.numberOfIncreases((x.readLines("src/main/resources/input.txt"))));
    // Day 1 Part 2 solution
    System.out.println(x.numberOf3SumIncreases((x.readLines("src/main/resources/input.txt"))));
  }

  public List<Integer> readLines(String filename) throws IOException {
    List<Integer> result = new ArrayList<>(2000);
    FileReader x = new FileReader(filename);
    BufferedReader reader = new BufferedReader(x);
    String line = null;
    while ((line = reader.readLine()) != null) {
      result.add(Integer.parseInt(line));
    }
    reader.close();
    return result;
  }

  public int numberOfIncreases(List<Integer> input) {
    int result = 0;
    for (int i = 1; i < input.size(); i++) {
      if (input.get(i) > input.get(i - 1)) {
        result++;
      }
    }
    return result;
  }

  public int numberOf3SumIncreases(List<Integer> input) {
    int result = 0;
    for (int i = 0; i < (input.size() - 3); i++)
      if (input.get(i) + input.get(i + 1) + input.get(i + 2)
          < input.get(i + 1) + input.get(i + 2) + input.get(i + 3)) {
        result++;
      }
    return result;
  }
}
