package main.java.Day4;

public class Testing {


    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("");
        String a = "1112031584";
        for(int i = 1; i < a.length(); i++) {
            if(a.charAt(i) % 2 == a.charAt(i -1) % 2){
                s.append(Math.max(a.charAt(i) - '0', a.charAt(i -1) - '0'));
            }
        }
        System.out.println(s);
    }



}
