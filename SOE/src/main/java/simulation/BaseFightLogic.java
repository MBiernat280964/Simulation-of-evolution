package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseFightLogic implements FightLogic{

    EnemyFoodUtility enemyFoodUtility;
    Random random = new Random();

    private List<Creature> findEnemies (Creature creature, List<Creature> creatureList){
        List<Creature> results = new ArrayList<>();
        for(int i = 0; i < creatureList.size(); i++){
            if (enemyFoodUtility.isEnemy(creature, creatureList.get(i))) {
                double dist = Math.sqrt(Math.pow(creature.getX() - creatureList.get(i).getX(), 2) + Math.pow(creature.getY() - creatureList.get(i).getY(), 2));
                if(dist <= Math.sqrt(2)){
                    results.add(creatureList.get(i));
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

    private boolean isAttackPosible (Creature creature, List <Creature> creatureList){
        if (creature.getHp()==0){
            return false;
        }
        if (findEnemies(creature, creatureList).isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean performAttack(Creature creature, List <Creature> creatureList) {
        for (int i=0; i<creatureList.size(); i++){
            if (isAttackPosible(creature, creatureList)){
                attack(chooseEnemy(creature, creatureList));
            }
        }
        //TODO usuwanie z listy atakujących na kolejkę
        return false;
    }
}
