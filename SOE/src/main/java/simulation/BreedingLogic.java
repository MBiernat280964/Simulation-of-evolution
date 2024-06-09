package simulation;

import java.util.List;

public interface BreedingLogic {

    void performBreeding(Creature creature, Creature secondParent, List<Creature> creatureList, int populationCount);

    void performBreeding(Creature creature, List<Creature> creatureList, int populationCount);
}
