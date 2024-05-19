package simulation;

import java.util.*;

/**
 * Object <code>Map</code> creates a two-dimensional map, using one of three templates chosen by the user
 */

public class Map {
    static ArrayList<Creature> population;
    public Map(int num) {
        this.population = new ArrayList<>();
        if (num == 1)
            createMap1();
        else if (num == 2)
            createMap2();
        else if (num == 3)
            createMap3();
    }

    void addCreature(Creature creature)
    {
        population.add(creature);
    }
    int[][] biome = new int[100][100];
    static Creature[][] mapOfCreatures = new Creature[100][100];

    void createMap1()
    {
        /*
        miejsce na stworzenie mapy z jeziorem
         */
    }

    void createMap2()
    {
        /*
        miejsce na stworzenie mapy z rzeka
         */
    }

    void createMap3()
    {
        /*
        miejsce na stworzenie mapy z oceanem(wyspa)
         */
    }

    static boolean checkIfFree(int x, int y)
    {
        if (mapOfCreatures[x][y] == null)
            return true;
        else
            return false;
    }

}
