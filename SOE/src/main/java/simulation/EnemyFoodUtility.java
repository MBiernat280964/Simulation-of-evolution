package simulation;

import java.util.*;
import java.util.Map;

public class EnemyFoodUtility {

    private Map<String, List<Species>> speciesToEnemies = new HashMap<>();
    private Map<String, List<Species>> speciesToFoods = new HashMap<>();

    public void initSpecies(List<Species> species){
        for (int i=0; i<species.size(); i++){
            List<Species> enemies = new ArrayList<>();
            speciesToEnemies.put(species.get(i).getName(), enemies);

            List<Species> food = new ArrayList<>();
            speciesToFoods.put(species.get(i).getName(), food);
        }

    }

    public void addEnemy(Species species, Species enemy){
        speciesToEnemies.get(species.getName()).add(enemy);
    }

    public void addFood(Species species, Species food){
        speciesToFoods.get(species.getName()).add(food);
    }

    public boolean isEnemy(Creature creature, Creature other){
        return false;
    }

}
