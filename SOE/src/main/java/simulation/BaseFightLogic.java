package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Object <code>BaseFightLogic</code> Is responsible for selecting opponent and dealing damage to creatures
 */
public abstract class BaseFightLogic implements FightLogic{

    EnemyFoodUtility enemyFoodUtility = new EnemyFoodUtility();
    Random random = new Random();

    /**
     * Create a list of enemies around the creature, returns a list of enemies
     *
     * @param creature The object to check surroundings of
     * @param creatureList List of the entire population to iterate through
     * @return List of enemies around the creature
     */
    private List<Creature> findEnemies (Creature creature, List<Creature> creatureList){
        List<Creature> results = new ArrayList<>();
        for (Creature value : creatureList) {
            if (enemyFoodUtility.isFood(creature, value)) {
                double dist = Math.sqrt(Math.pow(creature.getX() - value.getX(), 2) + Math.pow(creature.getY() - value.getY(), 2));
                if (dist < 2) {
                    results.add(value);
                }
            }
        }
        return results;
    }
    /**
     * Chooses creature that will be enemy for object
     *
     * @param creature The object witch enemy will be chosen
     * @param creatureList List of the entire population to iterate through
     * @return enemy for object
     */
    private Creature chooseEnemy (Creature creature, List<Creature> creatureList){
        Creature other = null;
        if (!findEnemies(creature, creatureList).isEmpty()) {
            other = findEnemies(creature, creatureList).get(random.nextInt(findEnemies(creature, creatureList).size()));
        }
        return other;
    }
    /**
     * Chooses creature that will be enemy for object
     *
     * @param other The enemy object that is under attack
     * @param creatureList List of the entire population
     */
    void attack (Creature other, List<Creature> creatureList){
        int hp;
        hp = other.getHp();
        hp--;
        other.setHp(hp);
        if (hp <= 0){
            creatureList.remove(other);
        }
    }
    /**
     * Check if creature can attack
     *
     * @param creature The object to check whether its attack is possible
     * @param creatureList List of the entire population to iterate through
     * @return if the object can attack or not
     */
    private boolean isAttackPossible (Creature creature, List <Creature> creatureList){
        if (creature.getHp()<=0 || findEnemies(creature, creatureList).isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * Checks for all conditions for fighting and performs the fight behavior
     *
     * @param creature The object to perform attack for
     * @param creatureList List of the entire population
     */
    @Override
    public boolean performAttack(Creature creature, List <Creature> creatureList) {
        if (isAttackPossible(creature, creatureList)){
            attack(chooseEnemy(creature, creatureList), creatureList);
            return true;
        }
        return false;
    }

    public void setEnemyFoodUtility(EnemyFoodUtility enemyFoodUtility) {
        this.enemyFoodUtility = enemyFoodUtility;
    }
}
