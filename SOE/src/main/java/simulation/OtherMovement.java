package simulation;

import java.util.List;

public class OtherMovement extends BaseMovementLogic{

    private boolean isMovePossible (List<Creature> creatureList, int[] tab, char landOrWater){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == tab[0] && creatureList.get(i).getY()== tab[1]) || landOrWater == 'L'){
                return false;
            }
        }
        return true;

    }
}
