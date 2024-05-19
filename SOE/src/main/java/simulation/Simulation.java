package simulation;

/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 */
public class Simulation {
    Simulation()
    {
        Map mapa = new Map(1);
    }

    /* to defaultowe, do podania przez uzytkownika*/ int yearsInput = 100;
    void updateMap(Creature creature) {
        Map.mapOfCreatures[creature.x][creature.y] = creature;
    }
    void simulationCycle()
    {
        int years = 0;
        while (years < yearsInput) {
            int i = 0;
            while(Map.population.get(i) != null)
            {
                /*
                co sie dzieje ze zwierzatkiem podczas jego kolejki
                 */
                i++;
            }
        }
    }

    }




