package simulation;

import java.util.List;
/**
 * Object <code>WaterBreed</code> Is responsible for water creatures breeding behaviour
 */
public class WaterBreed extends BaseBreedingLogic{
    /**
     * Checks if breeding is enabled and returns a boolean
     *
     * @param creature The object to check if the breeding behavior is possible
     * @param creatureList List of the entire population
     * @param surfaceLayer two-dimensional array of surface
     * @param tab array with localisation of new creature
     * @return true Returns true if breeding behavior is possible
     */
    @Override
    protected boolean isBreedingEnabled(Creature creature, List<Creature> creatureList, char [][] surfaceLayer, int[] tab) {
        for (int i=0; i<creatureList.size(); i++){
            Creature other = creatureList.get(i);
            if ((0>tab[0] || tab[0]>=surfaceLayer.length || 0>tab[1] || tab[1]>=surfaceLayer.length) || (other.getX() == tab[0] && other.getY() == tab[1]) || surfaceLayer[tab[0]][tab[1]] == 'L'){
                return false;
            }
        }
        return true;
    }
}
