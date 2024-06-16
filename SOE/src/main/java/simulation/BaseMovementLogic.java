package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseMovementLogic implements MovementLogic{
    protected EnemyFoodUtility enemyFoodUtility;
    Random random = new Random();


    protected int calculateDistance(Creature creature, Creature other){
        int dist = Math.abs(creature.getX() - other.getX()) + Math.abs(creature.getY() - other.getY());
        return dist;
    }

    protected List<Creature> findNearestEnemies (Creature creature, List<Creature> creatureList){
        int minDistanceToEnemy = creature.getSpecies().getSightRange();
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility!= null && enemyFoodUtility.isEnemy(creature, creatureList.get(i))) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < minDistanceToEnemy){
                    minDistanceToEnemy = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == minDistanceToEnemy) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    protected List<Creature> findNearestFriends (Creature creature, List<Creature> creatureList){
        int minDistanceToFriend = creature.getSpecies().getSightRange();
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (creature.getSpecies()==creatureList.get(i).getSpecies() && !(creature.getX()==creatureList.get(i).getX() && creature.getY()==creatureList.get(i).getY())) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < minDistanceToFriend){
                    minDistanceToFriend = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == minDistanceToFriend) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    protected List<Creature> findNearestFood (Creature creature, List<Creature> creatureList){
        int minDistanceToFood = creature.getSpecies().getSightRange();
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility.isFood(creature, creatureList.get(i))) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < minDistanceToFood){
                    minDistanceToFood = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == minDistanceToFood) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList, char[][] surfaceLayer) {
        List<Creature> enemies = findNearestEnemies(creature, creatureList);

        int foodDist = creature.getSpecies().getSightRange();
        List<Creature> food = findNearestFood(creature, creatureList);
        if (!food.isEmpty()) {
            foodDist = calculateDistance(creature, food.get(0));
        }

        int friendDist = creature.getSpecies().getSightRange();
        List<Creature> friends = findNearestFriends(creature, creatureList);
        if (!friends.isEmpty()) {
            friendDist = calculateDistance(creature, friends.get(0));
        }
        Creature other;
        int goForward;
        if(!enemies.isEmpty()) {
            other = enemies.get(random.nextInt(enemies.size()));
            goForward = -1;
        } else if (!food.isEmpty()) {
            other = food.get(random.nextInt(food.size()));
            goForward = 1;
        }  else if(!friends.isEmpty()) {
            other = friends.get(random.nextInt(friends.size()));
            goForward = 1;
        } else {
            if (random.nextDouble()<0.05 || creature.getVelocity()==null || (creature.getVelocity()[0] == creature.getX() && creature.getVelocity()[1] == creature.getY())){
                chooseVelocity(creature, surfaceLayer);
            }
            int x = Integer.signum(creature.getVelocity()[0] - creature.getX());
            int y = Integer.signum(creature.getVelocity()[1] - creature.getY());
            return new int[]{x,y};
        }
        int x = Integer.signum(other.getX() - creature.getX()) * goForward;
        int y = Integer.signum(other.getY() - creature.getY()) * goForward;
        return new int[]{x,y};
    }


    protected void chooseVelocity(Creature creature, char[][] surfaceLayer){
        int[] velocity = new int[2];
        do {
            velocity[0] = random.nextInt(surfaceLayer.length);
            velocity[1] = random.nextInt(surfaceLayer.length);
        } while (creature.getX()==velocity[0] && creature.getY()==velocity[1]);
        creature.setVelocity(velocity);
    }
     protected boolean isMovePossible(List<Creature> creatureList, int[] newSpot, char[][] surfaceLayer){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == newSpot[0] && creatureList.get(i).getY()== newSpot[1])){
                return false;
            }
        }
        if (newSpot[0]<0 || newSpot[1]<0 || newSpot[0]>=surfaceLayer.length || newSpot[1]>=surfaceLayer[0].length || surfaceLayer[newSpot[0]][newSpot[1]] == 'W' ){
            return false;
        }
        return true;
    }
    @Override
    public void performSingleStep(Creature creature, List<Creature> creatureList, char [][] surfaceLayer) {
        int[] moveDirection = chooseMoveDirection(creature, creatureList, surfaceLayer);
        double i = random.nextDouble();
        if (((i<0.5 && moveDirection[0] != 0) || moveDirection[1] == 0) && isMovePossible(creatureList, new int[]{creature.getX()+moveDirection[0], creature.getY()}, surfaceLayer)) {
            creature.setX(creature.getX()+moveDirection[0]);
        } else if (isMovePossible(creatureList, new int[]{creature.getX(), creature.getY()+moveDirection[1]}, surfaceLayer)){
            creature.setY(creature.getY()+moveDirection[1]);
        } else if (isMovePossible(creatureList, new int[]{creature.getX()+moveDirection[0], creature.getY()+moveDirection[1]}, surfaceLayer)) {
            creature.setX(creature.getX()+moveDirection[0]);
            creature.setY(creature.getY()+moveDirection[1]);
        } else if (moveDirection[0] == 0){
            if (isMovePossible(creatureList, new int[]{(creature.getX()+1), creature.getY()}, surfaceLayer)){
                creature.setX(creature.getX()+1);
            } else if (isMovePossible(creatureList, new int[] {(creature.getX()-1), creature.getY()}, surfaceLayer)){
                creature.setX(creature.getX()-1);
            }
        } else if (moveDirection[1] == 0){
            if (isMovePossible(creatureList, new int[]{creature.getX(), (creature.getY()+1)}, surfaceLayer)){
                creature.setY(creature.getY()+1);
            } else if (isMovePossible(creatureList, new int[] {creature.getX(), (creature.getY()-1)}, surfaceLayer)){
                creature.setY(creature.getY()-1);
            }
        }
    }

    public void setEnemyFoodUtility(EnemyFoodUtility enemyFoodUtility) {
        this.enemyFoodUtility = enemyFoodUtility;
    }
}
