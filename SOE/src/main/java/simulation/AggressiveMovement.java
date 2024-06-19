package simulation;

import java.util.List;
/**
 * Object <code>AggressiveMovement</code> Is responsible for aggressive creatures moving behaviour
 */
public class AggressiveMovement extends BaseMovementLogic{
    /**
     * Chooses direction for the creature
     *
     * @param creature The object that direction is selected
     * @param creatureList List of the entire population to iterate through
     * @param surfaceLayer two-dimensional array of surface
     * @return Array of direction for creature
     */
    @Override
    protected int[] chooseMoveDirection (Creature creature, List<Creature> creatureList, char[][] surfaceLayer) {
        List<Creature> friends = findNearestFriends(creature, creatureList);
        List<Creature> food = findNearestFood(creature, creatureList);

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
