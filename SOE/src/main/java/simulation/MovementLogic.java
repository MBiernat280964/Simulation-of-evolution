package simulation;

import java.util.List;
/**
 * Introduces moving functionality
 */
public interface MovementLogic {
    void performSingleStep (Creature creature, List<Creature> creatureList, char[][] surfaceLayer);
}
