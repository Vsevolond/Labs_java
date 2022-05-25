package com.company;

public class Universe {
    private String name; // имя вселенной
    private static double gravityConst = 6.6e-11; // гравитационная постоянная
    private int countPlanets; // кол-во планет
    private Planet[] planets; // массив планет
    public Universe(String universeName) {
        this.name = universeName;
    }
    public void addPlanet(Planet planet) { // добавление планеты во вселенную
        countPlanets++;
        Planet[] newPlanets = new Planet[countPlanets]; // новый массив планет
        for (int i = 0; i < countPlanets - 1; i++) newPlanets[i] = planets[i]; // копирование старого массива в новый
        newPlanets[countPlanets - 1] = planet; // добавление планеты в новый массив
        planets = newPlanets; // присваиваем старому массиву новый
        planets[countPlanets - 1].setCountCoPlanets(countPlanets - 1); // изменение кол-ва со-планет через статический метод
    }
    public double averageGravity(Planet centralPlanet) {
        boolean isExist = false;
        for (Planet p : planets) {
            if (p == centralPlanet) {
                isExist = true;
                break;
            }
        }
        if (isExist && countPlanets > 1) {
            double[] gravities = new double[countPlanets - 1]; // массив сил притяжения к центральной планете
            int i = 0;
            for (Planet p : planets) {
                if (p != centralPlanet) {
                    double mX = centralPlanet.x;
                    double mY = centralPlanet.y;
                    double mZ = centralPlanet.z;
                    double r = (p.x - mX) * (p.x - mX) + (p.y - mY) * (p.y - mY) + (p.z - mZ) * (p.z - mZ); // расстояние до ЦП
                    gravities[i] = (gravityConst * centralPlanet.mass * p.mass) / r; // формула для силы притяжения между 2мя объектами
                    i++;
                }
            }
            double averageGravity = 0; // средняя сила притяжения к центральной планете
            for (double x : gravities) averageGravity += x;
            averageGravity /= (countPlanets - 1);
            return averageGravity;
        } else {
            return 0;
        }
    }
    public String toString() {
        String res = "----------Вселенная " + name + "----------\n";
        for (Planet p : planets) {
            res += p.toString() + '\n';
        }
        return res;
    }
}
