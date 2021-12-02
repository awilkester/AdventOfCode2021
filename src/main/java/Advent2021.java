package main.java;

import java.io.IOException;

public class Advent2021 {

    public static void main(String[] args) throws IOException {
        Day1 x = new Day1();
        //Day 1 Part 1 solution:
        System.out.println(x.numberOfIncreases((x.readLines("src/main/resources/input.txt"))));
        //Day 1 Part 2 solution
        System.out.println(x.numberOf3SumIncreases((x.readLines("src/main/resources/input.txt"))));

        Day2 day2 = new Day2();
        //Day 2 Part 2 solution
        day2.getCommands(day2.readLines("src/main/resources/input2.txt"));
        System.out.printf("The current depth is: %s, and the current horizontal position is: %s.%n", day2.getDepth(), day2.getHorizontal());
        System.out.println(day2.getDepth() * day2.getHorizontal());
        Day2 day2part2 = new Day2();
        day2part2.getCommandsWithAim(day2part2.readLines("src/main/resources/input2.txt"));
        System.out.printf("The current depth is: %s, and the current horizontal position is: %s.%n", day2part2.getDepth(), day2part2.getHorizontal());
        System.out.println(day2part2.getDepth() * day2part2.getHorizontal());

    }
}
