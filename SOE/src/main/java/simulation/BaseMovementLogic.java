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
        int minDistanceToEnemy = Integer.MAX_VALUE;
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
        int minDistanceToFriend = Integer.MAX_VALUE;
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
        int minDistanceToFood = Integer.MAX_VALUE;
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

    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList) {
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
        if (enemyDist > friendDist && !friends.isEmpty()) {
            other = friends.get(random.nextInt(friends.size()));
            x = other.getX() - creature.getX();
            y = other.getY() - creature.getY();

            if (x < 0) {
                x = -1;
            } else  if (x>0){
                x = 1;
            }
            if (y < 0) {
                y = -1;
            } else if (y > 0) {
                y = 1;
            }

        } else {
            other = enemies.get(random.nextInt(enemies.size()));
            x = creature.getX() - other.getX();
            y = creature.getY() - other.getY();
            if (x < 0) {
                x = - 1;
            } else {
                x = 1;
            }
            if (y < 0) {
                y = - 1;
            } else if (y > 0) {
                y = 1;
            }
        }
        return new int[]{x,y};
    }
     protected boolean isMovePossible(List<Creature> creatureList, int[] tab, char[][] surfaceLayer){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == tab[0] && creatureList.get(i).getY()== tab[1])){
                return false;
            }
        }
        if (tab[0]<0 || tab[1]<0 || tab[0]>=surfaceLayer.length || tab[1]>=surfaceLayer[0].length || surfaceLayer[tab[0]][tab[1]] == 'W' ){
            return false;
        }
        return true;
    }
    @Override
    public void performSingleStep(Creature creature, List<Creature> creatureList, char [][] surfaceLayer) {
        int[] tab = chooseMoveDirection(creature, creatureList);
        double i = random.nextDouble();
        if (i<0.5 && isMovePossible(creatureList, new int[]{creature.getX()+tab[0], creature.getY()}, surfaceLayer)) {
            creature.setX(creature.getX()+tab[0]);
        } else if (isMovePossible(creatureList, new int[]{creature.getX(), creature.getY()+tab[1]}, surfaceLayer)){
            creature.setY(creature.getY()+tab[1]);
        } else if (isMovePossible(creatureList, new int[]{creature.getX()+tab[0], creature.getY()+tab[1]}, surfaceLayer)) {
            creature.setY(creature.getX()+tab[0]);
            creature.setY(creature.getY()+tab[1]);
        }
    }

    public void setEnemyFoodUtility(EnemyFoodUtility enemyFoodUtility) {
        this.enemyFoodUtility = enemyFoodUtility;
    }
}
