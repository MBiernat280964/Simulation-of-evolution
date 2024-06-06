package simulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 * It is responsible for creation and deletion of Creatures instances and updating map from Map instance
 */
public class Simulation {

    private int years = 100;     // default: from user input

    private List<Species> speciesList;
    private List<Creature> creatureList = new ArrayList<>();
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
        map.showMap();
    }

    void updateMap(Creature creature) {
        //bruhowo
        System.out.println("This method updates map");
    }

    public int[] getCreatureCount(){
        int[] animalCount = new int[6];
        for(int i=0; i<creatureList.size(); i++){
            if (creatureList.get(i).getSpecies() == bird) {
                animalCount[0]++;
            }
            else if (creatureList.get(i).getSpecies() == cockroach) {
                animalCount[1]++;
            }
            else if (creatureList.get(i).getSpecies() == dinosaur) {
                animalCount[2]++;
            }
            else if (creatureList.get(i).getSpecies() == fish) {
                animalCount[3]++;
            }
            else if (creatureList.get(i).getSpecies() == human) {
                animalCount[4]++;
            }
            else if (creatureList.get(i).getSpecies() == wolf) {
                animalCount[5]++;
            }
            else {
                System.out.println("error: creature species not found");
                return null;
            }
        }
        return animalCount;
    }

    void addToPopulation (Creature creature){
        creatureList.add(creature);
    }
    void removeFromPopulation (Creature creature){
        creatureList.remove(creature);
    }

    public List<Creature> getCreatureList() {
        return creatureList;
    }

    void simulationCycle()
    {
        int years = 0;
        while (years < this.years) {
            int i = 0;
            while(creatureList.get(i) != null)
            {

                i++;
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(1);
        System.out.println("costam");
    }
    // yey
}






