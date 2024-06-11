package simulation;

import java.util.*;
import java.util.Map;

public class EnemyFoodUtility {

    private Map<Species, List<Species>> speciesToEnemies = new HashMap<>();
    private Map<Species, List<Species>> speciesToFoods = new HashMap<>();

    public void initSpecies(List<Species> species){
        for (int i=0; i<species.size(); i++){
            List<Species> enemies = new ArrayList<>();
            speciesToEnemies.put(species.get(i), enemies);

            List<Species> food = new ArrayList<>();
            speciesToFoods.put(species.get(i), food);
        }

    }

    public void addEnemy(Species species, Species enemy){
        speciesToEnemies.get(species).add(enemy);
    }

    public void addFood(Species species, Species food){
        speciesToFoods.get(species).add(food);
    }

    public boolean isEnemy(Creature creature, Creature other){
        if (speciesToEnemies.get(creature.getSpecies()) != null) {
            for (int i=0; i<speciesToEnemies.get(creature.getSpecies()).size(); i++) {
                if (other.getSpecies().equals(speciesToEnemies.get(creature.getSpecies()).get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFood(Creature creature, Creature other){
        for (int i=0; i<speciesToFoods.get(creature.getSpecies()).size(); i++) {
            if (other.getSpecies().equals(speciesToFoods.get(creature.getSpecies()).get(i))) {
                return true;
            }
        }
        return false;
    }
}
