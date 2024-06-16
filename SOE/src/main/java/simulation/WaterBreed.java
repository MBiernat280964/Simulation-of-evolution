package simulation;

import java.util.List;

public class WaterBreed extends BaseBreedingLogic{
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
