package simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMovementLogic implements MovementLogic{
    private EnemyFoodUtility enemyFoodUtility;

    void moveSingleStep (){

    }

    private int calculateDistance(Creature creature, Creature other){
        int dist = Math.abs(creature.getX() - other.getX()) + Math.abs(creature.getY() - other.getY());
        return 0;
    }

    private List<Creature> findNearestEnemies (Creature creature, List<Creature> creatureList){
        int maxDistanceToEnemy = Integer.MAX_VALUE;
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility.isEnemy(creature, creatureList.get(i))) {
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
            if (creature.equals(creatureList.get(i))) {
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

//     * @return int[2] = {x, y}

    private int[] chooseMoveDirection (Creature creature, List<Creature> creatureList){
        int enemyDist = Integer.MAX_VALUE;
        List <Creature> enemies = findNearestEnemies(creature, creatureList);
        if(!enemies.isEmpty()) {
            enemyDist = calculateDistance(creature, enemies.get(0));
        }
        int friendDist = Integer.MAX_VALUE;
        List <Creature> friends = findNearestFriends(creature, creatureList);
        if(!friends.isEmpty()){
            friendDist = calculateDistance(creature, friends.get(0));
        }
        if(enemyDist<friendDist){
            //odejmowanie wartości x, y i obliczanie wektora, po czym losowanie jak do niego iść
        } else {
            //Jeżeli nie to ucieka od enemy (dinozaury będą iść do jedzenia)
        }
        return null;
    }
    private boolean isMovePosible (){
        /*Trzeba sprawdzić czy dane pole (x,y) jest wolne
        * Ale jak to sprawdzić?
        * Jak dostać się do tego co jest na danym polu
        * iterujesz po wszystkim i spradzasz czy coś na tym stoi*/
        return false;
    }
    @Override
    public void performSingleStep(Creature creature, List<Creature> creatureList) {
        //Wszystkie kroki po kolei

    }
}
