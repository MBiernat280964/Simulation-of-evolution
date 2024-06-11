package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseMovementLogic implements MovementLogic{
    private EnemyFoodUtility enemyFoodUtility;
    Random random = new Random(System.currentTimeMillis());


    private int calculateDistance(Creature creature, Creature other){
        int dist = Math.abs(creature.getX() - other.getX()) + Math.abs(creature.getY() - other.getY());
        return dist;
    }

    private List<Creature> findNearestEnemies (Creature creature, List<Creature> creatureList){
        int maxDistanceToEnemy = Integer.MAX_VALUE;
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility!= null && enemyFoodUtility.isEnemy(creature, creatureList.get(i))) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < maxDistanceToEnemy){
                    maxDistanceToEnemy = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == maxDistanceToEnemy) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    private List<Creature> findNearestFriends (Creature creature, List<Creature> creatureList){
        int maxDistanceToFriend = Integer.MAX_VALUE;
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (creature.getSpecies()==creatureList.get(i).getSpecies()) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < maxDistanceToFriend){
                    maxDistanceToFriend = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == maxDistanceToFriend) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    private List<Creature> findNearestFood (Creature creature, List<Creature> creatureList){
        int maxDistanceToFood = Integer.MAX_VALUE;
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility.isFood(creature, creatureList.get(i))) {
                int dist =  calculateDistance(creature, creatureList.get(i));
                if(dist < maxDistanceToFood){
                    maxDistanceToFood = dist;
                    results.clear();
                    results.add(creatureList.get(i));
                } else if (dist == maxDistanceToFood) {
                    results.add(creatureList.get(i));
                }
            }
        }
        return results;
    }

    private int[] chooseMoveDirection (Creature creature, List<Creature> creatureList) {
        int enemyDist = Integer.MAX_VALUE;
        List<Creature> enemies = findNearestEnemies(creature, creatureList);
        if (!enemies.isEmpty()) {
            enemyDist = calculateDistance(creature, enemies.get(0));
        }
        int friendDist = Integer.MAX_VALUE;
        List<Creature> friends = findNearestFriends(creature, creatureList);
        if (!friends.isEmpty()) {
            friendDist = calculateDistance(creature, friends.get(0));
        }

        Creature other;
        int x;
        int y;
        int newCreatureX = creature.getX();
        int newCreatureY = creature.getY();
        if (enemyDist < friendDist && !friends.isEmpty()) {
            other = friends.get(0);
            x = other.getX() - creature.getX();
            y = other.getY() - creature.getY();
            int i = random.nextInt(10);

            if (i < 5 && x != 0) {
                if (x < 0) {
                    newCreatureX = creature.getX() - 1;
                } else {
                    newCreatureX = creature.getX() + 1;
                }
            } else {
                if (y < 0) {
                    newCreatureY = creature.getY() - 1;
                } else if (y > 0) {
                    newCreatureY = creature.getY() + 1;
                }
            }

        } else if (!enemies.isEmpty()) {
            other = enemies.get(random.nextInt(enemies.size()));
            x = creature.getX() - other.getX();
            y = creature.getY() - other.getY();
            int i = random.nextInt(8);
            if (i < 5 && x != 0) {
                if (x < 0) {
                    newCreatureX = creature.getX() - 1;
                } else {
                    newCreatureX = creature.getX() + 1;
                }
            } else {
                if (y < 0) {
                    newCreatureY = creature.getY() - 1;
                } else if (y > 0) {
                    newCreatureY = creature.getY() + 1;
                }
            }
        }

        int[] vector = {newCreatureX,newCreatureY};
        return vector;
    }
    private boolean isMovePossible (Creature creature, List <Creature> creatureList, char landOrWater){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == chooseMoveDirection(creature, creatureList)[0] && creatureList.get(i).getY()==chooseMoveDirection(creature, creatureList)[1]) || landOrWater == 'W'){
                return false;
            }
        }
        return true;

    }
    @Override
    public void performSingleStep(Creature creature, List<Creature> creatureList, char landOrWater) {
        for(int i=0; i<creature.getSpecies().getSpeed(); i++){
            if (isMovePossible(creature, creatureList, landOrWater)){
                creature.setX(chooseMoveDirection(creature, creatureList)[0]);
                creature.setY(chooseMoveDirection(creature, creatureList)[1]);
            }
        }
    }
}
