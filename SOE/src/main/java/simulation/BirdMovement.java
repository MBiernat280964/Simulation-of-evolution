package simulation;

import java.util.List;
/**
 * Object <code>BirdMovement</code> Is responsible for bird moving behaviour
 */
public class BirdMovement extends BaseMovementLogic{
    /**
     * Checks if move is possible
     *
     * @param creatureList List of the entire population
     * @param newSpot array of parameters of new spot
     * @param surfaceLayer two-dimensional array of surface
     * @return is move possible or not
     */
    protected boolean isMovePossible(List<Creature> creatureList, int[] newSpot, char[][] surfaceLayer){
        for (int i=0; i<creatureList.size(); i++){
            if ((creatureList.get(i).getX() == newSpot[0] && creatureList.get(i).getY()== newSpot[1])){
                return false;
            }
        }
        if (newSpot[0]<0 || newSpot[1]<0 || newSpot[0]>=surfaceLayer.length || newSpot[1]>=surfaceLayer[0].length){
            return false;
        }
        return true;
    }
}
