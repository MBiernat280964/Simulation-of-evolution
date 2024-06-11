package simulation;

import java.lang.Math;

/**
 * Object <code>Map</code> creates a three-dimensional map, using one of three templates chosen by the user
 * it is responsible for generating terrain
 * as well as storing information about terrain and coordinates of creatures placed on the map
 */

public class Map {
    final public int sizeOfMap = 100;

    char [][][] map = new char [2][sizeOfMap][sizeOfMap];

    /**
     * Constructor for Map class
     */
    public Map(String biome) {
        this.chooseGenerator(biome);
    }

    void chooseGenerator(String biome) {
        if (biome.equals("riverside")) {
            this.genMapRiver();
        } else if (biome.equals("lake")) {
            this.genMapLake();
        } else if (biome.equals("island")) {
            this.genMapIsland();
        }
    }

    /**
     * Prints map containing chosen biome and currently population of creatures
     */
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

    /**
     * Generates map biome: lake
     */
    void genMapLake() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (i >= (j-sizeOfMap/2)*(j-sizeOfMap/2)/20 + 15 &&
                        (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/20 +30 > 0 &&
                        i <= (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +3*sizeOfMap/4){
                    this.map[0][i][j] = 'W';
                } else {
                    this.map[0][i][j] = 'L';
                }
            }
        }
    }
    /**
     * Generates map biome: river
     */
    void genMapRiver() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (4*Math.sin(((float)j/5)) + 34 >= i){
                    this.map[0][i][j] = 'W';
                } else {
                    this.map[0][i][j] = 'L';
                }
                if (4*Math.sin(((float)j/5)) + 20 >= i){
                    this.map[0][i][j] = 'L';
                }
            }
        }
    }
    /**
     * Generates map biome: island
     */
    void genMapIsland() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (i >= (j-sizeOfMap/2)*(j-sizeOfMap/2)/20 + 5 &&
                        (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/20 +30 > 0 &&
                        i <= (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +sizeOfMap - 4){
                    this.map[0][i][j] = 'L';
                } else {
                    this.map[0][i][j] = 'W';
                }
            }
        }
    }


}
