package simulation;

import java.util.List;
/**
 * Introduces fighting functionality
 */
public interface FightLogic {

    boolean performAttack(Creature creature, List<Creature> creatureList);
}
