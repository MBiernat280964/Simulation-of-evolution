package simulation;

import java.util.List;

public class aggressiveMovement extends BaseMovementLogic{
    @Override
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
}
