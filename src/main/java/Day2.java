package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Day2 {

  // It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:
  //
  // forward X increases the horizontal position by X units.
  // down X increases the depth by X units.
  // up X decreases the depth by X units.
  // Note that since you're on a submarine, down and up affect your depth,
  // and so they have the opposite result of what you might expect.

  // horizontal position and depth both start at 0

  private int horizontal = 0;
  private int depth = 0;
  private int aim = 0;

  public static void main(String[] args) throws IOException {
    // Day 2 Part 1 solution
    Day2 day2 = new Day2();
    day2.getCommands(day2.readLines("src/main/resources/input2.txt"));
    System.out.printf(
        "The current depth is: %s, and the current horizontal position is: %s.%n",
        day2.getDepth(), day2.getHorizontal());
    System.out.println(day2.getDepth() * day2.getHorizontal());
    // Day 2 Part 2 solution
    Day2 day2part2 = new Day2();
    day2part2.getCommandsWithAim(day2part2.readLines("src/main/resources/input2.txt"));
    System.out.printf(
        "The current depth is: %s, and the current horizontal position is: %s.%n",
        day2part2.getDepth(), day2part2.getHorizontal());
    System.out.println(day2part2.getDepth() * day2part2.getHorizontal());
  }

  public List<String> readLines(String filename) throws IOException {
    List<String> result = new LinkedList<>();
    FileReader x = new FileReader(filename);
    BufferedReader reader = new BufferedReader(x);
    String line = null;
    while ((line = reader.readLine()) != null) {
      result.add(line);
    }
    reader.close();
    return result;
  }

  public void getCommands(List<String> input) {
    for (String line : input) {
      executeCommands(line);
    }
  }

  public void getCommandsWithAim(List<String> input) {
    for (String line : input) {
      executeCommandsWithAim(line);
    }
  }

  public void executeCommands(String command) {
    int i = Integer.parseInt(command.substring(command.length() - 1));
    if (command.startsWith("forward")) {
      setHorizontal(getHorizontal() + i);
    } else if (command.startsWith("down")) {
      setDepth(getDepth() + i);
    } else if (command.startsWith("up")) {
      setDepth(getDepth() - i);
    }
  }

  public void executeCommandsWithAim(String command) {
    int i = Integer.parseInt(command.substring(command.length() - 1));
    if (command.startsWith("forward")) {
      setHorizontal(getHorizontal() + i);
      setDepth(getDepth() + (i * getAim()));
    } else if (command.startsWith("down")) {
      setAim(getAim() + i);
    } else if (command.startsWith("up")) {
      setAim(getAim() - i);
    }
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public void setHorizontal(int horizontal) {
    this.horizontal = horizontal;
  }

  public int getAim() {
    return aim;
  }

  public void setAim(int aim) {
    this.aim = aim;
  }
}
