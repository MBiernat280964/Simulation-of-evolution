package simulation;

import java.util.*;
import java.util.Map;
/**
 * Object <code>EnemyFoodUtility</code>
 */
public class EnemyFoodUtility {

    private Map<Species, List<Species>> speciesToEnemies = new HashMap<>();
    private Map<Species, List<Species>> speciesToFoods = new HashMap<>();
    /**
     * Inits two list of different species for one species
     *
     * @param species List of all species in simulation
     */
    public void initSpecies(List<Species> species){
        for (int i=0; i<species.size(); i++){
            List<Species> enemies = new ArrayList<>();
            speciesToEnemies.put(species.get(i), enemies);

            List<Species> food = new ArrayList<>();
            speciesToFoods.put(species.get(i), food);
        }

    }
    /**
     * Add species to enemy list
     *
     * @param species List of all species in simulation
     * @param enemy Species that is enemy for other species
     */
    public void addEnemy(Species species, Species enemy){
        speciesToEnemies.get(species).add(enemy);
    }
    /**
     * Add species to food list
     *
     * @param species List of all species in simulation
     * @param food Species that is food for other species
     */
    public void addFood(Species species, Species food){
        speciesToFoods.get(species).add(food);
    }
    /**
     * Check if creature is enemy for other creature
     *
     * @param creature Creature for witch it will be checked
     * @param other Other creature that could be enemy
     * @return is other creature enemy or not
     */
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
    /**
     * Check if creature is food for other creature
     *
     * @param creature Creature for witch it will be checked
     * @param other Other creature that could be food
     * @return is other creature food or not
     */
    public boolean isFood(Creature creature, Creature other){
        for (int i=0; i<speciesToFoods.get(creature.getSpecies()).size(); i++) {
            if (other.getSpecies().equals(speciesToFoods.get(creature.getSpecies()).get(i))) {
                return true;
            }
        }
        return false;
    }
}
