package simulation;

import java.util.List;
/**
 * Introduces breeding functionality
 */
public interface BreedingLogic {
    void performBreeding(Creature creature, List<Creature> creatureList, int populationCount, char[][] surfaceLayer);
}
