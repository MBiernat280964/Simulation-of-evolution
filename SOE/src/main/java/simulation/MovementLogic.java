package simulation;

import java.util.List;

public interface MovementLogic {
//    void moveSingleStep ();
//    List<Creature> findNearestEnemies ();
//    List<Creature> findNearestFriends ();
//    List<Creature> findNearestFood ();
//
//    /**
//     *
//     * @return int[2] = {x, y}
//     */
//    int[] chooseMoveDirection ();
//    boolean isMovePosible ();
    void performSingleStep (Creature creature, List<Creature> creatureList);
}
