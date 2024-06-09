package simulation;
import java.util.List;
import java.util.Random;

public class Creature {
    private Species species;
    private int x;
    private int y;
    private int hp;
    private boolean isBreedingEnabled;
    private int speed;

    public Creature(Species species) {
        this.species = species;
    }

    public Species getSpecies() {
        return species;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isBreedingEnabled() {
        return isBreedingEnabled;
    }

    public void setBreedingEnabled(boolean breedingEnabled) {
        isBreedingEnabled = breedingEnabled;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

