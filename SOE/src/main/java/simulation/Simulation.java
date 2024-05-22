package simulation;

/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 * It is responsible for creation and deletion of Creatures instances and updating map from Map instance
 */

//TODO: a class which holds the logic for creatures' behaviours
public class Simulation {

    int years = 100;     // default: from user input

    Simulation(int years) {
        Map map = new Map();
        this.years = years;

    }

    void updateMap(Creature creature) {
        //bruhowo
    }
    void simulationCycle()
    {
        int years = 0;
        while (years < this.years) {
            int i = 0;
            while(Map.population.get(i) != null)
            {
                /*
                1. checks for enemies
                    -if no enemies, proceed forward
                    -if enemies found, fight/flight behaviour. END OF ACTION QUEUE
                2. checks for members of the same species
                    -if found, chance to reproduce
                    -if none is found in the nearest vicinity, wander around until at least one other is found
                 */
                i++;
            }
        }
    }
    /*boolean checkIfFree(int x, int y)
    {
        if (map.map[1][x][y] == null)
            return true;
        else
            return false;
    }*/
    }




