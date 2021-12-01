package main.java;

import java.io.IOException;

public class Advent2021 {

    public static void main(String[] args) throws IOException {
        Day1 x = new Day1();
        System.out.println(x.numberOf3SumIncreases((x.readLines("src/main/resources/input.txt"))));
    }
}
