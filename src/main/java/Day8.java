package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// TODO we need to figure out how to do the char to char translation to figure out the ambiguous
// cases

public class Day8 {

  public static String sortString(String inputString) {
    char tempArray[] = inputString.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day8.txt"));
    String line;
    int count = 0;
    String[] toBeDoneWith, output, input;

    // a table to hold new values of a - g in order
    // maybe a map would be better here to hold keys for values a - g
    Map<Character, Character> charToChar = new HashMap<>(7);

    List<Character> a2g = new ArrayList<>(7);
    a2g.add('a');
    a2g.add('b');
    a2g.add('c');
    a2g.add('d');
    a2g.add('e');
    a2g.add('f');
    a2g.add('g');


    //this list will store the
    List<String> alphaTranslation = new ArrayList<>(10);
    char[] temp;

    /*
    a   8 on, 2 off
    b   6 on, 4 off     unique
    c   8 on, 2 off
    d   7 on, 3 off
    e   4 on, 6 off     unique
    f   9 on, 1 off     unique
    g   7 on, 3 off

    1 is 2 segments
        on : c, f
        off : a, b, d, e, g
    4 is 4 segments
        on : b, c, d, f
        off : a, e, g
    7 is 3 segments
        on : a, c, f
        off : b, d, e, g
    8 is 7 segments
        on : a, b, c, d, e, f, g
        off :

    2 is 5
        on : a, c, d, e, g
        off : b, f
    3 is 5
        on : a, c, d, f, g
        off : b, e
    5 is 5
        on : a, b, d, f, g
        off : c, e

    0 is 6 (this identifies d, segment d)
        on : a, b, c, e, f, g
        off : d
    6 is 6 (this identifies c, segment c)
        on : a, b, d, e, f, g
        off : c
    9 is 6 (this identifies e, segment e)
        on : a, b, c, d, f, g
        off : e
     */
    while ((line = reader.readLine()) != null) {
      toBeDoneWith = line.split("( \\| )");
      output = toBeDoneWith[1].split(" ");
      input = toBeDoneWith[0].split(" ");
      for(int i = 0; i < output.length; i++){
        String tempString = sortString(output[i]);
        output[i] = tempString;
      }
    for(int i = 0; i < input.length; i++){
      String tempString = sortString(input[i]);
      input[i] = tempString;
    }

      String zero = "", one = "",
              two = "", three = "",
              four = "", five = "",
              six = "", seven = "",
              eight = "", nine = "";
      List<String> fiveSegments = new ArrayList<>(3);
      List<String> sixSegments = new ArrayList<>(3);
      // assign each unique value to its respective temp string or string arraylist
//      System.out.println(sortString(output[0]));
//      System.out.println(sortString(input[0]));
      for (String t : input) {
        switch (t.length()) {
          case 2:
            one = t;
            break;
          case 4:
            four = t;
            break;
          case 3:
            seven = t;
            break;
          case 7:
            eight = t;
            break;
          case 5:
            fiveSegments.add(t);
            break;
          case 6:
            sixSegments.add(t);
            break;
          default:
            break;
        }
      }
      // step 1 (identify a)
      for (String c : seven.split("")) {
        if (!(one.contains(c))) {
          charToChar.put('a', c.charAt(0));
        }
      }
      //          step 2: identify c (segment 3)
      //          we know what c and f are from num1 ON (
      //          and we can use one of (0, 6, and 9) to find c because it will have it off
      //            result
      //                a, c
      Set<Character> tempC = new HashSet<>(7);
      for (char c : a2g) {
        tempC.add(c);
      }
      for (String c : sixSegments) {
        // 0, 6, 9
        String[] cf = one.split("");
        if(!(c.contains(cf[0]) && c.contains(cf[1]))) {
          for(Character z: c.toCharArray()) {
            tempC.remove(z);
          }
          six = c;
          charToChar.put('c', tempC.iterator().next());
        }
      }
      sixSegments.remove(six);
      // step 3: identify b, e, f
      int[] letterFrequency = new int[7];
      for (String c : input) {
        for (char ch : c.toCharArray()) {
          switch (ch) { // since only b, e, f have unique letter frequencies, only they matter
            case 'a':
              letterFrequency[0]++;
              break;
            case 'b':
              letterFrequency[1]++;
              break;
            case 'c':
              letterFrequency[2]++;
              break;
            case 'd':
              letterFrequency[3]++;
              break;
            case 'e':
              letterFrequency[4]++;
              break;
            case 'f':
              letterFrequency[5]++;
              break;
            case 'g':
              letterFrequency[6]++;
              break;
            default:
              break;
          }
        }
      }
      // 6 (b), 4 (e), 9 (f)
      for (int i = 0; i < letterFrequency.length; i++) {
        if (letterFrequency[i] == 6) {
          charToChar.put('b', a2g.get(i));
        } else if (letterFrequency[i] == 4) {
          charToChar.put('e', a2g.get(i));
        } else if (letterFrequency[i] == 9) {
          charToChar.put('f', a2g.get(i));
        }
      }
      // a, b, c, e, f
      // step 4
      // how to find d and g
      StringBuilder fourMinusB = new StringBuilder();
      for (char x : four.toCharArray()) {
        if (x != charToChar.get('b')) {
          fourMinusB.append(x);
        }
      }
      for (char x : String.valueOf(fourMinusB).toCharArray()) {
        if (!one.contains(String.valueOf(x))) {
         charToChar.put('d', x);
        }
      }
      for (String x : sixSegments) {
        boolean containsD = false;
        for (char z : x.toCharArray()) {
          if (z == charToChar.get('d')) {
            containsD = true;
          }
        }
        if (containsD) {
          nine = x;
        } else {
          zero = x;
        }
      }

//      StringBuilder fourPlusA = new StringBuilder(four);
//      fourPlusA.append(charToChar.get('a'));
////      fourPlusAandE.append(charToChar.get("e"));
//      char[] tempZ = sortString(fourPlusA.toString()).toCharArray();
//      for(String z: sixSegments) {
//        //z = bcdefg, fourPlusA = bcdeg
//        for (char x : z.toCharArray()) {
//          if (!String.valueOf(tempZ).contains(String.valueOf(x))) {
//
//          }
//        }
//      }
//      for(char z: charToChar.values()) {
//        tempC.add(z);
//      }
//      for(char z: a2g) {
//        if(!zero.contains(String.valueOf(z))) {
//          charToChar.put('d', z);
//        }
//      }

      //two (b & f missing), three(b & e missing), and five (c & e missing)
      for(String z : fiveSegments) {
        //two (b & f missing)
        if (!z.contains(String.valueOf(charToChar.get('b'))) && !z.contains(String.valueOf(charToChar.get('f')))) {
          two = z;
          //three(b & e missing)
        } else if (!z.contains(String.valueOf(charToChar.get('b'))) && !z.contains(String.valueOf(charToChar.get('e')))) {
          three = z;
        } else {
          five = z;
        }
      }
      List<String> numbers = List.of(zero, one, two, three, four, five, six, seven, eight, nine);
      Map<String, Integer> translation = new HashMap<>(10);
      for(int i = 0; i < numbers.size(); i++) {
        translation.put(numbers.get(i), i);
      }
//      for (String z : numbers) {sortString(z);}

      List<Integer> numberTotalForRow = new ArrayList<>(4);
      for (String x : output) {
        for(int i = 0; i < numbers.size(); i++) {
          if (x.equals(numbers.get(i))) {
            numberTotalForRow.add(i);
          }
        }
      }

      int total = 0;
      for (Integer i : numberTotalForRow) {
        total = 10 * total + i;
      }
      count += total;

      // this commented out code is for part 1
      //            for(String z : output){
      //                switch(z.length()){
      //                    case 2:
      //                    case 4:
      //                    case 3:
      //                    case 7:
      //                        count++;
      //                        break;
      //                    default:
      //                        break;
      //                }
      //            }
    }
    System.out.println(count);
  }
}
