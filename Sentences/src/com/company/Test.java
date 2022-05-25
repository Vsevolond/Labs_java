package com.company;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Sentence []test = new Sentence[] {
                new Sentence("Hello World!"),
                new Sentence("What is your name?"),
                new Sentence("So, it was very easy"),
                new Sentence("I didn't understand you."),
                new Sentence("Can you do this for me, please?")
        };
        Arrays.sort(test);
        for (Sentence s : test) System.out.println(s);
    }
}

