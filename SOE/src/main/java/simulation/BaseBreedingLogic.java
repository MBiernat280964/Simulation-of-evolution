package simulation;

import java.util.List;
import java.util.Random;

public abstract class BaseBreedingLogic implements BreedingLogic{

    EnemyFoodUtility enemyFoodUtility = new EnemyFoodUtility();
    Random random = new Random();

    protected boolean isBreedingEnabled(Creature creature, List<Creature> creatureList) {
        for (int i=0; i<creatureList.size(); i++){
            if (creatureList.get(i).getX()== chooseBreedingSpot(creature, creatureList)[0] && creatureList.get(i).getY()==chooseBreedingSpot(creature, creatureList)[1]){
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

        if(random.nextInt(10)<10 && creature.getX()!=0 || creature.getX()==100){
            x=creature.getX()-1;
        } else{
            x=creature.getX()+1;
        }

        if(random.nextInt(10)<10 && creature.getY()!=0 || creature.getY()==100){
            y=creature.getY()-1;
        } else{
            y=creature.getY()+1;
        }
        int[] spot ={x,y};
        return spot;
    }

    protected boolean isEnemyNear(Creature creature, List<Creature> creatureList) {
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility.isEnemy(creature, creatureList.get(i))) {
                double dist = Math.sqrt(Math.pow(creature.getX() - creatureList.get(i).getX(), 2) + Math.pow(creature.getY() - creatureList.get(i).getY(), 2));
                if(dist <= Math.sqrt(8)){
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPartnerNear(Creature creature, List<Creature> creatureList) {
        for(int i = 0; i < creatureList.size(); i++){
            if (creature.getSpecies()==creatureList.get(i).getSpecies()) {
                double dist = Math.sqrt(Math.pow(creature.getX() - creatureList.get(i).getX(), 2) + Math.pow(creature.getY() - creatureList.get(i).getY(), 2));
                if(dist <= Math.sqrt(2)){
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isMaxPopulationReached(Creature creature, int populationCount) {
        if (populationCount==creature.getSpecies().getMaxPopulation()){
            return true;
        }
        return false;
    }

    protected boolean drawBreedingChance(Creature creature) {
        if (random.nextInt(creature.getSpecies().getBreedingChance())<creature.getSpecies().getBreedingChance()/2){
            return true;
        }
        return false;
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
            }
        }
    }

    @Override
    public void performBreeding(Creature creature, Creature secondParent, List<Creature> creatureList, int populationCount) {
        if (isEmptySpotAround(creature, creatureList)){
            if (!isEnemyNear(creature, creatureList)){
                if (isPartnerNear(creature, creatureList)){
                    if (drawBreedingChance(creature)){
                        if (!isMaxPopulationReached(creature, populationCount)){
                            breed(creature, secondParent, creatureList);
                        }
                    }
                }
            }
        }
    }

}
