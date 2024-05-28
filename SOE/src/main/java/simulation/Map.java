package simulation;

import java.util.*;
import java.lang.Math;

/**
 * Object <code>Map</code> creates a three-dimensional map, using one of three templates chosen by the user
 * it is responsible for generating terrain
 * as well as storing information about terrain and cordinates of creatures placed on the map
 */

public class Map {
    final public int sizeOfMap = 20;
    static ArrayList<Creature> population = new ArrayList<Creature>();
    //boolean [][] biome = new boolean [sizeOfMap][sizeOfMap];
    //Creature[][] mapOfCreatures = new Creature[sizeOfMap][sizeOfMap];

    //map - char array [][][] layer 0: biome, layer 1: creatures

    char [][][] map = new char [2][sizeOfMap][sizeOfMap];

    /*public Map(int num) {
        this.population = new ArrayList<>();
        if (num == 1)
            createMap1();
        else if (num == 2)
            createMap2();
        else if (num == 3)
            createMap3();
    }*/

    public Map() {
        this.genMapLake();

    }

    void showMap() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (this.map[1][i][j] == 0) {
                    System.out.print(this.map [0][i][j] + " ");

                }
                else {
                    System.out.print("\u001B[31m" + this.map [1][i][j] + " \u001B[0m");
                }
            }
            System.out.print("\n");
        }
    }

    void addCreature(Creature creature)
    {
        population.add(creature);
    }


    //on layer 0: W means water while L means land
    //on layer 1: W-wolf, C-cockroach, B-bird, D-dinosaur, H-human, F-fish
    //terrain created in real-time with the help of two paraboles
    void genMapLake() //lake map
    {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (i >= (j-sizeOfMap/2)*(j-sizeOfMap/2)/4 && (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +3*sizeOfMap/4 > 0 && i <= (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +3*sizeOfMap/4){
                    this.map[0][i][j] = 'L';
                } else {
                    this.map[0][i][j] = 'W';
                }
            }
        }
    }

    void genMapRiver() //river map
    {
        /*
        miejsce na stworzenie mapy z rzeka
         */
    }

    void genMapIsland() //island map
    {
        /*
        miejsce na stworzenie mapy z oceanem(wyspa)
         */
    }


}
