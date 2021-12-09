package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

    public static void main(String[] args) throws IOException {
        int[][] grid = new int[1000][1000];
        int x1 = 0, x2 = 2, y1 = 1, y2 = 3;
        List<int[]> nonDiags = new ArrayList<>();
        for(int[] line : readLines("src/main/resources/day5.txt")){
            //this commented out code is for part 1
/*            if(line[x1] == line[x2] | line[y1] == line[y2]){
                int[] toAdd = new int[4];
                for(int i = 0; i < line.length; i++){
                    toAdd[i] = line[i];
                }
                nonDiags.add(toAdd);
            }
        }
        for(int[] line: nonDiags){*/
            if(line[x1] == line[x2]){
                int lengthOfLine = Math.abs(line[y1] - line[y2]);
                for(int i = 0; i <= lengthOfLine; i++){
                    grid[i+Math.min(line[y1], line[y2])][line[x1]]++;
                }
            } else if (line[y1] == line[y2]){
                int lengthOfLine = Math.abs(line[x1] - line[x2]);
                for(int i = 0; i <= lengthOfLine; i++){
                    grid[line[y1]][i+Math.min(line[x1], line[x2])]++;

                }
            //this else case is for diagonal lines
            } else{
                int x = Math.abs(line[x1] - line[x2]);
                int y = Math.abs(line[y1] - line[y2]);
                int lengthOfLine = Math.max(x,y);
                //in this case, the diagonal line will be TL-BR
                if((line[y1] - line[y2]) / (line[x1] - line[x2]) > 0){
                    //starting from the rightmost element->
                    //I need to decrement both the x and the y, but I need to dec it from the greater of the 2 x's and y's
                    for(int i = 0; i <= lengthOfLine; i++){
                        grid[Math.max(line[y1], line[y2]) - i][Math.max(line[x1], line[x2]) - i]++;
                    }
                //in this case, the diagonal line will be BL-TR
                }else if((line[y1] - line[y2]) / (line[x1] - line[x2]) < 0){
                    //starting from the rightmost element ->
                    //I need to inc the y, but dec the x, starting from the min y, max x
                    for(int i = 0; i <= lengthOfLine; i++){
                        grid[Math.min(line[y1], line[y2]) + i][Math.max(line[x1], line[x2]) - i]++;
                    }
                }
            }
        }
        for(int[] row : grid){
            System.out.println(Arrays.toString(row));
        }
        int intersections = 0;
        for(int[] row : grid){
            for(int point : row){
                if(point > 1){
                    intersections++;
                }
            }
        }
        System.out.println(intersections);
    }

    public static List<int[]> readLines(String filename) throws IOException {
        List<int[]> result = new ArrayList<>(500);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = reader.readLine()) != null){
            result.add(Arrays.stream(line.split("( -> )|,")).mapToInt(Integer::parseInt).toArray());
        }
        reader.close();
        return result;
    }
}
