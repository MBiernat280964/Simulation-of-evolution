package simulation;

import java.util.List;

public interface BreedingLogic {
    void performBreeding(Creature creature, List<Creature> creatureList, int populationCount);
}
