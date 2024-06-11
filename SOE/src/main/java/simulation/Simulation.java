package simulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 * It is responsible for creation and deletion of Creatures instances and updating map from Map instance
 */
public class Simulation {

    private int years = 100;     // default: from user input
    HashMap<Character, Integer> mapOfCreatures;
    String mapName = "lake";

    private List<Species> speciesList;
    private List<Creature> creatureList = new ArrayList<>();
    private EnemyFoodUtility enemyFoodMapping = new EnemyFoodUtility();
    private DefaultMovement defaultMovement = new DefaultMovement();
    private DefaultFight defaultFight = new DefaultFight();
    private OtherBreed otherBreed = new OtherBreed();

    Species wolf = new Species(2,1,"Wolf", 4, 200, 'w');
    Species bird = new Species(10, 1, "Bird", 8, 200, 'b');
    Species cockroach = new Species(1, 1, "Cockroach", 2, 150, 'c');
    Species human = new Species(3, 3, "Human", 4, 200, 'h');
    Species dinosaur = new Species(1, 6, "Dinosaur", 2, 100, 'd');
    Species fish = new Species(5, 1, "Fish", 8, 150, 'f');
    Simulation(int years, HashMap<Character,Integer> mapOfCreatures, String mapName) {
        this.years = years;
        this.mapOfCreatures = mapOfCreatures;
        this.mapName = mapName;
    }
    Map map;
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
        map.showMap();
    }

    void updateMap(List<Creature> creatureList) {
        int x;
        int y;
        for (int i=0; i<creatureList.size(); i++){
            x = creatureList.get(i).getX();
            y = creatureList.get(i).getY();
            map.map[1][x][y] = creatureList.get(i).getSpecies().getCharacter();
        }
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

    private boolean canBeHere (Species species, Creature creature){
        if (species == bird) {
            System.out.println("Bird");
            return true;
        } else if (species == fish && this.map.map[0][creature.getX()][creature.getY()] == 'W') {
            System.out.println("Fish");
            return true;
        } else if ((species == wolf || species == human || species == cockroach || species == dinosaur) && this.map.map[0][creature.getX()][creature.getY()] == 'L'){
            System.out.println("Other");
            return true;
        }
        return false;
    }

    void initCreature (Species species){
        for (int i=0; i<mapOfCreatures.get(Character.valueOf(species.getCharacter())).intValue() ; i++){
            Creature creature = new Creature(species);
            this.creatureList.add(creature);
            do {
                generateXY(creature);
            } while (!isFree(creature,this.creatureList) && !canBeHere(species, creature));
            System.out.println(creature.getX() + " " + creature.getY() + " " + creature.getSpecies().getName());
        }
    }

    void firstAddToMap (){
        initCreature(wolf);
        initCreature(bird);
        initCreature(cockroach);
        initCreature(human);
        initCreature(fish);
        initCreature(dinosaur);
        updateMap(creatureList);
    }

    void simulationCycle()
    {
        for (int year=0; year < this.years; year++) {
            for (Creature creature : creatureList) {
                creature.setSpeed(creature.getSpecies().getSpeed());
                creature.setHp(creature.getSpecies().getBaseHp());
            }
            for(int i=0; i<creatureList.size(); i++){
                otherBreed.performBreeding(creatureList.get(i), creatureList, getCreatureCount(creatureList.get(i).getSpecies()));
                map.map[1][creatureList.get(i).getX()][creatureList.get(i).getY()] = '\0';
                while(creatureList.get(i).getSpeed()!=0) {
                    creatureList.get(i).setSpeed(creatureList.get(i).getSpeed() - 1);
                    defaultMovement.performSingleStep(creatureList.get(i), creatureList, this.map.map[0][creatureList.get(i).getX()][creatureList.get(i).getY()] );
                }
                defaultFight.performAttack(creatureList.get(i), creatureList);
                if (creatureList.get(i).getHp()==0){
                    map.map[1][creatureList.get(i).getX()][creatureList.get(i).getY()] = '\0';
                    creatureList.remove(i);
                }
            }
            updateMap(creatureList);
        }
    }


    /**
     * functionvgenerateXY generates two random numbers, x and y, and puts them in an array
     * seed is current computer time in milliseconds
     * @return array with first index - x coordinate and second index - y coordinate
     */
    void generateXY(Creature creature) {
        int x;
        int y;
        Random rand = new Random();
        int n = rand.nextInt(100);
        x = n;
        n = rand.nextInt(100);
        y = n;

        creature.setX(x);
        creature.setY(y);
    }

    private boolean isFree (Creature creature, List <Creature> creatureList){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == creature.getX() && creatureList.get(i).getY() == creature.getY())){
                return false;
            }
        }
        return true;

    }

    public static void main(HashMap<Character, Integer> mapCrFromGUI, String mapNameFromGUI) {
        Simulation simulation = new Simulation(5, mapCrFromGUI, mapNameFromGUI);
        simulation.map = new Map(simulation.mapName);
        simulation.initSpecies();
        simulation.firstAddToMap();
        simulation.generateMap();
        simulation.simulationCycle();
        for (int i=0; i<simulation.creatureList.size();i++){
            System.out.println(simulation.creatureList.get(i).getX() + " " + simulation.creatureList.get(i).getY() + " " + simulation.creatureList.get(i).getSpecies().getName());
        }
        simulation.generateMap();
    }
}






