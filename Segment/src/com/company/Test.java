package com.company;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
	    Segment []test = new Segment[] {
                new Segment(12, 57),
                new Segment(1, 5),
                new Segment(89, 102),
                new Segment(16, 321),
                new Segment(-321, 32)
        };
        Arrays.sort(test);
        for (Segment s : test) System.out.println(s);
    }
}
