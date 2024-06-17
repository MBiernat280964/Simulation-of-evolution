package simulation;

import java.lang.Math;

/**
 * Object <code>Map</code> creates a three-dimensional map, using one of three templates chosen by the user
 * it is responsible for generating terrain
 * as well as storing information about terrain and coordinates of creatures placed on the map
 */

public class Map {
    static final public int sizeOfMap = 24;

    static char [][][] map = new char [2][sizeOfMap][sizeOfMap];

    /**
     * Constructor for Map class
     */
    public Map(String biome) {
        chooseGenerator(biome);
    }

    void chooseGenerator(String biome) {
        if (biome.equals("riverside")) {
            genMapRiver();
        } else if (biome.equals("lake")) {
            genMapLake();
        } else if (biome.equals("island")) {
            genMapIsland();
        }
    }

    /**
     * Prints map containing chosen biome and currently population of creatures
     */
    void showMap() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (map[1][i][j] == 0) {
                    System.out.print(map [0][i][j] + " ");
                } else if (map[1][i][j] == 'W' || map[1][i][j] == 'L') {
                    System.out.print("\u001B[36m" + map[1][i][j] + " \u001B[0m");
                } else  if (map[1][i][j] == 'x') {
                    System.out.print("\u001B[37m" + map [1][i][j] + " \u001B[0m");
                } else
                    System.out.print("\u001B[31m" + map [1][i][j] + " \u001B[0m");
            }
            System.out.print("\n");
        }
    }

    /**
     * Generates map biome: lake
     */
    private void genMapLake() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (i >= (j-sizeOfMap/2)*(j-sizeOfMap/2)/20 + 15 &&
                        (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/20 +30 > 0 &&
                        i <= (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +3*sizeOfMap/4){
                    map[0][i][j] = 'W';
                } else {
                    map[0][i][j] = 'L';
                }
            }
        }
    }
    /**
     * Generates map biome: river
     */
    private void genMapRiver() {
//        for (int i = 0; i < sizeOfMap; i++){
//            for (int j = 0; j<sizeOfMap/2; j++){
//                map[0][j][i] = 'W';
//                map [0][sizeOfMap/2+j][i] = 'L';
//            }
//        }
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (4*Math.sin(((float)j/5)) + 34 >= i){
                    map[0][i][j] = 'W';
                } else {
                    map[0][i][j] = 'L';
                }
                if (4*Math.sin(((float)j/5)) + 20 >= i){
                    map[0][i][j] = 'L';
                }
            }
        }
    }
    /**
     * Generates map biome: island
     */
    private void genMapIsland() {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (i >= (j-sizeOfMap/2)*(j-sizeOfMap/2)/20 + 5 &&
                        (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/20 +30 > 0 &&
                        i <= (-(j-sizeOfMap/2)*(j-sizeOfMap/2))/4 +sizeOfMap - 4){
                    map[0][i][j] = 'L';
                } else {
                    map[0][i][j] = 'W';
                }
            }
        }
    }


}
