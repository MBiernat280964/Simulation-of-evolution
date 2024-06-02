package simulation;

import java.util.ArrayList;
import java.util.List;


/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 * It is responsible for creation and deletion of Creatures instances and updating map from Map instance
 */
public class Simulation {

    private int years = 100;     // default: from user input

    private List<Species> speciesList;
    private EnemyFoodUtility enemyFoodMapping = new EnemyFoodUtility();

    Species wolf = new Species(2,1,"Wolf");
    Species bird = new Species(10, 1, "Bird");
    Species cockroach = new Species(1, 1, "Cockroach");
    Species human = new Species(3, 3, "Human");
    Species dinosaur = new Species(1, 6, "Dinosaur");
    Species fish = new Species(5, 1, "Fish");
    Simulation(int years) {
        this.years = years;
        this.generateMap();
    }
    private void initSpecies(){
        speciesList = new ArrayList<>();
        speciesList.add(wolf);
        speciesList.add(bird);
        speciesList.add(cockroach);
        speciesList.add(human);
        speciesList.add(dinosaur);
        speciesList.add(fish);

        enemyFoodMapping.initSpecies(speciesList);
        enemyFoodMapping.addEnemy(wolf , dinosaur);
        enemyFoodMapping.addEnemy(bird , human);
        enemyFoodMapping.addEnemy(cockroach , fish);
        enemyFoodMapping.addEnemy(human , dinosaur);
        enemyFoodMapping.addEnemy(human , cockroach);
        enemyFoodMapping.addEnemy(dinosaur , wolf);
        enemyFoodMapping.addEnemy(dinosaur , human);
        enemyFoodMapping.addEnemy(fish , human);
        enemyFoodMapping.addEnemy(fish , bird);
        enemyFoodMapping.addEnemy(fish , wolf);
        enemyFoodMapping.addEnemy(fish , dinosaur);

        enemyFoodMapping.addFood(wolf , fish);
        enemyFoodMapping.addFood(bird , fish);
        enemyFoodMapping.addFood(bird , cockroach);
        enemyFoodMapping.addFood(human , fish);
        enemyFoodMapping.addFood(human , bird);
        enemyFoodMapping.addFood(dinosaur , fish);
        enemyFoodMapping.addFood(fish , cockroach);
    }

    void generateMap (){
        Map map = new Map();

        for (int i=0; i<=3; i++){
            map.population.add (new Creature(wolf));
            map.map[1][i+1][i+8] = 'w';
        }
        for (int i=4; i<=6; i++){
            map.population.add (new Creature(cockroach));
            map.map[1][i][i+2] = 'c';
        }
        for (int i=7; i<=9; i++){
            map.population.add (new Creature(dinosaur));
            map.map[1][i-1][i+4] = 'd';
        }

        map.showMap();
    }

    void updateMap(Creature creature) {
        //bruhowo
        System.out.println("This method updates map");
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
    public static void main(String[] args) {
        Simulation simulation = new Simulation(1);
        System.out.println("costam");
    }
    // yey
}






