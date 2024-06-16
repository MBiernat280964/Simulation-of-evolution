package simulation;

import java.util.List;

public class AggressiveMovement extends BaseMovementLogic{
    @Override
    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList, char[][] surfaceLayer) {
        int friendDist = creature.getSpecies().getSightRange();
        List<Creature> friends = findNearestFriends(creature, creatureList);
        if (!friends.isEmpty()) {
            friendDist = calculateDistance(creature, friends.get(0));
        }
        int foodDist = creature.getSpecies().getSightRange();
        List<Creature> food = findNearestFood(creature, creatureList);
        if (!food.isEmpty()) {
            foodDist = calculateDistance(creature, food.get(0));
        }
        Creature other;
        if(!food.isEmpty()){
            other = food.get(random.nextInt(food.size()));
        } else if (!friends.isEmpty()) {
            other = friends.get(random.nextInt(friends.size()));
        } else {
            if (random.nextDouble()<0.1 || creature.getVelocity()==null || (creature.getVelocity()[0] == creature.getX() && creature.getVelocity()[1] == creature.getY())){
                chooseVelocity(creature, surfaceLayer);
            }
            int x = Integer.signum(creature.getVelocity()[0] - creature.getX());
            int y = Integer.signum(creature.getVelocity()[1] - creature.getY());
            return new int[]{x,y};
        }
        int x = Integer.signum(other.getX() - creature.getX());
        int y = Integer.signum(other.getY() - creature.getY());

        return new int[]{x,y};
    }
}
