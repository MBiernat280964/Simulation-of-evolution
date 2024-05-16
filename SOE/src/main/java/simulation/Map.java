package simulation;

import java.util.*;

/**
 * Object <code>Map</code> creates a two-dimensional map, using one of three templates chosen by the user
 */

public class Map {
    private ArrayList<Creature> population;
    public Map() {
        this.population = new ArrayList<>();
        createMap1();
    }

    void addCreature(Creature creature)
    {
        population.add(creature);
    }

    ArrayList<ArrayList<Integer>> map1 = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();

    void createMap1()
    {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j< 100; j++)
            {
                temp.add(0);
            }
            map1.add(temp);
        }
    }

    void createMap2()
    {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j< 100; j++)
            {
                temp.add(0);
            }
            map1.add(temp);
        }
    }

    void createMap3()
    {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j< 100; j++)
            {
                temp.add(0);
            }
            map1.add(temp);
        }
    }
}
