package simulation;

import java.util.List;

public class BirdMovement extends BaseMovementLogic{
    private boolean isMovePossible (List<Creature> creatureList, int[] tab, char landOrWater){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == tab[0] && creatureList.get(i).getY()== tab[1])){
                return false;
            }
        }
        return true;
    }
}
