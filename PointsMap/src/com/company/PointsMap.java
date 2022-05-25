package com.company;

import com.company.Point;

import java.util.ArrayList;
import java.util.stream.Stream;


public class PointsMap {
    ArrayList<Point> Points;
    int count;
    PointsMap() {
        Points = new ArrayList<>();
        count = 0;
    }
    void add(int x, int y) {
        Points.add(new Point(x, y));
        count++;
    }
    double averageValue(ArrayList<Double> list) {
        double average = 0;
        for (int i = 0; i < list.size(); i++) average += list.get(i);
        return average / list.size();
    }

    public Stream<Double> nameStream(int k) {
        ArrayList<Double> res = new ArrayList<>();
        Points.stream()
                .forEach(x -> res.add(Math.PI * x.distances(Points).get(k) * x.distances(Points).get(k)));
        return res.stream().filter(x -> x < averageValue(res));
        //return res.stream();
    }

}
