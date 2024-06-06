package simulation;

import java.util.List;

public abstract class BaseMovementLogic implements MovementLogic{

    private Simulation simulation;

    void moveSingleStep (){

    }

    private List<Creature> findNearestEnemies (Creature creature, List<Creature> creatureList){
        creatureList = simulation.getCreatureList();

        return null;
    }

    private List<Creature> findNearestFriends (){

        return null;
    }

    private List<Creature> findNearestFood (){
        return null;
    }

//     * @return int[2] = {x, y}

    private int[] chooseMoveDirection (){
        return null;
    }
    private boolean isMovePosible (){
        return false;
    }
    @Override
    public void performSingleStep(Creature creature, List<Creature> creatureList) {

    }
}
