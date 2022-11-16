package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {

        Day3 day3 = new Day3();
        List<String> input = day3.readLines("src/main/resources/day3.txt");
        int gamma = day3.findGamma(input);
        int epsilon = day3.findEpsilon(gamma);
        System.out.println(gamma * epsilon);
        System.out.println(day3.findLifeSupportNumber(input));
    }

    public List<String> readLines(String filename) throws IOException {
            List<String> result = new ArrayList<>(1000);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine()) != null){
                result.add(line);
            }
            reader.close();
            return result;
        }

        public int findGamma(List<String> lines) {
            List<Integer> total = new ArrayList<>(12);
            for(int i = 0; i < 12; i++){
                total.add(0);
            }
            StringBuilder gamma = new StringBuilder();
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    total.set(i, total.get(i) + Character.getNumericValue((line).charAt(i)));
                }
            }
            for (Integer integer : total) {
                if (integer >= 500) {
                    gamma.append(1);
                } else {
                    gamma.append(0);
                }
            }
            return getIntValue(gamma.toString());
        }

        public int findEpsilon(int gamma){
            return ~gamma;
        }

        public int getIntValue(String input){
            return Integer.parseInt(input, 2);
        }

    public int findLifeSupportNumber(List<String> input){
        int o2 = binaryStringToInt(sumTotalsHCD(input, 0));
        int co2 = binaryStringToInt(sumTotalsLCD(input, 0));
        System.out.println(o2);
        System.out.println(co2);
        return o2 * co2;
    }

    public int binaryStringToInt(String input){
        return Integer.parseInt(input, 2);
    }

    public String sumTotalsHCD(List<String> input, int which){
        if(input.size() == 1){
            return input.get(0);
        }
        List<String> toAdd = new ArrayList<>();
        int count = 0;
        for(String i : input){
            if(i.charAt(which) == '1'){
                count++;
            }
        }
        if(count >= (input.size() / 2)){
            for(String i : input){
                if(i.charAt(which) == '1'){
                    toAdd.add(i);
                }
            }
        }else{
            for(String i : input){
                if(i.charAt(which) == '0'){
                    toAdd.add(i);
                }
            }
        }
        which++;
        input = toAdd;
        return sumTotalsHCD(input, which);
    }

    public String sumTotalsLCD(List<String> input, int which){
        if(input.size() == 1){
            return input.get(0);
        }
        List<String> toAdd = new ArrayList<>();
        int count = 0;
        for(String i : input){
            if(i.charAt(which) == '1'){
                count++;
            }
        }
        if(count < (input.size() / 2)){
            for(String i : input){
                if(i.charAt(which) == '1'){
                    toAdd.add(i);
                }
            }
        }else{
            for(String i : input){
                if(i.charAt(which) == '0'){
                    toAdd.add(i);
                }
            }
        }
        which++;
        input = toAdd;
        return sumTotalsLCD(input, which);
    }
}
