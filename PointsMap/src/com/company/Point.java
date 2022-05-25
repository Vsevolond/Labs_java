package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Point {
    private int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public ArrayList<Double> distances(ArrayList<Point> Points) {
        ArrayList<Double> dist = new ArrayList<Double>();
        for (Point point : Points) {
            if (point.x != this.x && point.y != this.y)
                dist.add(Math.sqrt((point.x - this.x) * (point.x - this.x)
                    + (point.y - this.y) * (point.y - this.y)));
        }
        Collections.sort(dist);
        return dist;
    }
}
