package simulation;

import java.util.List;

public class AggressiveMovement extends BaseMovementLogic{
    @Override
    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList) {
        int friendDist = Integer.MAX_VALUE;
        List<Creature> friends = findNearestFriends(creature, creatureList);
        if (!friends.isEmpty()) {
            friendDist = calculateDistance(creature, friends.get(0));
        }
        int foodDist = Integer.MAX_VALUE;
        List<Creature> food = findNearestFood(creature, creatureList);
        if (!food.isEmpty()) {
            foodDist = calculateDistance(creature, food.get(0));
        }
        Creature other;
        int x;
        int y;


        if (foodDist > friendDist && foodDist>4) {
            other = friends.get(random.nextInt(friends.size()));
        } else {
            other = food.get(random.nextInt(food.size()));
        }
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
        return new int[]{x,y};
    }
}
