package simulation;

public class Species {
    private int speed;
    private int baseHp;
    private String name;
    private float breedingChance;

    public Species(int speed, int baseHp, String name, float breedingChance) {
        this.speed = speed;
        this.baseHp = baseHp;
        this.name = name;
        this.breedingChance = breedingChance;
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

    public float getBreedingChance() {
        return breedingChance;
    }

    public void setBreedingChance(float breedingChance) {
        this.breedingChance = breedingChance;
    }
}
