package simulation;

import java.util.List;

public interface MovementLogic {
    void performSingleStep (Creature creature, List<Creature> creatureList);
}
