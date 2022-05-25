package com.company;

public class Planet {
    public String name; // имя планеты
    public double mass, x, y, z; // масса и координаты
    private static int countCoPlanets = 0; // кол-во со-планет во вселенной
    public Planet(String namePlanet, double inMass, double inX, double inY, double inZ) {
        this.name = namePlanet;
        this.mass = inMass;
        this.x = inX;
        this.y = inY;
        this.z = inZ;
    }
    public void setCountCoPlanets(int cnt) {
        countCoPlanets = cnt;
    }
    public String toString() {
        String res = "Имя планеты: " + name + " | " + "Масса: " + mass +
                " | " + "Координаты: " + "(" + x + ", " + y + ", " + z + ")" +
                " | " + "Кол-во со-планет: " + countCoPlanets;
        return res;
    }
}
