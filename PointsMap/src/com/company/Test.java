package com.company;

public class Test {

    public static void main(String[] args) {
	    PointsMap p = new PointsMap();
        p.add(1, 3);
        p.add(5, 4);
        p.add(10, 28);
        p.add(1, 7);
        p.add(5, 10);
        p.add(3, 8);
        p.nameStream(3).forEach(System.out::println);
    }
}
