package simulation;
/**
 * Object <code>Species</code> has information about species
 */
public class Species {
    private int speed;
    private int baseHp;
    private String name;
    private int breedingChance;
    private int maxPopulation;
    private char character;
    private int sightRange;
    /**
     *Constructor for species
     */
    public Species(int speed, int baseHp, String name, int breedingChance, int maxPopulation, char character, int sightRange) {
        this.speed = speed;
        this.baseHp = baseHp;
        this.name = name;
        this.breedingChance = breedingChance;
        this.maxPopulation = maxPopulation;
        this.character = character;
        this.sightRange = sightRange;
    }

    public int getSpeed() {
        return speed;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public String getName() {
        return name;
    }

    public int getBreedingChance() {
        return breedingChance;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public char getCharacter() {
        return character;
    }

    public int getSightRange() {
        return sightRange;
    }
}
