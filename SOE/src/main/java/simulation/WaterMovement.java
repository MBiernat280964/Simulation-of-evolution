package simulation;

import java.util.List;
/**
 * Object <code>WaterMovement</code> Is responsible for water creatures moving behaviour
 */
public class WaterMovement extends BaseMovementLogic{
    /**
     * Checks if move is possible
     *
     * @param creatureList List of the entire population
     * @param newSpot array of parameters of new spot
     * @param surfaceLayer two-dimensional array of surface
     * @return is move possible or not
     */
    @Override
    protected boolean isMovePossible(List<Creature> creatureList, int[] newSpot, char[][] surfaceLayer){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == newSpot[0] && creatureList.get(i).getY()== newSpot[1])){
                return false;
            }
        }
        if (newSpot[0]<0 || newSpot[1]<0 || newSpot[0]>=surfaceLayer.length || newSpot[1]>=surfaceLayer[0].length || surfaceLayer[newSpot[0]][newSpot[1]] == 'L' ){
            return false;
        }
        return true;
    }
}
