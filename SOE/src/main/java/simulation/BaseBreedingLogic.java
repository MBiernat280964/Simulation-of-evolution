package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseBreedingLogic implements BreedingLogic{

    EnemyFoodUtility enemyFoodUtility = new EnemyFoodUtility();
    Random random = new Random();

    protected boolean isBreedingEnabled(Creature creature, List<Creature> creatureList) {
        for (int i=0; i<creatureList.size(); i++){
            if (creatureList.get(i).getX()== chooseBreedingSpot(creature, creatureList)[0] &&
                    creatureList.get(i).getY()==chooseBreedingSpot(creature, creatureList)[1]){
                return false;
            }
        }
        return true;
    }

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

    private int[] chooseBreedingSpot(Creature creature, List<Creature> creatureList){
        int x;
        int y;

        if(creature.getX()!=0){
            x=creature.getX()-1;
        } else{
            x=creature.getX()+1;
        }

        if(creature.getY()!=0){
            y=creature.getY()-1;
        } else{
            y=creature.getY()+1;
        }
        return new int[]{x,y};
    }

    protected boolean isEnemyNear(Creature creature, List<Creature> creatureList) {
        for (Creature value : creatureList) {
            if (enemyFoodUtility.isEnemy(creature, value)) {
                double dist = Math.sqrt(Math.pow(creature.getX() - value.getX(), 2) + Math.pow(creature.getY() - value.getY(), 2));
                if (dist <= Math.sqrt(8)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPartnerNear(Creature creature, List<Creature> creatureList) {
        for (Creature value : creatureList) {
            if (creature.getSpecies() == value.getSpecies()) {
                double dist = Math.sqrt(Math.pow(creature.getX() - value.getX(), 2) + Math.pow(creature.getY() - value.getY(), 2));
                if (dist <= Math.sqrt(2)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isMaxPopulationReached(Creature creature, int populationCount) {
        return populationCount == creature.getSpecies().getMaxPopulation();
    }

    protected boolean drawBreedingChance(Creature creature) {
        return random.nextInt(creature.getSpecies().getBreedingChance()) < creature.getSpecies().getBreedingChance() / 2;
    }

    private Creature chooseSecondParent (Creature creature, List<Creature> creatureList){
        Creature secondParent;
        List<Creature> potentialParents = new ArrayList<Creature>();
        for (Creature value : creatureList) {
            if (creature.getSpecies() == value.getSpecies()) {
                double dist = Math.sqrt(Math.pow(creature.getX() - value.getX(), 2) + Math.pow(creature.getY() - value.getY(), 2));
                if (dist <= Math.sqrt(2)) {
                    if (value.isBreedingEnabled())
                        potentialParents.add(value);
                }
            }
        }
        secondParent= potentialParents.get(random.nextInt(potentialParents.size()));
        return secondParent;
    }

    protected void breed(Creature creature, Creature secondParent, List<Creature> creatureList) {
        while(creature.isBreedingEnabled() || secondParent.isBreedingEnabled()){
            if(isBreedingEnabled(creature, creatureList)){
                Creature baby = new Creature(creature.getSpecies());
                baby.setX(chooseBreedingSpot(creature, creatureList)[0]);
                baby.setY(chooseBreedingSpot(creature, creatureList)[1]);
                creatureList.add(baby);
                creature.setBreedingEnabled(false);
                secondParent.setBreedingEnabled(false);
                baby.setBreedingEnabled(false);
                baby.setSpeed(0);
                creature.setSpeed(0);
                secondParent.setSpeed(0);
            }
        }
    }
    @Override
    public void performBreeding(Creature creature, List<Creature> creatureList, int populationCount) {
        if (isEmptySpotAround(creature, creatureList) &&
                !isEnemyNear(creature, creatureList) &&
                isPartnerNear(creature, creatureList) &&
                drawBreedingChance(creature) &&
                !isMaxPopulationReached(creature, populationCount)){
            breed(creature, chooseSecondParent(creature, creatureList), creatureList);
        }
    }

}
