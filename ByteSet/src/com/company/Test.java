package com.company;

public class Test {
    public static void main(String[] args) {
	    String s = "abcdefghijklmnopqrstuvwxyza";
        ByteSet byteSet = new ByteSet(s);
        for (Object x : byteSet) { System.out.println(x); }

    }
}
