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
    private DefaultMovement defaultMovement = new DefaultMovement();
    private DefaultFight defaultFight = new DefaultFight();
    private OtherBreed otherBreed = new OtherBreed();

    Species wolf = new Species(2,1,"Wolf", 4, 200);
    Species bird = new Species(10, 1, "Bird", 8, 200);
    Species cockroach = new Species(1, 1, "Cockroach", 2, 150);
    Species human = new Species(3, 3, "Human", 4, 200);
    Species dinosaur = new Species(1, 6, "Dinosaur", 2, 100);
    Species fish = new Species(5, 1, "Fish", 8, 150);
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
        System.out.println("This method updates map");
    }

    public int getCreatureCount(Species species){
        int count = 0;
        for (int i=0; i<creatureList.size(); i++){
            if (creatureList.get(i).getSpecies()==species){
              count++;
            }
        }
        return count;
    }

    public Species winSpecies (){
        int maxCount=0;
        Species win = null;
        speciesList = new ArrayList<>();
        speciesList.add(wolf);
        speciesList.add(bird);
        speciesList.add(cockroach);
        speciesList.add(human);
        speciesList.add(dinosaur);
        speciesList.add(fish);

        for (int i=0; i<speciesList.size(); i++){
            if (getCreatureCount(speciesList.get(i))>maxCount){
                maxCount=getCreatureCount(speciesList.get(i));
                win = speciesList.get(i);
            }
        }
        return win;
    }

    public List<Creature> getCreatureList() {
        return creatureList;
    }

    void simulationCycle()
    {
        int years = 0;
        while (years < this.years) {
            for(int i=0; i<creatureList.size(); i++) {
                creatureList.get(i).setSpeed(creatureList.get(i).getSpecies().getSpeed());
                creatureList.get(i).setHp(creatureList.get(i).getSpecies().getBaseHp());
            }
            for(int i=0; i<creatureList.size(); i++){
                otherBreed.performBreeding(creatureList.get(i), creatureList, getCreatureCount(creatureList.get(i).getSpecies()));
                while(creatureList.get(i).getSpeed()!=0) {
                    defaultMovement.performSingleStep(creatureList.get(i), creatureList);
                }
                defaultFight.performAttack(creatureList.get(i), creatureList);
                if (creatureList.get(i).getHp()==0){
                    creatureList.remove(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(1);
        simulation.initSpecies();
        simulation.simulationCycle();
    }
}






