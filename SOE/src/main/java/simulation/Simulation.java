package simulation;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
/**
 * Object <code>Simulation</code> handles simulation of evolution, cooperates with Object Map
 * It is responsible for creation and deletion of Creatures instances and updating map from Map instance
 */
public class Simulation {

    private static final double POPULATION_FACTOR_BASE = 0.01;

    private static final long SLEEP_TIME_MILLIS = 500;

    Map map;
    private static int years = 10;
    HashMap<Character, Integer> mapOfCreatures;
    String mapName = "lake";

    private List<Species> speciesList;
    private List<Creature> creatureList = new ArrayList<>();
    private EnemyFoodUtility enemyFoodMapping = new EnemyFoodUtility();
    private DefaultMovement defaultMovement = new DefaultMovement();
    private WaterMovement fishMovement = new WaterMovement();
    private DefaultFight defaultFight = new DefaultFight();
    private DefaultBreed defaultBreed = new DefaultBreed();
    private WaterBreed fishBreed = new WaterBreed();
    private BirdMovement birdMovement = new BirdMovement ();
    private AggressiveMovement aggressiveMovement = new AggressiveMovement();

    Species wolf;
    Species bird;
    Species cockroach;
    Species human;
    Species dinosaur;
    Species fish;

    private MapWindow mapWindow = null;
    /**
     *Constructor for simulation
     */
    Simulation(int years, HashMap<Character,Integer> mapOfCreatures, String mapName) {
        this.years = years;
        this.mapOfCreatures = mapOfCreatures;
        this.mapName = mapName;
    }
    /**
     *Adds species to the simulation
     */
    private void initSpecies(){
        double populationFactor = map.sizeOfMap * map.sizeOfMap * POPULATION_FACTOR_BASE;
        wolf = new Species(2,2,"Wolf", 4, (int)(populationFactor * 2), 'w', 8);
        bird = new Species(5, 1, "Bird", 8, (int)(populationFactor * 2), 'b', 10);
        cockroach = new Species(2, 1, "Cockroach", 2, (int)(populationFactor * 1.5), 'c', 4);
        human = new Species(3, 2, "Human", 4, (int)(populationFactor * 2), 'h', 8);
        dinosaur = new Species(2, 4, "Dinosaur", 2, (int)(populationFactor), 'd', 10);
        fish = new Species(6, 1, "Fish", 8, (int)(populationFactor * 1.5), 'f', 4);


        speciesList = new ArrayList<>();
        speciesList.add(wolf);
        speciesList.add(bird);
        speciesList.add(cockroach);
        speciesList.add(human);
        speciesList.add(dinosaur);
        speciesList.add(fish);

        enemyFoodMapping.initSpecies(speciesList);

        enemyFoodMapping.addEnemy(wolf , dinosaur);
//        enemyFoodMapping.addEnemy(bird , human);
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
        enemyFoodMapping.addFood(wolf, dinosaur);
        enemyFoodMapping.addFood(bird , fish);
        enemyFoodMapping.addFood(bird , cockroach);
        enemyFoodMapping.addFood(human , fish);
        enemyFoodMapping.addFood(human , bird);
        enemyFoodMapping.addFood(human, dinosaur);
        enemyFoodMapping.addFood(dinosaur , fish);
        enemyFoodMapping.addFood(dinosaur, human);
        enemyFoodMapping.addFood(dinosaur, wolf);
        enemyFoodMapping.addFood(fish , cockroach);
        enemyFoodMapping.addFood(cockroach, human);

        defaultMovement.setEnemyFoodUtility(enemyFoodMapping);
        fishMovement.setEnemyFoodUtility(enemyFoodMapping);
        birdMovement.setEnemyFoodUtility(enemyFoodMapping);
        aggressiveMovement.setEnemyFoodUtility(enemyFoodMapping);

        defaultFight.setEnemyFoodUtility(enemyFoodMapping);

        defaultBreed.setEnemyFoodUtility(enemyFoodMapping);
        fishBreed.setEnemyFoodUtility(enemyFoodMapping);
    }
    /**
     *Is responsible for showing map array in console
     */
    void generateMap (){
        map.showMap();
    }
    /**
     *Updates map with creatures' new positions
     * @param creatureList contains a list of all creatures to iterate through
     */
    void updateMap(List<Creature> creatureList) {
        int x;
        int y;
        for (int i=0; i<creatureList.size(); i++){
            x = creatureList.get(i).getX();
            y = creatureList.get(i).getY();
            map.map[1][x][y] = creatureList.get(i).getSpecies().getCharacter();
        }
    }


    /**
     *Counts the population of each species
     * @param species is the current species to iterate through
     */
    public int getCreatureCount(Species species){
        int count = 0;
        for (int i=0; i<creatureList.size(); i++){
            if (creatureList.get(i).getSpecies()==species){
              count++;
            }
        }
        return count;
    }
    /**
     *Decides which species won in the simulation
     * @return the winner species
     */
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
    /**
     *Checks if a creature can occupy land, water or both
     * @param species of the creature
     * @param creature to check the condition
     * @return boolean value
     */
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
    /**
     *Adds new creatures
     * @param species of the new creature
     */
    void initCreature (Species species){
        for (int i=0; i<mapOfCreatures.get(Character.valueOf(species.getCharacter())).intValue() ; i++){
            Creature creature = new Creature(species);
            do {
                generateXY(creature);
            } while (!isFree(creature,creatureList) || !canBeHere(species, creature));
            creatureList.add(creature);
            System.out.println(creature.getX() + " " + creature.getY() + " " + creature.getSpecies().getName());
        }
    }
    /**
     *Decides the order of object initialization
     */
    void firstAddToMap (){
        initCreature(wolf);
        initCreature(bird);
        initCreature(cockroach);
        initCreature(human);
        initCreature(fish);
        initCreature(dinosaur);
        updateMap(creatureList);
    }
    /**
     *Handles movement logic
     */
    void moving(){
        for(int j=10; j>0; j--){
            for (int i=0; i <creatureList.size(); i++){
                Creature creature = creatureList.get(i);
                double remainingPower = (double)creature.getSpeed()/(double)creature.getSpecies().getSpeed();
                if(remainingPower >= (double)j/10 && creature.getSpeed() > 0){
                    if (map.map[0][creature.getX()][creature.getY()] == 'W'){
                        map.map[1][creature.getX()][creature.getY()] = 'W';
                    } else {
                        map.map[1][creature.getX()][creature.getY()] = 'L';
                    }                    if (creature.getSpecies() == fish) {
                        fishMovement.performSingleStep(creature, creatureList, map.map[0]);
                    } else if (creature.getSpecies() == bird) {
                        birdMovement.performSingleStep(creature, creatureList, map.map[0]);
                    } else if(creature.getSpecies() == dinosaur) {
                        aggressiveMovement.performSingleStep(creature, creatureList, map.map[0]);
                    } else {
                        defaultMovement.performSingleStep(creature, creatureList, map.map[0]);
                    }
                    creature.setSpeed(creature.getSpeed() - 1);
                }
                updateMap(creatureList);
            }
            printMap(-1);
        }
    }

    /**
     * Handles fighting logic
     */
    boolean fighting (){
        List<Creature> yetToFight = new ArrayList<>(creatureList);
        boolean somebodyAttacks = false;
        for (int i=0; i<yetToFight.size(); i++){
            Creature creature = yetToFight.get(i);
            map.map[1][creature.getX()][creature.getY()] = 'x';
            somebodyAttacks = defaultFight.performAttack(creature, creatureList) || somebodyAttacks;
        }
        return somebodyAttacks;
    }
    /**
     * Handles breeding logic
     */
    void breeding (){
        List<Creature> yetToBreed = new ArrayList<>(creatureList);
        for (int i=0; i<yetToBreed.size(); i++){
            Creature creature = yetToBreed.get(i);
            if (creature.getSpecies() == fish){
                fishBreed.performBreeding(creature, creatureList, getCreatureCount(creature.getSpecies()), map.map[0]);
            } else {
                defaultBreed.performBreeding(creature, creatureList, getCreatureCount(creature.getSpecies()), map.map[0]);
            }
        }
    }
    /**
     * Opens map GUI and shows simulation cycles
     */
    void simulationCycle()
    {
        for (int year=0; year < this.years; year++) {
            for (Creature creature : creatureList) {
                creature.setSpeed(creature.getSpecies().getSpeed());
                creature.setHp(creature.getSpecies().getBaseHp());
                creature.setBreedingEnabled(true);
                map.map[1][creature.getX()][creature.getY()] = '\0';
            }
            breeding();
            printMap(year);
            updateMap(creatureList);
            printMap(-1);
            moving();
            while (fighting());
            updateMap(creatureList);
            printMap(year);

            for (int i=0; i<map.sizeOfMap; i++){
                for (int j=0; j<map.sizeOfMap; j++){
                    map.map[1][i][j]='\0';
                }
            }
        }
    }
    /**
     *Handles map update schedule
     * @param year to check if the year is below the maximum amount
     */
    private void printMap(int year){
        updateMap(creatureList);
        try{
            Thread.sleep(SLEEP_TIME_MILLIS);
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        if(year != -1){
            System.out.println("year: " + (year + 1));
        }
        generateMap();

        System.out.println();

        mapWindow.updateDisplay(map.map);
    }


    /**
     * function generateXY generates two random numbers, x and y, and puts them in an array
     * seed is current computer time in milliseconds
     * @param creature is a current creature to generate XY coordinates for
     * @return array with first index - x coordinate and second index - y coordinate
     */
    void generateXY(Creature creature) {
        int x;
        int y;
        Random rand = new Random();
        int n = rand.nextInt(map.sizeOfMap);
        x = n;
        n = rand.nextInt(map.sizeOfMap);
        y = n;

        creature.setX(x);
        creature.setY(y);
    }
    /**
     * Checks if current spot is available
     * @param creature to check the spot for
     * @param creatureList to iterate through
     * @return boolean value of isFree check
     */
    private boolean isFree (Creature creature, List <Creature> creatureList){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == creature.getX() && creatureList.get(i).getY() == creature.getY())){
                return false;
            }
        }
        return true;

    }
    /**
     *Initializes Map object
     */
    private void init(){
        map = new Map(mapName);

        initSpecies();
        firstAddToMap();
        generateMap();
    }
    /**
     * Shows final results of the simulation
     * @param frame in which the result is supposed to render in
     * @param mapCrFromGUI hashmap of characters of species and their corresponding number of population
     * @param mapNameFromGUI is the name of window
     */
    private void globalFunction(HashMap<Character, Integer> mapCrFromGUI, String mapNameFromGUI, JFrame frame){
        simulationCycle();

        for (int i=0; i<speciesList.size(); i++){
            Species species = speciesList.get(i);
            System.out.println("Count of " + species.getName() + ": " + getCreatureCount(species));
        }
        String nameOfWinner = winSpecies().getName();
        System.out.println("The winner is: " + nameOfWinner);
        frame.dispose();
        EndingWindow endingWindow = new EndingWindow(mapOfCreatures,nameOfWinner);
    }
    public static void main (HashMap<Character, Integer> mapCrFromGUI, String mapNameFromGUI) {
        Simulation simulation = new Simulation(years , mapCrFromGUI, mapNameFromGUI);
        simulation.init();
        JFrame frame = new JFrame("Map view");
        MapWindow mapWindow = new MapWindow(simulation.map.map,frame);
        simulation.mapWindow = mapWindow;
        mapWindow.frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        simulation.globalFunction(mapCrFromGUI, mapNameFromGUI, frame);
                        return null;
                    }
                };
                worker.execute();
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}






