package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseFightLogic implements FightLogic{

    EnemyFoodUtility enemyFoodUtility = new EnemyFoodUtility();
    Random random = new Random();

    private List<Creature> findEnemies (Creature creature, List<Creature> creatureList){
        List<Creature> results = new ArrayList<>();
        for (Creature value : creatureList) {
            if (enemyFoodUtility.isEnemy(creature, value)) {
                double dist = Math.sqrt(Math.pow(creature.getX() - value.getX(), 2) + Math.pow(creature.getY() - value.getY(), 2));
                if (dist <= Math.sqrt(2)) {
                    results.add(value);
                }
            }
        }
        return results;
    }

    private Creature chooseEnemy (Creature creature, List<Creature> creatureList){
        Creature other = null;
        if (!findEnemies(creature, creatureList).isEmpty()) {
            other = findEnemies(creature, creatureList).get(random.nextInt(findEnemies(creature, creatureList).size()));
        }
        return other;
    }

    void attack (Creature other){
        int hp;
        hp = other.getHp();
        hp--;
        other.setHp(hp);
    }

    private boolean isAttackPossible (Creature creature, List <Creature> creatureList){
        if (creature.getHp()==0 || findEnemies(creature, creatureList).isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean performAttack(Creature creature, List <Creature> creatureList) {
        for (int i=0; i<creatureList.size(); i++){
            if (isAttackPossible(creature, creatureList)){
                attack(chooseEnemy(creature, creatureList));
            }
        }
        return false;
    }
}
