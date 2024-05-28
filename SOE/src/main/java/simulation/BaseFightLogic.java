package simulation;

import java.util.List;

public abstract class BaseFightLogic implements FightLogic{

    private List<Creature> findEnemies (){

        return null;
    }

    private Creature chooseEnemy (){

        return null;
    }

    void attack (){

    }

    void beAttacked (){

    }

    @Override
    public void performAttack() {

    }
}
