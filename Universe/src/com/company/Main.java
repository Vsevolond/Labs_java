package com.company;

public class Main {

    public static void main(String[] args) {
	    Universe marvel = new Universe("Marvel");
        Planet earth = new Planet("Earth", 500, 5, 3, 2);
        Planet asgard = new Planet("Asgard", 1000, 2, 4, 5);
        Planet titan = new Planet("Titan", 3261, 13, 432, 21);
        Planet morag = new Planet("Morag", 843728, 653, 143, 6753);
        marvel.addPlanet(earth);
        marvel.addPlanet(asgard);
        marvel.addPlanet(titan);
        marvel.addPlanet(morag);
        System.out.print(marvel);
        System.out.println("Средняя сила притяжения до Асгарда: " + marvel.averageGravity(asgard) + '\n');

        Universe dc = new Universe("DC");
        Planet krypton = new Planet("Krypton", 800, 6, 3, 4);
        Planet apokalyps = new Planet("Apokalyps", 6431, 73472, 621, 7312);
        dc.addPlanet(krypton);
        dc.addPlanet(apokalyps);
        System.out.println(dc);
    }
}
