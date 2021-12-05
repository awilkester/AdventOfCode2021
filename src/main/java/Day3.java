package main.java;

//The diagnostic report (your puzzle input) consists of a list of binary numbers which, when decoded properly,
// can tell you many useful things about the conditions of the submarine. The first parameter to check is the power consumption.
//
//You need to use the binary numbers in the diagnostic report to generate two new binary numbers (called the gamma
// rate and the epsilon rate). The power consumption can then be found by multiplying the gamma rate by the epsilon rate.
//
//Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position of all
// numbers in the diagnostic report. For example, given the following diagnostic report:

//00100
//11110
//10110
//10111
//10101
//01111
//00111
//11100
//10000
//11001
//00010
//01010

//Considering only the first bit of each number, there are five 0 bits and seven 1 bits. Since the most common bit is
// 1, the first bit of the gamma rate is 1.
//
//The most common second bit of the numbers in the diagnostic report is 0, so the second bit of the gamma rate is 0.
//
//The most common value of the third, fourth, and fifth bits are 1, 1, and 0, respectively, and so the final three bits
// of the gamma rate are 110.
//
//So, the gamma rate is the binary number 10110, or 22 in decimal.
//
//The epsilon rate is calculated in a similar way; rather than use the most common bit, the least common bit from each
// position is used. So, the epsilon rate is 01001, or 9 in decimal. Multiplying the gamma rate (22) by the epsilon rate
// (9) produces the power consumption, 198.
//
//Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate, then multiply them
// together. What is the power consumption of the submarine? (Be sure to represent your answer in decimal, not binary.)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day3 {


    public static void main(String[] args) throws IOException {
        Day3 day3 = new Day3();
        List<String> input = day3.readLines("src/main/resources/day3.txt");
//        int gamma = day3.findGamma(input);
//        int epsilon = day3.findEpsilon(gamma);
//        System.out.println(gamma * epsilon);
        System.out.println(day3.findLifeSupportNumber(input));
    }

    public List<String> readLines(String filename) throws IOException {
            List<String> result = new ArrayList<>(1000);
            FileReader x = new FileReader(filename);
            BufferedReader reader = new BufferedReader(x);
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
            for(int i = 0; i < total.size(); i++) {
                if(total.get(i) >= 500){
                    gamma.append(1);
                } else{
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
        List<String> output = input;
        int o2 = binaryStringToInt(sumTotalsHCD(output, 0));
        int co2 = binaryStringToInt(sumTotalsLCD(output, 0));
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
