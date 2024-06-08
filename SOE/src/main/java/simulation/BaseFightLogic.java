package simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFightLogic implements FightLogic{

    EnemyFoodUtility enemyFoodUtility;

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

    private Creature chooseEnemy (){

        return null;
    }

    void attack (Creature creature, Creature other){
        //Zaatakowanie innej kreatury
        int hp;
        hp = other.getHp();
        hp--;
        other.setHp(hp);
    }

    @Override
    public boolean performAttack() {
        //Czy został przeprowadzony atak, jeżeli nie to nie uwzględnia przy kolejnej turze, jeśli true to usuwa z llisty atakujących na daną kolejkę
        return false;
    }
}
