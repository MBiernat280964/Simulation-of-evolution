package simulation;

import java.util.List;

public interface FightLogic {

    boolean performAttack(Creature creature, List<Creature> creatureList);
}
