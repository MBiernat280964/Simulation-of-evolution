package simulation;

public class Species {
    private int speed;
    private int baseHp;
    private String name;
    private int breedingChance;
    private int maxPopulation;

    public Species(int speed, int baseHp, String name, int breedingChance, int maxPopulation) {
        this.speed = speed;
        this.baseHp = baseHp;
        this.name = name;
        this.breedingChance = breedingChance;
        this.maxPopulation = maxPopulation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBreedingChance() {
        return breedingChance;
    }

    public void setBreedingChance(int breedingChance) {
        this.breedingChance = breedingChance;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }
}
