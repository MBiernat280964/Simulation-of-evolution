package simulation;

import java.util.List;

public class AggressiveMovement extends BaseMovementLogic{
    @Override
    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList, char[][] surfaceLayer) {
        int dist;
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
        if (foodDist > friendDist && foodDist>4) {
            other = friends.get(random.nextInt(friends.size()));
            dist = friendDist;
        } else {
            other = food.get(random.nextInt(food.size()));
            dist = foodDist;
        }
        int x = Integer.signum(other.getX() - creature.getX());
        int y = Integer.signum(other.getY() - creature.getY());


        if (dist<creature.getSpecies().getSightRange()){
            if (random.nextDouble()<0.1 || creature.getVelocity()==null || (creature.getVelocity()[0] == creature.getX() && creature.getVelocity()[1] == creature.getY())){
                chooseVelocity(creature, surfaceLayer);
                x = Integer.signum(creature.getVelocity()[0] - creature.getX());
                y = Integer.signum(creature.getVelocity()[1] - creature.getY());
            }
        }
        return new int[]{x,y};
    }
}
