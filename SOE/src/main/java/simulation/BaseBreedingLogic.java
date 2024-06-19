package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Object <code>BaseBreedingLogic</code> Is responsible for creating new creatures
 */
public abstract class BaseBreedingLogic implements BreedingLogic{

    EnemyFoodUtility enemyFoodUtility = new EnemyFoodUtility();
    Random random = new Random();
    /**
     * Checks if breeding is enabled and returns a boolean
     *
     * @param creature The object to check if the breeding behavior is possible
     * @param creatureList List of the entire population
     * @param surfaceLayer two-dimensional array of surface
     * @param tab array with localisation of new creature
     * @return true Returns true if breeding behavior is possible
     */
    protected boolean isBreedingEnabled(Creature creature, List<Creature> creatureList, char [][] surfaceLayer, int[] tab) {
        for (int i=0; i<creatureList.size(); i++){
            Creature other = creatureList.get(i);
            if ((0>tab[0] || tab[0]>=surfaceLayer.length || 0>tab[1] || tab[1]>=surfaceLayer.length) || (other.getX() == tab[0] && other.getY() == tab[1]) || surfaceLayer[tab[0]][tab[1]] == 'W'){
                return false;
            }
        }
        return true;
    }
    /**
     * Checks if an empty spot is available and returns a boolean
     *
     * @param creature The object to check its surroundings for an empty spot
     * @param creatureList List of the entire population
     * @return true Returns true if an empty spot is available
     */
    private boolean isEmptySpotAround(Creature creature, List<Creature> creatureList){
        int occupiedSpot = 0;
        for (int i=0; i<creatureList.size(); i++){
            double dist = Math.sqrt(Math.pow(creature.getX() - creatureList.get(i).getX(), 2) + Math.pow(creature.getY() - creatureList.get(i).getY(), 2));
            if (dist<Math.sqrt(2)){
                occupiedSpot ++;
                if (occupiedSpot==8){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Chooses a spot around creature and returns an integer array
     *
     * @param creature The object to choose a surrounding spot of
     * @param surfaceLayer two-dimensional array of surface
     * @return the coordinates for the new creature to appear
     */
    private int[] chooseBreedingSpot(Creature creature, char[][] surfaceLayer){
        int x = creature.getX();
        int y = creature.getY();
        double i = random.nextDouble();

        if(creature.getX()<=0 && i>0.5){
            x=creature.getX()+1;
        } else if(creature.getX()<surfaceLayer.length) {
            x=creature.getX()-1;
        } else if(creature.getY()<=0 && i>0.5){
            y=creature.getY()+1;
        } else if(creature.getX()<surfaceLayer.length){
            y=creature.getY()-1;
        }
        int[] tab = {x, y};
        return tab;
    }
    /**
     * Checks if an enemy is near, returns a boolean
     *
     * @param creature The object to check surroundings of
     * @param creatureList List of the entire population to iterate through
     * @return true Returns true if an enemy is near; breeding behavior not possible / Returns false if breeding behavior is possible
     */
    protected boolean isEnemyNear(Creature creature, List<Creature> creatureList) {
        for (Creature other : creatureList) {
            if (enemyFoodUtility.isEnemy(creature, other)) {
                double dist = Math.sqrt(Math.pow(creature.getX() - other.getX(), 2) + Math.pow(creature.getY() - other.getY(), 2));
                if (dist < 3) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks for a potential partner
     *
     * @param creature The object to check surroundings of
     * @param creatureList List of the entire population to iterate through
     * @return true Returns true if a potential partner is near
     */
    protected boolean isPartnerNear(Creature creature, List<Creature> creatureList) {
        for (Creature other : creatureList) {
            if (creature.getSpecies() == other.getSpecies()) {
                double dist = Math.sqrt(Math.pow(creature.getX() - other.getX(), 2) + Math.pow(creature.getY() - other.getY(), 2));
                if (dist>0 && dist < 2) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if max population count of species has been reached
     *
     * @param creature The object to check if its species max population count has been reached
     * @param populationCount List of the entire population to count
     * @return true Returns true if max population has been reached
     */
    protected boolean isMaxPopulationReached(Creature creature, int populationCount) {
        return populationCount >= creature.getSpecies().getMaxPopulation();
    }
    /**
     * Returns if breeding can occur based on random chance
     *
     * @param creature The object to check if the breeding is possible
     * @return true Returns true if breeding is possible
     */
    protected boolean drawBreedingChance(Creature creature) {
        return random.nextInt(creature.getSpecies().getBreedingChance()) < creature.getSpecies().getBreedingChance() / 2;
    }
    /**
     * Checks for the second parent, returns the object
     *
     * @param creature The object to find a potential mate for
     * @param creatureList List of the entire population to iterate through
     * @return object Returns the creature that will be the second parent
     */
    private Creature chooseSecondParent (Creature creature, List<Creature> creatureList){
        Creature secondParent;
        List<Creature> potentialParents = new ArrayList<Creature>();
        for (Creature other : creatureList) {
            if (creature.getSpecies() == other.getSpecies()) {
                double dist = Math.sqrt(Math.pow(creature.getX() - other.getX(), 2) + Math.pow(creature.getY() - other.getY(), 2));
                if (dist <= Math.sqrt(2) && dist > 0) {
                    if (other.getBreedingEnabled())
                        potentialParents.add(other);
                }
            }
        }
        if (!potentialParents.isEmpty()) {
            secondParent = potentialParents.get(random.nextInt(potentialParents.size()));
            return secondParent;
        }
        return creature;
    }
    /**
     * Performs the breeding behavior; creates another creature of the same species
     *
     * @param creature The first parent of the new creature
     * @param secondParent The second parent of the new creature
     * @param creatureList List of the entire population to iterate through
     * @param surfaceLayer two-dimensional array of surface
     */
    protected void breed(Creature creature, Creature secondParent, List<Creature> creatureList, char[][] surfaceLayer) {
        int tab[] = chooseBreedingSpot(creature, surfaceLayer);
        if(isBreedingEnabled(creature, creatureList, surfaceLayer, tab) && creature.getBreedingEnabled() && secondParent.getBreedingEnabled()){
            Creature baby = new Creature(creature.getSpecies());
            baby.setX(tab[0]);
            baby.setY(tab[1]);
            creatureList.add(baby);
            creature.setBreedingEnabled(false);
            secondParent.setBreedingEnabled(false);
            baby.setBreedingEnabled(false);
            baby.setSpeed(0);
            creature.setSpeed(0);
            secondParent.setSpeed(0);
        }
    }

    /**
     * Checks for all conditions for breeding and performs the breeding behavior
     *
     * @param creature The object to perform breeding for
     * @param creatureList List of the entire population
     * @param populationCount List of the entire population to count
     * @param surfaceLayer two-dimensional array of surface
     */
    @Override
    public void performBreeding(Creature creature, List<Creature> creatureList, int populationCount, char[][] surfaceLayer) {
        if (isEmptySpotAround(creature, creatureList) &&
                !isEnemyNear(creature, creatureList) &&
                isPartnerNear(creature, creatureList) &&
                drawBreedingChance(creature) &&
                !isMaxPopulationReached(creature, populationCount)){
            breed(creature, chooseSecondParent(creature, creatureList), creatureList, surfaceLayer);
        }
    }
    public void setEnemyFoodUtility(EnemyFoodUtility enemyFoodUtility) {
        this.enemyFoodUtility = enemyFoodUtility;
    }
}
