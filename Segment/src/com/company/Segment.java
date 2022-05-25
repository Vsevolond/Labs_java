package com.company;

public class Segment implements Comparable<Segment> {
    private double a, b;
    public Segment(double start, double end) {
        this.a = start;
        this.b = end;
    }

    public double len() { return b - a; }

    public String toString() { return "[" + a + ", " + b + "]"; }

    public int compareTo(Segment obj) {
        double len1 = this.len(), len2 = obj.len();
        if (len1 < len2) return -1;
        else if (len1 == len2) return 0;
        else return 1;
    }
}
