package simulation;

import java.util.List;

public class OtherMovement extends BaseMovementLogic{

    private boolean isMovePossible (List <Creature> creatureList, int[] tab, char[][] surfaceLayer){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == tab[0] && creatureList.get(i).getY()== tab[1])){
                return false;
            }
        }
        if (tab[0]<0 || tab[1]<0 || tab[0]>surfaceLayer.length || tab[1]>surfaceLayer[0].length || surfaceLayer[tab[0]][tab[1]] == 'W' ){
            return false;
        }
        return true;
    }
}
